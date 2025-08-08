# Question 6(b): Longest Increasing Subsequence (LIS)

## Problem Statement
Given array A = {2, 1, 10, 9, 15, 12, 16, 13}, calculate the length of the longest increasing subsequence using dynamic programming.

## Dynamic Programming Approach

### Algorithm Overview
- `dp[i]` = length of longest increasing subsequence ending at index i
- For each element, check all previous elements and extend the longest subsequence

### Step-by-Step Solution

#### Step 1: Initialize
```
Array A = [2, 1, 10, 9, 15, 12, 16, 13]
Indices = [0, 1,  2, 3,  4,  5,  6,  7]
dp     = [1, 1,  1, 1,  1,  1,  1,  1]  // Each element forms LIS of length 1
```

#### Step 2: Fill DP Table

| i | A[i] | Check Previous Elements | dp[i] Calculation | dp[i] |
|---|------|------------------------|-------------------|-------|
| 0 | 2    | None                   | 1                 | 1     |
| 1 | 1    | A[0]=2 > 1            | 1                 | 1     |
| 2 | 10   | A[0]=2 < 10, A[1]=1 < 10 | max(dp[0]+1, dp[1]+1) = max(2,2) = 2 | 2 |
| 3 | 9    | A[0]=2 < 9, A[1]=1 < 9, A[2]=10 > 9 | max(dp[0]+1, dp[1]+1) = max(2,2) = 2 | 2 |
| 4 | 15   | A[0]=2 < 15, A[1]=1 < 15, A[2]=10 < 15, A[3]=9 < 15 | max(dp[0]+1, dp[1]+1, dp[2]+1, dp[3]+1) = max(2,2,3,3) = 3 | 3 |
| 5 | 12   | A[0]=2 < 12, A[1]=1 < 12, A[2]=10 < 12, A[3]=9 < 12, A[4]=15 > 12 | max(2,2,3,3) = 3 | 3 |
| 6 | 16   | A[0]=2 < 16, A[1]=1 < 16, A[2]=10 < 16, A[3]=9 < 16, A[4]=15 < 16, A[5]=12 < 16 | max(2,2,3,3,4,4) = 4 | 4 |
| 7 | 13   | A[0]=2 < 13, A[1]=1 < 13, A[2]=10 < 13, A[3]=9 < 13, A[4]=15 > 13, A[5]=12 < 13, A[6]=16 > 13 | max(2,2,3,3,4) = 4 | 4 |

#### Step 3: Detailed Calculations

**For i = 2 (A[2] = 10):**
- A[0] = 2 < 10 ✓ → dp[2] = max(dp[2], dp[0] + 1) = max(1, 2) = 2
- A[1] = 1 < 10 ✓ → dp[2] = max(dp[2], dp[1] + 1) = max(2, 2) = 2

**For i = 3 (A[3] = 9):**
- A[0] = 2 < 9 ✓ → dp[3] = max(dp[3], dp[0] + 1) = max(1, 2) = 2
- A[1] = 1 < 9 ✓ → dp[3] = max(dp[3], dp[1] + 1) = max(2, 2) = 2
- A[2] = 10 > 9 ✗ → skip

**For i = 4 (A[4] = 15):**
- A[0] = 2 < 15 ✓ → dp[4] = max(dp[4], dp[0] + 1) = max(1, 2) = 2
- A[1] = 1 < 15 ✓ → dp[4] = max(dp[4], dp[1] + 1) = max(2, 2) = 2
- A[2] = 10 < 15 ✓ → dp[4] = max(dp[4], dp[2] + 1) = max(2, 3) = 3
- A[3] = 9 < 15 ✓ → dp[4] = max(dp[4], dp[3] + 1) = max(3, 3) = 3

**For i = 5 (A[5] = 12):**
- A[0] = 2 < 12 ✓ → dp[5] = max(1, 2) = 2
- A[1] = 1 < 12 ✓ → dp[5] = max(2, 2) = 2
- A[2] = 10 < 12 ✓ → dp[5] = max(2, 3) = 3
- A[3] = 9 < 12 ✓ → dp[5] = max(3, 3) = 3
- A[4] = 15 > 12 ✗ → skip

