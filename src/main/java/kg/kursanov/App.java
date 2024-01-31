package kg.kursanov;

import kg.kursanov.dao.impl.EmployeeDaoImpl;
import kg.kursanov.dao.impl.JobDaoImpl;
import kg.kursanov.model.Employee;
import kg.kursanov.model.Job;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        EmployeeDaoImpl employeeService = new EmployeeDaoImpl();
        JobDaoImpl jobDao = new JobDaoImpl();
//        employeeService.createEmployee();
//        Employee employee =new Employee(id, "zarip","Kursanov",21,"zarip@gmail.com");
//        employeeService.addEmployee(new Employee("zarip","kursanov",23,"z@gmail.com"));
//        employeeService.addEmployee(new Employee("aza","zamat",23,"aza@gmail.com"));
//        employeeService.addEmployee(new Employee("aza","zamat",23,"aza@gmail.com"));
//        employeeService.dropTable();
//        employeeService.cleanTable();
//        employeeService.updateEmployee(6L,new Employee("aza","zamat",23,"aza@gmail.com"));
//        System.out.println(employeeService.getAllEmployees());
//        employeeService.findByEmail("zarip@gmail.com");
        System.out.println(employeeService.getEmployeeBYId(3L));

        //  пустой кайтып  атат.
//        System.out.println(employeeService.getEmployeeByPosition("Zsas"));


//        jobDao.createJobTable();

//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",2));
//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",3));
//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",5));
//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",3));
//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",2));
//        jobDao.addJob(new Job("zarip","dfvfdfd","fdvdfdfvdf",4));
//        System.out.println(jobDao.getJobById(1L));

//        System.out.println(jobDao.sortByExperience("Desc"));

//        System.out.println(jobDao.getJobByEmployeeId(2L));
//        jobDao.deleteDescriptionColumn();



    }
}
