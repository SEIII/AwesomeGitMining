package Application.common.DTO;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EventInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	/*event产生日期*/
	Date created_at;

	@Override
	public String toString() {
		return "EventInfo [created_at=" + created_at + "]";
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
}
