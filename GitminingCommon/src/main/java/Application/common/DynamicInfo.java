package Application.common;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.eclipse.egit.github.core.event.Event;


public class DynamicInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String avatar_url;

	String login;

	String dateString;

	String content;

	String eventType;

	String repoName;



	public DynamicInfo(Event e) {
		// TODO Auto-generated constructor stub

		this.avatar_url = e.getActor().getAvatarUrl();
		this.login = e.getActor().getLogin();
		Date date = e.getCreatedAt();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		this.dateString = dateFormat.format(date);
		this.repoName = e.getRepo().getName();
		this.eventType = e.getType();

	}

	public String getAvatar_url() {
		return avatar_url;
	}

	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}


}
