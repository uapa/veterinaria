<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="org.cfg.uapa.java.entidades.Pais"%>
<%@page import="org.cfg.uapa.java.servicios.ServicioPais"%>
<%
    List<Pais> listaPaises = ServicioPais.getInstancia().getPaises();
    
    %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    
    <body>
        <p>Tiene <%=listaPaises.size()%> paises</p>
       
    </body>
</html>
