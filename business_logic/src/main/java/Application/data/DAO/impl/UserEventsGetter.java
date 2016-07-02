package Application.data.DAO.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import Application.common.DTO.EventInfo;

public class UserEventsGetter {
    
    ObjectMapper mapper = new ObjectMapper();

    public List<Integer[]> getUserEvent(String login) {

        List<Integer[]> resultList = new ArrayList<Integer[]>();
        List<Integer> eventNumber = new ArrayList<Integer>();

        List<EventInfo> events = new ArrayList<EventInfo>();

        int[] tempResult = { 0, 0, 0, 0, 0, 0, 0 };

        long day = 1000 * 60 * 60 * 24;
        long hour = 1000 * 60 * 60;
        long currentTime = System.currentTimeMillis();
        int[][] results = new int[7][24];
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                results[i][j] = 0;
            }
        }

        boolean quit = false;

        for (int page = 0; page < 10; page++) {

            if (!quit) {

                String url = "api.github.com/users/" + login
                        + "/received_events?page=" + page;
                String cmd = UrlExcuter.execURL(url);

                try {
                    events = mapper.readValue(cmd,new TypeReference<List<EventInfo>>() {});
                } catch (JsonParseException e) {
                    e.printStackTrace();
                } catch (JsonMappingException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                if (events.size() != 0) {

                    long last_time = events.get(events.size() - 1)
                            .getCreated_at().getTime();

                    if ((int) ((currentTime - last_time) / day) > 7)
                        quit = true;

                    for (EventInfo event : events) {

                        int dayValue = (int) ((currentTime
                                - event.getCreated_at().getTime()) / day);
                        if (dayValue >= 7)
                            break;

                        tempResult[dayValue]++;

                        int hourValue = (int) ((currentTime
                                - event.getCreated_at().getTime()) / hour);
                        hourValue -= 24 * dayValue;

                        results[dayValue][hourValue]++;

                    }

                }
            }
        }
        for (int i = 0; i < 7; i++)
            eventNumber.add(tempResult[i]);

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 24; j++) {
                Integer tempItem[] = new Integer[3];
                tempItem[0] = i;
                tempItem[1] = j;
                tempItem[2] = results[i][j];
                resultList.add(tempItem);
            }
        }

        return resultList;
    }

}
