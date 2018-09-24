#include <iostream>
#include <vector>
using namespace std;
class WordDictionary {
private:
    struct wordNode
    {
        bool isWord;
        vector<wordNode *> next;
        wordNode(bool valid = false) : isWord(valid)
        {
            for (int i = 0; i < 26; i++) next.push_back(NULL);
        }
    };
    wordNode *root;
public:
    /** Initialize your data structure here. */
    WordDictionary() {
        root = new wordNode();
    }
    
    /** Adds a word into the data structure. */
    void addWord(string word) {
        wordNode *tmp = root;
        for (int i = 0; i < word.size(); i++)
        {
            if (!tmp->next[word[i] - 'a']) tmp->next[word[i] - 'a'] = new wordNode(); // no such character
            tmp = tmp->next[word[i] - 'a'];
        }
        tmp->isWord = true;
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    bool search(string word) {
        return dfs(root, word, 0);
    }
    bool dfs(wordNode *cur, string word, int pos)
    {
        // if (pos == word.size()) return cur->isWord;
        // if (word[pos] == '.')
        // {
        //     for (int i = 0; i < 26; i++) if (cur->next[i] && dfs(cur->next[i], word, pos + 1)) return true;
        //     return false;
        // }
        // else return cur->next[word[pos] - 'a'] ? dfs(cur->next[word[pos] - 'a'], word, pos + 1) : false;
        for (int i = pos; i < word.size(); i++)
        {
            if (word[i] == '.')
            {
                for (int j = 0; j < 26; j++)
                {
                    if (cur->next[j] && dfs(cur->next[j], word, i + 1)) return true;
                }
                return false;
            }
            else if (cur->next[word[i] - 'a']) cur = cur->next[word[i] - 'a'];
            else return false;
        }
        return cur->isWord;
    }
};

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * bool param_2 = obj.search(word);
 */