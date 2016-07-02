package Application.gitAPIExtends;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;
import org.eclipse.egit.github.core.client.RequestException;
import org.eclipse.egit.github.core.service.GitHubService;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import Application.gitAPIExtends.githubVO.CodeFrequency;
import Application.gitAPIExtends.githubVO.CommitActivity;
import Application.gitAPIExtends.githubVO.Contribution;
import Application.gitAPIExtends.githubVO.HourCommit;
import Application.gitAPIExtends.githubVO.Participation;

import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_REPOS;

public class GithubRepoStatsService extends GitHubService {

    public static final String SEGMENT_STATS = "/stats";

    public static final String FIELD_CONTRIBUTORS = "contributors";

    public static final String FIELD_COMMIT_ACTIVITY = "commit_activity";

    public static final String FIELD_CODE_FREQUENCY = "code_frequency";

    public static final String FIELD_PARTICIPATION = "participation";

    public static final String FIELD_PUNCH_CARD = "punch_card";

    private int defualtMaxTryTime = 5;

    public GithubRepoStatsService() {
		// TODO Auto-generated constructor stub
	}

    public GithubRepoStatsService(GitHubClient client) {
        super(client);
    }

    public List<CodeFrequency> getCodeFrequency(String owner, String repoName){

        Type type = new TypeToken<List<int[]>>() {}.getType();
        List<int[]> dataList = getStatResult(owner, repoName, FIELD_CODE_FREQUENCY,100, type, null);
        List<CodeFrequency> codeFrequencieList = new ArrayList<>(dataList.size());

        for(int[] oneData:dataList) {
            codeFrequencieList.add(new CodeFrequency(oneData));
        }
        return codeFrequencieList;

    }

    public Participation getParticipation(String owner, String repoName) {

        Participation part = getStatResult
                (owner, repoName, FIELD_PARTICIPATION, Participation.class
                ,(Participation obj)-> obj.getAll()!=null);

        return part;
    }

    public List<HourCommit> getHourCommit(String owner, String repoName){
        Type type = new TypeToken<List<int[]>>() {}.getType();
        List<int[]> dataList = getStatResult(owner, repoName, FIELD_PUNCH_CARD, type, null);

        List<HourCommit> hourCommitList = new ArrayList<>(dataList.size());
        for(int[] oneData:dataList) {
            hourCommitList.add(new HourCommit(oneData));
        }
        return hourCommitList;

    }



    public List<CommitActivity> getCommitActivity(String owner, String repoName){

        Type type = new TypeToken<List<CommitActivity>>(){}.getType();

        List<CommitActivity> commitActivitieList =
                getStatResult(owner, repoName, FIELD_COMMIT_ACTIVITY,type,null);
        return commitActivitieList;
    }

    public List<Contribution> getContributions(String owner, String repoName) {

        Type type = new TypeToken<List<Contribution>>(){}.getType();

        List<Contribution> result =
                getStatResult(owner, repoName, FIELD_CONTRIBUTORS,type,null);
        return result;
    }


    /**
     *
     * 抽象出一次请求,然后获取对象,验证对象的过程.
     * @param owner
     * @param repoName
     * @param field
     * @param valid
     * @return
     */
    protected <T> T getStatResult(String owner,String repoName,String field,Type type,ValidObj<T> valid) {
        return getStatResult(owner, repoName, field,defualtMaxTryTime, type, valid);
    }
    
    protected <T> T getStatResult(String owner,String repoName,String field,int maxTryTime,Type type,ValidObj<T> valid) {

        validateName(owner, repoName);

        String uri = getURLString(owner, repoName, field);

        GitHubRequest request = new GitHubRequest();
        request.setUri(uri);
        request.setType(type);

        T resultObj = null;

        try {
            resultObj = getBodyFromRequest(request,valid,maxTryTime);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resultObj;
    }

    protected boolean validateName(String owner,String repoName) {
        if (owner == null) {
            throw new IllegalArgumentException("owner cannot be null"); //$NON-NLS-1$
        }

        if (owner.length() == 0) {
            throw new IllegalArgumentException("owner cannot be empty"); //$NON-NLS-1$
        }

        if (repoName == null) {
            throw new IllegalArgumentException("Repository cannot be null"); //$NON-NLS-1$
        }

        return true;
    }

    @SuppressWarnings("unchecked")
    protected <T> T getBodyFromRequest(GitHubRequest request,ValidObj<T> valid,int maxTryTime)
            throws IOException,NullPointerException {

        T resultObject = null;
        int time = 0;

        while (resultObject == null && time <= maxTryTime ) {
            time++;

            long beginTime = System.currentTimeMillis();

            try {
                resultObject = (T)client.get(request).getBody();

                if(valid ==null || valid.valid(resultObject)) {
                    return resultObject;
                }

            }catch(RequestException requestException) {
                //request遇到错误
                requestException.printStackTrace();

                NullPointerException nullPointerException =
                        new NullPointerException("统计方法数据获取失败:"+request.getUri());
                throw nullPointerException;
            }
            catch (JsonSyntaxException|IOException exception) {

                if(exception.getMessage().contains("(451)")){
                    exception.printStackTrace();
                    NullPointerException nullPointerException =
                            new NullPointerException("统计方法数据获取失败:"+request.getUri());
                    throw nullPointerException;
                }

                System.out.println(request.getUri()+"轮询中");

            }finally {
                resultObject = null;
            }

            long endTime = System.currentTimeMillis();
            System.out.println("轮询 delta time: " + (endTime - beginTime));

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("try time end!");
        
        return resultObject;
    }

    protected String getURLString(String owner, String repoName, String field) {
        return SEGMENT_REPOS + "/" + owner + "/" + repoName + SEGMENT_STATS
                + "/" + field;
    }

    public static void main(String[] args) {

        GitHubClient client = new GitHubClient();
        client.setOAuth2Token("f6d2b6464f71e59be799e24082a0dd350696dacd");

        GithubRepoStatsService service = new GithubRepoStatsService(client);

        List<Contribution> contributions = service
                .getContributions("rubinius", "rubinius-actor");

        System.out.println("contributions");
        System.out.println(contributions);

        List<CommitActivity> commitActivities = service
                .getCommitActivity("sincoder","s");
        System.out.println("commit activities");
        System.out.println(commitActivities);

        List<CodeFrequency> frequencies = service.getCodeFrequency("rubinius", "rubinius");
        System.out.println("frequencies");
        System.out.println(frequencies);

        List<HourCommit> hourCommits = service.getHourCommit("NJUShenbin", "EL-game");
        System.out.println("hourCommits");
        System.out.println(hourCommits);

        Participation participation = service.getParticipation("angular","a");
        System.out.println("partcipation");
        System.out.println(participation);

    }

    interface ValidObj<T>{
        boolean valid(T obj);
    }

}
