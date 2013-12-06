<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBProblemsGroup, db_manager.DBLaboratoryGroup, db_manager.LaboratoryGroup, db_manager.ProblemGroups, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Students Application Form</title>
    </head>
    <body>
        <h1><center><b>Students Application Form</b></center></h1>
        <center><form name="students_form" method="get" action="students_management.jsp">
            <table align="center">
                <tr>
                    <%
                        String id=request.getParameter("id");
                        String idnum=request.getParameter("idnum");
                        String name=request.getParameter("name");
                        String surname=request.getParameter("surname");
                        String email=request.getParameter("email");
                        String action=request.getParameter("action");
                        String idprob=request.getParameter("idprob");
                        String idpract=request.getParameter("idpract");
                        if(name==null && surname==null && email==null && idnum==null){
                            name="";
                            surname="";
                            email="";
                            idnum="";
                        }     
                    %>
                    <td>ID NUM:</td>
                        <td><input type="text" name="idnum" value="<%=idnum %>" size="15"></td>
                </tr>
                <tr>
                    <td>NAME:</td>
                        <td><input type="text" name="name" value="<%=name %>" size="15"></td>
                </tr>
                <tr>
                    <td>SURNAME:</td>
                        <td><input type="text" name="surname" value="<%=surname %>" size="15"></td>
                </tr>
                <tr>
                    <td>E-Mail:</td>
                        <td><input type="text" name="email" value="<%=email %>" size="15"></td>
                </tr>
                <tr><td>Problem Group:</td>
                    <td> 
                        <Select name="idprob">
                            <%
                                DBProblemsGroup operation1 =new DBProblemsGroup();
                                ProblemGroups row1 =new ProblemGroups();
                                ArrayList<ProblemGroups> problemGroups =new ArrayList<ProblemGroups>();
                                problemGroups = operation1.getProblemGroups();
                                int i=0;
                                while (problemGroups !=null && i< problemGroups.size()){
                                    row1 = problemGroups.get(i);
                                    out.print("<option value=");
                                    out.print(row1.getId());
                                    if (idprob!=null && row1.getId()==Integer.parseInt(idprob))
                                        out.print(" selected");
                                    out.print(">");
                                    out.print(row1.getGroup());
                                    out.print("</option>");
                                    i++;
                                }
                                   operation1.closeConexion();
                            %>            
                        </select>
                    </td>
                </tr>
                
                <tr><td>Laboratory Group:</td>
                    <td>
                        <Select name="idpract">
                            <%
                                DBLaboratoryGroup operation =new DBLaboratoryGroup();
                                LaboratoryGroup row =new LaboratoryGroup();
                                ArrayList<LaboratoryGroup> laboratoryGroups =new ArrayList<LaboratoryGroup>();
                                laboratoryGroups = operation.getLabGroups();
                                int k=0;
                                while (laboratoryGroups !=null && k< laboratoryGroups.size()){
                                    row = laboratoryGroups.get(k);
                                    out.print("<option value=");
                                    out.print(row.getId());
                                    if (idpract!=null && row.getId()==Integer.parseInt(idpract))
                                        out.print(" selected");
                                    out.print(">");
                                    out.print(row.getGroup());
                                    out.print("</option>");
                                    k++;
                                }
                                   operation.closeConexion();
                            %>          
                        </select>
                    </td>
                </tr>
                <tr>
                     <td>
                        <input type="reset" name="delete"></td>
                        <input type="hidden" name="id" value="<%=id %>">
                     <td>
                         <input type="submit" name="action" value="<%=action %>">
                     </td>
                     
                </tr>
            </table>
        </form></center>
    </body>
</html>
