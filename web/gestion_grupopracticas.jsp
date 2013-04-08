<%@page contentType="text/html" pageEncoding="UTF-8"
        import="db_manager.DBGrupoPracticas, db_manager.GrupoPracticas, java.util.ArrayList"
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>GESTIÓN DE GRUPOS DE PRÁCTICAS</title>
    </head>
    <body>
        <center><h1>Gestión Grupo de Prácticas</h1></center><br>
        <%
        
        //Obtener la información recibida, parámetros: accion, grupo, id
        String accion=request.getParameter("accion"); //apartado 3
        String grupo=request.getParameter("grupo");
        String id=request.getParameter("id");

        //Declaracion del objeto para invocar las operaciones de acceso a la tabla grupo_practicas_t
        
	DBGrupoPracticas operacion=new DBGrupoPracticas();//apartado 4	

        //Llamada al método correspondiente para insertar en la tabla
        if (accion!=null && accion.equals("insertar")) { //apartado 7
            if (operacion.insertar_GrupoPracticas(grupo))
                out.print("<script language='JavaScript'> alert('Datos añadidos a la base de datos')</script>");                            
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al añadir los datos')</script>");
        }

        //Llamada al método correspondiente para actualizar en la tabla
        if (accion!=null && accion.equals("actualizar")) {
            if(operacion.actualizar_GrupoPracticas((Integer.parseInt(id)), grupo))
                out.print("<script language='JavaScript'> alert('Datos actualizados en la base de datos')</script>"); 
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al actualizar los datos')</script>");
	}

        //Llamada al método correspondiente para eliminar de la tabla
        if (accion!=null && accion.equals("borrar")) {
            if(operacion.borrar_GrupoPracticas(Integer.parseInt(id)))
                out.print("<script language='JavaScript'> alert('Datos eliminados de la base de datos')</script>");
            else
                out.print("<script language='JavaScript'> alert('Hubo un error al eliminar los datos')</script>");	
        }
        %>

        <!-- Código HTML que implemente el enlace Insertar -->
        <center><h2><a href="formulario_grupopracticas.jsp?accion=insertar">Insertar</a></h2></center><br> <!--apartado 5-->
        <table align="center" border="0">
            <th>ID</th><th>Grupo</th><th>Acciones</th>

        <%
        // Añadir el código necesario para mostrar los registros de la tabla grupo_practicas_t. Un registro en cada fila
        GrupoPracticas fila=new GrupoPracticas();
        ArrayList<GrupoPracticas> array=new ArrayList<GrupoPracticas>();
        array=operacion.consultar_GrupoPracticas();
        int i=0;
        while(array!=null && i<array.size()){
            fila=array.get(i);
            out.print("<tr><td>");
            out.print(fila.getId());
            out.print("</td><td>");
            out.print(fila.getGrupo());
            out.print("</td><td>");
            out.print("<a href='formulario_grupopracticas.jsp?accion=actualizar&id=");
            out.print(fila.getId());
            out.print("&grupo=");
            out.print(fila.getGrupo());
            out.print("'>Editar</a> <a href='gestion_grupopracticas.jsp?accion=borrar&id=");
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
