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

	public boolean isStudentInserted(String idnum, String name, String surname, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
        Date date = new Date();
        Timestamp stamp = new Timestamp (date.getTime());
		try {
            stmt= MyConnection.prepareStatement("Insert into students (sname, surname, email, idprob, idpract, sdate, idnum) Values (?,?,?,?,?,?,?)");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setInt(4, idprob);
            stmt.setInt(5, idpract);
            stmt.setTimestamp(6, stamp);
            stmt.setString(7, idnum);
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
            stmt= MyConnection.prepareStatement("DELETE FROM students WHERE id=?");
			stmt.setInt(1, id);                    			
            stmt.executeUpdate();
            /**stmt=MyConnection.prepareStatement("TRUNCATE students");
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
	public boolean isStudentUpdated(int id, String idnum, String name, String surname, String email, int idprob, int idpract) {
		boolean res = false;
		PreparedStatement stmt = null;
        Date date = new Date();
        Timestamp stamp = new Timestamp (date.getTime());
		try {
            stmt= MyConnection.prepareStatement("UPDATE students SET sname=?, surname=?, email=?, idnum=?, idprob=?, idpract=?, sdate=? WHERE id=?");
            stmt.setString(1, name);
            stmt.setString(2, surname);
            stmt.setString(3, email);
            stmt.setString(4, idnum);
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
            stmt= MyConnection.prepareStatement("Select students.*,laboratorygroups.lgroup " +
                    "as lab_group,problemgroups.pgroup " +
                    "as prob_group From students " +
                    "Inner Join laboratorygroups " +
                    "ON laboratorygroups.id = students.idpract " +
                    "Inner Join problemgroups " +
                    "ON problemgroups.id = students.idprob");
            rs=stmt.executeQuery();
            while (rs.next()) {
                students.add(new Students(
                        rs.getInt("id"),
                        rs.getInt("idprob"),
                        rs.getInt("idpract"),
                        rs.getString("idnum"),
                        rs.getString("sname"),
                        rs.getString("surname"),
                        rs.getString("email"),
                        rs.getString("prob_group"),
                        rs.getString("lab_group"),
                        rs.getString("sdate")));
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
