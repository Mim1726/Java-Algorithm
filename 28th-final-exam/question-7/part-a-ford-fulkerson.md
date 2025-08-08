# Question 7(a): Ford-Fulkerson Algorithm for Maximum Flow

## Problem Statement
Apply the Ford-Fulkerson method to calculate the maximum flow from source S to sink T in the given directed weighted graph.

## Graph Description
Based on the provided information, I'll reconstruct the graph:

```
        A ----7----> D
       /|           /|
      / |          / |
     5  |         6  |
    /   |10      /   |7
   S    |       E    |
    \   |      /|    |
     3  |     / |    |
      \ |    2  |8   |
       \|   /   |    |
        B --5-- C ---
             \      /
              \    /
               \  /
                T
               /|
              4 |
               \|
                F
                |
               12
```

### Edges and Capacities:
- S → A: 5
- S → B: 3  
- A → D: 7
- A → B: 10
- B → C: 5
- B → F: 4
- C → T: 6
- C → F: 5
- D → E: 6
- D → T: 7
- E → B: 2
- E → C: 8
- F → T: 12

## Ford-Fulkerson Algorithm Implementation

### Step 1: Initialize Residual Graph
```
Initial Capacities:
S → A: 5    S → B: 3
A → D: 7    A → B: 10  
B → C: 5    B → F: 4
C → T: 6    C → F: 5
D → E: 6    D → T: 7
E → B: 2    E → C: 8
F → T: 12

All flows initially = 0
```

### Step 2: Find Augmenting Paths using DFS/BFS

#### Iteration 1: S → A → D → T
**Path Found:** S → A → D → T
**Bottleneck:** min(5, 7, 7) = 5
**Flow Added:** 5

**Update Residual Graph:**
- S → A: 5 - 5 = 0 (forward), A → S: 0 + 5 = 5 (backward)
- A → D: 7 - 5 = 2 (forward), D → A: 0 + 5 = 5 (backward)
- D → T: 7 - 5 = 2 (forward), T → D: 0 + 5 = 5 (backward)

**Current Total Flow:** 5

#### Iteration 2: S → B → C → T
**Path Found:** S → B → C → T  
**Bottleneck:** min(3, 5, 6) = 3
**Flow Added:** 3

**Update Residual Graph:**
- S → B: 3 - 3 = 0 (forward), B → S: 0 + 3 = 3 (backward)
- B → C: 5 - 3 = 2 (forward), C → B: 0 + 3 = 3 (backward)  
- C → T: 6 - 3 = 3 (forward), T → C: 0 + 3 = 3 (backward)

**Current Total Flow:** 5 + 3 = 8

#### Iteration 3: Check for More Paths
**Available from S:**
- S → A: 0 (exhausted)
- S → B: 0 (exhausted)

**No direct augmenting paths from S exist.**

Let's check if we can use backward edges or alternative routes...

#### Check Alternative Paths Through Backward Edges:
Since both S → A and S → B are saturated, we need to check if any flow can be rerouted.

Looking at current residual capacities:
- A → B: 10 (unused)
- B → F: 4 (unused)
- F → T: 12 (unused)
- D → E: 6 (unused)
- E → C: 8 (unused)
- E → B: 2 (unused)

But we need a path starting from S, and both outgoing edges are saturated.

#### Detailed Analysis:
After iterations 1 & 2, remaining residual capacities:
- S → A: 0, A → S: 5
- S → B: 0, B → S: 3
- A → D: 2, D → A: 5  
- A → B: 10, B → A: 0
- B → C: 2, C → B: 3
- C → T: 3, T → C: 3
- D → T: 2, T → D: 5
- And other unused edges...

**No augmenting path exists** because:
1. Both edges from S are saturated (capacity 0)
2. No combination of backward flows can create a valid augmenting path from S to T

## Final Result

**Maximum Flow = 8**

### Flow Distribution:
1. **Path 1:** S → A → D → T with flow 5
2. **Path 2:** S → B → C → T with flow 3

### Final Edge Flows:
- f(S,A) = 5
- f(S,B) = 3
- f(A,D) = 5
- f(B,C) = 3
- f(C,T) = 3
- f(D,T) = 5
- All other edges: f = 0

### Verification - Flow Conservation:
- **S (source):** outflow = 5 + 3 = 8, inflow = 0 ✓
- **A:** outflow = 5, inflow = 5 ✓
- **B:** outflow = 3, inflow = 3 ✓  
- **C:** outflow = 3, inflow = 3 ✓
- **D:** outflow = 5, inflow = 5 ✓
- **T (sink):** outflow = 0, inflow = 3 + 5 = 8 ✓

### Min-Cut Verification:
The minimum cut that separates S from T:
**Cut: {S} | {A, B, C, D, E, F, T}**
**Cut Capacity:** c(S,A) + c(S,B) = 5 + 3 = 8

This confirms: **Max Flow = Min Cut = 8** ✓

## Java Implementation

```java
import java.util.*;

public class FordFulkerson {
    static class Edge {
        int to, capacity, flow;
        Edge reverse;
        
        Edge(int to, int capacity) {
            this.to = to;
            this.capacity = capacity;
            this.flow = 0;
        }
    }
    
    private List<List<Edge>> graph;
    private boolean[] visited;
    
    public FordFulkerson(int n) {
        graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        visited = new boolean[n];
    }
    
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(to, capacity);
        Edge backward = new Edge(from, 0);
        forward.reverse = backward;
        backward.reverse = forward;
        
        graph.get(from).add(forward);
        graph.get(to).add(backward);
    }
    
    public int maxFlow(int source, int sink) {
        int totalFlow = 0;
        
        while (true) {
            Arrays.fill(visited, false);
            int pathFlow = dfs(source, sink, Integer.MAX_VALUE);
            
            if (pathFlow == 0) break;
            totalFlow += pathFlow;
        }
        
        return totalFlow;
    }
    
    private int dfs(int node, int sink, int minCapacity) {
        if (node == sink) return minCapacity;
        
        visited[node] = true;
        
        for (Edge edge : graph.get(node)) {
            if (!visited[edge.to] && edge.capacity - edge.flow > 0) {
                int bottleneck = Math.min(minCapacity, edge.capacity - edge.flow);
                int pathFlow = dfs(edge.to, sink, bottleneck);
                
                if (pathFlow > 0) {
                    edge.flow += pathFlow;
                    edge.reverse.flow -= pathFlow;
                    return pathFlow;
                }
            }
        }
        
        return 0;
    }
    
    public static void main(String[] args) {
        // Assuming vertices: S=0, A=1, B=2, C=3, D=4, E=5, F=6, T=7
        FordFulkerson ff = new FordFulkerson(8);
        
        // Add edges based on the graph
        ff.addEdge(0, 1, 5);  // S → A
        ff.addEdge(0, 2, 3);  // S → B
        ff.addEdge(1, 4, 7);  // A → D
        ff.addEdge(1, 2, 10); // A → B
        ff.addEdge(2, 3, 5);  // B → C
        ff.addEdge(2, 6, 4);  // B → F
        ff.addEdge(3, 7, 6);  // C → T
        ff.addEdge(3, 6, 5);  // C → F
        ff.addEdge(4, 5, 6);  // D → E
        ff.addEdge(4, 7, 7);  // D → T
        ff.addEdge(5, 2, 2);  // E → B
        ff.addEdge(5, 3, 8);  // E → C
        ff.addEdge(6, 7, 12); // F → T
        
        int maxFlow = ff.maxFlow(0, 7); // S to T
        System.out.println("Maximum Flow: " + maxFlow);
    }
}
```

**Maximum Flow = 8**
