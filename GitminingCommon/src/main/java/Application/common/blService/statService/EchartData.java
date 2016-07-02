package Application.common.blService.statService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;

public class EchartData<X extends Comparable<X>, Y extends Comparable<Y>> {

    List<X> xData = new ArrayList<>();

    List<Y> yData = new ArrayList<>();
    
    @Getter Map<String, Object> other = new HashMap<>();

    public EchartData(Map<X, Y> map) {
        for (X key : map.keySet()) {
            xData.add(key);
            yData.add(map.get(key));
        }
    }


    public EchartData(StatResult<X, Y> statResult) {
        this(statResult.getMap());
    }
    
    public void put(String key,Object value) {
        other.put(key, value);
    }

    public List<X> getxData() {
        return xData;
    }

    public List<Y> getyData() {
        return yData;
    }

}
