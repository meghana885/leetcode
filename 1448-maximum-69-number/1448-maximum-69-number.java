class Solution {
    public int maximum69Number (int num) {
        // Convert number to character array for easy manipulation
        char[] digits = String.valueOf(num).toCharArray();
        
        // Change the first '6' to '9' and stop
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == '6') {
                digits[i] = '9';
                break; // Only one change allowed
            }
        }
        
        // Convert back to integer
        return Integer.parseInt(new String(digits));
    }
}
