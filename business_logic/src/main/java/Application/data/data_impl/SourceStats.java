package Application.data.data_impl;

import java.util.List;

import org.eclipse.egit.github.core.Repository;
import org.springframework.cache.annotation.Cacheable;

public interface SourceStats {

	List<Repository> statUserCreatedRepository(String login);

}