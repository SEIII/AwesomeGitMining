package Application.webService.totalStatPage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("user")
public class WebTotalStatController {

    @RequestMapping("/repoChartTest")
    public String testRepoChart(){
        return "repoTotalChart";
    }

    @RequestMapping("/userChartTest")
    public String testUserChart(){
        
        return "userTotalChart";
    }

}
