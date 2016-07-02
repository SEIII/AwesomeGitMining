package APITest.AxeContent;

public class Contributor_ID {

	long  timestamp;
	long machineIdentifier;
	int processIdentifier;
	long counter;
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public long getMachineIdentifier() {
		return machineIdentifier;
	}
	public void setMachineIdentifier(long machineIdentifier) {
		this.machineIdentifier = machineIdentifier;
	}
	public int getProcessIdentifier() {
		return processIdentifier;
	}
	public void setProcessIdentifier(int processIdentifier) {
		this.processIdentifier = processIdentifier;
	}
	public long getCounter() {
		return counter;
	}
	public void setCounter(long counter) {
		this.counter = counter;
	}
	@Override
	public String toString() {
		return "Contributor_ID [timestamp=" + timestamp
				+ ", machineIdentifier=" + machineIdentifier
				+ ", processIdentifier=" + processIdentifier + ", counter="
				+ counter + "]";
	}
	
	
}
