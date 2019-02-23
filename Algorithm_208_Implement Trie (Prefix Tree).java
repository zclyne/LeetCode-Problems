// 思路：TreeNode保存每一个字符，每个TreeNode有一个数组next用于指向后继节点
// TreeNode的isWord域用于指示是否存在以当前节点结尾的word

class Trie {

    class TreeNode {
        public boolean isWord = false;
        public TreeNode[] next = new TreeNode[26];
    }

    private TreeNode root;

    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode();
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (curNode.next[word.charAt(i) - 'a'] == null) {
                curNode.next[word.charAt(i) - 'a'] = new TreeNode();
            }
            curNode = curNode.next[word.charAt(i) - 'a'];
        }
        curNode.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode curNode = root;
        for (int i = 0; i < word.length(); i++) {
            if (curNode.next[word.charAt(i) - 'a'] == null) {
                return false;
            }
            curNode = curNode.next[word.charAt(i) - 'a'];
        }
        return curNode.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode curNode = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (curNode.next[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            curNode = curNode.next[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */