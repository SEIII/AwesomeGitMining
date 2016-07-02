package Application.data.data_impl.git;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.eclipse.egit.github.core.service.RepositoryService;
import org.eclipse.egit.github.core.service.UserService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.AppContextSupport;
import Application.common.ReposType;
import Application.common.DTO.ContributedRepoInfo;
import Application.common.blService.statService.callback.StatTotalCallback;
import Application.data.DAO.common.GithubServiceFactory;
import Application.gitAPIExtends.GithubRepoStatsService;
import Application.gitAPIExtends.githubVO.Contribution;

@Component
public class SourceDataAuxiliary {

    ObjectMapper mapper;

    static {
        trustAllHosts();
    }

    @Async
    public Future<List<Repository>> statUserCreatedRepository(String login,StatTotalCallback callback) {

        RepositoryService service = GithubServiceFactory.getRepoitoryService();

        List<Repository> repos = new ArrayList<Repository>();
        List<Repository> resultRepos = new ArrayList<Repository>();

        try {
            repos = service.getRepositories(login);
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(Repository repo: repos){
        	if(!repo.isFork())
        		resultRepos.add(repo);
        }

        callback.completeCreatedBasicInfo(resultRepos.size());

        return new AsyncResult<List<Repository>>(resultRepos);
    }

    @Async
    public Future<List<Repository>> statUserContributedRepository(String login,
            StatTotalCallback callback)  throws IllegalStateException {
        mapper = new ObjectMapper();

        SourceDataAuxiliary self = AppContextSupport.getApplicationContext()
                .getBean(SourceDataAuxiliary.class);

        List<Repository> contributedRepo = new ArrayList<Repository>();
        List<Future<Repository>> futureRepoList = new ArrayList<Future<Repository>>();
        List<String> repoNameList = new ArrayList<String>();

        String url = "https://githubcontributions.io/api/user/" + login;

        String cmd = execNormalURL(url);

        if (cmd != null && cmd != "") {
            ContributedRepoInfo repoList = null;
            try {
                repoList = mapper.readValue(cmd, ContributedRepoInfo.class);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (repoList != null) {
                repoNameList = getContributedRepoNameList(repoList.getRepos(),
                        login);
                callback.setContributedNum(repoNameList.size());
            }

            try {


                for (String repoName : repoNameList) {
                    Future<Repository> futureRepo = self.getSingleRepoInfo(repoName,callback);
                    futureRepoList.add(futureRepo);
                }


                while (true) {

                    boolean result = true;
                    for (Future<Repository> futureRepo : futureRepoList)
                        result = result && futureRepo.isDone();

                    if (result)
                        break;

                    Thread.sleep(10);
                }

                for (Future<Repository> futureRepo : futureRepoList) {
                    Repository tempRepo = futureRepo.get();
                    if (tempRepo != null)
                        contributedRepo.add(tempRepo);

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return new AsyncResult<List<Repository>>(contributedRepo);
        } else {
            System.out.println(
                    "---------------GitHubContribution Collapsed-------------------");
            return new AsyncResult<List<Repository>>(contributedRepo);
        }

    }

    protected List<String> getContributedRepoNameList(List<String> repoNameList,
            String login) {
        List<String> contributedRepoNameList = new ArrayList<>();
        for (String repoName : repoNameList) {
            if (!repoName.split("/")[0].equals(login)) {
                contributedRepoNameList.add(repoName);
            }
        }
        return contributedRepoNameList;
    }

    @Async
    public Future<Map<String, Long>> getRepoLanguage(Repository repo) {

        RepositoryService service = GithubServiceFactory.getRepoitoryService();
        try {
            return new AsyncResult<Map<String, Long>>(
                    service.getLanguages(repo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AsyncResult<Map<String, Long>>(new HashMap<String, Long>());
    }

    @Async
    public Future<List<Contribution>> getRepoContribution(String ownerLogin,
            String repoName) {

        List<Contribution> contributions = new ArrayList<Contribution>();

        GithubRepoStatsService service = GithubServiceFactory
                .getGithubRepoStatsService();
        contributions = service.getContributions(ownerLogin, repoName);

        return new AsyncResult<List<Contribution>>(contributions);

    }

    @Async
    public Future<List<Event>> getUserEvent(String login) {

        // EventService service =
        // (EventService)getService(GitHubServiceType.Event);
        EventService service = GithubServiceFactory.getEventService();

        PageIterator<Event> page = service.pageUserEvents(login, true);
        List<Event> events = new ArrayList<Event>();
        events = (List<Event>) page.next();

        return new AsyncResult<List<Event>>(events);

    }

    @Async
    public Future<User> getSingleUserInfo(String userLogin) {
        // UserService userService =
        // (UserService)getService(GitHubServiceType.User);
        UserService userService = GithubServiceFactory.getUserService();
        User tempUser = new User();
        try {
            tempUser = userService.getUser(userLogin);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AsyncResult<User>(tempUser);
    }

    @Async
    public Future<Repository> getSingleRepoInfo(String repoFullName,StatTotalCallback callback) {
        String[] tempList = repoFullName.split("/");
        String ownerName = tempList[0];
        String repoName = tempList[1];
        Repository result = new Repository();
        // RepositoryService service =
        // (RepositoryService)getService(GitHubServiceType.Repository);
        RepositoryService service = GithubServiceFactory.getRepoitoryService();

        try {
            result = service.getRepository(ownerName, repoName);
            callback.getOneRepoBasicInfo(repoFullName);
            return new AsyncResult<Repository>(result);
        } catch (IOException e) {
            return new AsyncResult<Repository>(null);
        }

    }

    /**
     * Trust every server - dont check for any certificate
     */
    public static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[] {};
                    }

                    public void checkClientTrusted(X509Certificate[] chain,
                            String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain,
                            String authType) throws CertificateException {
                    }
                } };

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    private String execNormalURL(String urlstring) {

        String string = "";


        try {
            URL url = new URL(urlstring);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET"); // 设定请求方式
            conn.connect(); // 建立到远程对象的实际连接
            // 返回打开连接读取的输入流
            DataInputStream dis = new DataInputStream(conn.getInputStream());
            // 判断是否正常响应数据
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                System.out.println("网络错误异常！!!!");
            } else {
                string = dis.readLine();
            }
        } catch (IOException e) {
            return "";
        }

        return string;
    }

    // private String execURL(String urlstring) {
    // return getGithubContentUsingURLConnection(
    // "be3744c8c8b3c1f3b02ff66a3be0eac60f0940e6", urlstring);
    // }
    //
    // private static String getGithubContentUsingURLConnection(String token,
    // String url) {
    // String newUrl = "https://" + url;
    // String result = "";
    // try {
    // URL myURL = new URL(newUrl);
    // URLConnection connection = myURL.openConnection();
    // token = token + ":x-oauth-basic";
    // String authString = "Basic "
    // + Base64.encodeBase64String(token.getBytes());
    // connection.setRequestProperty("Authorization", authString);
    // InputStream crunchifyInStream = connection.getInputStream();
    //
    // result = crunchifyGetStringFromStream(crunchifyInStream);
    // } catch (Exception e) {
    // e.printStackTrace();
    // }
    //
    // return result;
    // }

    // private static String crunchifyGetStringFromStream(
    // InputStream crunchifyStream) throws IOException {
    // if (crunchifyStream != null) {
    // Writer crunchifyWriter = new StringWriter();
    //
    // char[] crunchifyBuffer = new char[8192];
    // try {
    // Reader crunchifyReader = new BufferedReader(
    // new InputStreamReader(crunchifyStream, "UTF-8"));
    // int counter;
    // while ((counter = crunchifyReader
    // .read(crunchifyBuffer)) != -1) {
    // crunchifyWriter.write(crunchifyBuffer, 0, counter);
    // }
    // } finally {
    // crunchifyStream.close();
    // }
    // return crunchifyWriter.toString();
    // } else {
    // return "No Contents";
    // }
    // }

    public static void main(String[] args) {

        SourceDataAuxiliary impl = new SourceDataAuxiliary();
        for (int i = 0; i < 1; i++) {
            long start_time = System.currentTimeMillis();

            long end_time = System.currentTimeMillis();
            System.out.println((end_time - start_time) + " ms");
            System.out.println();
        }

    }

}
