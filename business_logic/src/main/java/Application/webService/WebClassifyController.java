package Application.webService;

import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import Application.common.ClassifyItem;
import Application.common.blService.ClassifyService;
import Application.common.blService.statService.EchartData;

@Controller
@RequestMapping("/classify")
public class WebClassifyController {

    @Autowired
    ClassifyService classifyService;

    @RequestMapping("/repoLanguage")
    @ResponseBody
    public EchartData<String, Integer> classifyRepoLanguage(@RequestParam("key")String key) {
    	System.out.println("The key is: "+key);

        ClassifyItem item = classifyService.classifyRepoLanguage(key);
        Map<String, Integer> totalMap = item.getClassifyResult();
        Map<String, Integer> tempMap = new TreeMap<String, Integer>();
        int index = 0;

        for(Map.Entry<String, Integer> entry: totalMap.entrySet()){

        	if(entry.getKey().equals("unknow"))
        		continue;

        	if(index>7)
        		break;
        	tempMap.put(entry.getKey(), entry.getValue());
        	index++;
        }

        return new EchartData<>(tempMap);
    }

    @RequestMapping("/userLanguage")
    @ResponseBody
    public EchartData<String, Integer> classifyUserLanguage(@RequestParam("key")String key){
        ClassifyItem item = classifyService.classifyUserLanguage(key);
        Map<String, Integer> totalMap = item.getClassifyResult();
        Map<String, Integer> tempMap = new TreeMap<String, Integer>();
        int index = 0;

        for(Map.Entry<String, Integer> entry: totalMap.entrySet()){

        	if(entry.getKey().equals("unknow"))
        		continue;

        	if(index>7)
        		break;
        	tempMap.put(entry.getKey(), entry.getValue());
        	index++;
        }

        return new EchartData<>(tempMap);
    }


    @RequestMapping("/userType")
    @ResponseBody
    public EchartData<String, Integer> classifyUserType(@RequestParam("key")String key){
        ClassifyItem item = classifyService.classifyUserType(key);
        return new EchartData<>(item.getClassifyResult());
    }

    @RequestMapping("/repoDate")
    @ResponseBody
    public EchartData<String, Integer> classifyRepoDate(@RequestParam("key")String key){
        ClassifyItem item = classifyService.classifyRepoDate(key);
        return new EchartData<>(item.getClassifyResult());
    }

    @RequestMapping("/userDate")
    @ResponseBody
    public EchartData<String, Integer> classifyUserDate(@RequestParam("key")String key){
        ClassifyItem item = classifyService.classifyUserDate(key);
        return new EchartData<>(item.getClassifyResult());
    }



    @RequestMapping(value = "/repoExp")
    @ResponseBody
    public EchartData<String, Integer> classifyRepoExp(@RequestParam("key")String key){
    	System.out.println("The repo exp key is " + key);
        return new EchartData<>(classifyService.classifyRepoExp(key));


    }

    @RequestMapping("/userExp")
    @ResponseBody
    public EchartData<String, Integer> classifyUserExp(@RequestParam("key")String key){
        return new EchartData<>(classifyService.classifyUserExp(key));
    }

}
