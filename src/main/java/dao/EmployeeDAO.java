package dao;

import java.util.List;

import entity.Employee;

public interface EmployeeDAO {
	List<Employee> findAll();

	Employee findById(String id);

	void create(Employee employee);

	void update(Employee employee);

	void delete(String id);
}
