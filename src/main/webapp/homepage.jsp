<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Homepage</title>
</head>
<body>

<%
    HttpSession s = request.getSession();
    Long idUser= (Long)s.getAttribute("idUser");
    if(idUser==null)
    {
        response.sendRedirect("login.html");
    }
%>

<div>

    aici am un meniu
    <br>
    <a href ="listAllUsers"> afiseaza userii din db </a>

</div>

<p>
<a href="logoutUser">Logout</a>
</body>
</html>