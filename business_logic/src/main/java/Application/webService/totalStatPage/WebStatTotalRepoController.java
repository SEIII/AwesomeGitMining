package Application.webService.totalStatPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Application.common.StarRelatedItem;
import Application.common.blService.statService.EchartData;
import Application.common.blService.statService.StatTotalReposService;

@Controller
@RequestMapping("/statTotalRepos")
public class WebStatTotalRepoController {

    @Autowired
    StatTotalReposService reposService;

    @RequestMapping("/language")
    @ResponseBody
    public EchartData<String,Integer> statLanguages(){
        return new EchartData<>(reposService.statLanguages());
    }


    @RequestMapping("/fork")
    @ResponseBody
    public EchartData<Integer,Double> statForkDistri(
           @RequestParam(value = "increment",defaultValue = "10") int increment){
        return new EchartData<>(reposService.statForkDistriLogE(increment));
    }


    @RequestMapping("/createTime")
    @ResponseBody
    public EchartData<Integer,Integer> statCreateTime(){
        return new EchartData<>(reposService.statCreateTime());
    }


    @RequestMapping("/star")
    @ResponseBody
    public EchartData<Integer,Double> statStarDistri(
            @RequestParam(value = "increment",defaultValue = "15") int increment){
        return new EchartData<>(reposService.statStarDistriLogE(increment));

    }


    @RequestMapping("/contributor")
    @ResponseBody
    public EchartData<Integer,Double> statContributorDistri(
            @RequestParam(value = "increment",defaultValue = "5") int increment){
        return new EchartData<>(reposService.statContributorDistriLogE(increment));
    }



    /**
     * 统计有关star的其他的散点分布
     * @return
     */
    @RequestMapping("/starRelated")
    @ResponseBody
    public EchartData<Integer, Integer> statStarRelatedDistri(
            @RequestParam("item") String item,
            @RequestParam(value = "increment",defaultValue = "50") int increment){
        double discrete = 0.01;
        StarRelatedItem relatedItem = StarRelatedItem.valueOf(item);
        return new EchartData<>(reposService
                .statStarRelatedDistri(relatedItem, increment, discrete));
    }
}
