package de.htw.ai.kbe.myFirstServlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

public class EchoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private String mySignature = null;

	public void init(ServletConfig servletConfig) throws ServletException {
		this.mySignature = servletConfig.getInitParameter("signature");
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// alle Parameter (keys)
		Enumeration<String> paramNames = request.getParameterNames();

		String responseStr = "";
		String param = "";
		while (paramNames.hasMoreElements()) {
			param = paramNames.nextElement();
			responseStr = responseStr + param + "=" + request.getParameter(param) + "\n";
		}
		response.setContentType("text/plain");
		try (PrintWriter out = response.getWriter()) {
			responseStr += "\n" + mySignature;
			out.println(responseStr);
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/plain");
		ServletInputStream inputStream = request.getInputStream();
		byte[] inBytes = IOUtils.toByteArray(inputStream);
		try (PrintWriter out = response.getWriter()) {
			out.println(new String(inBytes));
		}
	}
	
	protected String getSignature () {
		return this.mySignature;
	}
}