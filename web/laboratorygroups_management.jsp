<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBLaboratoryGroup, db_manager.LaboratoryGroup, java.util.ArrayList"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LABORATORY GROUP MANAGEMENT</title>
    </head>
    <body>
        <center><h1>Laboratory Group Management</h1></center>
        <br>
        <%
            String action=request.getParameter("action");
            String group=request.getParameter("group");
            String id=request.getParameter("id");

            DBLaboratoryGroup operation =new DBLaboratoryGroup();

            if (action !=null && action.equals("insert")) {
                if (operation.isLabGroupInserted(group))
                    out.print("<script language='JavaScript'> alert('Data have been added into the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error inserting the data')</script>");
            }

            if (action !=null && action.equals("update")) {
                if(operation.isLabGroupUpdated((Integer.parseInt(id)), group))
                    out.print("<script language='JavaScript'> alert('Data have been updated into the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error updating the data')</script>");
            }

            if (action !=null && action.equals("delete")) {
                if(operation.isLabGroupDeleted(Integer.parseInt(id)))
                    out.print("<script language='JavaScript'> alert('Data have been deleted from the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error deleting the data')</script>");
            }
        %>

        <center><h2><a href="laboratorygroups_form.jsp?action=insert">Insert</a></h2></center><br>
        <table align="center" border="0">
            <th>ID</th><th>Group</th><th>Actions</th>

        <%
            LaboratoryGroup row =new LaboratoryGroup();
            ArrayList<LaboratoryGroup> laboratoryGroups =new ArrayList<LaboratoryGroup>();
            laboratoryGroups = operation.getLabGroups();
            int i=0;
            while(laboratoryGroups !=null && i< laboratoryGroups.size()){
                row = laboratoryGroups.get(i);
                out.print("<tr><td>");
                out.print(row.getId());
                out.print("</td><td>");
                out.print(row.getGroup());
                out.print("</td><td>");
                out.print("<a href='laboratorygroups_form.jsp?action=update&id=");
                out.print(row.getId());
                out.print("&group=");
                out.print(row.getGroup());
                out.print("'>Edit</a> <a href='laboratorygroups_management.jsp?action=delete&id=");
                out.print(row.getId() + "' ");
                out.print(">Delete</a></td></tr>");
                i++;
            }
            operation.closeConexion();
        %>
        </table>
        <center><h2><a href="index.jsp">Back</a></h2></center>
    </body>
</html>
