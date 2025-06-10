import java.util.*;

public class Graph2{
    int v;
    List<List<Integer>> adj;
    public Graph2(int v){
        this.v=v;
        adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
    }

    void addEdge(int v,int u){
        adj.get(v).add(u);
    }

    void bfs(int start){
        boolean[] visited=new boolean[v];
        Queue<Integer> queue=new LinkedList<>();
        visited[start]=true;
        queue.add(start);

        while(!queue.isEmpty()){
            int current=queue.poll();
            System.out.print(current+" ");

            for(int neighbor:adj.get(current)){
                if(!visited[neighbor]){
                    visited[neighbor]=true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args){
        Graph2 g=new Graph2(6);

        g.addEdge(0,1);
        g.addEdge(0,2);
        g.addEdge(1,3);
        g.addEdge(1,4);
        g.addEdge(2,5);

        g.bfs(0);

    }
}