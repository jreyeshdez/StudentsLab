package db_manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.Date;

public class DBStudents {
	protected DBConnection dbconn;
    protected Connection MyConnection;

	public DBStudents() {
		dbconn = new DBConnection();
        MyConnection = dbconn.getConnection();
	}

	public boolean isStudentInserted(String id, String name, String surname, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
        Date date = new Date();
        Timestamp stamp = new Timestamp (date.getTime());
		try {
            stmt= MyConnection.prepareStatement("Insert into estudiantes_t (nombre, apellidos, email, idprob, idpract, fecha, dni) Values (?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setInt(4, idprob);
            stmt.setInt(5, idpract);
            stmt.setTimestamp(6, stamp);
            stmt.setString(7, id);
            stmt.executeUpdate();
            res=true;

		} catch (SQLException e) {
			System.out.println("Exception inserting students: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception inserting students: "+e.getMessage());
				}
		}
		return res;
	}

	public boolean isStudentsDeleted(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("DELETE FROM estudiantes_t WHERE id=?");
			stmt.setInt(1, id);                    			
            stmt.executeUpdate();
            /**stmt=MyConnection.prepareStatement("TRUNCATE estudiantes_t");
             stmt.executeUpdate(); //delete whole table**/
            res=true;


		} catch (SQLException e) {
			System.out.println("Exception deleting students: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception deleting students: "+e.getMessage());
				} 
		}
		return res;
	}
	/**
	 * Update table <br>
	 * @param id, name,surname,email,idprob,idpract
	 *
	 * @return <code>true</code> - operation successfull, <code>false</code> - error
	 */
	public boolean isStudentUpdated(int id, String dni, String name, String surname, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
        Date date = new Date();
        Timestamp stamp = new Timestamp (date.getTime());
		try {
            stmt= MyConnection.prepareStatement("UPDATE estudiantes_t SET nombre=?, apellidos=?, email=?, dni=?, idprob=?, idpract=?, fecha=? WHERE id=?");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setString(4, dni);
			stmt.setInt(5, idprob); 
            stmt.setInt(6, idpract);
            stmt.setTimestamp(7, stamp);
			stmt.setInt(8, id);  
            stmt.executeUpdate();
            res=true;

		} catch (SQLException e) {
			System.out.println("Exception updating students: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				}catch (SQLException e) {
					System.out.println("Exception updating students: "+e.getMessage());
				} 
		}
		return res;
	}

	public ArrayList<Students> getStudents() {
		ArrayList<Students> students = new ArrayList<Students>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
            stmt= MyConnection.prepareStatement("Select estudiantes_t.*,grupo_practicas_t.grupo " +
                    "as grupo_pract,grupo_problemas_t.grupo " +
                    "as grupo_prob From estudiantes_t " +
                    "Inner Join grupo_practicas_t " +
                    "ON grupo_practicas_t.id = estudiantes_t.idpract " +
                    "Inner Join grupo_problemas_t " +
                    "ON grupo_problemas_t.id = estudiantes_t.idprob");
            rs=stmt.executeQuery();
            while (rs.next()) {
                students.add(new Students(rs.getInt("id"),
                        rs.getInt("idprob"),
                        rs.getInt("idpract"),
                        rs.getString("dni"),
                        rs.getString("nombre"),
                        rs.getString("apellidos"),
                        rs.getString("email"),
                        rs.getString("grupo_prob"),
                        rs.getString("grupo_pract"),
                        rs.getString("fecha")));
            }

		}catch (SQLException e) {
			System.out.println("Exception selecting students: " + e.getMessage());
		}finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception selecting students: " + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Exception selecting students: " + e.getMessage());
				}
		}
		return students;
	}

	public void closeConnection() {
        try {
            MyConnection.close();
        }catch (SQLException e) {
		    System.out.println("Exception closing connection: "+e.getMessage());
        }
    }
}
