package Application.webService;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.ReposSort;
import Application.common.SearchPage.SearchPage;
import Application.common.SearchPage.WebPage;
import Application.common.blService.SearchService;
import Application.common.info.AbstractInfo;
import Application.common.info.InfoType;
import Application.common.info.ReposInfo;
import Application.common.info.UserInfo;

@Controller
@SessionAttributes("user")
public class WebSearchController {

    @Autowired
    SearchService searchService;

    ObjectMapper mapper;
    Map<ReposSort,String> sortMap;

    public WebSearchController(){

    	mapper = new ObjectMapper();
    	sortMap = new TreeMap<ReposSort,String>();
    	sortMap.put(ReposSort.general, "repoSearch");
    	sortMap.put(ReposSort.star,"repoSearch_star");
    	sortMap.put(ReposSort.fork, "repoSearch_fork");
    	sortMap.put(ReposSort.contributors, "repoSearch_cont");

    }

    @RequestMapping(value = "/repoSearch")
    public String searchRepos(
            @RequestParam("key")String key,
            @RequestParam(value = "sort",defaultValue = "general") String sort,
            @RequestParam(value = "page",defaultValue = "0") int pageIndex,
            Model model) {

        WebPage<ReposInfo> resultPage = getList(key,
                () -> searchService.searchRepos(key, ReposSort.valueOf(sort)),
                0);

        configSearchModel(model, InfoType.repo, resultPage, key, pageIndex);

        return sortMap.get(ReposSort.valueOf(sort));

    }


    @RequestMapping(value = "/repoJsonSearch")
    @ResponseBody
    public String searchReposJson(
            @RequestParam("key")String key,
            @RequestParam(value = "sort",defaultValue = "general") String sort,
            @RequestParam(value = "page",defaultValue = "0") int pageIndex) {

    	System.out.println(sort);

        WebPage<ReposInfo> resultPage = getList(key,
                () -> searchService.searchRepos(key, ReposSort.valueOf(sort)),pageIndex);
        return getJsonList(resultPage);

    }

    @RequestMapping(value = "/userJsonSearch")
    @ResponseBody
    public String searchUserJson(
            @RequestParam("key")String key,
            @RequestParam(value = "page",defaultValue = "0") int pageIndex) {

    	 WebPage<UserInfo> resultPage= getList(key,()-> searchService.searchUser(key),pageIndex);
        return getJsonList(resultPage);
    }



    @RequestMapping(value = "/userSearch")
    public String searchUser(
            @RequestParam("key")  String key,
            @RequestParam(value = "page",defaultValue = "0") int pageIndex,
            Model model) {



        WebPage<UserInfo> resultPage= getList(key,()-> searchService.searchUser(key),pageIndex);
        configSearchModel(model, InfoType.user, resultPage, key, pageIndex);

        return "userSearch";


    }

    private  <T extends AbstractInfo>  String getJsonList(WebPage<T> webPage){
		List<T> resultList = webPage.getCurrentContent();
		String jsonList = "";
		try {
			jsonList = mapper.writeValueAsString(resultList);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return jsonList;
    }

    /**
     * model content:
     *
     * userInfoList / repoInfoList
     * userInfoJsonList / repoInfoJsonList
     * key
     * currentPage
     * totalPageNum
     * totalInfoNum
     *
     *
     */
    @SuppressWarnings("rawtypes")
    private void configSearchModel(Model model,InfoType type, WebPage webPage,
                String key,int pageIndex) {

    	webPage.turnPageTo(pageIndex);
        List resultList =  webPage.getCurrentContent();

        model.addAttribute(type.toString()+"InfoList", webPage.getCurrentContent());
        model.addAttribute("key", key);

        model.addAttribute("currentPage", pageIndex);
        model.addAttribute("totalPageNum", webPage.totalPageNum());
        model.addAttribute("totalInfoNum", webPage.getTotalListSize());

        try {
            model.addAttribute
                (type.toString()+"InfoJsonList", mapper.writeValueAsString(resultList));

            System.out.println(type.toString()+"InfoJsonList");


        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }


    @RequestMapping(value = "/reposContributors")
    public String searchReposContributors(
            @RequestParam("owner")String owner,
            @RequestParam("repoName") String repoName,
            Model model) {

        List<UserInfo> userInfoList = getList(
                ()->searchService.searchReposContributors(owner, repoName));

        model.addAttribute("userInfoList", userInfoList);
        return "contributorsList";

    }

    @RequestMapping(value = "/reposCollaborators")
    public String searchReposCollaborators(
            @RequestParam("owner")String owner,
            @RequestParam("repoName") String repoName,
            Model model) {

        List<UserInfo> userInfoList = getList(
                ()->searchService.searchReposCollaborators(owner, repoName));

        model.addAttribute("userInfoList", userInfoList);
        return "collaboratorsList";
    }



    protected <T extends AbstractInfo> WebPage<T> getList(String key,
            Search<T> oneSearch,
            int pageIndex) {

        SearchPage<T> infoPage = oneSearch.search();
        WebPage<T> onePage = new WebPage<T>(infoPage);

        onePage.turnPageTo(pageIndex);
        return onePage;
    }


    protected <T extends AbstractInfo> List<T> getList(Search<T> oneSearch){
        return oneSearch.search().getAllContent();
    }

    private interface Search<T extends AbstractInfo> {
        public SearchPage<T> search();
    }

}
