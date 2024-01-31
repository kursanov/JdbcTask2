package kg.kursanov.service;

import kg.kursanov.model.Job;

import java.util.List;

public interface JobDaoService {

    void createJobTable();

    void addJob(Job job);

    Job getJobById(Long jobId);

    List<Job> sortByExperience(String ascOrDesc);

    Job getJobByEmployeeId(Long employeeId);

    void deleteDescriptionColumn();

}
