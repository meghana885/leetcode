class Solution {
    public int totalFruit(int[] fruits) {
        int maxFruits = 0;
        Map<Integer, Integer> basket = new HashMap<>();
        int start = 0;

        for (int end = 0; end < fruits.length; end++) {
            int fruit = fruits[end];
            basket.put(fruit, basket.getOrDefault(fruit, 0) + 1);

            while (basket.size() > 2) {
                int leftFruit = fruits[start];
                basket.put(leftFruit, basket.get(leftFruit) - 1);
                if (basket.get(leftFruit) == 0) {
                    basket.remove(leftFruit);
                }
                start++;
            }

            maxFruits = Math.max(maxFruits, end - start + 1);
        }

        return maxFruits;
    }
}
