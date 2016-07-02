package Application.business_logic.bl.stat;

import java.util.*;

import org.eclipse.egit.github.core.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.common.DTO.UserRelatedRepository;
import Application.data.data_impl.StatDataFactory;

@Component
public class StatGitRepoValue {

	@Autowired
	StatDataFactory statDataFactory;

	int repoExpValue = 0;

	public void statSingleRepoValue(UserRelatedRepository relatedRepo) {
	    statSingleRepoValue(relatedRepo,100, 300, 500, 300);
	}

	public void statSingleRepoValue(UserRelatedRepository relatedRepo,
										int starValue, int forkValue, int issueValue, int intervalValue){

		int repoValue = 0;

		Repository repo = relatedRepo.getRepository();
		int starCount = repo.getWatchers();
		int forkCount = repo.getForks();
		int issueCount = repo.getOpenIssues();
		int sizeCount = repo.getSize();
		Date updatedAt = repo.getUpdatedAt();

		int interval = caculateIntervalTime(updatedAt);
		if(interval<30){
			repoValue += (30-interval)*intervalValue;
		}

		repoValue += starCount*starValue + forkCount*forkValue + issueCount*issueValue;
		if(repoValue<2000){
			repoValue += (1000 + (sizeCount/10)) ;
		}

		if(repoValue<1000)
			repoValue = 1000;
		relatedRepo.setValue((double)repoValue);


		int repoExpValue = statRepoExpValue(starValue, forkValue, issueValue);

		double rate = ((double)repoValue) / ((double)repoExpValue);
		rate = rate/(1+rate);

		relatedRepo.setRate(rate);

	}





	public int statRepoExpValue(int starValue, int forkValue, int issueValue){

		if(repoExpValue != 0){
			return repoExpValue;
		}else{
			Map<Integer, Integer> repoWithStar = statDataFactory.getStatData("repo-star");
			Map<Integer, Integer> repoWithFork = statDataFactory.getStatData("repo-fork");
			Map<Integer, Integer> repoWithIssue = statDataFactory.getStatData("repo-issue");

			int starMedian = caculateMedian(repoWithStar);
			int forkMedian = caculateMedian(repoWithFork);
			int issueMedian = caculateMedian(repoWithIssue);

			repoExpValue = 	starMedian*starValue + forkMedian*forkValue + issueMedian*issueValue;
			return repoExpValue;
		}
	}


	private int caculateIntervalTime(Date date){
		long past = date.getTime();
		long current = System.currentTimeMillis();

		long interval = (current - past);
		int days = (int) ((double)interval/(1000*3600*24));

		return days;
	}


	private int caculateMedian(Map<Integer, Integer> map){

		int answer = 0;
		int index = 0;
		int total =0;

		for(Map.Entry<Integer, Integer> entry: map.entrySet()){
			total += entry.getValue();
		}

		for(Map.Entry<Integer, Integer> entry: map.entrySet()){
			index += entry.getValue();
			if(index>= (total/2)){
				answer = entry.getKey();
				break;
			}

		}


		return answer;
	}
}
