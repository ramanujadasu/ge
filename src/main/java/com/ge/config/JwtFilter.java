package com.ge.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ge.exception.ResponseException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;

public class JwtFilter extends GenericFilterBean {

	private Logger logger = LoggerFactory.getLogger(JwtFilter.class);

	@Override
	public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain)
			throws IOException, ServletException {

		final HttpServletRequest request = (HttpServletRequest) req;
		final HttpServletResponse response = (HttpServletResponse) res;
		final String token = request.getHeader("token");

		if ("OPTIONS".equals(request.getMethod())) {
			response.setStatus(HttpServletResponse.SC_OK);
			chain.doFilter(req, res);
		} else {
			logger.debug("doFilter: token: {}", token);
			if (token == null || (token.length() == 0)) {
				setUnauthorizedResponse(response, "Missing or invalid Token header");
				return;
			}
			try {
				final Claims claims = Jwts.parser().setSigningKey("secretkey").parseClaimsJws(token).getBody();
				request.setAttribute("claims", claims);
			} catch (final SignatureException e) {
				setUnauthorizedResponse(response, "Invalid token.");
				return;
			} catch (final Exception e) {
				setUnauthorizedResponse(response, "Invalid token.");
				return;
			}
			chain.doFilter(req, res);
		}
	}

	public void setUnauthorizedResponse(HttpServletResponse response, String errorMessage) throws IOException {
		logger.error("Exception occured: {}", errorMessage);
		response.setStatus(HttpServletResponse.SC_OK);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		
		out.println(getJsonObject(ResponseException.getTokenExceptionDTO(errorMessage)));
	}

	public String getJsonObject(Object objectData) throws IOException {

		ObjectMapper mapperObj = new ObjectMapper();
		String personalInfoJson = null;
		personalInfoJson = mapperObj.writeValueAsString(objectData);
		return personalInfoJson;
	}

}
