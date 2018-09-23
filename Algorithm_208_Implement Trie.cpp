#include <iostream>
#include <unordered_set>
#include <vector>
using namespace std;
struct charNode
{
    bool isWord;
    vector<charNode *> nextChars;
    charNode(bool valid) : isWord(valid) { for (int i = 0; i < 26; i++) nextChars.push_back(NULL);}
};
class Trie {
private:
    charNode *root;
public:
    /** Initialize your data structure here. */
    Trie() {
        root = new charNode(false);
    }
    
    /** Inserts a word into the trie. */
    void insert(string word) {
        charNode *tmp = root;
        for (int i = 0; i < word.size(); i++)
        {
            if (!tmp->nextChars[word[i] - 'a']) tmp->nextChars[word[i] - 'a'] = new charNode(false);// doesn't have such character yet
            tmp = tmp->nextChars[word[i] - 'a'];
        }
        tmp->isWord = true; // set isWord of the last character to true
    }
    
    /** Returns if the word is in the trie. */
    bool search(string word) {
        charNode *tmp = root;
        for (int i = 0; i < word.size() && tmp; i++)
        {
            if (!tmp->nextChars[word[i] - 'a']) return false;// doesn't have such character yet
            tmp = tmp->nextChars[word[i] - 'a'];

        }
        return tmp ? tmp->isWord : false;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    bool startsWith(string prefix) {
        charNode *tmp = root;
        int i = 0;
        for (; i < prefix.size() && tmp; i++)
        {
            if (!tmp->nextChars[prefix[i] - 'a']) return false;// doesn't have such character yet
            tmp = tmp->nextChars[prefix[i] - 'a'];
        }
        return tmp ? true : false;
    }
};

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * bool param_2 = obj.search(word);
 * bool param_3 = obj.startsWith(prefix);
 */