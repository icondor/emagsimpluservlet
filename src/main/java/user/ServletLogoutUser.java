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
import java.sql.SQLException;

@WebServlet("/logoutUser")
public class ServletLogoutUser extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {

        HttpSession session = request.getSession();
       session.removeAttribute("idUser");
       session.invalidate();

        // il trimit automat la homepage
        RequestDispatcher rd = request.getRequestDispatcher("login.html");
        try {
            rd.forward(request,resp);
        } catch (ServletException | IOException e) {
            throw new RuntimeException(e);
        }

        }

    }




