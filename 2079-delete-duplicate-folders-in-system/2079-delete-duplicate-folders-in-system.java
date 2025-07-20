class Solution {
    class TrieNode {
        String name;
        java.util.Map<String, TrieNode> children = new java.util.HashMap<>();
        String serial;
        boolean toDelete = false;

        TrieNode(String name) {
            this.name = name;
        }
    }

    public java.util.List<java.util.List<String>> deleteDuplicateFolder(java.util.List<java.util.List<String>> paths) {
        TrieNode root = new TrieNode("");

        // Step 1: Build the trie
        for (java.util.List<String> path : paths) {
            TrieNode node = root;
            for (String folder : path) {
                if (!node.children.containsKey(folder)) {
                    node.children.put(folder, new TrieNode(folder));
                }
                node = node.children.get(folder);
            }
        }

        // Step 2: Serialize subtrees and detect duplicates
        java.util.Map<String, java.util.List<TrieNode>> map = new java.util.HashMap<>();
        serialize(root, map);

        // Step 3: Mark duplicates
        for (java.util.List<TrieNode> group : map.values()) {
            if (group.size() > 1) {
                for (TrieNode node : group) {
                    node.toDelete = true;
                }
            }
        }

        // Step 4: Collect remaining paths
        java.util.List<java.util.List<String>> result = new java.util.ArrayList<>();
        collect(root, new java.util.ArrayList<>(), result);
        return result;
    }

    private String serialize(TrieNode node, java.util.Map<String, java.util.List<TrieNode>> map) {
        if (node.children.isEmpty()) {
            return "";
        }

        java.util.List<String> parts = new java.util.ArrayList<>();
        for (String childName : node.children.keySet()) {
            TrieNode child = node.children.get(childName);
            String childSerial = serialize(child, map);
            parts.add(childName + "(" + childSerial + ")");
        }

        java.util.Collections.sort(parts);
        String serial = "";
        for (String s : parts) {
            serial += s;
        }

        node.serial = serial;
        if (!map.containsKey(serial)) {
            map.put(serial, new java.util.ArrayList<>());
        }
        map.get(serial).add(node);
        return serial;
    }

    private void collect(TrieNode node, java.util.List<String> path, java.util.List<java.util.List<String>> result) {
        for (TrieNode child : node.children.values()) {
            if (child.toDelete) continue;
            path.add(child.name);
            result.add(new java.util.ArrayList<>(path));
            collect(child, path, result);
            path.remove(path.size() - 1);
        }
    }
}
