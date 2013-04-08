package db_manager;

import db_manager.Estudiantes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

public class DBEstudiantes {
	protected DBConnection dbconn;
        protected Connection MiConexion;
	/**
	 * Constructor . <br>
	 */
	public DBEstudiantes() {
		dbconn = new DBConnection();
                MiConexion = dbconn.getConnection();
                
	}


	/**
	 * A�adir regitro a la tabla <br>
	 * @param nombre, apellidos,email,idprob,idpract
	 * 			
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public boolean insertar_Estudiantes(String dni, String nombre, String apellidos, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
                Date fecha = new Date();
                Timestamp stamp = new Timestamp (fecha.getTime());
		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("Insert into estudiantes_t (nombre, apellidos, email, idprob, idpract, fecha, dni) Values (?,?,?,?,?,?,?)");
                        //la sentencia SQL que inserta los datos tiene que estar en el mismo orden (campos) que cuando se creó la 
                        //tabla con el navicat.
                        stmt.setString(1, nombre);
                        stmt.setString(2, apellidos);
                        stmt.setString(3, email);
                        stmt.setInt(4, idprob);
                        stmt.setInt(5, idpract);
                        stmt.setTimestamp(6, stamp);
                        stmt.setString(7, dni);
                        stmt.executeUpdate();
                        res=true;


                       
		} catch (SQLException e) {
			System.out.println("insertar_Estudiantes exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("insertar_Estudiantes exception: "+e.getMessage());
				}
		}

		return res;
	}

	/**
	 * Eliminar regitro de la tabla <br>
	 * @param id
	 * 			id del registro
         *
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public boolean borrar_Estudiantes(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
			// Preparar sentencia	
                        stmt=MiConexion.prepareStatement("DELETE FROM estudiantes_t WHERE id=?");  //Añadir sentecia SQL para eliminar
                        // Añadir el valor del parámetro ID
			stmt.setInt(1, id);                    			
                        stmt.executeUpdate();
                        /**stmt=MiConexion.prepareStatement("TRUNCATE estudiantes_t");
                        stmt.executeUpdate(); //para borrar toda la tabla**/
                        res=true;


		} catch (SQLException e) {
			System.out.println("borrar_Estudiantes exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("borrar_Estudiantes exception: "+e.getMessage());
				} 
		}

		return res;
	}


	/**
	 * Actualizar regitro de la tabla <br>
	 * @param id, nombre, apellidos,email,idprob,idpract
	 * 		         *
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public  boolean actualizar_Estudiantes(int id, String dni, String nombre, String apellidos, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
                Date fecha = new Date();
                Timestamp stamp = new Timestamp (fecha.getTime());
		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("UPDATE estudiantes_t SET nombre=?, apellidos=?, email=?, dni=?, idprob=?, idpract=?, fecha=? WHERE id=?");  // //Añadir sentecia SQL para actualizar
                        // Añadir los valores de 8 parámetros a la consulta actualizar
                        stmt.setString(1, nombre);
                        stmt.setString(2, apellidos);
                        stmt.setString(3, email);
                        stmt.setString(4, dni);
			stmt.setInt(5, idprob); 
                        stmt.setInt(6, idpract);
                        stmt.setTimestamp(7, stamp);
			stmt.setInt(8, id);  
                        stmt.executeUpdate();
                        res=true;

		} catch (SQLException e) {
			System.out.println("actualizar_Estudiantes exception: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("actualizar_Estudiantes exception: "+e.getMessage());
				} 
		}

		return res;
	}



	/**
	 * Consultar la tabla <br>

	 */
	public  ArrayList<Estudiantes> consultar_Estudiantes() {
		ArrayList<Estudiantes> array = new ArrayList<Estudiantes>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Preparar sentencia. Utilizar metodo add de la clase ArrayList
                        stmt=MiConexion.prepareStatement("Select estudiantes_t.*,grupo_practicas_t.grupo as grupo_pract,grupo_problemas_t.grupo as grupo_prob From estudiantes_t Inner Join grupo_practicas_t ON grupo_practicas_t.id = estudiantes_t.idpract Inner Join grupo_problemas_t ON grupo_problemas_t.id = estudiantes_t.idprob");
                        rs=stmt.executeQuery();
                        while (rs.next()) {
                            array.add(new Estudiantes(rs.getInt("id"), rs.getInt("idprob"),rs.getInt("idpract"),rs.getString("dni"),rs.getString("nombre"), rs.getString("apellidos"), rs.getString("email"), rs.getString("grupo_prob"), rs.getString("grupo_pract"), rs.getString("fecha")));
                        }


		} catch (SQLException e) {
			System.out.println("consultar_Estudiantes exception" + e.getMessage());
		} 
		finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("consultar_Estudiantes exception" + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("consultar_Estudiantes exception" + e.getMessage());
				}
		}
		return array;
	}
	

	/**
	 * Cerrar la conexi�n <br>

	 */
	public void closeConexion() {
            try {
                MiConexion.close();
            }
             catch (SQLException e) {
			System.out.println("Close exception: "+e.getMessage());
             }
        }

}
