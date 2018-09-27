package com.local.msvc.controller;

import java.io.IOException;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.local.msvc.model.response.Response;

/**
 * An example of creating a Rest api using Spring Annotations @RestController.
 * 
 * 
 * 
 * @author Tibu Padmakumar
 */
@RestController
public class HelloController
{
	
    /**
     * 
     */
    public HelloController()
    {
        super();
    }
    
    /**
     * Sample Endpoint which returns a Welcome Message
     * 
     * @param echo
     *            - the string to echo back
     * @return -
     * @throws IOException 
     */
    @SuppressWarnings("nls")
    @RequestMapping(value = "/echo", method = RequestMethod.GET)
    public Response index(@RequestParam(value = "echo", defaultValue = "echo this text") String echo)
    {
    	Response response = new Response();
    	response.setStatus("Success");
    	response.setResponse("Greetings from Local Spring Boot! echo=" + echo + " " + (new Date()));
        return response;
    }

    /**
     * @return -
     */
    @SuppressWarnings("nls")
    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health()
    {
        return String.format("{\"status\":\"up\", \"date\": \" " + new Date() + "\"}");
    }

}
