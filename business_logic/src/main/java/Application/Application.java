
package Application;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import Application.business_logic.bl.stat.StatGitRepoValue;
import Application.business_logic.bl.stat.impl.StatSingleRepoImpl;
import Application.business_logic.bl.stat.impl.StatSingleUserImpl;
import Application.common.DTO.WatchUserInfo;
import Application.common.blService.statService.StatResult;
import Application.common.data_service.GitUserTotalInfoService;
import Application.data.DAO.sql.SQLTemplate;
import Application.data.data_impl.FollowDataImpl;
import Application.data.data_impl.git.GitRepoInfoImpl;
import Application.data.data_impl.git.SearchGitRepoTest;
import Application.data.data_impl.git.SourceDataAuxiliary;

public class Application {


    
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication
                .run(GitMiningServer.class, args);

        GitUserTotalInfoService service = context
                .getBean(GitUserTotalInfoService.class);
        SourceDataAuxiliary auxiliary = context
                .getBean(SourceDataAuxiliary.class);
        
        
        
        SearchGitRepoTest searchRepo = context
        		.getBean(SearchGitRepoTest.class);
        
        StatGitRepoValue statGitRepoValue = context
        		.getBean(StatGitRepoValue.class);


        StatSingleUserImpl statSingleUserImpl = context.getBean(StatSingleUserImpl.class);


        GitRepoInfoImpl gitRepoInfoImpl = context.getBean(GitRepoInfoImpl.class);

        StatSingleRepoImpl statSingleRepoImpl = context.getBean(StatSingleRepoImpl.class);

        FollowDataImpl followDataImpl = context.getBean(FollowDataImpl.class);


        String userLogin = null;



        Scanner scanner = new Scanner(System.in);


        while (true) {

        	String params = scanner.nextLine();

            long start_time = System.currentTimeMillis();
//            List<WatchUserInfo> infoList = followDataImpl.;
//            for(WatchUserInfo info: infoList)
//            	System.out.println(info.toString());

            long end_time = System.currentTimeMillis();
            System.out.println(
                    "The function takes " + (end_time - start_time) + " ms");
        }

    }

}
