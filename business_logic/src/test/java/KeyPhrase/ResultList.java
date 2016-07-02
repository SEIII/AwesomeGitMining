package KeyPhrase;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultList implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	List<ResultItem> documents;


	public List<ResultItem> getDocuments() {
		return documents;
	}


	public void setDocuments(List<ResultItem> documents) {
		this.documents = documents;
	}


	@Override
	public String toString() {
		return "ResultList [documents=" + documents + "]";
	}

}
