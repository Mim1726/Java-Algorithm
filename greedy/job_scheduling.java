package greedy;

import java.util.*;

class Job {
    char id;       // Job ID (A, B, C, ...)
    int deadline;  // Deadline to finish job
    int profit;    // Profit if done before deadline

    public Job(char id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class job_scheduling {

    public static void jobScheduling(Job[] jobs) {
        // Step 1: Sort jobs by profit in descending order
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find maximum deadline
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 3: Create time slots
        char[] result = new char[maxDeadline];
        boolean[] slot = new boolean[maxDeadline]; // false means free slot

        int totalProfit = 0;

        // Step 4: Place jobs in available slots
        for (Job job : jobs) {
            // Try from last possible slot backwards
            for (int j = job.deadline - 1; j >= 0; j--) {
                if (!slot[j]) {
                    slot[j] = true;
                    result[j] = job.id;
                    totalProfit += job.profit;
                    break;
                }
            }
        }

        // Step 5: Print scheduled jobs
        System.out.print("Scheduled Jobs: ");
        for (char jobId : result) {
            if (jobId != 0) {
                System.out.print(jobId + " ");
            }
        }
        System.out.println("\nTotal Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job('A', 2, 100),
            new Job('B', 1, 19),
            new Job('C', 2, 27),
            new Job('D', 1, 25),
            new Job('E', 3, 15)
        };

        jobScheduling(jobs);
    }
}
