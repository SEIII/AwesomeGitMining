package Application.gitAPIExtends.githubVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

//[
// 1152403200,
// 4756,
// 0
//]
public class CodeFrequency {
    
    long week;
    
    int add;
    int delete;
    
    public CodeFrequency(List<Integer> datas) {
        init(datas);
    }
    public CodeFrequency(int[] datas) {
        List<Integer> dataList = new ArrayList<>();
        for(int oneData:datas) {
            dataList.add(oneData);
        }
        init(dataList);
    }
    
    private void init(List<Integer> datas) {
        week = datas.get(0);
        add = datas.get(1);
        
        //符号很关键
        delete = -datas.get(2);
    }
    
    public Date getWeek() {
        return new Date(week*1000);
    }
    public void setWeek(long week) {
        this.week = week;
    }
    public int getAdd() {
        return add;
    }
    public void setAdd(int add) {
        this.add = add;
    }
    public int getDelete() {
        return delete;
    }
    public void setDelete(int delete) {
        this.delete = delete;
    }

}
