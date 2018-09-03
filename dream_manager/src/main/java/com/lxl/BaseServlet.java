package com.lxl;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseServlet extends HttpServlet {

    public Logger logger = Logger.getLogger(this.getClass());


    private void noSupportMethod(HttpServletResponse response) throws ServletException, IOException {
        throw new ServletException();
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanByName(String beanName) {
        return (T) ((ApplicationContext) getServletContext().getAttribute("springBeansContext")).getBean(beanName);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBeanByClass(Class cl) {
        return (T) ((ApplicationContext) getServletContext().getAttribute("springBeansContext")).getBean(cl);
    }

    @Override
    protected void doHead(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doOptions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doTrace(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        noSupportMethod(response);
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String url) throws IOException, ServletException {
        request.getRequestDispatcher(url).forward(request, response);
    }

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }


}