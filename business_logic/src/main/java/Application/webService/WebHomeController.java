package Application.webService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import Application.common.info.GitMiningUserInfo;

@Controller
@SessionAttributes("user")
public class WebHomeController {

    @RequestMapping("/")
    public String home(@ModelAttribute("user") GitMiningUserInfo userInfo) {
        return "landingPage";
    }

    @RequestMapping("/home")
    public String testHome(){
        return "homePage";
    }

    @RequestMapping("/homepic")
    public String homepic(){
    	return "homePic";
    }

}
