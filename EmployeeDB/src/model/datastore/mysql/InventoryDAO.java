package model.datastore.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Inventory;
import model.IInventoryDAO;

/**
 * @author John Phillips
 * @version 20151015
 *
 */
public class InventoryDAO implements IInventoryDAO {
	
	protected final static boolean DEBUG = true;

	@Override
	public void createRecord(Inventory employee) {
		final String QUERY = "insert into auction_list (empId, lastName, firstName, homePhone, salary, productn, Employee) VALUES (null, ?, ?, ?, ?, ?, ?)";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY);) {
			stmt.setString(1, employee.getLastName());
			stmt.setString(2, employee.getFirstName());
			stmt.setString(3, employee.getHomePhone());
			stmt.setDouble(4, employee.getSalary());
			stmt.setInt(5, employee.getProductn());
			stmt.setInt(6, employee.getAuction());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("createRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public Inventory retrieveRecordById(int id) {
		final String QUERY = "select empId, lastName, firstName, homePhone, salary, Employee, productn from employee where empId = " + id;
		// final String QUERY = "select empId, lastName, firstName, homePhone,
		// salary from employee where empId = ?";
		Inventory emp = null;

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			// stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);

			if (rs.next()) {
				emp = new Inventory(rs.getInt("empId"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getDouble("salary"), rs.getInt("productn"), rs.getInt("Employee"));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveRecordById SQLException: " + ex.getMessage());
		}

		return emp;
	}

	@Override
	public List<Inventory> retrieveAllRecords() {
		final List<Inventory> myList = new ArrayList<>();
		final String QUERY = "select empId, lastName, firstName, homePhone, salary, productn, Employee from auction_list";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			if(DEBUG) System.out.println(stmt.toString());
			ResultSet rs = stmt.executeQuery(QUERY);
			
			while (rs.next()) {
				myList.add(new Inventory(rs.getInt("empId"), rs.getString("lastName"), rs.getString("firstName"),
						rs.getString("homePhone"), rs.getDouble("salary"), rs.getInt("productn"), rs.getInt("Employee")));
			}
		} catch (SQLException ex) {
			System.out.println("retrieveAllRecords SQLException: " + ex.getMessage());
		}

		return myList;
	}

	@Override
	public void updateRecord(Inventory updatedEmployee) {
		final String QUERY = "update auction_list set lastName=?, firstName=?, homePhone=?, salary=? where empId=?, productn=?, Employee=?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setString(1, updatedEmployee.getLastName());
			stmt.setString(2, updatedEmployee.getFirstName());
			stmt.setString(3, updatedEmployee.getHomePhone());
			stmt.setDouble(4, updatedEmployee.getSalary());
			stmt.setInt(5, updatedEmployee.getEmpId());
			stmt.setInt(6, updatedEmployee.getAuction());
			stmt.setInt(7, updatedEmployee.getProductn());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("updateRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(int id) {
		final String QUERY = "delete from employee where empId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, id);
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public void deleteRecord(Inventory employee) {
		final String QUERY = "delete from employee where empId = ?";

		try (Connection con = DBConnection.getConnection(); PreparedStatement stmt = con.prepareStatement(QUERY)) {
			stmt.setInt(1, employee.getEmpId());
			if(DEBUG) System.out.println(stmt.toString());
			stmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("deleteRecord SQLException: " + ex.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Inventory Employee : retrieveAllRecords()) {
			sb.append(Employee.toString() + "\n");
		}

		return sb.toString();
	}
}
