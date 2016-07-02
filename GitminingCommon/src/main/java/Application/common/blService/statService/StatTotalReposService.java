package Application.common.blService.statService;

import Application.common.StarRelatedItem;

/**
 * @author 申彬
 * distri是distribution的缩写，意思是分布情况
 */
public interface StatTotalReposService {

	StatResult<String,Integer> statLanguages();
	StatResult<Integer,Integer> statCreateTime();
	StatResult<Integer,Integer> statForkDistri(int increment);
	StatResult<Integer, Integer> statStarDistri(int increment);
	StatResult<Integer, Integer> statContributorDistri(int increment);

	StatResult<Integer,Double> statForkDistriLogE(int increment);
	StatResult<Integer, Double> statStarDistriLogE(int increment);
	StatResult<Integer, Double> statContributorDistriLogE(int increment);

	/**
	 * 统计有关star的其他的散点分布
	 * @return
	 */
	StatResult<Integer, Integer> statStarRelatedDistri(StarRelatedItem item, int increment, double discrete);

}
