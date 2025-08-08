# Question 5(b): Finding Third Smallest Value in Array

## Problem Statement
Given an array A, devise an efficient solution to find the third smallest value. Analyze space and time complexity.

## Algorithm: Linear Scan Approach

### Pseudocode
```
function findThirdSmallest(A):
    if length(A) < 3:
        return "Not enough elements"
    
    first = infinity
    second = infinity  
    third = infinity
    
    for each element x in A:
        if x < first:
            third = second
            second = first
            first = x
        elif x < second and x != first:
            third = second
            second = x
        elif x < third and x != second and x != first:
            third = x
    
    if third == infinity:
        return "Not enough distinct elements"
    
    return third
```

### Java Implementation
```java
public class ThirdSmallest {
    public static Integer findThirdSmallest(int[] arr) {
        if (arr.length < 3) {
            System.out.println("Array must have at least 3 elements");
            return null;
        }
        
        int first = Integer.MAX_VALUE;
        int second = Integer.MAX_VALUE;
        int third = Integer.MAX_VALUE;
        
        for (int num : arr) {
            if (num < first) {
                third = second;
                second = first;
                first = num;
            } else if (num < second && num != first) {
                third = second;
                second = num;
            } else if (num < third && num != second && num != first) {
                third = num;
            }
        }
        
        if (third == Integer.MAX_VALUE) {
            System.out.println("Not enough distinct elements");
            return null;
        }
        
        return third;
    }
    
    public static void main(String[] args) {
        int[] A = {10, 12, 3, 6, 17, 2, 9, 11, 20, 25, 71, 4};
        Integer result = findThirdSmallest(A);
        System.out.println("Third smallest element: " + result);
    }
}
```

## Complexity Analysis

### Time Complexity: O(n)
- Single pass through the array
- Each element is processed in constant time O(1)
- Overall: O(n) where n is the array size

### Space Complexity: O(1)
- Only uses three variables (first, second, third)
- No additional data structures that grow with input size
- Constant extra space

## Simulation on A = {10, 12, 3, 6, 17, 2, 9, 11, 20, 25, 71, 4}

| Step | Element | First | Second | Third | Action |
|------|---------|-------|--------|-------|--------|
| 0    | -       | ∞     | ∞      | ∞     | Initialize |
| 1    | 10      | 10    | ∞      | ∞     | 10 < ∞ (first) |
| 2    | 12      | 10    | 12     | ∞     | 12 < ∞ (second), 12 ≠ 10 |
| 3    | 3       | 3     | 10     | 12    | 3 < 10 (first) |
| 4    | 6       | 3     | 6      | 10    | 6 < 10 (second), 6 ≠ 3 |
| 5    | 17      | 3     | 6      | 10    | 17 < 10 (third), 17 ≠ 6, 17 ≠ 3 |
| 6    | 2       | 2     | 3      | 6     | 2 < 3 (first) |
| 7    | 9       | 2     | 3      | 6     | 9 < 6 (third), 9 ≠ 3, 9 ≠ 2 |
| 8    | 11      | 2     | 3      | 6     | 11 ≮ 6 (no change) |
| 9    | 20      | 2     | 3      | 6     | 20 ≮ 6 (no change) |
| 10   | 25      | 2     | 3      | 6     | 25 ≮ 6 (no change) |
| 11   | 71      | 2     | 3      | 6     | 71 ≮ 6 (no change) |
| 12   | 4       | 2     | 3      | 4     | 4 < 6 (third), 4 ≠ 3, 4 ≠ 2 |

**Final Result: Third smallest = 4**

**Verification:**
Sorted array: {2, 3, 4, 6, 9, 10, 11, 12, 17, 20, 25, 71}
- First smallest: 2
- Second smallest: 3  
- Third smallest: 4 ✓

## Alternative Approaches

### 1. Sorting Approach
- Sort the array and return the third element
- Time Complexity: O(n log n)
- Space Complexity: O(1) or O(n) depending on sorting algorithm

### 2. Priority Queue (Min-Heap)
- Use a min-heap to extract three smallest elements
- Time Complexity: O(n log n)
- Space Complexity: O(n)

### 3. Quick Select
- Use partition logic to find the third smallest
- Average Time Complexity: O(n)
- Worst Case Time Complexity: O(n²)
- Space Complexity: O(1)

## Conclusion
The linear scan approach is the most efficient with O(n) time and O(1) space complexity, making it optimal for this specific problem of finding the third smallest element.
