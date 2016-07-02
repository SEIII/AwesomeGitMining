package Application.business_logic.bl.userComparator;

import java.util.Comparator;

import Application.common.info.UserInfo;

public class ContributedComparator implements Comparator<UserInfo> {

	@Override
	public int compare(UserInfo o1, UserInfo o2) {
		// TODO Auto-generated method stub
		return Integer.compare(o1.getContributedRepoNum(), o2.getContributedRepoNum());
	}

}
