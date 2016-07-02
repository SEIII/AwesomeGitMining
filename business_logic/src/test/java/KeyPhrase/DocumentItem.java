package KeyPhrase;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DocumentItem implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	String language;
	String id;
	String text;


	public DocumentItem(String text, int id){
		language = "en";
		this.id = id+"";
		this.text = text;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	@Override
	public String toString() {
		return "DocumentItem [language=" + language + ", id=" + id + ", text=" + text + "]";
	}

}
