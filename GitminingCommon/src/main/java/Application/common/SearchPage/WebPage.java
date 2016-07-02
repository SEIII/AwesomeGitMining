package Application.common.SearchPage;

import java.util.ArrayList;
import java.util.List;

import Application.common.info.AbstractInfo;

public class WebPage<T extends AbstractInfo> extends SearchPage<T> {

    public WebPage(SearchPage<T> onePage) {
        super(null, null, 0);

        contentList = onePage.getAllContent();
        totalListSize = contentList.size();
        searchKey = onePage.searchKey;
    }

    @Override
    protected boolean isLocalPage() {
        return true;
    }

}
