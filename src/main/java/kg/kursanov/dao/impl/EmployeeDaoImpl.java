package kg.kursanov.dao.impl;

import kg.kursanov.config.Configration;
import kg.kursanov.dao.EmployeeDao;
import kg.kursanov.model.Employee;
import kg.kursanov.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDaoImpl implements EmployeeDao {

    private  final Connection connection = Configration.getConnection();
    @Override
    public void createEmployee() {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                            create table if not exists employees(
                            id serial primary key,
                            first_name varchar,
                            last_name varchar,
                            age int,
                            email varchar,
                            job_id int
                            )""");
            System.out.println("Tuzdu");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }


    @Override
    public void addEmployee(Employee employee) {
        String query = "insert into employees(first_name,last_name,age,email)"+
                "values(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.executeUpdate();
            System.out.println("tuzuldu");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void dropTable() {
        String query = "drop table employees";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
            System.out.println("successfull deleted!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void cleanTable() {
        String query = "delete from employees";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
            System.out.println("Taza!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String query = "UPDATE employees SET " +
                "first_name = ?, " +
                "last_name = ?, " +
                "age = ?, " +
                "email = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.executeUpdate();

            System.out.println("Employee with ID " + id + " updated successfully.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public List<Employee> getAllEmployees() {

        List<Employee> employeeList = new ArrayList<>();

        String query = "select * from employees";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong(1));
                employee.setFirstName(resultSet.getString(2));
                employee.setLastName(resultSet.getString(3));
                employee.setAge(resultSet.getInt(4));
                employee.setEmail(resultSet.getString(5));
                employeeList.add(employee);
            }

            preparedStatement.executeUpdate();

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return employeeList ;
    }

    @Override
    public Employee findByEmail(String email) {
        String query = "select * from employees where email = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1,email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if(resultSet.next()){
                    Long id = resultSet.getLong("id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    int age = resultSet.getInt("age");
                    Long jobId = resultSet.getLong("jobId");

                  Employee employee =  new Employee(firstName, lastName, age, email);
                    System.out.println(employee);


                }

            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Map<Employee, Job> getEmployeeBYId(Long employeeId) {
        Map<Employee,Job> employeeJobMap = new HashMap<>();

        String query = "select e.*,j.* from employees e " +
                "join jobs j on e.job_id = id "+
                "where e.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Employee employee = new Employee(
                            resultSet.getString("e.first_name"),
                            resultSet.getString("e.last_name"),
                            resultSet.getInt("e.age"),
                            resultSet.getString("e.email")
                    );

                    Job job = new Job(
                            resultSet.getLong("j.id"),
                            resultSet.getString("j.position"),
                            resultSet.getString("j.profession"),
                            resultSet.getString("j.description"),
                            resultSet.getInt("j.experience")
                    );
                    employeeJobMap.put(employee,job);
                }


            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        List<Employee> employees = new ArrayList<>();

        String query = "SELECT * FROM employees e JOIN jobs j ON e.job_id = j.id WHERE j.position = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, position);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Long id = resultSet.getLong("e.id");
                    String firstName = resultSet.getString("e.first_name");
                    String lastName = resultSet.getString("e.last_name");
                    int age = resultSet.getInt("e.age");
                    String email = resultSet.getString("e.email");
                    Long jobId = resultSet.getLong("e.jobId");

                    Employee employee = new Employee(firstName, lastName, age, email);
                    System.out.println(employees.add(employee));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception according to your application's needs
        }

        return employees;
    }
}
