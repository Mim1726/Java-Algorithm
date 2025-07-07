package evaluation;

import java.util.*;

class Activity {
    String start_d;
    int start_t;
    String end_d;
    int end_t;

    int start;
    int end;

    Activity(String a, int b, String c, int d, Map<String, Integer> map) {
        start_d = a;
        start_t = b;
        end_d = c;
        end_t = d;
        start =map.get(a);
        end =map.get(c);
    }
}

public class knapsack{
    public static int maxCnt(List<Activity> activities) {
        activities.sort((a,b)->{
            if (a.end != b.end)
                return a.end-b.end;
            else
                return a.end_t-b.end_t;
        });

        int cnt=1;
        Activity last=activities.get(0);

        for(int i=1;i<activities.size();i++){
            Activity curr=activities.get(i);

            if(curr.start > last.end || (curr.start== last.end && curr.start_t >= last.end_t)){
                cnt++;
                last=curr;
            }
        }
        return cnt;
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        Map<String, Integer> map=new HashMap<>();
        map.put("sun", 0);
        map.put("mon", 1);
        map.put("tue", 2);
        map.put("wed", 3);
        map.put("thu", 4);
        map.put("fri", 5);
        map.put("sat", 6);

        int n=sc.nextInt();
        List<Activity> activities=new ArrayList<>();

        for(int i=0;i<n;i++){
            String a=sc.next();
            int b=sc.nextInt();
            String c=sc.next();
            int d=sc.nextInt();

            activities.add(new Activity(a,b,c,d,map));
        }
        
        System.out.println(maxCnt(activities));
    }
}

