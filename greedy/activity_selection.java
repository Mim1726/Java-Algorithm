package greedy;

import java.util.*;

class Activity {
    int start, end;
    Activity(int start, int end){
        this.start=start;
        this.end=end;
    }
}

public class activity_selection{
    public static int maxActivities(List<Activity> activities){
        activities.sort(Comparator.comparingInt(a->a.end));

        int count=1;
        int lastEndTime=activities.get(0).end;

        for(int i=1;i<activities.size();i++){
            if(activities.get(i).start >= lastEndTime){
                count++;
                lastEndTime=activities.get(i).end;
            }
        }
        return count;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);


        int n=sc.nextInt();

        List<Activity> activities=new ArrayList<>();

        for(int i=0;i<n;i++){
            int start=sc.nextInt();
            int end=sc.nextInt();
            activities.add(new Activity(start, end));
        }

        int result=maxActivities(activities);
        System.out.println("Maximum number of non-overlapping activities: "+result);
    }
}