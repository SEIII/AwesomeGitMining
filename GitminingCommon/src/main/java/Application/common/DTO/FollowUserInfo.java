package Application.common.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class FollowUserInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	String avatarUrl;

	String login;

	String name;

	String type;

	String created;

	int followers;

	int publicRepos;

}
