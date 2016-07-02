package Application.gitAPIExtends.githubVO;

import java.util.Date;
import java.util.List;

//{
//    "days": [
//      0,
//      3,
//      26,
//      20,
//      39,
//      1,
//      0
//    ],
//    "total": 89,
//    "week": 1336280400
//  }
public class CommitActivity {

    List<Integer> days;

    int total;

    long week;

    public List<Integer> getDays() {
        return days;
    }

    public void setDays(List<Integer> days) {
        this.days = days;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public Date getWeek() {
        return new Date(week*1000);
    }

    public void setWeek(long week) {
        this.week = week;
    }

}
