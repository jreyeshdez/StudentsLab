

<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBGrupoProblemas, db_manager.DBGrupoPracticas, db_manager.GrupoPracticas, db_manager.GrupoProblemas, java.util.ArrayList"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulario Estudiantes</title>
    </head>
    <body>
        <h1><center><b>Formulario Estudiantes</b></center></h1>
    <center><form name="formulario_estudiantes" method="get" action="gestion_estudiantes.jsp">
            <table align="center">
                <tr>
                    <%
                        String id=request.getParameter("id");
                        String dni=request.getParameter("dni");
                        String nombre=request.getParameter("nombre");
                        String apellidos=request.getParameter("apellidos");
                        String email=request.getParameter("email");
                        String accion=request.getParameter("accion");
                        String idprob=request.getParameter("idprob");
                        String idpract=request.getParameter("idpract");
                        if(nombre==null && apellidos==null && email==null && dni==null){
                            nombre="";
                            apellidos="";
                            email="";
                            dni="";
                        }     
                    %>
                    <td>DNI:</td>        <td><input type="text" name="dni" value="<%=dni %>" size="15"></td>
                </tr>
                <tr>
                    <td>Nombre:</td>     <td><input type="text" name="nombre" value="<%=nombre %>" size="15"></td>
                </tr>
                <tr>
                    <td>Apellidos:</td>  <td><input type="text" name="apellidos" value="<%=apellidos %>" size=15"></td>
                </tr>
                <tr>
                    <td>E-Mail:</td>     <td><input type="text" name="email" value="<%=email %>" size="15"></td>
                </tr>
                <tr><td>Grupo de Problemas:</td>
                    <td> 
                        <Select name="idprob">
                            <%
                                DBGrupoProblemas oper1=new DBGrupoProblemas();
                                GrupoProblemas fila1=new GrupoProblemas();
                                ArrayList<GrupoProblemas> array1=new ArrayList<GrupoProblemas>();
                                array1=oper1.consultar_GrupoProblemas();
                                int i=0;
                                while (array1!=null && i<array1.size()){
                                    fila1=array1.get(i);
                                    out.print("<option value=");
                                    out.print(fila1.getId());
                                    if (idprob!=null && fila1.getId()==Integer.parseInt(idprob))
                                        out.print(" selected");
                                    out.print(">");
                                    out.print(fila1.getGrupo());
                                    out.print("</option>");
                                    i++;
                                }
                                   oper1.closeConexion();                                                                              
                            %>            
                        </select>
                    </td>
                </tr>
                
                <tr><td>Grupo de Prácticas:</td> 
                    <td>
                        <Select name="idpract">
                            <%
                                DBGrupoPracticas oper2=new DBGrupoPracticas();
                                GrupoPracticas fila2=new GrupoPracticas();
                                ArrayList<GrupoPracticas> array2=new ArrayList<GrupoPracticas>();
                                array2=oper2.consultar_GrupoPracticas();
                                int k=0;
                                while (array2!=null && k<array2.size()){
                                    fila2=array2.get(k);
                                    out.print("<option value=");
                                    out.print(fila2.getId());
                                    if (idpract!=null && fila2.getId()==Integer.parseInt(idpract))
                                        out.print(" selected");
                                    out.print(">");
                                    out.print(fila2.getGrupo());
                                    out.print("</option>");
                                    k++;
                                }
                                   oper2.closeConexion();                                                                              
                            %>          
                        </select>
                    </td>
                                  
                   
                </tr>
                <tr>
                     <td><input type="reset" name="borrar"></td>
                        <input type="hidden" name="id" value="<%=id %>">
                     <td><input type="submit" name="accion" value="<%=accion %>"></td>
                     
                </tr>
            </table>
        </form></center>
    </body>
</html>
