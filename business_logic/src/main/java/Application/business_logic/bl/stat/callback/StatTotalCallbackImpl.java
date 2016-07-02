package Application.business_logic.bl.stat.callback;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import Application.common.blService.statService.callback.StatTotalCallback;

public class StatTotalCallbackImpl implements StatTotalCallback{

    private SseEmitter emitter;
    private String login;
    
    private double currentValue;
    
    private int createdNum = 10;
    private int contributedNum = 10;
    
    /**
     * 统计开始的value
     */
    double startValue = 10;
    /**
     * 收到create或contribute数量之后增加的进度
     */
    private double receiveValue = 5;
    
    /**
     * 完成所有create或contribute的basicRepoInfo统计增加的进度
     */
    private double basicInfoBound = 10;
    
    /**
     * 完成所有create或contribute的contribution统计增加的进度
     */
    private double contributionBound = 55; 
    
    
    public StatTotalCallbackImpl(SseEmitter emitter,String login) {
        this.emitter = emitter;
        this.login = login;
        
        if(emitter!=null) {
            emitter.onCompletion(()-> System.out.println("emitter completion"));
        }
    }
    
    @Override
    public void start() {
        send("我们在统计"+login+"的一些信息", startValue);
    }

    @Override
    public void setCreatedNum(int num) {
        this.createdNum = num;
        
        if(num>0) {
            send("他拥有"+num+"个仓库",receiveValue);
        }else {
            send("他还没有创建过仓库",receiveValue+basicInfoBound);
        }
        
    }

    @Override
    public void setContributedNum(int num) {
        
        this.contributedNum = num;
        
        if(num>0) {
            send("他为"+num+"个仓库做出了贡献", receiveValue);
        }else {
            send("他还没有贡献过仓库", receiveValue+basicInfoBound);
        }
        
    }

    @Override
    public void getOneRepoBasicInfo(String repoName) {
        
        double repoNum = getRepoNum();
        
        System.out.println("basic info bound:"+basicInfoBound);
        System.out.println("repo num:"+repoNum);
        
        send("我们在统计"+repoName+"的信息", basicInfoBound/repoNum);
    }

    @Override
    public void getOneRepoContributionInfo(String repoName) {
        double repoNum = getRepoNum();
        send("我们在统计他为"+repoName+"做出的贡献", contributionBound/repoNum);
    }

    @Override
    public void done() {
        send("搞定了", 100-currentValue);
        if(emitter!=null) {
            emitter.complete();
        }
    }
    
    private double getRepoNum() {
        return createdNum+contributedNum;
    }
    
    private void send(String message,double value) {
        
        currentValue += value;
        System.out.println("sending message:"+message+" value:"+(int)currentValue);
        if(emitter==null) {
            return;
        }
        
        StatTotalUserInfoMessage statMessage = new StatTotalUserInfoMessage();
        statMessage.setMessage(message);
        statMessage.setValue((int) currentValue);
        try {
            emitter.send(statMessage,MediaType.APPLICATION_JSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

    @Override
    public void completeCreatedBasicInfo(int num) {
        createdNum = num;
        send("我们统计了他创建仓库的信息", basicInfoBound);
    }

}
