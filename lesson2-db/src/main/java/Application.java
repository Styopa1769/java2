import Repositories.AgentRepository;
import Repositories.CustomerRepository;
import Servlets.AgentServlet;
import Servlets.CustomerServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Application {
    public static void main(String[] args) {
        CustomerServlet customerServlet = new CustomerServlet(new CustomerRepository());
        AgentServlet agentServlet = new AgentServlet(new AgentRepository());
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(customerServlet), "/customer");
        context.addServlet(new ServletHolder(agentServlet), "/agent");
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
