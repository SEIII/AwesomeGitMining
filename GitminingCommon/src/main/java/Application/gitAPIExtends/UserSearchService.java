package Application.gitAPIExtends;

import static org.eclipse.egit.github.core.client.IGitHubConstants.CHARSET_UTF8;
import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_LEGACY;
import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_SEARCH;
import static org.eclipse.egit.github.core.client.IGitHubConstants.SEGMENT_USER;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.egit.github.core.IResourceProvider;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.PagedRequest;
import org.eclipse.egit.github.core.service.GitHubService;
import org.springframework.stereotype.Component;

import Application.gitAPIExtends.githubVO.SearchUser;

public class UserSearchService extends GitHubService {

    public UserSearchService() {
    }

    public UserSearchService(GitHubClient client) {
        super(client);
    }

    public List<SearchUser> searchUser(String query) {
        String uri = SEGMENT_LEGACY + SEGMENT_USER + SEGMENT_SEARCH + "/";
        try {
            String encodedQuery = URLEncoder.encode(query, CHARSET_UTF8)
                    .replace("+", "%20") //$NON-NLS-1$ //$NON-NLS-2$
                    .replace(".", "%2E");
            uri += encodedQuery;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        PagedRequest<SearchUser> request = createPagedRequest();

        request.setUri(uri);
        request.setType(SearchUserContainer.class);

        List<SearchUser> users = new ArrayList<>(
                createPageIterator(request).next());
        
        for(SearchUser user:users) {
            user.setType("User");
        }

        return users;

    }

    private static class SearchUserContainer
            implements IResourceProvider<SearchUser> {

        private List<SearchUser> users;

        /**
         * @see org.eclipse.egit.github.core.IResourceProvider#getResources()
         */
        public List<SearchUser> getResources() {
            return users;
        }
    }

}
