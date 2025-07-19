class Solution {
    public java.util.List<String> removeSubfolders(String[] folder) {
        // Sort the folder paths lexicographically
        java.util.Arrays.sort(folder);
        java.util.List<String> result = new java.util.ArrayList<>();

        // Iterate through sorted folders
        for (String path : folder) {
            // Check if current folder is not a subfolder of the last added one
            if (result.isEmpty() || !path.startsWith(result.get(result.size() - 1) + "/")) {
                result.add(path);
            }
        }

        return result;
    }
}
