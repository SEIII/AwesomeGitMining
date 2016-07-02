package APITest.AxeContent;


public class Branch {

	Contributor_ID _id ;
	String name = "";
	Commit commit ;
	String fn = "";
	public Contributor_ID get_id() {
		return _id;
	}
	public void set_id(Contributor_ID _id) {
		this._id = _id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Commit getCommit() {
		return commit;
	}
	public void setCommit(Commit commit) {
		this.commit = commit;
	}
	public String getFn() {
		return fn;
	}
	public void setFn(String fn) {
		this.fn = fn;
	}
	
	
	@Override
	public String toString() {
		return "Branch [_id=" + _id.toString() + ", name=" + name + ", commit=" + commit.toString()
				+ ", fn=" + fn + "]";
	}
	
	
	
}
