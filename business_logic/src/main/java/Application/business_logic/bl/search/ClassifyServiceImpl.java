package Application.business_logic.bl.search;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Application.business_logic.bl.resultPool.ReposResultPool;
import Application.business_logic.bl.resultPool.UserResultPool;
import Application.common.ClassifyItem;
import Application.common.UserType;
import Application.common.blService.ClassifyService;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Component
public class ClassifyServiceImpl implements ClassifyService{

	@Autowired
	ReposResultPool repoPool;

	@Autowired
	UserResultPool userPool;


	ClassifyItem repoLanguageItem;

	ClassifyItem userLanguageItem;


	ClassifyItem repoDateItem;


	ClassifyItem userTypeItem;


	ClassifyItem userDateItem;




	@Override
	public ClassifyItem classifyRepoLanguage(String key) {
		// TODO Auto-generated method stub

		repoLanguageItem = new ClassifyItem();
		List<ReposInfo> repoInfoList = repoPool.getResult(key);


			for(ReposInfo info: repoInfoList){
				String language = info.getLanguage();
				repoLanguageItem.addItem(language);

			}

		return repoLanguageItem;
	}


	@Override
	public ClassifyItem classifyUserLanguage(String key) {
		// TODO Auto-generated method stub
		userLanguageItem = new ClassifyItem();
		List<UserInfo> userInfoList = userPool.getResult(key);

		for(UserInfo info: userInfoList){
			String language = info.getMainLanguage();
			userLanguageItem.addItem(language);

		}


		return userLanguageItem;
	}

	@Override
	public ClassifyItem classifyUserType(String key) {
		// TODO Auto-generated method stub

		userTypeItem = new ClassifyItem();
		List<UserInfo> userInfoList = userPool.getResult(key);

		for(UserInfo info: userInfoList){
			UserType type = info.getType();
			userTypeItem.addItem(type.toString());
		}

		return userTypeItem;
	}


	@Override
	public ClassifyItem classifyRepoDate(String key) {
		// TODO Auto-generated method stub


		repoDateItem = new ClassifyItem();

		List<ReposInfo> repoInfoList = repoPool.getResult(key);
		for(ReposInfo info: repoInfoList){
			Date date = info.getCreated_at();

			@SuppressWarnings("deprecation")
			int year = date.getYear();

			repoDateItem.addItem(year+"");
		}

		return repoDateItem;
	}


	@Override
	public ClassifyItem classifyUserDate(String key) {
		// TODO Auto-generated method stub


		userDateItem = new ClassifyItem();
		List<UserInfo> userInfoList = userPool.getResult(key);
		for(UserInfo info: userInfoList){
			Date date = info.getCreated_at();

			@SuppressWarnings("deprecation")
			int year = date.getYear();

			userDateItem.addItem(year+"");
		}

		return userDateItem;
	}


	@Override
	public Map<String, Integer> classifyRepoExp(String key) {
		// TODO Auto-generated method stub

		List<ReposInfo> repoList = repoPool.getResult(key);

		int star_exp = 0;
		int	contributor_exp = 0;
		int fork_exp = 0;
		int length = repoList.size();


		if(length != 0){
			for(ReposInfo info : repoList){
				star_exp += info.getStargazers_count();
				contributor_exp += info.getOpen_issues_count();
				fork_exp += info.getForks_count();
			}

			star_exp /= length;
			contributor_exp /= length;
			fork_exp /= length;
		}



		Map<String, Integer> repoExp = new LinkedHashMap<String, Integer>();
		repoExp.put("Star", star_exp);
		repoExp.put("Contri.", contributor_exp);
		repoExp.put("Fork", fork_exp);



		return repoExp;
	}



	@Override
	public Map<String, Integer> classifyUserExp(String key) {
		// TODO Auto-generated method stub

		List<UserInfo> userList = userPool.getResult(key);

		int contributed_exp = 0;
		int follower_exp = 0;
		int following_exp = 0;
		int length = userList.size();

		for(UserInfo info : userList){
			contributed_exp += info.getContributedRepoNum();
			follower_exp += info.getFollowers();
			following_exp += info.getFollowing();
		}

		if(length != 0){
			contributed_exp /= length;
			follower_exp /= length;
			following_exp /= length;
		}
		Map<String, Integer> userExp = new LinkedHashMap<String, Integer>();
		userExp.put("Contri.", contributed_exp);
		userExp.put("Follower", follower_exp);
		userExp.put("Following", following_exp);


		return userExp;
	}






}