**For i = 6 (A[6] = 16):**
- A[0] = 2 < 16 ✓ → dp[6] = max(1, 2) = 2
- A[1] = 1 < 16 ✓ → dp[6] = max(2, 2) = 2
- A[2] = 10 < 16 ✓ → dp[6] = max(2, 3) = 3
- A[3] = 9 < 16 ✓ → dp[6] = max(3, 3) = 3
- A[4] = 15 < 16 ✓ → dp[6] = max(3, 4) = 4
- A[5] = 12 < 16 ✓ → dp[6] = max(4, 4) = 4

**For i = 7 (A[7] = 13):**
- A[0] = 2 < 13 ✓ → dp[7] = max(1, 2) = 2
- A[1] = 1 < 13 ✓ → dp[7] = max(2, 2) = 2
- A[2] = 10 < 13 ✓ → dp[7] = max(2, 3) = 3
- A[3] = 9 < 13 ✓ → dp[7] = max(3, 3) = 3
- A[4] = 15 > 13 ✗ → skip
- A[5] = 12 < 13 ✓ → dp[7] = max(3, 4) = 4
- A[6] = 16 > 13 ✗ → skip

#### Final DP Array
```
dp = [1, 1, 2, 2, 3, 3, 4, 4]
```

### Finding the Actual LIS

**Length of LIS = max(dp) = 4**

**One possible LIS:** [2, 9, 12, 16] or [1, 9, 12, 16] or [2, 10, 12, 16]

Let's trace back one LIS ending at index 6 (value 16):
- dp[6] = 4, A[6] = 16
- Look for dp[i] = 3 where A[i] < 16 → found at i=4 (A[4]=15) or i=5 (A[5]=12)
- Choose i=5: dp[5] = 3, A[5] = 12
- Look for dp[i] = 2 where A[i] < 12 → found at i=2 (A[2]=10) or i=3 (A[3]=9)
- Choose i=3: dp[3] = 2, A[3] = 9
- Look for dp[i] = 1 where A[i] < 9 → found at i=0 (A[0]=2) or i=1 (A[1]=1)
- Choose i=1: dp[1] = 1, A[1] = 1

**LIS: [1, 9, 12, 16]**

## Implementation

```java
import java.util.*;

public class LIS {
    public static int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        
        return Arrays.stream(dp).max().orElse(0);
    }
    
    public static List<Integer> findLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        int[] parent = new int[n];
        Arrays.fill(dp, 1);
        Arrays.fill(parent, -1);
        
        int maxLength = 1;
        int maxIndex = 0;
        
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    parent[i] = j;
                }
            }
            if (dp[i] > maxLength) {
                maxLength = dp[i];
                maxIndex = i;
            }
        }
        
        // Reconstruct LIS
        List<Integer> lis = new ArrayList<>();
        int current = maxIndex;
        while (current != -1) {
            lis.add(nums[current]);
            current = parent[current];
        }
        
        Collections.reverse(lis);
        return lis;
    }
    
    public static void main(String[] args) {
        int[] A = {2, 1, 10, 9, 15, 12, 16, 13};
        
        int length = lengthOfLIS(A);
        List<Integer> lis = findLIS(A);
        
        System.out.println("Array: " + Arrays.toString(A));
        System.out.println("Length of LIS: " + length);
        System.out.println("One LIS: " + lis);
    }
}
```

## Complexity Analysis

### Time Complexity: O(n²)
- Outer loop runs n times
- Inner loop runs up to n times for each outer iteration
- Overall: O(n²)

### Space Complexity: O(n)
- DP array of size n
- Additional space: O(n)

## Optimized Approach (Binary Search)

For better efficiency, we can use binary search with a patience sorting approach:

```java
public static int lengthOfLISOptimized(int[] nums) {
    List<Integer> tails = new ArrayList<>();
    
    for (int num : nums) {
        int pos = Collections.binarySearch(tails, num);
        if (pos < 0) {
            pos = -(pos + 1);
        }
        if (pos == tails.size()) {
            tails.add(num);
        } else {
            tails.set(pos, num);
        }
    }
    
    return tails.size();
}
```

**Optimized Complexity:**
- Time: O(n log n)
- Space: O(n)

## Final Answer

**Length of Longest Increasing Subsequence = 4**

**Example LIS:** [1, 9, 12, 16]

**All possible LIS of length 4:**
- [1, 9, 12, 16]
- [1, 9, 12, 13]  
- [1, 10, 12, 16]
- [1, 10, 12, 13]
- [2, 9, 12, 16]
- [2, 9, 12, 13]
- [2, 10, 12, 16]
- [2, 10, 12, 13]
