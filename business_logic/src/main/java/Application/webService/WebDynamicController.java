package Application.webService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import Application.common.DynamicAllInfo;
import Application.common.DynamicInfo;
import Application.common.blService.GitEventService;

@Controller
@SessionAttributes("user")
public class WebDynamicController {

	@Autowired
	GitEventService gitEventService;

	@RequestMapping("/dynamic")
	public String getUserDynamics(String account,Model model){


		if(gitEventService.getUserIsHit(account)){
			System.out.println("targeted");

			List<DynamicAllInfo> allResult = new ArrayList<DynamicAllInfo>();
			Map<String, List<DynamicInfo>> result = gitEventService.getUserDynamics(account);
			for(Entry<String, List<DynamicInfo>> entry: result.entrySet()){
				allResult.add(new DynamicAllInfo(entry.getKey(), entry.getValue()));
			}


			model.addAttribute("result", allResult);
			gitEventService.evictCache(account);
			return "dynamic";
		}else{
			System.out.println("Not targeted");
			return "dynamicLoad";
		}

	}

	@RequestMapping("/dynamicInfo")
	@ResponseBody
	public Map<String, List<DynamicInfo>> getUserDynamicInfo(String account){
		return gitEventService.getUserDynamics(account);
	}

}
