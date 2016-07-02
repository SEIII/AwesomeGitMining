package Application.webService.statSingle;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Application.common.blService.statService.EchartData;
import Application.common.blService.statService.QueryService;
import Application.common.blService.statService.StatSingleUserService;
import Application.common.info.UserInfo;
import Application.common.util.CollectionHelper;
import Application.common.util.DateHelper;
import Application.common.util.StrConverter;
import Application.webService.statSingle.levelClassify.LevelClassify;

@Controller
@RequestMapping("/statSingleUser")
public class WebStatSingleUserController {

    LevelClassify codeLevel;
    LevelClassify valueLevel;

    @Autowired
    QueryService queryService;

    public WebStatSingleUserController() {
        codeLevel = new LevelClassify("config/codeLevel.properties");
        valueLevel = new LevelClassify("config/valueLevel.properties");
    }


    @Autowired
    StatSingleUserService statSingleUserService;

    @RequestMapping("/radar")
    @ResponseBody
    //{"xData":["creativity","geek","famous","variety","core","activity"]}
    //"yData":[7.868605817452358,9.83830467204383,9.65435041716329,5.479452054794521,4.021925754522188,4.111111111111111]
    public EchartData<String,Double> statUserRadar(
    		@RequestParam("login") String login){
        return new EchartData<>(statSingleUserService.statUserRadar(login));
    }

    @RequestMapping("/productive")
    @ResponseBody
    public EchartData<String,Double> statProductive(String login){
        EchartData<String,Double> data =
                new EchartData<>(statSingleUserService.statUserProductive(login));

        int maxValueIndex = CollectionHelper.maxValueIndex(data.getyData());
        String mostProductiveRepo = data.getxData().get(maxValueIndex);

        //生产力最高的仓库名
        data.put("repo", mostProductiveRepo);
        //对应的生产力
        data.put("value", StrConverter.getString(
                       data.getyData().get(maxValueIndex) ));

        return data;
    }

    @RequestMapping("/contributionPie")
    @ResponseBody
    public EchartData<String, Integer> statContributionPie(String login){

        EchartData<String, Integer> data =
                new EchartData<>(statSingleUserService.statUserContributionPie(login));

        int total = (int) CollectionHelper.addAll(data.getyData());
        //contribution次数
        data.put("total", total);

        return data;
    }

    @RequestMapping("/value")
    @ResponseBody
    public EchartData<String,Double> statValue(String login){
        EchartData<String,Double> data =
                new EchartData<>(statSingleUserService.statUserValue(login));

        double total = CollectionHelper.addAll(data.getyData());
        String message = valueLevel.getLevelMessage(total);

        int maxIndex = CollectionHelper.maxValueIndex(data.getyData());
        String repo = data.getxData().get(maxIndex);
        double repoValue = data.getyData().get(maxIndex);
        double percent = repoValue/total;

        //最值钱仓库名
        data.put("repo", repo);
        //对应的价值
        data.put("value", StrConverter.getString(repoValue));
        //对应的百分比
        data.put("percent", StrConverter.getString(percent));

        //总价值
        data.put("total", StrConverter.getString(total));
        //比喻
        data.put("message", message);

    	return data;
    }


    @RequestMapping("/code")
    @ResponseBody
    public EchartData<String,Long> statCode(String login){

        EchartData<String,Long> data =
                new EchartData<>(statSingleUserService.statUserCode(login));
        long total = 0;
        for(Long num:data.getyData()) {
            total+=num;
        }

        int maxIndex = CollectionHelper.maxValueIndex(data.getyData());
        String maxLanguage = data.getxData().get(maxIndex);
        long maxLanguageNum = data.getyData().get(maxIndex);
        double percent = maxLanguageNum*1.0/total;

        //语言数
        data.put("languageNum", data.getxData().size());
        //代码量最多的语言
        data.put("language", maxLanguage);
        //对应的百分比
        data.put("percent", StrConverter.getString(percent));

        //总代码量,以KB为单位
        data.put("total", total/1024);
        //相当于....
        data.put("message",codeLevel.getLevelMessage(total));

    	return data;
    }

    @RequestMapping("/codeValue")
    @ResponseBody
    public EchartData<String, Double> statCodeValue(String login){

        EchartData<String, Double> data =
                   new EchartData<>(statSingleUserService.statUserCodeValue(login));

        int maxIndex = CollectionHelper.maxValueIndex(data.getyData());
        
        String language = "";
        double maxValue = 0;
        double percent = 0;
        
        if(maxIndex!=-1) {
            language = data.getxData().get(maxIndex);
            maxValue = data.getyData().get(maxIndex);
            percent = CollectionHelper.maxValuePercent(data.getyData());
        }
        

        //最有价值语言
        data.put("language", language);
        //对应的价值
        data.put("value", maxValue);
        //对应的百分比
        data.put("percent",StrConverter.getString(percent));

    	return data;
    }

    @RequestMapping("/event")
    @ResponseBody
    public List<Integer[]> statSingleUserWeekEvent(String login){
        return statSingleUserService.statSingleUserWeekEvent(login);
    }

    @RequestMapping("/age")
    @ResponseBody
    public Map<String, Integer> statUserAge(String login) {
        UserInfo userInfo = queryService.findUserInfo(login);
        Date createdDate = userInfo.getCreated_at();
        long current = System.currentTimeMillis();
        Date date = new Date(current);
        return DateHelper.dateBetween(createdDate, date);
    }

}
