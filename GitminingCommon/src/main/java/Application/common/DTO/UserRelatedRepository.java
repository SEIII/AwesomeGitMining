package Application.common.DTO;

import java.util.Map;

import org.eclipse.egit.github.core.Repository;

import Application.common.util.StrConverter;
import Application.gitAPIExtends.githubVO.Contribution;

/**
 * 与某一个用户相关的仓库(创建过,贡献过)的有用信息
 * @author admin
 *
 */
/**
 * @author lenovo
 *
 */
/**
 * @author lenovo
 *
 */
public class UserRelatedRepository{


    Repository repository;


    /**
     * 语言名/bytes
     */
    Map<String, Long> languageList;

    /**
     * 只要用户自己的contribution,其他人的扔掉
     */
    Contribution contribution;

    /**
     * 仓库总的commit数量
     */
    int totalCommitCount;

    /**
     * 仓库总行数
     */
    int totalLineNum;

    /**
     * 仓库总的贡献者数量,就是contributions里的author的数量
     */
    int totalContributorNum;


    /**
     * 用户个人的贡献度(代码行数)
     */
    int userContribution;


    /**
     * 仓库估价
     */
    double value;

    /**
     * 仓库价值的排名(0~1 值越高说明仓库价值越高)
     */
    double rate;

    public UserRelatedRepository() {
		// TODO Auto-generated constructor stub
	}

    public UserRelatedRepository(Repository repo) {
		// TODO Auto-generated constructor stub
    	this.repository = repo;
    }

    public String getCreatedAt() {
        return StrConverter.getString(repository.getCreatedAt());
    }
    
    public String getUpdatedAt() {
        return StrConverter.getString(repository.getUpdatedAt());
    }
    
    public String getFullName() {
        return repository.getOwner().getLogin()+"/"+repository.getName();
    }
    
    public Repository getRepository() {
        return repository;
    }

    public void setRepository(Repository repository) {
        this.repository = repository;
    }

    public Map<String, Long> getLanguageList() {
        return languageList;
    }

    public void setLanguageList(Map<String, Long> languageList) {
        this.languageList = languageList;
    }

    public int getTotalCommitCount() {
        return totalCommitCount;
    }

    public void setTotalCommitCount(int totalCommitCount) {
        this.totalCommitCount = totalCommitCount;
    }

    public int getTotalLineNum() {
        return totalLineNum;
    }

    public void setTotalLineNum(int totalLineNum) {
        this.totalLineNum = totalLineNum;
    }

    public int getTotalContributorNum() {
        return totalContributorNum;
    }

    public void setTotalContributorNum(int totalContributorNum) {
        this.totalContributorNum = totalContributorNum;
    }

	public Contribution getContribution() {
		return contribution;
	}

	public void setContribution(Contribution contribution) {
		this.contribution = contribution;
	}

	public double getValue() {
		if(value == 0)
			return 1000;
		else{
			return value;
		}
	}

	public int getValueInt() {
	    return (int)getValue();
	}
	
	public void setValue(double value) {
		this.value = value;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public int getUserContributeCount(){
		return contribution.getTotal();
	}

	public double getUserContribution() {

		int result = contribution.getTotal();

		if(totalCommitCount == 0)
			totalCommitCount = 1;

		double rate = (double)result / (double)totalCommitCount;

		return rate;

	}


	public long getTotalSizeInByte(){
		long result = 0;
		for(Map.Entry<String, Long> entry: languageList.entrySet())
			result += entry.getValue();
		return result;
	}

	public void setUserContribution(int userContribution) {
		this.userContribution = userContribution;
	}

	@Override
	public String toString() {
		return "UserRelatedRepository [repository=" + repository.getOwner().getLogin()+"/"+repository.getName() + ", languageList=" + languageList.toString()
				+ ", contribution=" + contribution.toString() + ", totalCommitCount=" + totalCommitCount + ", totalLineNum="
				+ totalLineNum + ", totalContributorNum=" + totalContributorNum + "]";
	}

}
