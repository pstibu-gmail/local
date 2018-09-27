package com.local.msvc.model.error;

import java.util.Map;

import com.local.msvc.model.error.Error;

/**
 * 
 * @author Tibu Padmakumar
 */
public class EventError
{
    /**
     * status
     */
    private String status;
    /**
     * 
     */
    /**
     * response
     */
    private String response;
    /**
     * 
     */
    /**
     * error
     */
    private Error error;

    public String getStatus() {
		return status;
	}

	public String getResponse() {
		return response;
	}

	public Error getError() {
		return error;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public void setError(Error error) {
		this.error = error;
	}

	/**
     * @param status
     *            -
     * @param errorAttributes
     *            -
     */
    public EventError(int errorCode, Map<String, Object> errorAttributes)
    {
    	super();
        this.status = "Failed";
        this.response = "null";
        Error err = new Error();
        err.setCode(String.valueOf(errorCode)); //$NON-NLS-1$
        err.setMessage((String) errorAttributes.get("error") + "::" + (String) errorAttributes.get("message"));//$NON-NLS-1$
        this.setError(err);
    }
}
