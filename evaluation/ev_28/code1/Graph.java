package evaluation.ev_28.code1;

import java.util.*;

public class Graph{
    static class node{
        int city;
        int fuel;
        node(int city,int fuel){
            this.city=city;
            this.fuel=fuel;
        }
    }

    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);

        int c=sc.nextInt();
        int r=sc.nextInt();
        int k=sc.nextInt();
        int l=sc.nextInt();

        List<List<Integer>> graph=new ArrayList<>();
        for(int i=0;i<=c;i++){
            graph.add(new ArrayList<>());
        }

        for(int i=0;i<r;i++){
            int u=sc.nextInt();
            int v=sc.nextInt();
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        Queue<node> queue=new LinkedList<>();
        boolean[] visited=new boolean[c+1];
        int[] minFuelUsed=new int[c+1];

        Arrays.fill(minFuelUsed, Integer.MAX_VALUE);

        queue.add(new node(l, 0));
        visited[l]=true;
        minFuelUsed[l]=0;

        while(!queue.isEmpty()){
            node current=queue.poll();
            int city=current.city;
            int fuelUsed=current.fuel;

            for(int neighbour:graph.get(city)){
                int nextFuel=fuelUsed+1;
                if(nextFuel <= k && nextFuel < minFuelUsed[neighbour]){
                    minFuelUsed[neighbour]=nextFuel;
                    queue.add(new node(neighbour, nextFuel));
                }
            }
        }
        int cnt=0;
        for(int i=1;i<=c;i++){
            if(minFuelUsed[i] <= k){
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}
