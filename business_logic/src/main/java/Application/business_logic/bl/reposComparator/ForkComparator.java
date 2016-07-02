package Application.business_logic.bl.reposComparator;

import java.util.Comparator;

import Application.common.info.ReposInfo;


public class ForkComparator implements Comparator<ReposInfo>{

	@Override
	public int compare(ReposInfo one, ReposInfo other) {
		return Integer.compare(one.getForks_count(), other.getForks_count());
	}
	
}
