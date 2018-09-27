package com.local.msvc.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.local.msvc.model.error.EventError;

/**
 * 
 * @author Tibu Padmakumar
 */
@RestController
public class ErrorResponseController
        implements ErrorController
{
    private static final String PATH = "/error"; //$NON-NLS-1$

    @Value("${debug:false}")
    private boolean             debug;

    @Autowired
    private ErrorAttributes     errorAttributes;

    /**
     * @param request -
     * @param response -
     * @return -
     */
    @RequestMapping(value = PATH)
    EventError error(HttpServletRequest request, HttpServletResponse response)
    {
        // Appropriate HTTP response code (e.g. 404 or 500) is automatically set by Spring.
        // Here we just define response body.
    	// Here we are defaulting all the response to 200
    	int currentStatus = response.getStatus();
    	response.setStatus(HttpServletResponse.SC_OK);
        return new EventError(currentStatus, getErrorAttributes(request, this.debug));
    }

    @Override
    public String getErrorPath()
    {
        return PATH;
    }

    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace)
    {
        RequestAttributes requestAttributes = new ServletRequestAttributes(request);
        return this.errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
    }
}
