package com.apress.springrecipes.court.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class WelcomeInterceptor extends HandlerInterceptorAdapter /*implements HandlerInterceptor*/
{

	/*
	In the preHandle() method of this interceptor, you record the start time and save it to a request
	attribute. This method should return true, allowing DispatcherServlet to proceed with request
	handling. Otherwise, DispatcherServlet assumes that this method has already handled the request, so
	DispatcherServlet returns the response to the user directly. Then, in the postHandle() method, you load
	the start time from the request attribute and compare it with the current time.
	*/
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);
		return true;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		long startTime = (Long) request.getAttribute("startTime");
		request.removeAttribute("startTime");
		long endTime = System.currentTimeMillis();
		modelAndView.addObject("handlingTime", endTime - startTime);
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
