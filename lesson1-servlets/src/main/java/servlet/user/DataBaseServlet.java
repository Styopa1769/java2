package servlet.user;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DataBaseServlet extends HttpServlet {

    DataBase dataBase;

    public DataBaseServlet(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws ServletException, IOException {
        long id = Long.parseLong(request.getParameter("id"));
        if ( id >= 0 ){
            User user = dataBase.getUserById(id);
            if (user != null){
                response.getWriter().println(String.format("Hello %s",user.getFirstName()));
            } else {
                response.getWriter().println("User not found");
            }
        }

        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        User user = new User(firstName,lastName);
        dataBase.putUser(user);
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
