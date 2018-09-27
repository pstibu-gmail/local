package com.local.msvc.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	@SuppressWarnings("javadoc")
	@Value("${spring.profiles.active:local}")
	String profile;

	@SuppressWarnings("javadoc")
	@Value("${java.docs.url:null}")
	String docsUrl;
	
	/**
	 * @param request
	 *            -
	 * @param name
	 *            -
	 * @param model
	 *            -
	 * @return -
	 */
	@RequestMapping("/")
	public String greetings(HttpServletRequest request,
			@RequestParam(value = "name", required = false, defaultValue = "Local") String name, Model model) {
		StringBuffer requesturi = request.getRequestURL();
		String applicationURl = requesturi.toString().replaceAll("http", "https");
		if ("local".equalsIgnoreCase(this.profile)) { 
			applicationURl = requesturi.toString(); 
		}
		model.addAttribute("api", applicationURl.toString() + "api");
		model.addAttribute("health", applicationURl.toString() + "health");
		model.addAttribute("docs", this.docsUrl);
		return "index";
	}

	/**
	 * 
	 * @param request
	 *            - HttpServletRequest
	 * @param response
	 *            - HttpServletResponse
	 * @return - Model View
	 * @throws Exception
	 *             - Exception
	 */
	@RequestMapping("/docs")
	protected ModelAndView docs(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return new ModelAndView("redirect:/javadoc/index.html"); //$NON-NLS-1$

	}

	/**
	 * @param request
	 *            -
	 * @param response
	 *            -
	 * @throws IOException
	 *             -
	 */
	@RequestMapping("/api")
	public @ResponseBody void api(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String applicationURl = getApplicationUrl(request);
		response.sendRedirect(applicationURl.replace("/api", "/swagger-ui.html")); //$NON-NLS-1$//$NON-NLS-2$

	}

	/**
	 * 
	 * @param request
	 * @return - Application URL
	 */
	private String getApplicationUrl(final HttpServletRequest request) {

		String applicationURl = request.getRequestURL().toString().replaceAll("http", "https");//$NON-NLS-1$ //$NON-NLS-2$
		if ("local".equalsIgnoreCase(this.profile)) { //$NON-NLS-1$
			applicationURl = request.getRequestURL().toString(); // localhost
																	// support
																	// for http
		}
		return applicationURl;
	}
}
