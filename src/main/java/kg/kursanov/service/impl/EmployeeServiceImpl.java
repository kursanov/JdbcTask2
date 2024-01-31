package kg.kursanov.service.impl;

import kg.kursanov.dao.EmployeeDao;
import kg.kursanov.dao.impl.EmployeeDaoImpl;
import kg.kursanov.model.Employee;
import kg.kursanov.model.Job;
import kg.kursanov.service.EmployeeService;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();



    @Override
    public void createEmployee() {
        employeeDao.createEmployee();


    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDao.addEmployee(employee);


    }

    @Override
    public void dropTable() {
        employeeDao.dropTable();

    }

    @Override
    public void cleanTable() {
        employeeDao.cleanTable();

    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employeeDao.updateEmployee(id,employee);

    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.getAllEmployees();
    }

    @Override
    public Employee findByEmail(String email) {
        return employeeDao.findByEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeBYId(Long employeeId) {
        return employeeDao.getEmployeeBYId(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDao.getEmployeeByPosition(position);
    }
}
