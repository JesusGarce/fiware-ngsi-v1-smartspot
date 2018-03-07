package eu.hopu;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.concurrent.ExecutionException;

@WebListener
public class Initializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ManageDevices manageDevices = new ManageDevices();
        try {
            manageDevices.createDevice("http://172.18.0.1:1026");
            System.out.println("Device creado.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //System.out.println("Entramos a contextDestroyed");
        ManageDevices manageDevices = new ManageDevices();

        try {
            manageDevices.deleteDevice("http://172.18.0.1:1026");
            System.out.println("Device borrado.");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}