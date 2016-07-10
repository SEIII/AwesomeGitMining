package Application.webService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import Application.common.RegisterState;
import Application.common.blService.verifyService.LoginService;
import Application.common.info.GitMiningUserInfo;
import Application.webService.detailInfoPage.WebUserInfoDetailController;

@Controller
@SessionAttributes("user")
public class WebLoginOrRegister {

    @Autowired
    WebUserInfoDetailController userDetailController;

	@Autowired
	LoginService loginService;


	@RequestMapping("/login")
	public String login(){
			return "login";
	}

	@RequestMapping("/landing")
	public String landing(){
		System.out.println("invoked");
		return "landingPage";
	}



	@RequestMapping("/loginVerify")
	@ResponseBody
	public String loginVerify(
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			Model model,
			HttpSession session
			){


		GitMiningUserInfo info = loginService.verifyLogin(account, password);

		if(info!=null){
			session.setAttribute("user", info);
			model.addAttribute("user", info);
			String msg = info.getGitid();

			return msg;
		}

		else{
			String msg = "login fail";
			return msg;
		}
	}


	@RequestMapping("/register")
	public String register(Model model){
		System.out.println("register");
	    model.addAttribute("state", "NULL");
		return "register";
	}

	@RequestMapping("/registerVerify")
	public String registerVerify(
			@RequestParam("gitid") String gitID,
			@RequestParam("account") String account,
			@RequestParam("password") String password,
			Model model,
			HttpSession session){

		System.out.println("register verify");
		RegisterState state = loginService.verifyRegister(account, gitID, password);

		model.addAttribute("state", state.toString());
		return "register";
	}



}
