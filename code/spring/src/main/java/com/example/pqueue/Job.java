package com.example.pqueue;

import java.util.PriorityQueue;
import java.time.LocalDateTime;

public class Job implements Comparable<Job> {
    private static long sequence = 0;

    private int id;
    private String name;
    private int priority;
    private long sequenceNumber;
    private LocalDateTime creationTime;
    private JobStatus status;

    public enum JobStatus {
        PENDING,
        PROCESSING,
        COMPLETED
    }

    public Job(int id, String name, int priority) {
        this.id = id;
        this.name = name;
        this.priority = priority;
        this.sequenceNumber = sequence++;
        this.creationTime = LocalDateTime.now();
        this.status = JobStatus.PENDING;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public int compareTo(Job other) {
        // Higher priority jobs should come first

//        if (this.priority != other.priority) {
//            return Integer.compare(other.priority, this.priority);
//        } else {
//            return Integer.compare(this.sequenceNumber, other.sequenceNumber);
//        }
        if (this.priority != other.priority) {
            return Integer.compare(other.priority, this.priority);
        } else if (!this.creationTime.equals(other.creationTime)) {
            return this.creationTime.compareTo(other.creationTime);
        } else {
            return Integer.compare(this.id, other.id);
        }
    }

    @Override
    public String toString() {
        return "Job{id=" + id + ", name='" + name + "', priority=" + priority +
                ", creationTime=" + creationTime + ", status=" + status + '}';
    }
}

