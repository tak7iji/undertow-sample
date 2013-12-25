package org.sample.ut;

import io.undertow.Undertow;
import io.undertow.servlet.api.DeploymentInfo;
import io.undertow.servlet.api.DeploymentManager;
import io.undertow.servlet.api.ServletContainer;
import io.undertow.servlet.api.ServletInfo;
import io.undertow.servlet.handlers.ServletHandler;

import javax.servlet.ServletException;

/**
 * Created by mash on 13/12/25.
 */
public class UTTest {
    public static void main(String[] args) {
        final ServletContainer container = ServletContainer.Factory.newInstance();
        DeploymentInfo servletBuilder = new DeploymentInfo()
                .setClassLoader(UTTest.class.getClassLoader())
                .setContextPath("/myapp")
                .setDeploymentName("test.war")
                .addServlets(
                        new ServletInfo("MyServlet", SimpleServlet.class)
                                .addMapping("/"));

        DeploymentManager manager = container.addDeployment(servletBuilder);
        manager.deploy();

        try {
            Undertow.builder().addListener(8888, "localhost").setHandler(manager.start()).build().start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
