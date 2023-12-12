package com.example.pqueue;

public class JobManagerTest {
    public static void main(String[] args) {
        JobManager jobManager = new JobManager();

        // Adding jobs to the queue
        Job job1 = new Job(1, "Job1", 3);
        Job job2 = new Job(2, "Job2", 1);
        Job job3 = new Job(3, "Job3", 2);
        Job job4 = new Job(4, "Job4", 1);
        Job job5 = new Job(5, "Job5", 2);

        jobManager.addJob(job1);
        jobManager.addJob(job2);
        jobManager.addJob(job3);
        jobManager.addJob(job4);
        jobManager.addJob(job5);

        System.out.println(jobManager);

        // Changing the priority of a job
        jobManager.changePriority(job2, 4);
        jobManager.changePriority(job4, 3);

        // Processing jobs in priority order
        while (jobManager.hasJobs()) {
            Job nextJob = jobManager.getNextJob();
            System.out.println("Processing: " + nextJob);
            // Perform job processing logic here
        }
    }
}
