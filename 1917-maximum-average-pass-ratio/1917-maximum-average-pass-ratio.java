class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        // Max Heap based on the gain of adding an extra student
        java.util.PriorityQueue<double[]> pq = new java.util.PriorityQueue<>(
            (a, b) -> Double.compare(b[0], a[0])
        );
        
        int n = classes.length;
        
        // Precompute gain for each class and push into heap
        for (int[] c : classes) {
            int pass = c[0], total = c[1];
            double gain = gain(pass, total);
            pq.offer(new double[]{gain, pass, total});
        }
        
        // Distribute extra students
        for (int i = 0; i < extraStudents; i++) {
            double[] top = pq.poll();
            int pass = (int) top[1];
            int total = (int) top[2];
            
            pass++;
            total++;
            
            double newGain = gain(pass, total);
            pq.offer(new double[]{newGain, pass, total});
        }
        
        // Calculate final average ratio
        double sum = 0.0;
        while (!pq.isEmpty()) {
            double[] cur = pq.poll();
            sum += cur[1] / cur[2];
        }
        
        return sum / n;
    }
    
    // Gain when adding one student to class
    private double gain(int pass, int total) {
        return (double)(pass + 1) / (total + 1) - (double) pass / total;
    }
}