package db_manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;

public class DBLaboratoryGroup {
	protected DBConnection dbconn;
    protected Connection MyConnection;

	public DBLaboratoryGroup() {
		dbconn = new DBConnection();
        MyConnection = dbconn.getConnection();
	}

	public boolean isLabGroupInserted(String value) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("INSERT INTO grupo_practicas_t (grupo) VALUES (?)");
            stmt.setString(1, value);
            stmt.executeUpdate();
            res=true;
		} catch (SQLException e) {
			System.out.println("Exception inserting laboratory group: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception inserting laboratory group: "+e.getMessage());
				}
		}
		return res;
	}

	public boolean isLabGroupDeleted(int id) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("DELETE FROM grupo_practicas_t WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            /**stmt=MyConnection.prepareStatement("TRUNCATE grupo_practicas_t");
            stmt.executeUpdate();**/
            res=true;

		} catch (SQLException e) {
			System.out.println("Exception deleting laboratory group: "+e.getMessage());
		
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception deleting laboratory group: "+e.getMessage());
				} 
		}
		return res;
	}

	public boolean isLabGroupUpdated(int id, String value) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("UPDATE grupo_practicas_t SET grupo=? WHERE id=?");
            stmt.setString(1, value);
            stmt.setInt(2, id);
            stmt.executeUpdate();
            int[] aiTota1Updated=stmt.executeBatch();
            res=true;
		} catch (SQLException e) {
			System.out.println("Exception updating laboratory group: "+e.getMessage());
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception updating laboratory group: "+e.getMessage());
				} 
		}
		return res;
	}

	public ArrayList<LaboratoryGroup> getLabGroups() {
		ArrayList<LaboratoryGroup> laboratoryGroups = new ArrayList<LaboratoryGroup>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
            stmt= MyConnection.prepareStatement("SELECT id, grupo FROM grupo_practicas_t"); //Añadir sentecia SQL para consultar
            rs=stmt.executeQuery();
            while (rs.next()) {
                laboratoryGroups.add(new LaboratoryGroup(rs.getInt("id"), rs.getString("grupo")));
            }

		} catch (SQLException e) {
			System.out.println("Exception getting laboratory groups: " + e.getMessage());
		} 
		finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					System.out.println("Exception getting laboratory groups: " + e.getMessage());
				}
			if(rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Exception getting laboratory groups: " + e.getMessage());
				}
		}
		return laboratoryGroups;
	}

	public void closeConexion() {
        try {
            MyConnection.close();
        } catch (SQLException e) {
		    System.out.println("Exception closing the connection: "+e.getMessage());
        }
    }
}
