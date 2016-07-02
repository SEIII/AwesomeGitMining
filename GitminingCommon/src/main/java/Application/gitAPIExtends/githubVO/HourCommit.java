package Application.gitAPIExtends.githubVO;

public class HourCommit {

    // 0-6: Sunday - Saturday
    int weekDay;

    // 0-23: Hour of day
    int hour;

    // Number of commits
    int commitNum;
    
    public HourCommit(int[] data) {
        weekDay = data[0];
        hour = data[1];
        commitNum = data[2];
    }

    public int getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(int weekDay) {
        this.weekDay = weekDay;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(int commitNum) {
        this.commitNum = commitNum;
    }

}
