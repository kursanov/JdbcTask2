package kg.kursanov.service.impl;

import kg.kursanov.dao.JobDao;
import kg.kursanov.dao.impl.JobDaoImpl;
import kg.kursanov.model.Job;
import kg.kursanov.service.JobDaoService;

import java.util.List;

public class DaoServiceImpl implements JobDaoService {

    private  final JobDao jobDao = new JobDaoImpl();

    @Override
    public void createJobTable() {
        jobDao.createJobTable();

    }

    @Override
    public void addJob(Job job) {
        jobDao.addJob(job);

    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDao.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDao.sortByExperience(ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDao.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDao.deleteDescriptionColumn();

    }
}
