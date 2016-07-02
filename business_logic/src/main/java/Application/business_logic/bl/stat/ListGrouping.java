package Application.business_logic.bl.stat;

import java.util.List;
import java.util.Map;

import Application.common.ClassifyItem;
import Application.common.blService.statService.StatResult;

public class ListGrouping {

    public static StatResult<Integer, Integer> groupList(List<Integer> list,
            int increment) {

        StatResult<Integer, Integer> resultStat = new StatResult<Integer, Integer>();
        ClassifyItem classifyItem = new ClassifyItem();
        for (Integer number : list) {
            int quotient = (number / increment) + 1;
            String groupId = quotient + "";
            classifyItem.addItem(groupId);
        }

        Map<String, Integer> resultMap = classifyItem.getClassifyResult();

        for (Map.Entry<String, Integer> entry : resultMap.entrySet()) {
            Integer groupId = Integer.parseInt(entry.getKey()) * increment;
            Integer groupNum = entry.getValue();
            resultStat.put(groupId, groupNum);
        }

        return resultStat;

    }

    public static StatResult<Integer, Double> groupListLogE(List<Integer> list,
            int increment) {

        StatResult<Integer, Integer> numberResult = groupList(list, increment);
        return logResult(numberResult);
    }

    public static StatResult<Integer, Double> logResult(StatResult<Integer, Integer> intResult) {
        StatResult<Integer, Double> logResult = new StatResult<>();
        for(StatResult<Integer, Integer>.Turple turple : intResult.getTrupleList()) {
            logResult.put(turple.getKey(), Math.log(turple.getValue()));
        }

        logResult.sortByKeyReverse();
        return logResult;
    }

}
