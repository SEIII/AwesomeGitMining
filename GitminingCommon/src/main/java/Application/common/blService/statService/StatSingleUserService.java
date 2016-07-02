package Application.common.blService.statService;

import java.util.List;

import Application.common.info.UserInfo;

public interface StatSingleUserService {

    /**
     * @param userInfo
     * @return
     * 统计单个用户的雷达图
     */
//    StatResult<String,Double> statSingleUser(UserInfo userInfo);

    /**
     * @param login
     * @return
     * 统计GitHub用户的雷达图
     */
    StatResult<String, Double> statUserRadar(String login);

    /**
     * @param login
     * @return
     * 统计Github用户在不同仓库中的生产力
     */
    StatResult<String, Double> statUserProductive(String login);

    /**
     * @param login
     * @return
     * github用户对不同仓库的贡献数
     */
    StatResult<String, Integer> statUserContributionPie(String login);


    /**
     * @param login
     * @return
     * github用户的不同种类的code bytes
     */
    StatResult<String, Long> statUserCode(String login);

    /**
     * @param login
     * @return
     * github用户在不同仓库中所占的价值
     */
    StatResult<String, Double> statUserValue(String login);

    /**
     * @param login
     * @return
     * github用户不同种的代码所占的价值
     */
    StatResult<String, Double> statUserCodeValue(String login);

    /**
     * @param login
     * @return
     * 统计单个用户最近一个星期的提交等次数
     */
    List<Integer[]> statSingleUserWeekEvent(String login);
}
