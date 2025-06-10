import java.util.*;

public class low_value{
    int timer;
    List<List<Integer>> adj;
    boolean[] visited;
    int[] tin,low;
    
    low_value(int v){
        adj=new ArrayList<>();
        for(int i=0;i<v;i++){
            adj.add(new ArrayList<>());
        }
        visited=new boolean[v];
        tin=new int[v];
        low=new int[v];
        timer=0;
    }
    void addEdge(int u,int v){
        adj.get(u-1).add(v-1);
        adj.get(v-1).add(u-1);
    }

    void lowValue(){
        for(int i=0;i<adj.size();i++){
            if(!visited[i]){
                dfs(i,-1);
            }
        }

        System.out.println("Low values for each node:");
        for(int i=0;i<low.length;i++){
            System.out.println("Node "+(i+1)+" : "+low[i]);
        }
    }
    void dfs(int u, int parent){
        visited[u]=true;
        low[u]=tin[u]=++timer;
        
        for(int v:adj.get(u)){
            if(v==parent) continue;

            if(visited[v]){
                low[u]=Math.min(low[u],tin[v]);
            }

            else{
                dfs(v,u);
                low[u]=Math.min(low[u],low[v]);
            }
        }
    }
    public static void main(String[] args){
        int v=10;
        low_value l=new low_value(v);

        l.addEdge(1, 2);
        l.addEdge(1, 3);
        l.addEdge(2, 3);
        l.addEdge(3, 4);
        l.addEdge(4, 5);
        l.addEdge(4, 6);
        l.addEdge(5, 6);
        l.addEdge(6, 7);
        l.addEdge(7, 8);
        l.addEdge(7, 9);
        l.addEdge(8, 9);
        l.addEdge(9, 10);

        l.lowValue();
    }
}