<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBEstudiantes, db_manager.Estudiantes, java.util.ArrayList"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTIÓN DE GRUPOS DE ESTUDIANTES</title>
    </head>
    <body>
        <center><h1>Gestión Grupo de Estudiantes</h1></center><br>
        <%
        
        //Obtener la información recibida, parámetros: accion, grupo, id...
        String id=request.getParameter("id");
        String dni=request.getParameter("dni");
        String nombre=request.getParameter("nombre");
        String apellidos=request.getParameter("apellidos");
        String email=request.getParameter("email");
        String fecha=request.getParameter("fecha");
        String accion=request.getParameter("accion");
        String idprob=request.getParameter("idprob");
        String idpract=request.getParameter("idpract");

        //Declaracion del objeto para invocar las operaciones de acceso a la tabla grupo_Problemas_t
        
	DBEstudiantes operacion=new DBEstudiantes();//apartado 4	

        //Llamada al método correspondiente para insertar en la tabla
        if (accion!=null && accion.equals("insertar")) { //apartado 7
            /**out.println("accion: "+accion);
            out.println("dni: "+dni);
            out.println("nombre: "+nombre);
            out.println("apellidos: "+apellidos);
            out.println("mail: "+email);
            out.println("idprob: "+idprob);
            out.println("idpract: "+idpract);**/
            if (operacion.insertar_Estudiantes(dni, nombre, apellidos, email, Integer.parseInt(idprob), Integer.parseInt(idpract)))
                out.print("<script language='JavaScript'> alert('Datos añadidos a la base de datos')</script>");  
                //out.print("Datos añadidos a la base de datos");
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al añadir los datos')</script>");
        }

        //Llamada al método correspondiente para actualizar en la tabla
        if (accion!=null && accion.equals("actualizar")) {
            if(operacion.actualizar_Estudiantes(Integer.parseInt(id), dni, nombre, apellidos, email, Integer.parseInt(idprob), Integer.parseInt(idpract)))
                out.print("<script language='JavaScript'> alert('Datos actualizados en la base de datos')</script>"); 
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al actualizar los datos')</script>");
	}

        //Llamada al método correspondiente para eliminar de la tabla
        if (accion!=null && accion.equals("borrar")) {
            if(operacion.borrar_Estudiantes(Integer.parseInt(id)))
                out.print("<script language='JavaScript'> alert('Datos eliminados de la base de datos')</script>");
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al eliminar los datos')</script>");	
        }
        %>

        <!-- Código HTML que implemente el enlace Insertar -->
        <center><h2><a href="formulario_estudiantes.jsp?accion=insertar">Insertar</a></h2></center><br> <!--apartado 5-->
        <table align="center" border="0">
            <th>ID</th><th>Nombre</th><th>Apellidos</th><th>DNI</th><th>eMail</th><th>Grupo Prácticas</th><th>Grupo Problemas</th><th>Fecha</th>
  
        <%
        // Añadir el código necesario para mostrar los registros de la tabla grupo_Problemas_t. Un registro en cada fila
        Estudiantes fila=new Estudiantes();
        ArrayList<Estudiantes> array=new ArrayList<Estudiantes>();
        array=operacion.consultar_Estudiantes();
        int i=0;
        while(array!=null && i<array.size()){
            fila=array.get(i);
            out.print("<tr><td>");
            out.print(fila.getId());
            out.print("</td><td>");
            out.print(fila.getNombre());
            out.print("</td><td>");
            out.print(fila.getApellidos());
            out.print("</td><td>");
            out.print(fila.getDni());
            out.print("</td><td>");
            out.print(fila.getEmail());
            out.print("</td><td>");
            out.print(fila.getGrupopract());
            out.print("</td><td>");
            out.print(fila.getGrupoprob());
            out.print("</td><td>");
            out.print(fila.getFecha());
            out.print("</td><td>");
            out.print("<a href='formulario_estudiantes.jsp?accion=actualizar&id="+fila.getId()
                    +"&dni="+fila.getDni()
                    +"&nombre="+fila.getNombre()
                    +"&apellidos="+fila.getApellidos()
                    +"&email="+fila.getEmail()
                    +"&idprob="+fila.getIdprob()
                    +"&idpract="+fila.getIdpract());
            out.print("'>Editar</a> <a href='gestion_estudiantes.jsp?accion=borrar&id=");
            out.print(fila.getId() + "' ");
            out.print(">Eliminar</a></td></tr>");
            i++;
        }
        //Cerrar operacion
        operacion.closeConexion();
        //out.println("operación cerrada");
        %>
        </table>

        <!-- Código HTML que implemente el enlace Volver -->
        <center><h2><a href="index.jsp">Volver</a></h2></center><!--apartado 5 -->
		
    </body>
</html>
