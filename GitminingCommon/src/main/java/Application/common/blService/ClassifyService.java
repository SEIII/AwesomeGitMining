package Application.common.blService;

import java.util.Map;

import Application.common.ClassifyItem;


public interface ClassifyService {

	public ClassifyItem classifyRepoLanguage(String key);

	public ClassifyItem classifyUserLanguage(String key);

	public ClassifyItem classifyUserType(String key);

	public ClassifyItem classifyRepoDate(String key);

	public ClassifyItem classifyUserDate(String key);

	public Map<String, Integer> classifyRepoExp(String key);

	public Map<String, Integer> classifyUserExp(String key);

}
