package db_manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;


public class DBGrupoPracticas {
	protected DBConnection dbconn;
        protected Connection MiConexion;
	/**
	 * Constructor . <br>
	 */
	public DBGrupoPracticas() {
		dbconn = new DBConnection();
                MiConexion = dbconn.getConnection();
                
	}


	/**
	 * A�adir regitro a la tabla <br>
	 * @param nombre
	 * 			grupo de practicas
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public boolean insertar_GrupoPracticas(String nombre) {
		boolean res = false;
		PreparedStatement stmt = null;

		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("INSERT INTO grupo_practicas_t (grupo) VALUES (?)"); //Añadir sentecia SQL para insertar
                        stmt.setString(1, nombre);
                        stmt.executeUpdate();
                        res=true;
		} catch (SQLException e) {
			System.out.println("insertar_GrupoPracticas exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("insertar_GrupoPracticas exception: "+e.getMessage());
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
	public boolean borrar_GrupoPracticas(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
			// Preparar sentencia	
                        stmt=MiConexion.prepareStatement("DELETE FROM grupo_practicas_t WHERE id=?"); //Añadir sentecia SQL para borrar
                        stmt.setInt(1, id);
                        stmt.executeUpdate();
                        /**stmt=MiConexion.prepareStatement("TRUNCATE grupo_practicas_t");
                        stmt.executeUpdate();**/ //para borrar toda la tabla
                        //stmt=MiConexion.prepareStatement("ALTER TABLE grupo_practicas_t AUTO_INCREMENT=?");
                        //stmt.setInt(1, id);
                        //stmt.executeUpdate();
                        res=true;

		} catch (SQLException e) {
			System.out.println("borrar_GrupoPracticas exception: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("borrar_GrupoPracticas exception: "+e.getMessage());
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
	public  boolean actualizar_GrupoPracticas(int id, String nombre) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
			// Preparar sentencia
                        stmt=MiConexion.prepareStatement("UPDATE grupo_practicas_t SET grupo=? WHERE id=?"); //Añadir sentecia SQL para actualizar
                        stmt.setString(1, nombre);
                        stmt.setInt(2, id);
                        stmt.executeUpdate();
                        int[] aiTota1Updated=stmt.executeBatch();
                        res=true;
		} catch (SQLException e) {
			System.out.println("actualizar_GrupoPracticas exception: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("actualizar_GrupoPracticas exception: "+e.getMessage());
				} 
		}

		return res;
	}



	/**
	 * Consultar la tabla <br>

	 */
	public  ArrayList<GrupoPracticas> consultar_GrupoPracticas() {
		ArrayList<GrupoPracticas> array = new ArrayList<GrupoPracticas>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			//Preparar sentencia. Utilizar metodo add de la clase ArrayList
                        stmt=MiConexion.prepareStatement("SELECT id, grupo FROM grupo_practicas_t"); //Añadir sentecia SQL para consultar
                        rs=stmt.executeQuery();
                        while (rs.next()) {
                            array.add(new GrupoPracticas(rs.getInt("id"), rs.getString("grupo")));
                        }

		} catch (SQLException e) {
			System.out.println("consultar_GrupoPracticas exception" + e.getMessage());
		} 
		finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("consultar_GrupoPracticas exception" + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("consultar_GrupoPracticas exception" + e.getMessage());
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
