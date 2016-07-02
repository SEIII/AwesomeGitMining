package Application.common.blService.statService;

public interface StatTotalUserService {
	StatResult<Integer,Integer> statCreateTime();
	StatResult<Integer, Integer> statCreateReposDistri(int increment);
	StatResult<Integer, Integer> statContributeReposDistri(int increment);

	StatResult<Integer, Double> statCreateReposDistriLogE(int increment);
	StatResult<Integer, Double> statContributeReposDistriLogE(int increment);

}
