package Application.webService.totalStatPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Application.common.blService.statService.EchartData;
import Application.common.blService.statService.StatTotalUserService;

@Controller
@RequestMapping("/statTotalUser")
public class WebStatTotalUserController {

    @Autowired
    StatTotalUserService userService;

    @RequestMapping("/createTime")
    @ResponseBody
    public EchartData<Integer,Integer> statCreateTime(){
        return new EchartData<>(userService.statCreateTime());
    }



    @RequestMapping("/createRepo")
    @ResponseBody
    public EchartData<Integer, Double> statCreateReposDistriLogE(
            @RequestParam(value = "increment",defaultValue = "5") int increment){
       return new EchartData<>(userService.statCreateReposDistriLogE(increment));
    }
}
