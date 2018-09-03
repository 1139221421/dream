/**
 * @Project authority
 * @Title SystemFilter.java
 * @Package net.elementech.authority.action.filter
 * @Author BenNick
 * @Date 2017年03月24日 09:56
 * @Copyright: 2017 www.elementech.net Inc. All rights reserved.
 * @Version V1.0
 */
package com.lxl.filter;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * 系统拦截器
 */
@WebFilter(filterName = "systemFilter", urlPatterns = {"*.do", "*.htm"})
public class SystemFilter implements Filter {
    Logger logger = Logger.getLogger(this.getClass());
    private static ApplicationContext applicationContext;
    private String contextPath = "";
    private ServletContext servletContext;
    //    public static HashSet<String> blackList = new HashSet<String>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        contextPath = servletContext.getContextPath();
        servletContext.setAttribute("contextPath", contextPath);
        applicationContext = (ApplicationContext) servletContext.getAttribute("springBeansContext");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setHeader("Content-Type", "text/html;charset=UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        chain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
