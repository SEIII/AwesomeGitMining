package Application.common.praser;

import org.eclipse.egit.github.core.IRepositoryIdProvider;

public class SimpleRepoProvider implements IRepositoryIdProvider{

    String fullName;
    public SimpleRepoProvider(String owner,String name) {
        fullName = owner+"/"+name;
    }
    
    @Override
    public String generateId() {
        return fullName;
    }
    
}

