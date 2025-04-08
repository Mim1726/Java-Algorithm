import java.io.*;
import java.util.*;

public class Graph{
    private int vertices;
    private int edges;
    private List<List<Integer>> adj;

    public Graph(String filename) throws IOException{
        adj=new ArrayList<>();
        readGraphFromFile(filename);
    }

    void readGraphFromFile(String filename) throws IOException{
        BufferedReader reader=new BufferedReader(new FileReader(filename));
        String line= reader.readLine();
        String[] parts=line.split(" ");
        vertices=Integer.parseInt(parts[0]);
        edges=Integer.parseInt(parts[1]);

        for(int i=0;i<vertices;i++){
            adj.add(new ArrayList<>());
        }

        while ((line=reader.readLine())!=null) {
            parts=line.split(" ");
            int u=Integer.parseInt(parts[0]);
            int v=Integer.parseInt(parts[1]);
            addEdge(u, v);
        }
        reader.close();
    }

    public void addVertex(int n){
        for(int i=0;i<n;i++){
            adj.add(new ArrayList<>());
        }
        vertices+=n;
    }

    public void addEdge(int u,int v){
        adj.get(u).add(v);
    }

    public int getVertices(){
        return vertices;
    }

    public List<Integer> getAdj(int vertex){
        return adj.get(vertex);
    }

    public void displayGraph(){
        for(int i=0;i<adj.size();i++){
            System.out.print(i+"->");
            for(int adjVertex: adj.get(i)){
                System.out.print(adjVertex+" ");
            }
            System.out.println();
        }
    }

    public void dfs(int start){
        boolean[] visited=new boolean[vertices];
        System.out.print("DFS Traversal starting from vertex "+start+": ");
        dfsUtil(start, visited);
        System.out.println();
    }

    void dfsUtil(int vertex,boolean[] visited){
        visited[vertex]=true;
        System.out.print(vertex+" ");
        for(int neighbour: adj.get(vertex)){
            if(!visited[neighbour]){
                dfsUtil(neighbour, visited);
            }
        }
    }

    public List<Integer> topologicalSort(){
        Stack<Integer> stack=new Stack<>();
        boolean[] visited=new boolean[vertices];
        List<Integer> topoOrder=new ArrayList<>();

        for(int i=0;i<vertices;i++){
            if(!visited[i]){
                topologicalSortUtil(i, visited, stack);
            }
        }

        while(!stack.isEmpty()){
            topoOrder.add(stack.pop());
        }
        return topoOrder;
    }

    void topologicalSortUtil(int vertex,boolean[] visited, Stack<Integer> stack){
        visited[vertex]=true;

        for(int neighbour:adj.get(vertex)){
            if(!visited[neighbour]){
                topologicalSortUtil(neighbour, visited, stack);
            }
        }
        stack.push(vertex);
    }

    public static void main(String[] args)throws IOException{
        Graph graph=new Graph("input.txt");

        System.out.println("Graph adjacency list:");
        graph.displayGraph();

        System.out.println("\nPerforming DFS starting from vertex 5:");
        graph.dfs(5);

        System.out.println("\nPerforming Topological Sort:");
        List<Integer> topoOrder = graph.topologicalSort();
        System.out.println("Topological Sort order:"+topoOrder);
    }
}
