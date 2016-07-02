package KeyPhrase;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Document implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	List<DocumentItem> documents;

	public Document(List<DocumentItem> documents){
		this.documents = documents;
	}

	public List<DocumentItem> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentItem> documents) {
		this.documents = documents;
	}

}
