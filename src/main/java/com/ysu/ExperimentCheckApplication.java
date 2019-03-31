package com.ysu;/**
 * Created by 韩建国 on 2019/2/17
 */

import org.apache.commons.logging.LogFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import java.lang.management.ManagementFactory;
import java.util.Set;

/**
 * @Auther: han jianguo
 * @Date: 2019/2/17 21:46
 * @Description: 项目启动类
 **/

@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@EnableConfigurationProperties
@SpringBootApplication
public class ExperimentCheckApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ExperimentCheckApplication.class);
    }

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        int port = getTomcatPort();
        servletContext.setInitParameter("server.port", String.valueOf(port));
        this.logger = LogFactory.getLog(this.getClass());
        WebApplicationContext rootAppContext = this.createRootApplicationContext(servletContext);
        if (rootAppContext != null) {
            servletContext.addListener(new ContextLoaderListener(rootAppContext) {
                @Override
                public void contextInitialized(ServletContextEvent event) {
                }
            });
        } else {
            this.logger.debug("No ContextLoaderListener registered, as createRootApplicationContext() did not return an application context");
        }

    }

    public int getTomcatPort() {
        int port;
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                    Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            String portStr = objectNames.iterator().next().getKeyProperty("port");
            port = Integer.valueOf(portStr);
        } catch (Exception e) {
            port = 8080;
        }
        return port;
    }

}
