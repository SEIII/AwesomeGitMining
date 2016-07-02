package Application.business_logic.bl.stat.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.ListGrouping;
import Application.business_logic.bl.stat.StatInfoFactory;
import Application.common.ClassifyItem;
import Application.common.DTO.BasicUserInfo;
import Application.common.blService.statService.StatResult;
import Application.common.blService.statService.StatTotalUserService;
import Application.common.data_service.SearchUserService;
import Application.common.info.UserInfo;

@Component
public class StatTotalUserImpl implements StatTotalUserService{


	@Autowired
	StatInfoFactory statInfoFactory;

	@Autowired
	SearchUserService searchUserService;

	List<UserInfo> userList;


	public void init(){

		List<BasicUserInfo> basicInfoList = searchUserService.getAllUserInfo();
		userList = statInfoFactory.createUserList(basicInfoList);

	}

	public void initJudgement(){
		if(userList == null)
			init();
	}


	@Override
	public StatResult<Integer, Integer> statCreateTime() {

		StatResult<Integer, Integer> resultStat = new StatResult<Integer, Integer>();
		Map<Integer, Integer> map = searchUserService.getYearWithUser();
		for(Map.Entry<Integer, Integer> entry: map.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;
	}


	@Override
	public StatResult<Integer,Integer> statCreateReposDistri(int increment) {

		StatResult<Integer, Integer> resultStat = new StatResult<Integer, Integer>();
		Map<Integer, Integer> map = searchUserService.getCreateWithUser();
		for(Map.Entry<Integer, Integer> entry: map.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;

	}

	@Override
	public StatResult<Integer,Integer> statContributeReposDistri(int increment) {
		initJudgement();


		List<Integer> contributedList = new ArrayList<Integer>();
		for(UserInfo info : userList){
			contributedList.add(info.getContributedRepoNum());
		}

		StatResult<Integer, Integer> resultStat = ListGrouping.groupList(contributedList, increment);
		resultStat.sortByKeyReverse();


		System.out.println("user contributed");

		Map<Integer, Integer> tempMap = resultStat.getMap();
//		for(Map.Entry<Integer, Integer> entry: tempMap.entrySet())
//			System.out.println(entry.getKey()+":"+entry.getValue());

		return resultStat;
	}

    @Override
    public StatResult<Integer, Double> statCreateReposDistriLogE(
            int increment) {

        StatResult<Integer, Integer> intResult = statCreateReposDistri(increment);
        StatResult<Integer, Double> logResult = ListGrouping.logResult(intResult);


        System.out.println("user created log");
        Map<Integer, Double> tempMap = logResult.getMap();
//		for(Map.Entry<Integer, Double> entry: tempMap.entrySet())
//			System.out.println(entry.getKey()+":"+entry.getValue());

        return logResult;

    }

    @Override
    public StatResult<Integer, Double> statContributeReposDistriLogE(
            int increment) {

        StatResult<Integer,Integer> intResult = statContributeReposDistri(increment);
        StatResult<Integer, Double> logResult = ListGrouping.logResult(intResult);

        System.out.println("user contributed log");
        Map<Integer, Double> tempMap = logResult.getMap();
//		for(Map.Entry<Integer, Double> entry: tempMap.entrySet())
//			System.out.println(entry.getKey()+":"+entry.getValue());

        return logResult;

    }

}
