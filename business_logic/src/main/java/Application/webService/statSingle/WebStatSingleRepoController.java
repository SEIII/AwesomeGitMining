package Application.webService.statSingle;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.PostConstruct;

import org.eclipse.egit.github.core.service.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Application.common.blService.statService.EchartData;
import Application.common.blService.statService.StatResult;
import Application.common.blService.statService.StatSingleRepoService;
import Application.common.praser.SimpleRepoProvider;
import Application.data.DAO.common.GithubServiceFactory;
import Application.gitAPIExtends.githubVO.CodeFrequency;

@Controller
@RequestMapping("/statSingleRepo")
public class WebStatSingleRepoController {

    RepositoryService githubRepoService;

    @Autowired
    StatSingleRepoService statSingleRepoService;

    @PostConstruct
    public void init() {
        githubRepoService = GithubServiceFactory.getRepoitoryService();
    }

    @RequestMapping("/radar")
    @ResponseBody
    public EchartData<String,Double> statSingleRepos(
            @RequestParam("owner") String owner,
            @RequestParam("repoName") String repoName){

    	String repoFullName = owner+"/"+repoName;
        StatResult<String, Double> result = statSingleRepoService.statSingleGitRepos(repoFullName);

    	return new EchartData<>(result);
//    	return null;
    }

    @RequestMapping("/languages")
    @ResponseBody
    public EchartData<String, Long> statSingleRepoLanguages(
    		@RequestParam("owner") String owner,
            @RequestParam("repoName") String repoName
    		){

        Map<String, Long> languages = null;
        try {
            languages = githubRepoService.getLanguages(new SimpleRepoProvider(owner, repoName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, Long> tempMap = new TreeMap<>();
        int index = 0;
        for(Map.Entry<String, Long> entry: languages.entrySet()){
        	if(index > 7)
        		break;

        	tempMap.put(entry.getKey(), entry.getValue());
        	index++;
        }

        return new EchartData<>(tempMap);
    }




    @RequestMapping("/codeFrequency")
    @ResponseBody
    public List<CodeFrequency> statSingleRepoCodeFrequency(
    		@RequestParam("owner") String owner,
            @RequestParam("repoName") String repoName
    		){

        String repoFullName = owner +"/" +repoName;

        return statSingleRepoService.getRepoCodeFrequency(repoFullName);
    }

}
