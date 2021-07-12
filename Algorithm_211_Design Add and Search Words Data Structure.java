// 方法：构建类TreeNode，用树形结构来存储单词
// 一个TreeNode中有一个数组children，其元素类型也是TreeNode，长度为26，对应于26个字母
// 布尔值isWordEnd表示当前节点是否是一个单词的结束

class WordDictionary {

    class TreeNode {
        public boolean isWordEnd;
        public TreeNode[] children;
        TreeNode() {
            isWordEnd = false;
            children = new TreeNode[26];
        }
    }

    private TreeNode root;

    /** Initialize your data structure here. */
    public WordDictionary() {
        root = new TreeNode();
    }
    
    public void addWord(String word) {
        addWord(word, 0, root);
    }

    private void addWord(String word, int i, TreeNode node) {
        char c = word.charAt(i);
        int idx = c - 'a';
        if (node.children[idx] == null) {
            node.children[idx] = new TreeNode();
        }
        if (i == word.length() - 1) {
            node.children[idx].isWordEnd = true;
        } else {
            addWord(word, i + 1, node.children[idx]);
        }
    }
    
    public boolean search(String word) {
        return search(word, 0, root);
    }

    private boolean search(String word, int i, TreeNode node) {
        char c = word.charAt(i);
        if (Character.isLetter(c)) {
            int idx = c - 'a';
            if (node.children[idx] == null) {
                return false;
            }
            if (i == word.length() - 1) {
                return node.children[idx].isWordEnd;
            }
            return search(word, i + 1, node.children[idx]);
        } else { // c is '.'
            for (int j = 0; j < 26; j++) {
                if (node.children[j] != null) {
                    if (i == word.length() - 1) {
                        if (node.children[j].isWordEnd) {
                            return true;
                        }
                    } else if (search(word, i + 1, node.children[j])) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */