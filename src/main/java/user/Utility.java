package user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class Utility {

    public static boolean isLoggedIn(HttpServletRequest request, HttpServletResponse resp) {
        HttpSession session = request.getSession();
        Long idUser= (Long)session.getAttribute("idUser");
        if(idUser!=null){
            return true;
        }
        return false;
    }

}
