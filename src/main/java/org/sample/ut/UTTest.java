package org.sample.ut;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.core.ManagedServlet;
import io.undertow.servlet.handlers.ServletHandler;
import io.undertow.servlet.spec.ServletContextImpl;

import javax.servlet.ServletException;

/**
 * Created by mash on 13/12/25.
 */
public class UTTest {
    public static void main(String[] args) throws ServletException {
        final ServletContainer container = ServletContainer.Factory.newInstance();
        DeploymentInfo servletBuilder = new DeploymentInfo()
                .setClassLoader(UTTest.class.getClassLoader())
                .setContextPath("/myapp")
                .setDeploymentName("myapp.war")
                .addServlets(
                        new ServletInfo("MyServlet", SimpleServlet.class)
                                .addMapping("/hello"));

        DeploymentManager manager = container.addDeployment(servletBuilder);
        manager.deploy();

        Undertow.builder().addListener(8888, "localhost").setHandler(manager.start()).build().start();
    }
}
