class Solution {
    public int maximumGain(String s, int x, int y) {
        // Determine which substring gives more points and process that first
        if (x > y) {
            return calculate(s, "ab", x, "ba", y);
        } else {
            return calculate(s, "ba", y, "ab", x);
        }
    }

    private int calculate(String s, String first, int firstVal, String second, int secondVal) {
        int total = 0;
        StringBuilder sb = new StringBuilder();
        
        // First pass: remove all instances of the more valuable pair
        for (char c : s.toCharArray()) {
            int len = sb.length();
            if (len > 0 && sb.charAt(len - 1) == first.charAt(0) && c == first.charAt(1)) {
                sb.deleteCharAt(len - 1);
                total += firstVal;
            } else {
                sb.append(c);
            }
        }

        // Second pass: remove all instances of the less valuable pair
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            int len = temp.length();
            if (len > 0 && temp.charAt(len - 1) == second.charAt(0) && c == second.charAt(1)) {
                temp.deleteCharAt(len - 1);
                total += secondVal;
            } else {
                temp.append(c);
            }
        }

        return total;
    }
}
