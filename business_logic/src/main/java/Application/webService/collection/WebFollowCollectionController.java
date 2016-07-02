package Application.webService.collection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import Application.common.DTO.FollowRepoInfo;
import Application.common.DTO.FollowUserInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.blService.followService.FollowService;
import Application.common.info.GitMiningUserInfo;

@Controller
@SessionAttributes(names= {"user","userList"})
public class WebFollowCollectionController {


	@Autowired
    FollowService followService;



	@RequestMapping("/followCollection")
    public String getfollowCollection(@RequestParam("account") String account,Model model) {


		List<FollowUserInfo> userList = followService.getUserInfoList(account);
		List<FollowRepoInfo> repoList = followService.getRepoInfoList(account);

		model.addAttribute("userinfolist", userList);
		model.addAttribute("repoinfolist", repoList);
		return "collection";
    }


	@RequestMapping("/watch")
	public String getWatchList(@RequestParam("account") String account, Model model){

		List<WatchUserInfo> userList = followService.getWatchingUserList(account);
		model.addAttribute("userList", userList);

		return "watch";
	}


	@RequestMapping("/watchSearch")
	public String getWatchSearchList(@RequestParam("key") String key, Model model){

		List<WatchUserInfo> userSearchList = followService.getSearchWatchUserList(key);

		@SuppressWarnings("unchecked")
        List<WatchUserInfo> watchUsers = (List<WatchUserInfo>) model.asMap().get("userList");

		GitMiningUserInfo self = (GitMiningUserInfo) model.asMap().get("user");

		for(WatchUserInfo info:userSearchList) {
		    if(watchUsers.contains(info) || info.getAccount().equals(self.getAccount())) {
		        info.setWatched(true);
		    }
		}

		model.addAttribute("userSearchList", userSearchList);

		return "watch";
	}


	@RequestMapping("/addWatch")
	@ResponseBody
	public String addUserWatch(@RequestParam("watcher") String watcherAccount,
							   @RequestParam("watched") String watchedAccount){

		int index = followService.insertOneWatch(watcherAccount, watchedAccount);
		if(index == 1){
		    System.out.println("success");
			return "success";
		}
		return "fail";
	}

	@RequestMapping("/otherCollection")
	public String getOtherCollection(@RequestParam("account") String otherAccount,Model model){

		List<FollowUserInfo> userList = followService.getUserInfoList(otherAccount);
		List<FollowRepoInfo> repoList = followService.getRepoInfoList(otherAccount);

		GitMiningUserInfo otherInfo = followService.getSingleUserInfo(otherAccount);

		model.addAttribute("userinfolist", userList);
		model.addAttribute("repoinfolist", repoList);
		model.addAttribute("other", otherInfo);

		return "other_collection";
	}

}
