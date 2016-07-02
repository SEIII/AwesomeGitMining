package Application.webService.detailInfoPage;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.DTO.FollowRepoInfo;
import Application.common.blService.followService.FollowService;
import Application.common.blService.statService.QueryService;
import Application.common.info.ReposInfo;

@Controller
@SessionAttributes("user")
public class WebRepoInfoDetailController {

    @Autowired
    QueryService queryService;

    @Autowired
    FollowService followService;

    @RequestMapping("/repoDetail")
    public String getRepoDetail(
            @RequestParam("owner") String owner,
            @RequestParam("repoName") String repoName,
            Model model
            ) {
        ReposInfo repoInfo = queryService.findReposInfo(owner, repoName);
        model.addAttribute("repoinfo", repoInfo);
        return "repodetailinfo";
    }


    @RequestMapping("/repoFollow")
    @ResponseBody
    public String followSingleRepo(
    		@RequestParam("account") String account,
            @RequestParam("followInfo") String followInfoString
    		){

    	ObjectMapper mapper = new ObjectMapper();


    	FollowRepoInfo info = null;
    	try {
			info = mapper.readValue(followInfoString, FollowRepoInfo.class);
		} catch (IOException e1) {
			e1.printStackTrace();
		}


    	if(followService.insertOneRepo(account, info) == 1){
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




