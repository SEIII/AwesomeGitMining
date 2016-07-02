package KeyPhrase;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultItem implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	List<String> keyPhrases;
	String id;

	public ResultItem(){

	}

	public List<String> getKeyPhrases() {
		return keyPhrases;
	}

	public void setKeyPhrases(List<String> keyPhrases) {
		this.keyPhrases = keyPhrases;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ResultItem [keyPhrases=" + keyPhrases + ", id=" + id + "]";
	}


}
