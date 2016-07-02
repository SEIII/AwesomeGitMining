package Application.common.DTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.event.Event;

import Application.common.util.StrConverter;

public class GitUserTotalInfo {

    User user;

    List<Event> lastestEvent;

    List<UserRelatedRepository> createdRepos;

    List<UserRelatedRepository> contributedRepos;
    
    public GitUserTotalInfo() {
        
        createdRepos = Collections.synchronizedList(new ArrayList<>());
        contributedRepos = Collections.synchronizedList(new ArrayList<>());
        
    }
    

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Event> getLastestEvent() {
        return lastestEvent;
    }

    public void setLastestEvent(List<Event> lastestEvent) {
        this.lastestEvent = lastestEvent;
    }

    public List<UserRelatedRepository> getCreatedRepos() {
        return createdRepos;
    }

    public void setCreatedRepos(List<UserRelatedRepository> createdRepos) {
        this.createdRepos = createdRepos;
    }

    public List<UserRelatedRepository> getContributedRepos() {
        return contributedRepos;
    }

    public void setContributedRepos(
            List<UserRelatedRepository> contributedRepos) {
        this.contributedRepos = contributedRepos;
    }

}
