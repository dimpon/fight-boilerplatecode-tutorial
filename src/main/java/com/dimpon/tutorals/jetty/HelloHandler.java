package com.dimpon.tutorals.jetty;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

/**
 * @author Dmitrii Ponomarev
 */

public class HelloHandler extends AbstractHandler {
	final String greeting;
	final String body;

	public HelloHandler() {
		this("Hello World");
	}

	public HelloHandler(String greeting) {
		this(greeting, null);
	}

	public HelloHandler(String greeting, String body) {
		this.greeting = greeting;
		this.body = body;
	}

	@Override
	public void handle(String target,
			Request baseRequest,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException,
			ServletException {

		if (response.getStatus() == HttpServletResponse.SC_SERVICE_UNAVAILABLE) {
            return;
        }



		response.setContentType("text/html; charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);

		System.out.println("handle...");

		PrintWriter out = response.getWriter();

		out.println("<h1>" + greeting + "</h1>");
		if (body != null) {
			out.println(body);
		}

		baseRequest.setHandled(true);
	}
}