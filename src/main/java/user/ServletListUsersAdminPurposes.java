package user;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listAllUsers")
public class ServletListUsersAdminPurposes extends HttpServlet{

    protected void doGet(HttpServletRequest request, HttpServletResponse resp) {

        boolean isIn= Utility.isLoggedIn(request, resp);
        if(!isIn) {
            PrintWriter pw = null;
            try {
                pw = resp.getWriter();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            pw.println("Acces permis doar userilor logati");
            pw.close();
            return;
        }

        DbUsersOperations db = new DbUsersOperations();

        List<User> listUsers=null;

            listUsers= db.readAllUsers(true);
        JSONObject json = new JSONObject();
        json.put("users", listUsers);
        String objResponse = json.toString();

        PrintWriter pw = null;
        try {
            pw = resp.getWriter();
            pw.write(objResponse);
            pw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        }
    }



