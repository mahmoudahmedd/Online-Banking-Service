<%-- 
    Document   : logout.jsp
    Created on : Dec 18, 2020, 3:33:43 AM
    Author     : WIN
--%>

<%
session.invalidate();
response.sendRedirect("login.html");
%>


