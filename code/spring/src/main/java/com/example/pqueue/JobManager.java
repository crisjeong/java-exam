package com.example.pqueue;

import java.util.PriorityQueue;

public class JobManager {
    private PriorityQueue<Job> jobQueue;

    public JobManager() {
        this.jobQueue = new PriorityQueue<>();
    }

    public boolean addJob(Job job) {
        return jobQueue.add(job);
    }

    public void changePriority(Job job, int newPriority) {
        // Remove the job from the queue
        jobQueue.remove(job);

        // Update the priority
        job.setPriority(newPriority);

        // Add the job back to the queue
        jobQueue.add(job);
    }

    public boolean removeJob(Job job) {
        return jobQueue.remove(job);
    }

    public Job getNextJob() {
        return jobQueue.poll();
    }

    public boolean hasJobs() {
        return !jobQueue.isEmpty();
    }
}
