package com.xcms.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(filterName = "DoFilter")
public class DoFilter implements Filter {
    public DoFilter(){
        System.out.println("过滤器构造");
    }
    public void destroy() {
        System.out.println("过滤器销毁");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");//将编码改为utf-8
        response.setContentType("text/html;charset=utf-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        System.out.println("过滤器初始化");
    }

}
