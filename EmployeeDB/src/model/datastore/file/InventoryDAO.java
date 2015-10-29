package model.datastore.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import model.Inventory;
import model.IInventoryDAO;

/**
 * @author John Phillips
 * @version 20151009
 * 
 */
public class InventoryDAO implements  IInventoryDAO {

	protected String fileName = null;
	protected final List<Inventory> myList;

	public InventoryDAO() {
		Properties props = new Properties();
		FileInputStream fis = null;

		// read the properties file
		try {
			fis = new FileInputStream("res/file/db.properties");
			props.load(fis);
			this.fileName = props.getProperty("DB_FILENAME");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.myList = new ArrayList<>();
		try {
			Files.createFile(Paths.get(fileName));
		} catch (FileAlreadyExistsException fae) {
			;
		} catch (IOException ioe) {
			System.out.println("Create file error with " + ioe.getMessage());
		}
		readList();
	}

	@Override
	public void createRecord(Inventory Employee) {
		myList.add(Employee);
		writeList();
	}

	@Override
	public Inventory retrieveRecordById(int id) {
		for (Inventory Employee : myList) {
			if (Employee.getEmpId() == id) {
				return Employee;
			}
		}
		return null;
	}

	@Override
	public List<Inventory> retrieveAllRecords() {
		return myList;
	}

	@Override
	public void updateRecord(Inventory updatedEmployee) {
		for (Inventory Employee : myList) {
			if (Employee.getEmpId() == updatedEmployee.getEmpId()) {
				Employee.setLastName(updatedEmployee.getLastName());
				Employee.setFirstName(updatedEmployee.getFirstName());
				Employee.setHomePhone(updatedEmployee.getHomePhone());
				Employee.setSalary(updatedEmployee.getSalary());
				Employee.setAuction(updatedEmployee.getAuction());
				Employee.setProductn(updatedEmployee.getProductn());
				
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(int id) {
		for (Inventory Employee : myList) {
			if (Employee.getEmpId() == id) {
				myList.remove(Employee);
				break;
			}
		}
		writeList();
	}

	@Override
	public void deleteRecord(Inventory employee) {
		myList.remove(employee);
		writeList();
	}

	protected void readList() {
		Path path = Paths.get(fileName);
		try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
				int id = Integer.parseInt(data[0]);
				String last = data[1];
				String first = data[2];
				String homePhone = data[3];
				double salary = Double.parseDouble(data[4]);
				int productn = Integer.parseInt(data[5]);
				int auction = Integer.parseInt(data[6]);
				Inventory employee = new Inventory(id, last, first, homePhone, salary , auction , productn);
				myList.add(employee);
			}
		} catch (IOException ioe) {
			System.out.println("Read file error with " + ioe.getMessage());
		}
	}

	protected void writeList() {
		Path path = Paths.get(fileName);
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			for (Inventory Employee : myList) {
				writer.write(String.format("%d,%s,%s,%s,%.2f %d, %d \n", Employee.getEmpId(), Employee.getLastName(),
						Employee.getFirstName(), Employee.getHomePhone(), Employee.getSalary(), Employee.getAuction(), Employee.getProductn()));
			}
		} catch (IOException ioe) {
			System.out.println("Write file error with " + ioe.getMessage());
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Inventory Employee : myList) {
			sb.append(String.format("%5d : %s, %s, %s, %.2f %d, %d\n", Employee.getEmpId(), Employee.getLastName(),
					Employee.getFirstName(), Employee.getHomePhone(), Employee.getSalary(), Employee.getAuction(), Employee.getSalary()));
		}

		return sb.toString();
	}
}
