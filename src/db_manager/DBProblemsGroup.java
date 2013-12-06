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

	public boolean isProblemGroupInserted(String group) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("INSERT INTO problemgroups (pgroup) VALUES (?)");
            stmt.setString(1, group);
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
            stmt= MyConnection.prepareStatement("DELETE FROM problemgroups WHERE id=?");
            stmt.setInt(1, id);
            stmt.executeUpdate();
            /**stmt=MyConnection.prepareStatement("TRUNCATE problemgroups");
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

	public boolean isProblemGroupUpdated(int id, String group) {
		boolean res = false;
		PreparedStatement stmt = null;
		try {
            stmt= MyConnection.prepareStatement("UPDATE problemgroups SET pgroup=? WHERE id=?");
            stmt.setString(1, group);
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
            stmt= MyConnection.prepareStatement("SELECT id, pgroup FROM problemgroups");
            rs=stmt.executeQuery();
            while (rs.next()) {
                problemGroups.add(new ProblemGroups(rs.getInt("id"), rs.getString("pgroup")));
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
