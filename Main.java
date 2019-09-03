import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

	public static void main(String[] args) {
		int[][] jobs = {{0,3},{1,9},{2,6}};
		System.out.println(new Solution().solution(jobs));
	}
}

class Solution {
	class Job implements Comparable<Job>{
		int arrival; // 작업이 온 시간
		int process; // 작업 수행 시간
		public Job(int arrival, int process) {
			this.arrival = arrival;
			this.process = process;
		}
		@Override
		public int compareTo(Job j) {
			return this.process - j.process;
		}
		// for Debugging
//		@Override 
//		public String toString() {
//			return "arrival: " + this.arrival + " , process: " + this.process;
//		}
	}
    public int solution(int[][] jobs) {
        int curTime = 0;
        int processedJobs = 0;
        int numOfJobs = jobs.length;
        int sumOfProcessingTimes = 0;
        Arrays.sort(jobs, new Comparator<int[]>() {
			public int compare(final int[] entry1,final int[] entry2) {
				final int time1 = entry1[0];
				final int time2 = entry2[0];
				return Integer.compare(time1, time2);
			}
		});
        Queue<Job> jobList = new LinkedList<Job>();
        for (int[] job: jobs) {
        	jobList.add(new Job(job[0], job[1]));
        }
        
        PriorityQueue<Job> q = new PriorityQueue<>();
        
        Job curJob = null;
        while(processedJobs < numOfJobs) {
        	if (curJob != null) {
        		curTime += curJob.process;
    			sumOfProcessingTimes += (curTime-curJob.arrival);
    			processedJobs++;
    			curJob = null;
        	}
        	
        	while (!jobList.isEmpty() && curTime >= jobList.peek().arrival) {
        		q.add(jobList.poll());
        	}
        	
        	if (curJob == null) {
        		if (!q.isEmpty())
        			curJob = q.poll();
        		else if (!jobList.isEmpty()){
        			curTime = jobList.peek().arrival;
        		}
        	}
        }

        return sumOfProcessingTimes/numOfJobs;
    }
}