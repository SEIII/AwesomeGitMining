package Application.webService.detailInfoPage;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.business_logic.bl.stat.callback.StatTotalCallbackImpl;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.GitUserTotalInfo;
import Application.common.blService.followService.FollowService;
import Application.common.blService.statService.QueryService;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.common.blService.userService.GitUserTotalInfoLogicService;
import Application.common.info.GitMiningUserInfo;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Controller
@SessionAttributes("user")
public class WebUserInfoDetailController {

    @Autowired
    QueryService queryService;

    @Autowired
    FollowService followService;

    @Autowired
    GitUserTotalInfoLogicService gitUserTotalService;




    @RequestMapping("/userDetail")
    public String getUserDetail(@RequestParam("login") String login,Model model) {
        UserInfo info = queryService.findUserInfo(login);
        model.addAttribute("userinfo", info);

        if(gitUserTotalService.isHit(login)) {
        	GitUserTotalInfo userTotal = gitUserTotalService.cacheUser(login, null);
        	model.addAttribute("userTotal", userTotal);
            return "userinfo";
        }
        else {
            return "userInfoLoading";
        }
    }

    @RequestMapping("/otherDetail")
    public String getOtherDetail(@RequestParam("account") String account, Model model){

    	GitMiningUserInfo otherInfo = followService.getSingleUserInfo(account);
    	model.addAttribute("other", otherInfo);


    	String login = otherInfo.getGitid();

    	UserInfo info = queryService.findUserInfo(login);
        model.addAttribute("userinfo", info);

        if(gitUserTotalService.isHit(login)) {
        	GitUserTotalInfo userTotal = gitUserTotalService.cacheUser(login, null);
        	model.addAttribute("userTotal", userTotal);
            return "other_userinfo";
        }
        else {
            return "userInfoLoading";
        }
    }


    @RequestMapping("/loadingMessage")
    public SseEmitter getLoadingMessage(String login) {
        SseEmitter emitter = new SseEmitter();
        StatTotalCallback callback = new StatTotalCallbackImpl(emitter,login);

        new Thread(()->gitUserTotalService.cacheUser(login, callback)).start();
        return emitter;
    }

    @RequestMapping(value = "/createRepos")
    public String searchUserCreateRepos
        (@RequestParam("login")String login,Model model) {

        List<ReposInfo> repoInfoList =gitUserTotalService.getCreatedRepos(login);

        model.addAttribute("repoInfoList", repoInfoList);
        return "createdRepos";

    }

    @RequestMapping(value = "/contributeRepos")
    public String searchUserContributeRepos
        (@RequestParam("login")String login, Model model) {

        List<ReposInfo> repoInfoList =gitUserTotalService.getContributedRepos(login);
        model.addAttribute("repoInfoList", repoInfoList);
        return "contributedRepos";
    }


    @RequestMapping("/userFollow")
    @ResponseBody
    public String followSingleUser(
    		@RequestParam("account") String account,
            @RequestParam("userLogin") String userLogin,
            @RequestParam("followInfo") String followInfoString
    		){

    	ObjectMapper mapper = new ObjectMapper();


    	FollowUserInfo info = null;
    	try {
			info = mapper.readValue(followInfoString, FollowUserInfo.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}


    	if(followService.insertOneUser(account, info) == 1){
    		String message = "Success";
    		try {
				String msg = mapper.writeValueAsString(message);
				return msg;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}else{
    		String message = "You can't repeatedly follow";
    		try {
				String msg = mapper.writeValueAsString(message);
				return msg;
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
		return "";




    }

}
