package eatery.rest;

public class AppResponse {

	private String message;
	private String status;
	private Object payLoad;
	
	public final static String ERROR = "error";
	
	public AppResponse(){
		this.status = "success";
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}
	
	
}
