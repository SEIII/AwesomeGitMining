package Application.data.data_impl.git;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import org.eclipse.egit.github.core.client.PageIterator;
import org.eclipse.egit.github.core.event.Event;
import org.eclipse.egit.github.core.service.EventService;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import Application.data.DAO.common.GithubServiceFactory;

@Component
@Scope("prototype")
public class StatEventInfoObj {


	@Async
	public Future<List<Event>> getSingleUserEvent(String login){


		EventService eventService = GithubServiceFactory.getEventService();
		PageIterator<Event> eventItr = eventService.pageUserEvents(login);

		List<Event> resultList = new ArrayList<Event>();
		List<Event> eventList = ((List<Event>) eventItr.next());

		if(eventList == null || eventList.size()==0)
			return new AsyncResult<List<Event>>(null);

		System.out.println("?");

		if(eventList.size()<3){
			for(Event e: eventList)
				resultList.add(e);
		}else{
			for(int i=0; i<3; i++)
				resultList.add(eventList.get(i));
		}

		return new AsyncResult<List<Event>>(resultList);
	}

}
