package Application.business_logic.bl.resultPool;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import Application.business_logic.bl.reposComparator.ContributorComparator;
import Application.business_logic.bl.reposComparator.ForkComparator;
import Application.business_logic.bl.reposComparator.OpenIssueComparator;
import Application.business_logic.bl.reposComparator.StarComparator;
import Application.common.ReposSort;
import Application.common.info.ReposInfo;
import javafx.scene.control.TableColumn.SortType;

@Component
public class ReposResultPool extends SearchResultPool<ReposInfo>{

	//查询方法的表
	Map<ReposSort, Comparator<ReposInfo>> comparatorMap;

	/**
	 * 记录初始排序顺序，也就是按匹配度排序的map
	 */
	Map<String, List<ReposInfo>> generalSortMap;

	@PostConstruct
	protected void init(){
		comparatorMap = new HashMap<>();
		comparatorMap.put(ReposSort.contributors, new ContributorComparator());
		comparatorMap.put(ReposSort.fork, new ForkComparator());
		comparatorMap.put(ReposSort.star, new StarComparator());
		comparatorMap.put(ReposSort.open_issue, new OpenIssueComparator());
		generalSortMap = new HashMap<>();
	}


	@Override
	public void addResult(String keyword, List<ReposInfo> resultList) {
		super.addResult(keyword, resultList);
		List<ReposInfo> generalList = new ArrayList<>(resultList);
		generalSortMap.put(keyword, generalList);
	}
	/**
	 * @param keyword
	 * @param sort
	 * @return 按指定方式排序的搜索结果
	 */
	public List<ReposInfo> getResult(String keyword,ReposSort sort){
		if(containsResult(keyword)){

			if(sort == ReposSort.general){
				return generalSortMap.get(keyword);
			}else{
				return sort(sort, getResult(keyword));
			}

		}else{
			return null;
		}
	}

	public synchronized List<ReposInfo> sort(ReposSort sortType,List<ReposInfo> reposList){

		Collections.sort(reposList, comparatorMap.get(sortType));
		Collections.reverse(reposList);

		return reposList;
	}

	@Override
	public void removeResult(String keyword) {
		super.removeResult(keyword);
		generalSortMap.remove(keyword);
	}

}
