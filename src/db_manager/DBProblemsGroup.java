package db_manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class DBProblemsGroup {
	protected DBConnection dbconn;
    protected Connection MyConnection;

	public DBProblemsGroup() {
		dbconn = new DBConnection();
        MyConnection = dbconn.getConnection();
	}

	public boolean isProblemGroupInserted(String value) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("INSERT INTO grupo_problemas_t (grupo) VALUES (?)"); //Añadir sentecia SQL para insertar
            stmt.setString(1, value);
            stmt.executeUpdate();
            res=true;
		} catch (SQLException e) {
			System.out.println("Exception inserting problem group: "+e.getMessage());
		
		} finally {
            if(stmt != null)
				try {
				    stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception inserting problem group: "+e.getMessage());
				}
		}
		return res;
	}

	public boolean isProblemGroupDeleted(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("DELETE FROM grupo_problemas_t WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            /**stmt=MyConnection.prepareStatement("TRUNCATE grupo_problemas_t");
            stmt.executeUpdate; **/
            res=true;
		} catch (SQLException e) {
			System.out.println("Exception deleting problem group: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception deleting problem group: "+e.getMessage());
				} 
		}
		return res;
	}

	public boolean isProblemGroupUpdated(int id, String nombre) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("UPDATE grupo_problemas_t SET grupo=? WHERE id=?");
            stmt.setString(1, nombre);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            res=true;
		} catch (SQLException e) {
			System.out.println("Exception updating problem group: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception updating problem group: "+e.getMessage());
				} 
		}
		return res;
	}

	public ArrayList<ProblemGroups> getProblemGroups() {
		ArrayList<ProblemGroups> problemGroups = new ArrayList<ProblemGroups>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
            stmt= MyConnection.prepareStatement("SELECT id, grupo FROM grupo_problemas_t");
            rs=stmt.executeQuery();
            while (rs.next()) {
                problemGroups.add(new ProblemGroups(rs.getInt("id"), rs.getString("grupo")));
            }
		} catch (SQLException e) {
			System.out.println("Exception getting problem groups: " + e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception getting problem groups: " + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Exception getting problem groups: " + e.getMessage());
				}
		}
		return problemGroups;
	}

	public void closeConexion() {
        try {
            MyConnection.close();
        } catch (SQLException e) {
		    System.out.println("Exception closing the connection: "+e.getMessage());
        }
    }

}
