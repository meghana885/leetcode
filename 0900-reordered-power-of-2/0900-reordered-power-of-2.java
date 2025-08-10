class Solution {
    public boolean reorderedPowerOf2(int n) {
        String target = sortDigits(n);
        // There are at most powers of 2 up to 2^30 within int range
        for (int i = 0; i < 31; i++) {
            int power = 1 << i; // 2^i
            if (target.equals(sortDigits(power))) {
                return true;
            }
        }
        return false;
    }

    private String sortDigits(int num) {
        char[] arr = String.valueOf(num).toCharArray();
        // Simple selection sort (no imports allowed)
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            char temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        return new String(arr);
    }
}
