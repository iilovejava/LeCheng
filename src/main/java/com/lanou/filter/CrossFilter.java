package com.lanou.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CrossFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }



    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse rsp = (HttpServletResponse) response;
        System.out.println("===========================================================================================================");
        rsp.setHeader("Access-Control-Allow-Origin", "*");
        rsp.setHeader("Access-Control-Allow-Headers", "X-Requested-With,content-type,token");
        rsp.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        chain.doFilter(request, response);
    }



    public void destroy() {

    }
}
