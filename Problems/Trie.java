/**
 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. 
 * There are various applications of this data structure, such as autocomplete and spellchecker.
 * 
 * Implement the Trie class:
 * 
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 */
package Problems;

public class Trie {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isWord = false;

        public TrieNode() {
        }
    }

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String word) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (trieNode.children[c - 'a'] == null) {
                trieNode.children[c - 'a'] = new TrieNode();
            }
            trieNode = trieNode.children[c - 'a'];
        }
        trieNode.isWord = true;
    }

    public boolean search(String word) {
        return search(word, 1);
    }

    public boolean startsWith(String prefix) {
        return search(prefix, 2);
    }

    public boolean search(String word, int type) {
        TrieNode trieNode = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (trieNode.children[c - 'a'] == null)
                return false;
            trieNode = trieNode.children[c - 'a'];
        }
        return type == 1 ? trieNode.isWord : true;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple")); // return True
        System.out.println(trie.search("app")); // return False
        System.out.println(trie.startsWith("app")); // return True
        trie.insert("app");
        System.out.println(trie.search("app")); // return True
    }
}
