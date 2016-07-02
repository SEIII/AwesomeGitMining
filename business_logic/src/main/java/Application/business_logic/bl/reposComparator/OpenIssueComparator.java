package Application.business_logic.bl.reposComparator;

import java.util.Comparator;

import Application.common.info.ReposInfo;

public class OpenIssueComparator implements Comparator<ReposInfo>{

    @Override
    public int compare(ReposInfo o1, ReposInfo o2) {
        return Integer.compare(o1.getOpen_issues_count(), o2.getOpen_issues_count());
    }

}
