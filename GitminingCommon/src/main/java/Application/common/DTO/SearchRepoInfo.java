package Application.common.DTO;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SearchRepoInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	int total_count;
	boolean incomplete_results;
	List<BasicRepositoryInfo> items;
	public int getTotal_count() {
		return total_count;
	}
	public void setTotal_count(int total_count) {
		this.total_count = total_count;
	}
	public boolean isIncomplete_results() {
		return incomplete_results;
	}
	public void setIncomplete_results(boolean incomplete_results) {
		this.incomplete_results = incomplete_results;
	}
	public List<BasicRepositoryInfo> getItems() {
		return items;
	}
	public void setItems(List<BasicRepositoryInfo> items) {
		this.items = items;
	}


}
