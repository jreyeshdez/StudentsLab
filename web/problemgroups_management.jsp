<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBProblemsGroup, db_manager.ProblemGroups, java.util.ArrayList"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROBLEM GROUPS MANAGEMENT</title>
    </head>
    <body>
        <center><h1>Problem Groups Management</h1></center>
            <br>
                <%
                    String action =request.getParameter("action");
                    String pgroup=request.getParameter("pgroup");
                    String id=request.getParameter("id");

	                DBProblemsGroup operation =new DBProblemsGroup();
                    if (action !=null && action.equals("insert")) {
                        if (operation.isProblemGroupInserted(pgroup))
                            out.print("<script language='JavaScript'> alert('Data have been inserted into the DB')</script>");
                        else
                            out.print("<script language='JavaScript'> alert('There was an error inserting the data')</script>");
                    }

                    if (action !=null && action.equals("update")) {
                        if(operation.isProblemGroupUpdated((Integer.parseInt(id)), pgroup))
                            out.print("<script language='JavaScript'> alert('Data have been updated into the DB')</script>");
                        else
                            out.print("<script language='JavaScript'> alert('There was an error updating the data')</script>");
	                }

                    if (action !=null && action.equals("delete")) {
                        if(operation.isProblemGroupDeleted(Integer.parseInt(id)))
                            out.print("<script language='JavaScript'> alert('Data have been deleted from the DB')</script>");
                        else
                            out.print("<script language='JavaScript'> alert('There was an error deleting the data')</script>");
                    }
                %>

        <center><h2><a href="problemgroups_form.jsp?action=insert">Insert</a></h2></center><br>
        <table align="center" border="0">
            <th>ID</th><th>Group</th><th>Actions</th>
                <%
                    ProblemGroups row =new ProblemGroups();
                    ArrayList<ProblemGroups> problemGroups =new ArrayList<ProblemGroups>();
                    problemGroups = operation.getProblemGroups();
                    int i=0;
                    while(problemGroups !=null && i< problemGroups.size()){
                        row = problemGroups.get(i);
                        out.print("<tr><td>");
                        out.print(row.getId());
                        out.print("</td><td>");
                        out.print(row.getPgroup());
                        out.print("</td><td>");
                        out.print("<a href='problemgroups_form.jsp?action=update&id=");
                        out.print(row.getId());
                        out.print("&pgroup=");
                        out.print(row.getPgroup());
                        out.print("'>Edit</a> <a href='problemgroups_management.jsp?action=delete&id=");
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
