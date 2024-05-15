package user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/loginUser")
public class ServletLoginUser extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {

        String un = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        User u = new User(un,pwd);
        DbUsersOperations db = new DbUsersOperations();
        Long idUser=null;
        String errM=null;

        idUser=db.login(u);

        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(idUser!=null) {
            pw.println("Bine ai venit, te-am recunoscut");
            // sa il trimitem la produse
        }
        else
        {

                pw.println("Login failed !");

        }
    }



}
