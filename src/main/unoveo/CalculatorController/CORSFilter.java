
package CalculatorController;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
/**
 * Servlet Filter implementation class CORSFilter
 */



//@WebFilter("/*")
public class CORSFilter implements Filter {

    public CORSFilter() {
        // TODO Auto-generated constructor stub
    }

   
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        System.out.println("CORSFilter HTTP Request: " + request.getMethod());
        // Authorize (allow) all domains to consume the content

        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST");
//        ((HttpServletResponse) servletResponse).setHeader("Access-Control-Allow-Headers", " Origin, X-Requested-With, Content-Type, Accept");
//        ((HttpServletResponse) servletResponse).setHeader("Connection", "keep-alive");
//
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        // For HTTP OPTIONS verb/method reply with ACCEPTED status code -- per CORS handshake
        if (request.getMethod().equals("OPTIONS")) {
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            return;
        }
        // pass the request along the filter chain
        chain.doFilter(request, servletResponse);
    }
    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
