<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students Laboratory</title>
    </head>
    <body>
        <div style="text-align: center">
            <p></p>
            <h1>Menu</h1>
            <p></p>
            <a href="private/students_management.jsp">Students Management</a></br>
            <p></p>
            <a href="private/laboratorygroups_management.jsp">Laboratory Groups Management</a></br>
            <p></p>
            <a href="private/problemgroups_management.jsp">Problem Groups Management</a></br>
            <p></p>
            <a href="<c:url value="/j_spring_security_logout"/>">Logout</a>
        </div>
    </body>
</html>
