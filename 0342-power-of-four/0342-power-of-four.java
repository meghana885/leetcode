class Solution {
    public boolean isPowerOfFour(int n) {
        // n must be positive
        if (n <= 0) return false;
        
        // Keep dividing by 4 while possible
        while (n % 4 == 0) {
            n /= 4;
        }
        
        // If we reduced to 1, it's a power of four
        return n == 1;
    }
}

