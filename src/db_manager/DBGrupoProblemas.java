package db_manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;


public class DBGrupoProblemas {
	protected DBConnection dbconn;
        protected Connection MiConexion;
	/**
	 * Constructor . <br>
	 */
	public DBGrupoProblemas() {
		dbconn = new DBConnection();
                MiConexion = dbconn.getConnection();
                
	}


	/**
	 * A�adir regitro a la tabla <br>
	 * @param nombre
	 * 			grupo de problemas
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public boolean insertar_GrupoProblemas(String nombre) {
		boolean res = false;
		PreparedStatement stmt = null;
                
		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("INSERT INTO grupo_problemas_t (grupo) VALUES (?)"); //Añadir sentecia SQL para insertar
                        stmt.setString(1, nombre);
                        stmt.executeUpdate();
                        res=true;                       
		} catch (SQLException e) {
			System.out.println("insertar_GrupoProblemas exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("insertar_GrupoProblemas exception: "+e.getMessage());
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
	public boolean borrar_GrupoProblemas(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
			// Preparar sentencia	
                        stmt=MiConexion.prepareStatement("DELETE FROM grupo_problemas_t WHERE id=?"); //Añadir sentecia SQL para borrar
                        stmt.setInt(1, id);
                        stmt.executeUpdate();
                        /**stmt=MiConexion.prepareStatement("TRUNCATE grupo_problemas_t");
                        stmt.executeUpdate**/ //Para borrar toda la tabla
                        res=true;


		} catch (SQLException e) {
			System.out.println("borrar_GrupoProblemas exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("borrar_GrupoProblemas exception: "+e.getMessage());
				} 
		}

		return res;
	}


	/**
	 * Actualizar regitro de la tabla <br>
	 * @param id
	 * 			id del registro
   	 * @param nombre
	 * 			nombre del registro
         *
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public  boolean actualizar_GrupoProblemas(int id, String nombre) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("UPDATE grupo_problemas_t SET grupo=? WHERE id=?"); //Añadir sentecia SQL para actualizar
                        stmt.setString(1, nombre);
                        stmt.setInt(2, id);
                        stmt.executeUpdate();
                        res=true;

		} catch (SQLException e) {
			System.out.println("actualizar_GrupoProblemas exception: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("actualizar_GrupoProblemas exception: "+e.getMessage());
				} 
		}

		return res;
	}



	/**
	 * Consultar la tabla <br>

	 */
	public  ArrayList<GrupoProblemas> consultar_GrupoProblemas() {
		ArrayList<GrupoProblemas> array = new ArrayList<GrupoProblemas>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Preparar sentencia. Utilizar metodo add de la clase ArrayList
                        stmt=MiConexion.prepareStatement("SELECT id, grupo FROM grupo_problemas_t"); //Añadir sentecia SQL para consultar
                        rs=stmt.executeQuery();
                        while (rs.next()) {
                            array.add(new GrupoProblemas(rs.getInt("id"), rs.getString("grupo")));
                        }

		} catch (SQLException e) {
			System.out.println("consultar_GrupoProblemas exception" + e.getMessage());
		} 
		finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("consultar_GrupoProblemas exception" + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("consultar_GrupoProblemas exception" + e.getMessage());
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
