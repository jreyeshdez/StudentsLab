<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Laboratory Groups Application Form</title>
    </head>
    <body>
        <h1><center><b>Laboratory Groups Application Form</b></center></h1>
        <center><form name="laboratorygroups_form" method="get" action="laboratorygroups_management.jsp">
            <table align="center">
                <tr>
                    <%
                        String action=request.getParameter("action");
                        String id=request.getParameter("id");
                        String group=request.getParameter("group");
                        if(group==null){
                            group="";
                        }
                    %>
                    <td>Group:</td>
                    <td>
                        <input type="text" name="group" value="<%=group %>" size="15">
                    </td>
                </tr>
                <tr>
                    <td>
                        <input type="reset" name="delete" value="delete">
                        <input type="hidden" name="id" value="<%=id %>">
                    </td>
                    <td><input type="submit" name="action" value="<%=action %>"></td>
                </tr>
            </table>
        </form></center>
    </body>
</html>
