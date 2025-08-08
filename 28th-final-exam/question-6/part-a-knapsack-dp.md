# Question 6(a): Knapsack Problem - Restaurant Buffet

## Problem Statement
You're at a restaurant buffet with 5 unlimited food options. Each food provides pleasure and takes up stomach space. Your stomach can hold up to 8 liters. Find the maximum pleasure by choosing the best combination.

**Note: The food table is not provided in the question, so I'll create a sample table for demonstration.**

## Sample Food Table
| Food | Pleasure | Volume (liters) | Pleasure/Volume Ratio |
|------|----------|-----------------|----------------------|
| A    | 12       | 3              | 4.00                 |
| B    | 8        | 2              | 4.00                 |
| C    | 15       | 4              | 3.75                 |
| D    | 6        | 2              | 3.00                 |
| E    | 10       | 3              | 3.33                 |

## Solution Approach: Unbounded Knapsack Problem

Since each food has unlimited quantity, this is an **Unbounded Knapsack Problem**.

### Dynamic Programming Solution

#### Step 1: Define DP State
- `dp[w]` = maximum pleasure achievable with exactly `w` liters of stomach capacity

#### Step 2: Base Case
- `dp[0] = 0` (no capacity = no pleasure)

#### Step 3: Recurrence Relation
```
dp[w] = max(dp[w], dp[w - volume[i]] + pleasure[i])
for all foods i where volume[i] ≤ w
```

#### Step 4: Implementation Steps

**Initialize DP Array:**
```
dp = [0, 0, 0, 0, 0, 0, 0, 0, 0]  // indices 0 to 8
```

**Fill DP Table:**

| Capacity | Food A (12,3) | Food B (8,2) | Food C (15,4) | Food D (6,2) | Food E (10,3) | Max Pleasure |
|----------|---------------|---------------|---------------|---------------|---------------|--------------|
| 0        | 0             | 0             | 0             | 0             | 0             | **0**        |
| 1        | -             | -             | -             | -             | -             | **0**        |
| 2        | -             | 8             | -             | 6             | -             | **8**        |
| 3        | 12            | 8             | -             | 6+6=12        | 10            | **12**       |
| 4        | 12            | 8+8=16        | 15            | 6+6=12        | 10            | **16**       |
| 5        | 12+8=20       | 16            | 15            | 6+6+6=18      | 10+8=18       | **20**       |
| 6        | 12+12=24      | 8+8+8=24      | 15+8=23       | 6+6+6=18      | 10+10=20      | **24**       |
| 7        | 24            | 24+8=32       | 23            | 18+8=26       | 20+8=28       | **32**       |
| 8        | 24+8=32       | 32            | 15+15=30      | 18+6+6=30     | 20+8=28       | **32**       |

**Detailed Calculation for Capacity 8:**
- Option 1: Use Food A twice + Food B once = 12 + 12 + 8 = 32 (volume: 3+3+2 = 8)
- Option 2: Use Food B four times = 8 + 8 + 8 + 8 = 32 (volume: 2+2+2+2 = 8)
- Option 3: Use Food C twice = 15 + 15 = 30 (volume: 4+4 = 8)

#### Step 5: Trace Back Solution

**Maximum Pleasure = 32**

**Optimal Combinations:**
1. 2 × Food A + 1 × Food B = 2×12 + 1×8 = 32 pleasure, 2×3 + 1×2 = 8 liters
2. 4 × Food B = 4×8 = 32 pleasure, 4×2 = 8 liters

## Algorithm Implementation

```java
public class BuffetKnapsack {
    static class Food {
        int pleasure;
        int volume;
        String name;
        
        Food(String name, int pleasure, int volume) {
            this.name = name;
            this.pleasure = pleasure;
            this.volume = volume;
        }
    }
    
    public static int maxPleasure(Food[] foods, int capacity) {
        int[] dp = new int[capacity + 1];
        
        for (int w = 1; w <= capacity; w++) {
            for (Food food : foods) {
                if (food.volume <= w) {
                    dp[w] = Math.max(dp[w], 
                                   dp[w - food.volume] + food.pleasure);
                }
            }
        }
        
        return dp[capacity];
    }
    
    public static void main(String[] args) {
        Food[] foods = {
            new Food("A", 12, 3),
            new Food("B", 8, 2),
            new Food("C", 15, 4),
            new Food("D", 6, 2),
            new Food("E", 10, 3)
        };
        
        int maxCapacity = 8;
        int result = maxPleasure(foods, maxCapacity);
        System.out.println("Maximum pleasure: " + result);
    }
}
```

## Step-by-Step Execution

### Step 1: Initialize
```
dp = [0, 0, 0, 0, 0, 0, 0, 0, 0]
```

### Step 2: For capacity = 1
No food fits (all require ≥ 2 liters)
```
dp = [0, 0, 0, 0, 0, 0, 0, 0, 0]
```

### Step 3: For capacity = 2
- Food B (8, 2): dp[2] = max(0, dp[0] + 8) = 8
- Food D (6, 2): dp[2] = max(8, dp[0] + 6) = 8
```
dp = [0, 0, 8, 0, 0, 0, 0, 0, 0]
```

### Step 4: For capacity = 3
- Food A (12, 3): dp[3] = max(0, dp[0] + 12) = 12
- Food B (8, 2): dp[3] = max(12, dp[1] + 8) = 12
- Food E (10, 3): dp[3] = max(12, dp[0] + 10) = 12
```
dp = [0, 0, 8, 12, 0, 0, 0, 0, 0]
```

### Step 5: Continue until capacity = 8
Final result: dp[8] = 32

## Complexity Analysis
- **Time Complexity:** O(n × W) where n = number of foods, W = capacity
- **Space Complexity:** O(W) for the DP array

## Final Answer
**Maximum Pleasure Achievable: 32**

**Optimal Strategy:** 
- Take 2 portions of Food A and 1 portion of Food B
- OR take 4 portions of Food B
- Both achieve 32 pleasure units using exactly 8 liters of stomach capacity
