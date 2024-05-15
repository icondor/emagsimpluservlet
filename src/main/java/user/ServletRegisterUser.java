package user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/registerUser")
public class ServletRegisterUser extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {

        String un = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        User u = new User(un,pwd);
        DbUsersOperations db = new DbUsersOperations();
        boolean isOk=false;
        String errM=null;
        try {
            isOk=db.insert(u);
        } catch (SQLException e) {
            e.printStackTrace();
            errM=e.getMessage();
        }
        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if(isOk) {
            pw.println("Multumim pt inregistrare. Totul ok!");
        }
        else
        {
            if(errM!=null&&errM.indexOf("duplicate key value violates unique constraint")!=-1)
               pw.println("Ne pare rau dar acest user deja exista");
            else
                pw.println("Ne pare rau, avem probl tehnice !");

        }
    }



}
