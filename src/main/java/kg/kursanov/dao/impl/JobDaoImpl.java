package kg.kursanov.dao.impl;

import kg.kursanov.config.Configration;
import kg.kursanov.dao.JobDao;
import kg.kursanov.model.Job;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDaoImpl implements JobDao {

    private final Connection connection = Configration.getConnection();
    @Override
    public void createJobTable() {

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("""
                            create table if not exists jobs(
                            id serial primary key,
                            position varchar,
                            profession varchar,
                            description varchar,
                            experience int
                            )""");
            System.out.println("Tuzdu");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void addJob(Job job) {
        String query = "insert into jobs(position,profession,description,experience)" +
                "values(?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1,job.getPosition());
            preparedStatement.setString(2,job.getProfession());
            preparedStatement.setString(3,job.getDescription());
            preparedStatement.setInt(4,job.getExperience());
            preparedStatement.executeUpdate();
            System.out.println("koshuldu");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }


    }

    @Override
    public Job getJobById(Long jobId) {
        String query = "select * from jobs where id = ?";

        Job job = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,jobId);

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    job = new Job(
                            resultSet.getLong("id"), resultSet.getString("position"),
                            resultSet.getString("profession"),
                            resultSet.getString("description"),
                            resultSet.getInt("experience")

                    );
                }


            }


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return job;
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        List<Job> jobList = new ArrayList<>();

        String query = "select * from jobs order by experience " + ascOrDesc;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Job job = new Job(
                        resultSet.getLong("id"),
                        resultSet.getString("position"),
                        resultSet.getString("profession"),
                        resultSet.getString("description"),
                        resultSet.getInt("experience"));

                jobList.add(job);
            }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



        return jobList;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        Job job = null;

        String query = "SELECT j.* FROM jobs j " +
                "JOIN employees e ON j.id = e.job_id " +
                "WHERE e.id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1,employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    job = new Job(
                            resultSet.getLong("id"),
                            resultSet.getString("position"),
                            resultSet.getString("profession"),
                            resultSet.getString("description"),
                            resultSet.getInt("experience"));
                }

            }



        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return job;
    }

    @Override
    public void deleteDescriptionColumn() {
        String query = "alter table jobs drop column profession";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.executeUpdate();
            System.out.println("deleted!");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }



    }
}
