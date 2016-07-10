package Application.data.data_impl.git;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.egit.github.core.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.common.DynamicInfo;
import Application.common.DTO.WatchUserInfo;
import Application.common.data_service.GitEventInfoService;
import Application.data.DAO.sql.SQLTemplate;

@Component
public class GitEventInfoImpl implements GitEventInfoService {

	@Autowired
	SQLTemplate template;

	Map<String, String> eventTypeMap;

	public List<DynamicInfo> getWatcherEventList(String account){

		StatEventInfoObj statEventInfoObj = AppContextSupport.getApplicationContext()
                .getBean(StatEventInfoObj.class);

		List<WatchUserInfo> watchList = template.getUserWatcherListInfo(account);
		List<Future<List<Event>>> futureEventList = new ArrayList<Future<List<Event>>>();
		List<Event> answer = new ArrayList<Event>();

		for(WatchUserInfo watcher: watchList)
			System.out.println(watcher.getGitid());

		System.out.println("begin to get");

		for(WatchUserInfo watch: watchList){
			System.out.println("process single item");
			Future<List<Event>> futureEvent = statEventInfoObj.getSingleUserEvent(watch.getGitid());
			futureEventList.add(futureEvent);
		}

		while(true){
			boolean result = true;
            for (Future<List<Event>> futureEvent : futureEventList)
                result = result && futureEvent.isDone();
            if (result)
                break;

            try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for(Future<List<Event>> futureEvent: futureEventList){

			try {
				List<Event> events = futureEvent.get();
				if(events!=null){
					for(Event e: events)
						answer.add(e);
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		List<DynamicInfo> dynamicInfo = new ArrayList<DynamicInfo>();



		if(eventTypeMap == null){

			eventTypeMap = new HashMap<String, String>();

			Properties pro = new Properties();
	        Reader in;
	        try {
	            in = new FileReader("config/eventType.properties");
	            pro.load(in);
	            in.close();

	            for(Entry<Object, Object> entry: pro.entrySet()){
	            	String key = (String) entry.getKey();
	            	String value = (String) entry.getValue();
	            	eventTypeMap.put(key, value);
	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
		}

		for(Map.Entry<String, String> entry: eventTypeMap.entrySet())
			System.out.println(entry.getKey()+":"+entry.getValue());


		for(Event e: answer){
			DynamicInfo dyInfo = new DynamicInfo(e);
			if(eventTypeMap.containsKey(e.getType())){
				String value = eventTypeMap.get(e.getType());
				String[] tempList = value.split(" ");
				StringBuilder builder = new StringBuilder();

				builder.append(tempList[0]);
				builder.append(e.getRepo().getName());
				builder.append(tempList[1]);

				dyInfo.setContent(builder.toString());

			}else{
				dyInfo.setContent("和"+e.getRepo().getName()+"仓库做了一些py交易");
			}
			dynamicInfo.add(dyInfo);
		}


		return dynamicInfo;
	}

}
