package Application.common.SearchPage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import Application.common.blService.SearchService;
import Application.common.configInfoService.ConfigInfoService;
import Application.common.info.AbstractInfo;

/**
 * @author ShenBin 用于分页查询的页，从0开始
 * 关联查询会一次返回所有结果，存在list中，搜索查询会一次返回20个。
 * @param <T>
 */
public class SearchPage<T extends AbstractInfo> implements Serializable{
	protected List<T> contentList;
	public static int size = 20;
	int currentPageNum = 0;
	String searchKey;
	int totalListSize;

	transient protected SearchService searchService;
	/**
	 * 配置info的类，在返回时给他们注入service对象
	 */
	protected ConfigInfoService configInfoService;

	public void setSearchService(SearchService searchService) {
		this.searchService = searchService;
	}

	public void setConfigInfoService(ConfigInfoService configInfoService) {
		this.configInfoService = configInfoService;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public SearchPage(List<T> list, String key, int totalListSize) {
		this.contentList = list;
		searchKey = key;
		this.totalListSize = totalListSize;
	}

	public int getCurrentNum() {
		return currentPageNum;
	}

	public int totalPageNum() {
		int num = totalListSize / size;
		if (totalListSize % size != 0) {
			num++;
		}
		return num;
	}

	/**
	 * @param pageNum
	 * @return 那一页的list，如果pageNum过大，返回最后一页
	 */
	public void turnPageTo(int pageNum) {
		int totalNum = totalPageNum();

		if(totalNum==0) {
		    return;
		}

		if (pageNum >= totalNum) {
			currentPageNum = totalNum - 1;
		} else {
			currentPageNum = pageNum;
		}
	}

	/**
	 * @return 当前页的内容
	 */
	public List<T> getCurrentContent() {

		List<T> resultList = null;
		//判断是不是关联搜索，是的话直接从本地list读取结果
		if (isLocalPage()) {
			resultList = new ArrayList<T>(size);
			int offset = currentPageNum * size;
			// &&是为了处理最后一页的情况
			for (int i = 0; i < size && (i + offset) < contentList.size(); i++) {
				resultList.add(contentList.get(i + offset));
			}
		}
		else{
			contentList = invokeServer();
			resultList = new ArrayList<>(contentList);
		}

		//list返回时，给他们set进远程的service对象，方便界面层的调用
		if(configInfoService!=null) {
		    configInfoService.configInfo(resultList);
		}

		return resultList;
	}

	/**
	 * 向服务器查询的抽象方法
	 * @return
	 */
	protected List<T> invokeServer(){
		throw new RuntimeException("关联查询不访问server");
	}


	public boolean hasNext() {
		if (currentPageNum == totalPageNum() - 1) {
			return false;
		} else {
			return true;
		}
	}

	public boolean hasPre() {
		if (currentPageNum > 0) {
			return true;
		} else {
			return false;
		}
	}

	public void nextPage() {
		if (hasNext()) {
			currentPageNum++;
		}
	}

	public void prePage() {
		if (hasPre()) {
			currentPageNum--;
		}
	}

	protected boolean isLocalPage() {
	    return LocalPageJudge.isLocalPage(searchKey);
	}

	public List<T> getAllContent() {

	    int formerIndex = getCurrentNum();

	    turnPageTo(0);
	    List<T> totalList = new ArrayList<T>(totalListSize);
	    for(int i=0;i<totalPageNum();i++) {
            totalList.addAll(getCurrentContent());
            nextPage();
        }

	    turnPageTo(formerIndex);

	    return totalList;
	}

    public int getTotalListSize() {
        return totalListSize;
    }

}
