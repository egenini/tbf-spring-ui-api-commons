package ar.com.tbf.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import ar.com.tbf.common.share.RequestResponseAccessibility;

@Component
@Order(1)
public class RequestResponseAccessibilityFilter implements Filter {

	private final static Logger LOG = LoggerFactory.getLogger(RequestResponseAccessibilityFilter.class);
	
	@Override
	public void init(final FilterConfig filterConfig) throws ServletException {
		LOG.info("Initializing filter :{}", this);
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest  servletRequest  = (HttpServletRequest) request;
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		
		LOG.info( "Logging Request  {} : {}", servletRequest.getMethod(), servletRequest.getRequestURI());
		
		RequestResponseAccessibility.setRequestResponse( servletRequest, servletResponse );
		
		 chain.doFilter(request, response);
		 
		 LOG.info( "Logging Response :{}", servletResponse.getContentType());
	}
	
	@Override
	public void destroy() {
		LOG.warn("Destructing filter :{}", this);
	}
}
