package Application.data.data_impl.git;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.eclipse.egit.github.core.event.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import Application.AppContextSupport;
import Application.common.DTO.WatchUserInfo;
import Application.data.DAO.sql.SQLTemplate;

@Component
public class GitEventInfoImpl {

	@Autowired
	SQLTemplate template;



	public List<Event> getWatcherEventList(String account){

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

		return answer;
	}

}
