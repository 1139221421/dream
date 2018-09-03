package com.lxl.listner;

import ch.qos.logback.core.util.SystemInfo;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener(value = "systemListener")
public class SystemListener implements ServletContextListener, HttpSessionListener, HttpSessionAttributeListener {

    private Logger logger = Logger.getLogger(this.getClass());
    private ServletContext servletContext;
    private static ApplicationContext context;
    private SystemInfo systemInfo;
    boolean flag = true;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContext = servletContextEvent.getServletContext();
        initSpring();
        initUploadDir();
    }


    private void initSpring() {
        logger.info("初始化spring上下文...");
        context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        servletContext.setAttribute("springBeansContext", context);
        logger.info("初始化spring上下文...ok!");
    }

    private void initUploadDir() {
        logger.info("初始化上传目录...");
//        UploadService uploadService = context.getBean(UploadService.class);
//        uploadService.setRealPath(servletContext.getInitParameter("upload_path"));
//        logger.info("上传目录为: " + uploadService.getRealPath());
//        try {
//            uploadService.init();
//            //            systemInfo.setUploadDirIsOk(true);
//        } catch (FileNotFoundException e) {
//            logger.error("初始化上传目录...error，指定目录未找到！");
//        }
        logger.info("初始化上传目录...ok!");
    }



    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        flag = false;
    }


    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
    }
}
