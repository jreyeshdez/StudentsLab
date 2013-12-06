<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBStudents, db_manager.Students, java.util.ArrayList"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MANAGEMENT STUDENTS GROUP</title>
    </head>
    <body>
        <center><h1>Management Students Group</h1></center><br>
        <%
            String id=request.getParameter("id");
            String idnum=request.getParameter("idnum");
            String name=request.getParameter("name");
            String surname=request.getParameter("surname");
            String email=request.getParameter("email");
            String date=request.getParameter("date");
            String action =request.getParameter("action");
            String idprob=request.getParameter("idprob");
            String idpract=request.getParameter("idpract");

            DBStudents operation =new DBStudents();

            if (action !=null && action.equals("insert")) {
                /**out.println("action: "+action);
                out.println("idnum: "+idnum);
                out.println("name: "+name);
                out.println("surname: "+surname);
                out.println("mail: "+email);
                out.println("idprob: "+idprob);
                out.println("idpract: "+idpract);**/
                if (operation.isStudentInserted(idnum, name, surname, email, Integer.parseInt(idprob), Integer.parseInt(idpract)))
                    out.print("<script language='JavaScript'> alert('Data have been added into the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error inserting the data')</script>");
            }

            if (action !=null && action.equals("update")) {
                if(operation.isStudentUpdated(Integer.parseInt(id), idnum, name, surname, email, Integer.parseInt(idprob), Integer.parseInt(idpract)))
                    out.print("<script language='JavaScript'> alert('Data have been updated into the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error updating the data')</script>");
            }

            if (action !=null && action.equals("delete")) {
                if(operation.isStudentsDeleted(Integer.parseInt(id)))
                    out.print("<script language='JavaScript'> alert('Data have been deleted from the DB')</script>");
                else
                    out.print("<script language='JavaScript'> alert('There was an error deleting the data')</script>");
            }

        %>

        <center><h2><a href="students_form.jsp?action=insert">Insert</a></h2></center><br>
        <table align="center" border="0">
            <th>ID</th><th>Name</th><th>Surname</th><th>ID Num</th><th>eMail</th><th>Laboratory Group</th><th>Problem Group</th><th>Date</th>
  
        <%
            Students row = new Students();
            ArrayList<Students> students =new ArrayList<Students>();
            students = operation.getStudents();
            int i=0;
            while(students !=null && i< students.size()){
                row = students.get(i);
                out.print("<tr><td>");
                out.print(row.getId());
                out.print("</td><td>");
                out.print(row.getName());
                out.print("</td><td>");
                out.print(row.getSurname());
                out.print("</td><td>");
                out.print(row.getIdnum());
                out.print("</td><td>");
                out.print(row.getEmail());
                out.print("</td><td>");
                out.print(row.getLabgroup());
                out.print("</td><td>");
                out.print(row.getProblemgroup());
                out.print("</td><td>");
                out.print(row.getDate());
                out.print("</td><td>");
                out.print("<a href='students_form.jsp?action=update&id="+ row.getId()
                        +"&idnum="+ row.getIdnum()
                        +"&name="+ row.getName()
                        +"&surname="+ row.getSurname()
                        +"&email="+ row.getEmail()
                        +"&idprob="+ row.getIdprob()
                        +"&idpract="+ row.getIdpract());
                out.print("'>Edit</a> <a href='students_management.jsp?action=delete&id=");
                out.print(row.getId() + "' ");
                out.print(">Delete</a></td></tr>");
                i++;
            }
            operation.closeConnection();
        %>
        </table>

        <center><h2><a href="index.jsp">Back</a></h2></center>
		
    </body>
</html>
