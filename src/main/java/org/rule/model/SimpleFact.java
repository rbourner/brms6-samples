package org.rule.model;


public class SimpleFact {

	private String id;
	private String status;
	
	public SimpleFact(String anId, String aStatus) {
		this.id = anId;
		this.status = aStatus;
	}

	public String getId() {
		return id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()+" (id="+id+", status="+status+")";
	}
}
