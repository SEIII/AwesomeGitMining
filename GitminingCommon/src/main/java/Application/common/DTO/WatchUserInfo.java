package Application.common.DTO;

import java.io.Serializable;

import lombok.Data;

@Data
public class WatchUserInfo implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String account;

	String gitid;

	String avatarUrl;

	int repoCollection;

	int userCollection;
	
	boolean watched;
	
	@Override
	public boolean equals(Object obj) {
        if(obj instanceof WatchUserInfo) {
            WatchUserInfo other = (WatchUserInfo)obj;
            if(other.account.equals(account) && other.gitid.equals(gitid)) {
                return true;
            }
            return false;
        }
        return false;
	}

}
