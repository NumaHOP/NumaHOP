package fr.progilone.pgcn.domain.dto.audit;

import fr.progilone.pgcn.domain.train.Train;

public class AuditTrainRevisionDTO {

	private int rev;

	private long timestamp;

	private String username;

	private String identifier;

	private String label;

	private Train.TrainStatus status;

	public int getRev() {
		return rev;
	}

	public void setRev(final int rev) {
		this.rev = rev;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(final long timestamp) {
		this.timestamp = timestamp;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(final String username) {
		this.username = username;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(final String identifier) {
		this.identifier = identifier;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}

	public Train.TrainStatus getStatus() {
		return status;
	}

	public void setStatus(final Train.TrainStatus status) {
		this.status = status;
	}

}
