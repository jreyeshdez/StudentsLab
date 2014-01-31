<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix='c' uri='http://java.sun.com/jstl/core_rt' %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login Page</title>
    <style>
        .errorblock {
            color: #ff0000;
            background-color: #ffEEEE;
            border: 3px solid #ff0000;
            padding: 8px;
            margin: 16px;
        }
    </style>
</head>
<body onload='document.f.j_username.focus();'>
    <div style="text-align: center">
        <h3>Login with Username and Password</h3>
    </div>
    <c:if test="${not empty error}">
        <div class="errorblock" style="text-align: center">
            Your login attempt was not successful, try again.<br /> Caused :
            ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
        </div>
    </c:if>
    <center><form name='f' action="<c:url value='/j_spring_security_check'/>" method='POST'>
        <table style="text-align: center; width: auto">
            <tr>
                <td>User:</td>
                <td>
                    <input type='text' name='j_username' value=''>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td>
                    <input type='password' name='j_password' />
                </td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="submit" />
                </td>
                <td colspan='2'>
                    <input name="reset" type="reset" />
                </td>
            </tr>
        </table>
    </form></center>
</body>
</html>