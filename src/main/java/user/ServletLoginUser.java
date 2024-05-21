package user;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginUser")
public class ServletLoginUser extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {
        boolean isIn =Utility.isLoggedIn(request, resp);
        if(isIn)
        {

            PrintWriter pw = null;
            try {
                pw = resp.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            pw.println("Sunteti deja logat. Faceti intai logout");
            pw.close();
            return;
        }


        String un = request.getParameter("username");
        String pwd = request.getParameter("userpwd");
        User u = new User(un,pwd);
        DbUsersOperations db = new DbUsersOperations();
        Long idUser=null;
        String errM=null;

        idUser=db.login(u);


        if(idUser!=null) {
            // sa il trimitem la produse
            // marchez in sesiune ca omul e logat
            HttpSession session = request.getSession();
            session.setAttribute("idUser",idUser);
            session.setAttribute("username",u.getUsername());

            // il trimit automat la homepage
            RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
            try {
                rd.forward(request,resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }

        }
        else
        {

            // il trimit automat la homepage
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            try {
                rd.forward(request,resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }

        }
    }



}
