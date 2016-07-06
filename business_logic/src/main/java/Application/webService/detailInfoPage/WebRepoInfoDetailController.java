package Application.webService.detailInfoPage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import Application.common.blService.statService.StatSingleRepoService;
import Application.common.info.ReposInfo;
import Application.gitAPIExtends.githubVO.Contribution;

@Controller
@SessionAttributes("user")
public class WebRepoInfoDetailController {

    @Autowired
    QueryService queryService;

    @Autowired
    FollowService followService;

    @Autowired
    StatSingleRepoService statSingleRepoService;

    List<String> repoList;

    @RequestMapping("/repoDetail")
    public String getRepoDetail(
            @RequestParam("owner") String owner,
            @RequestParam("repoName") String repoName,
            Model model
            ) {
        ReposInfo repoInfo = queryService.findReposInfo(owner, repoName);
        model.addAttribute("repoinfo", repoInfo);
        String fullName = owner + "/" + repoName;

        if(statSingleRepoService.getRepoIsHit(fullName)){
        	List<Contribution> contributions = statSingleRepoService.getRepoContributors(fullName);
        	model.addAttribute("itemList", contributions);
        	return "repodetailinfo";
        }


        return "repoLoading";
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


    @RequestMapping("/contributors")
    @ResponseBody
    public List<Contribution> statRepoContributors(
    		@RequestParam("owner") String owner,
    		@RequestParam("repoName") String repoName
    		){


    	String fullName = owner + "/" + repoName;
    	try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return statSingleRepoService.getRepoContributors(fullName);

    }

}




