package Application.common;

import java.util.List;

/**
 * @author lenovo
 *
 */
public class DynamicAllInfo {

	String dateString;

	List<DynamicInfo> dynamics;

	public DynamicAllInfo(String dateString, List<DynamicInfo> dynamics) {
		super();
		this.dateString = dateString;
		this.dynamics = dynamics;
	}

	public String getDateString() {
		return dateString;
	}

	public void setDateString(String dateString) {
		this.dateString = dateString;
	}

	public List<DynamicInfo> getDynamics() {
		return dynamics;
	}

	public void setDynamics(List<DynamicInfo> dynamics) {
		this.dynamics = dynamics;
	}



}
