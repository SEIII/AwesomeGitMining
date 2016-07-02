package Application.business_logic.bl.stat.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.business_logic.bl.stat.ListGrouping;
import Application.business_logic.bl.stat.StatInfoFactory;
import Application.common.ClassifyItem;
import Application.common.StarRelatedItem;
import Application.common.DTO.BasicRepositoryInfo;
import Application.common.blService.statService.StatResult;
import Application.common.blService.statService.StatTotalReposService;
import Application.common.data_service.SearchRepositoryService;
import Application.common.info.ReposInfo;


@Component
public class StatTotalReposImpl implements StatTotalReposService{


	@Autowired
	StatInfoFactory statInfoFactory;

	@Autowired
	SearchRepositoryService searchRepositoryService;

	List<ReposInfo> reposList;

	Map<String, List<Integer>> starRelatedMap;


	public void init(){
		List<BasicRepositoryInfo> basicInfoList = searchRepositoryService.getAllRepoInfo();
		reposList = statInfoFactory.createReposList(basicInfoList);
	}


	public void initJudgement(){
		if(reposList == null)
			init();
	}



	@Override
	public StatResult<String, Integer> statLanguages() {
		StatResult<String, Integer> statResult = new StatResult<String, Integer>();
		Map<String, Integer> repoLanguage = searchRepositoryService.getLanagugeWithByte();
		for(Map.Entry<String, Integer> entry: repoLanguage.entrySet())
			statResult.put(entry.getKey(), entry.getValue());

		statResult.sortByValue();
		return statResult;
	}

	@Override
	public StatResult<Integer, Integer> statForkDistri(int increment) {
		StatResult<Integer, Integer> resultStat = new StatResult<>();
		Map<Integer, Integer> repoLanguage = searchRepositoryService.getForkWithRepo();
		for(Map.Entry<Integer, Integer> entry: repoLanguage.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;
	}

	@Override
	public StatResult<Integer, Integer> statCreateTime() {

		StatResult<Integer, Integer> resultStat = new StatResult<>();
		Map<Integer, Integer> repoYear = searchRepositoryService.getYearWithRepo();
		for(Map.Entry<Integer, Integer> entry: repoYear.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;

	}

	@Override
	public StatResult<Integer, Integer> statStarDistri(int increment) {

		StatResult<Integer, Integer> resultStat = new StatResult<>();
		Map<Integer, Integer> map = searchRepositoryService.getStarWithRepo();
		for(Map.Entry<Integer, Integer> entry: map.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;

	}

	@Override
	public StatResult<Integer, Integer> statContributorDistri(int increment) {

		StatResult<Integer, Integer> resultStat = new StatResult<>();
		Map<Integer, Integer> map = searchRepositoryService.getContributorWithRepo();
		for(Map.Entry<Integer, Integer> entry: map.entrySet())
			resultStat.put(entry.getKey(), entry.getValue());

		return resultStat;
	}


	@Override
	public StatResult<Integer, Integer> statStarRelatedDistri(StarRelatedItem item, int increment, double discrete) {

		Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
		StatResult<Integer, Integer> result = new StatResult<>();

		switch(item){

		case fork:
			map = searchRepositoryService.getStarWithFork();
			break;
		case contributor:
			map = searchRepositoryService.getStarWithContributor();
			break;

		default:
			break;

		}

		for(Map.Entry<Integer, Integer> entry: map.entrySet())
			result.put(entry.getKey(), entry.getValue());

		return result;

	}



	public void groupTupleList(List<StarRelatedTuple> list, int increment){

		starRelatedMap = new TreeMap<String, List<Integer>>();

		for(StarRelatedTuple item : list){

			int number = item.getStar();

			int quotient = (number/increment) + 1;
			String groupId = quotient + "";

			if(!starRelatedMap.containsKey(groupId)){
				List<Integer> init_star_related_list = new ArrayList<Integer>();
				init_star_related_list.add(item.getRelated());
				starRelatedMap.put(groupId, init_star_related_list);

			}else{
				starRelatedMap.get(groupId).add(item.getRelated());
			}

		}

	}


	public StatResult<Integer, Integer> generateStarRelatedResult(double discrete){

		StatResult<Integer, Integer> resultStat = new StatResult<Integer, Integer>();

		for(Map.Entry<String, List<Integer>> entry: starRelatedMap.entrySet()){

			int random = (int) (Math.random()*entry.getValue().size());
			resultStat.put(Integer.parseInt(entry.getKey()), entry.getValue().get(random));

		}
		resultStat.sortByKey();

		return reduction(resultStat, discrete);

	}


	public StatResult<Integer, Integer> reduction(StatResult<Integer, Integer> resultStat, double partition){

		StatResult<Integer, Integer> reduced_resultStat = new StatResult<Integer, Integer>();
		List<StatResult<Integer, Integer>.Turple> list = resultStat.getTrupleList();
		int length = list.size();


		int max_star = 0;
		int max_related = 0;

		int reduction_partiton = (int) (partition*length);

		for(int i=reduction_partiton; i<length; i++){

			int star = list.get(i).getKey();
			int related = list.get(i).getValue();

			reduced_resultStat.put(star, related);

			if(star> max_star)
				max_star = star;
			if(related > max_related)
				max_related = related;
		}



		StatResult<Integer, Integer> final_result = new StatResult<Integer, Integer>();
		List<StatResult<Integer, Integer>.Turple> reduced_tuple_list = reduced_resultStat.getTrupleList();
		for(StatResult<Integer, Integer>.Turple turple: reduced_tuple_list){

			int star = (int)(((double) turple.getKey() / (double)max_star)*100);
			int relate = (int)(((double) turple.getValue() / (double)max_related)*100);

			final_result.put(star, relate);
		}



		return final_result;

	}


	public class StarRelatedTuple{

		Integer star;
		Integer related;

		public StarRelatedTuple(Integer star, Integer related) {
			this.star = star;
			this.related = related;
		}

		public Integer getStar() {
			return star;
		}

		public void setStar(Integer star) {
			this.star = star;
		}

		public Integer getRelated() {
			return related;
		}

		public void setRelated(Integer related) {
			this.related = related;
		}

		@Override
		public String toString() {
			return "StarRelatedTuple [star=" + star + ", related=" + related + "]";
		}
	}


    @Override
    public StatResult<Integer, Double> statForkDistriLogE(int increment) {
        StatResult<Integer,Integer> intResult = statForkDistri(increment);
        StatResult<Integer, Double> logResult = ListGrouping.logResult(intResult);
        return logResult;
    }


    @Override
    public StatResult<Integer, Double> statStarDistriLogE(int increment) {
        StatResult<Integer,Integer> intResult = statStarDistri(increment);
        StatResult<Integer, Double> logResult = ListGrouping.logResult(intResult);
        return logResult;
    }


    @Override
    public StatResult<Integer, Double> statContributorDistriLogE(
            int increment) {
        StatResult<Integer,Integer> intResult = statContributorDistri(increment);
        StatResult<Integer, Double> logResult = ListGrouping.logResult(intResult);
        return logResult;
    }

}
