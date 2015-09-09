package com.invivoo.thymeleaf.servlet;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.context.WebContext;

@WebServlet("/")
public class ThymeleafServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ThymeleafServlet() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		WebContext ctx = new WebContext(request, response, this.getServletContext(), request.getLocale());

		Collections.list(request.getParameterNames())
				.forEach(parameter -> ctx.setVariable(parameter, request.getParameter(parameter)));

		TemplateEngineProvider.getTemplateEngine().process(request.getServletPath().substring(0), ctx,
				response.getWriter());

	}
}
