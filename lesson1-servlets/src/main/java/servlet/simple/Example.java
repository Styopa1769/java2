package servlet.simple;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Example {
    public static void main(String[] args) {
        HelloWorldServlet helloWorldServlet = new HelloWorldServlet();
        GoodByeServlet goodByeServlet = new GoodByeServlet();

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(helloWorldServlet), "/hello");
        context.addServlet(new ServletHolder(goodByeServlet), "/bye");

        Server server = new Server(8080);
        server.setHandler(context);

        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
