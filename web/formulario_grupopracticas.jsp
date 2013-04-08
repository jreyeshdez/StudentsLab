

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1><center><b>Formulario Grupo Practicas</b></center></h1>
    <center><form name="formulario_grupopracticas" method="get" action="gestion_grupopracticas.jsp">
            <table align="center">
                <tr>
                    <%
                        String accion=request.getParameter("accion");
                        String id=request.getParameter("id");
                        String grupo=request.getParameter("grupo");
                        if(grupo==null){
                            grupo="";
                        }
                    %>
                    <td>Grupo:</td>        <td><input type="text" name="grupo" value="<%=grupo %>" size="15"></td>
                </tr>
                
                <tr>
                    
                    <td><input type="reset" name="borrar" value="borrar">
                        <input type="hidden" name="id" value="<%=id %>">
                        
                    </td>
                    <td><input type="submit" name="accion" value="<%=accion %>"></td>
                </tr>
              
            </table>
        </form></center>
    </body>
</html>
