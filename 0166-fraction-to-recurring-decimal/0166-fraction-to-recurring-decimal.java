class Solution {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) return "0";

        StringBuilder result = new StringBuilder();

        // Handle sign
        if ((numerator < 0) ^ (denominator < 0)) result.append("-");

        // Convert to long to avoid overflow
        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        // Append integer part
        result.append(num / den);
        num %= den;

        if (num == 0) return result.toString();

        result.append(".");

        // Use arrays to track remainders and positions (no imports)
        long[] remainders = new long[10000];
        int[] positions = new int[10000];
        int index = 0;

        while (num != 0) {
            for (int i = 0; i < index; i++) {
                if (remainders[i] == num) {
                    result.insert(positions[i], "(");
                    result.append(")");
                    return result.toString();
                }
            }

            remainders[index] = num;
            positions[index] = result.length();
            index++;

            num *= 10;
            result.append(num / den);
            num %= den;
        }

        return result.toString();
    }
}
