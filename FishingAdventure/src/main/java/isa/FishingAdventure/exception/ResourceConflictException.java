package isa.FishingAdventure.exception;

// Custom izuzetak
public class ResourceConflictException extends RuntimeException {
	private static final long serialVersionUID = 1791564636123821405L;

	private Integer resourceId;

	public ResourceConflictException(Integer resourceId, String message) {
		super(message);
		this.setResourceId(resourceId);
	}

	public Integer getResourceId() {
		return resourceId;
	}

	public void setResourceId(Integer resourceId) {
		this.resourceId = resourceId;
	}

}
