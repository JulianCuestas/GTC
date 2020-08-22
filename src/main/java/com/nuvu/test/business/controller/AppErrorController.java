package com.nuvu.test.business.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Clase para controlar los errores de la aplicación
 * @author jcuestas
 *
 */
@Controller
public class AppErrorController implements ErrorController {
	
	public static final String ERROR_PATH = "errors/error";
	
	@RequestMapping("/error")
	public ModelAndView handleError(HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("errors/error");

		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
		modelAndView.addObject("statusCode", statusCode  == null ? "N/A" : statusCode);
		modelAndView.addObject("msjException", exception == null ? "N/A" : exception.getMessage());
		return modelAndView;
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
