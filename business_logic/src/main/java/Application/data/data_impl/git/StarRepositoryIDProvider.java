package Application.data.data_impl.git;

import org.eclipse.egit.github.core.IRepositoryIdProvider;

public class StarRepositoryIDProvider implements IRepositoryIdProvider {

	String repoFullName;

	public StarRepositoryIDProvider(String owner, String repoName) {
		// TODO Auto-generated constructor stub
		repoFullName = owner +"/" + repoName;
	}

	@Override
	public String generateId() {
		// TODO Auto-generated method stub
		return repoFullName;
	}

}
