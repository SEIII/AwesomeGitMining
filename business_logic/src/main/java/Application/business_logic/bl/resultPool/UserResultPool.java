package Application.business_logic.bl.resultPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import Application.business_logic.bl.reposComparator.ContributorComparator;
import Application.business_logic.bl.reposComparator.ForkComparator;
import Application.business_logic.bl.reposComparator.StarComparator;
import Application.business_logic.bl.userComparator.ContributedComparator;
import Application.common.ReposSort;
import Application.common.UserSort;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;


@Component
public class UserResultPool extends SearchResultPool<UserInfo>{
	//查询方法的表
		Map<UserSort, Comparator<UserInfo>> comparatorMap;

		/**
		 * 记录初始排序顺序，也就是按匹配度排序的map
		 */
		Map<String, List<UserInfo>> generalSortMap;

		@PostConstruct
		protected void init(){
			comparatorMap = new HashMap<>();
			comparatorMap.put(UserSort.contirbuted, new ContributedComparator());

			generalSortMap = new HashMap<>();
		}

		@Override
		public void addResult(String keyword, List<UserInfo> userList) {
			super.addResult(keyword, userList);
			List<UserInfo> generalList = new ArrayList<>(userList);
			generalSortMap.put(keyword, generalList);
		}
		/**
		 * @param keyword
		 * @param sort
		 * @return 按指定方式排序的搜索结果
		 */
		public List<UserInfo> getResult(String keyword,UserSort sort){
			if(containsResult(keyword)){

					return sort(sort, getResult(keyword));
			}else{
				return null;
			}
		}

		public synchronized List<UserInfo> sort(UserSort sortType,List<UserInfo> userList){

			Collections.sort(userList, comparatorMap.get(sortType));
			Collections.reverse(userList);

			return userList;
		}
}
