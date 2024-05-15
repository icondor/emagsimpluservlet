package user;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbUsersOperations {


    public Long login(User user) {
        Long idUser=null;
        // citeste din db toti userii si returneaza lista lor

        try {

            // conectare la db cu incarcare driver
            final String URLDB = "jdbc:postgresql://localhost:5432/icondor";
            final String USERNAMEDB = "postgres";
            final String PWDDB = "postgres";
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            // rulare sql
            String q;

                q = "select id from users where username=? and password=?";

            PreparedStatement pSt = conn.prepareStatement(q);

             pSt.setString(1, user.getUsername());
            pSt.setString(2, user.getPassword());

            ResultSet rs = pSt.executeQuery();


            while (rs.next()) {
                idUser= rs.getLong("id");
                System.out.println("uite-l ");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
if(idUser==null)
    System.out.println("nu e in db ");
        return idUser;
    }





    public boolean insert (User u) throws SQLException{

        // COD CARE SCRIE IN DB



        // daca are rezultate, citirea lor


        // conectare la db cu incarcare driver
        final String URLDB = "jdbc:postgresql://localhost:5432/icondor";
        final String USERNAMEDB ="postgres";
        final String PWDDB ="vvv";
        int val = 0; // 1
        try {
            Class.forName("org.postgresql.Driver");
            Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

            // rulare sql
            PreparedStatement pSt = conn.prepareStatement("insert into users(username, password, isactive, isbuyer) values(?, ?, ?,?)");
            pSt.setString(1, u.getUsername());
            pSt.setString(2, u.getPassword());
            pSt.setBoolean(3, u.isActive());
            pSt.setBoolean(4, u.isBuyer());
            val = pSt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        boolean ok = false;
        if(val!=0)
            ok=true;
        return ok;
    }

    public List<User> readAllUsers(boolean allDespiteStatus) {
        List<User> lu = new ArrayList<>();
        // citeste din db toti userii si returneaza lista lor

try {

    // conectare la db cu incarcare driver
    final String URLDB = "jdbc:postgresql://localhost:5432/icondor";
    final String USERNAMEDB = "postgres";
    final String PWDDB = "postgres";
    try {
        Class.forName("org.postgresql.Driver");
    } catch (ClassNotFoundException e) {
        throw new RuntimeException(e);
    }
    Connection conn = DriverManager.getConnection(URLDB, USERNAMEDB, PWDDB);

    // rulare sql
    String q;
    if (allDespiteStatus)
        q = "select * from users order by username asc ";
    else
        q = "select * from users where isActive=true order by username asc ";
    PreparedStatement pSt = conn.prepareStatement(q);

    // pSt.set...

    ResultSet rs = pSt.executeQuery();


    while (rs.next()) {

        String user = rs.getString("username").trim();
        String p = rs.getString("password").trim();
        boolean isa = rs.getBoolean("isactive");
        boolean isb = rs.getBoolean("isbuyer");

        long id = rs.getLong("id");

        User u = new User(user, p, isa, isb);
        u.setId(id);
        lu.add(u);
    }
}
catch (SQLException e) {
    e.printStackTrace();
}

        return lu;
    }

}
