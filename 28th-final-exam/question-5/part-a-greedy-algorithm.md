# Question 5(a): Greedy Coin Change Algorithm Failures

## Problem Statement
Given a set of coins C = {2, 7, 11, 17} with infinite frequency, using a greedy algorithm that always chooses the biggest possible coin not greater than the remaining amount X. Find three examples where this algorithm fails to produce the optimal solution.

## Greedy Algorithm Description
1. Choose the largest coin C_i ≤ X
2. Subtract C_i from X
3. Repeat until X = 0

## Three Examples Where Greedy Algorithm Fails

### Example 1: X = 18
**Greedy Solution:**
- Choose 17 (largest ≤ 18), remaining = 18 - 17 = 1
- No coin ≤ 1 available
- **Result: IMPOSSIBLE with greedy**

**Optimal Solution:**
- Choose 11 + 7 = 18 (2 coins)
- **Result: 18 with 2 coins**

### Example 2: X = 14
**Greedy Solution:**
- Choose 11 (largest ≤ 14), remaining = 14 - 11 = 3
- No coin ≤ 3 available
- **Result: IMPOSSIBLE with greedy**

**Optimal Solution:**
- Choose 7 + 7 = 14 (2 coins)
- **Result: 14 with 2 coins**

### Example 3: X = 16
**Greedy Solution:**
- Choose 11 (largest ≤ 16), remaining = 16 - 11 = 5
- Choose 2 (largest ≤ 5), remaining = 5 - 2 = 3
- No coin ≤ 3 available
- **Result: IMPOSSIBLE with greedy**

**Optimal Solution:**
- Choose 2 + 7 + 7 = 16 (3 coins)
- **Result: 16 with 3 coins**

## Additional Examples Where Greedy Produces Suboptimal (but valid) Solutions

### Example 4: X = 22
**Greedy Solution:**
- Choose 17 (largest ≤ 22), remaining = 22 - 17 = 5
- Choose 2 (largest ≤ 5), remaining = 5 - 2 = 3
- Choose 2 (largest ≤ 3), remaining = 3 - 2 = 1
- **Result: IMPOSSIBLE with greedy**

**Optimal Solution:**
- Choose 11 + 11 = 22 (2 coins)
- **Result: 22 with 2 coins**

### Example 5: X = 20
**Greedy Solution:**
- Choose 17 (largest ≤ 20), remaining = 20 - 17 = 3
- No coin ≤ 3 available
- **Result: IMPOSSIBLE with greedy**

**Optimal Solution:**
- Choose 2 + 2 + 2 + 7 + 7 = 20 (5 coins)
- Or: 2 + 9 + 9? No, we don't have coin 9
- Actually: Let's try 11 + 7 + 2 = 20 (3 coins)
- **Result: 20 with 3 coins**

## Conclusion
The greedy algorithm fails because it makes locally optimal choices without considering the global impact. By always choosing the largest coin, it can leave remainders that cannot be satisfied by the available coin denominations, even when alternative combinations would work perfectly.

## Key Insight
The coin set {2, 7, 11, 17} is **not canonical**, meaning the greedy algorithm doesn't always produce optimal solutions. For a coin set to be canonical, the greedy algorithm must always yield the minimum number of coins for any amount.
