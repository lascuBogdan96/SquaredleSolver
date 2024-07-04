import org.example.Trie;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrieTest {

    private Trie trie;

    @BeforeEach
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearch() {
        trie.insert("apple");
        assertTrue(trie.search("apple"), "The word 'apple' should be found.");
        assertFalse(trie.search("app"), "The word 'app' should not be found.");
    }

    @Test
    public void testInsertAndSearchPrefix() {
        trie.insert("banana");
        assertTrue(trie.startsWith("ban"), "The prefix 'ban' should be found.");
        assertFalse(trie.startsWith("band"), "The prefix 'band' should not be found.");
    }

    @Test
    public void testInsertAndSearchMultipleWords() {
        trie.insert("grape");
        trie.insert("grapes");
        assertTrue(trie.search("grape"), "The word 'grape' should be found.");
        assertTrue(trie.search("grapes"), "The word 'grapes' should be found.");
        assertFalse(trie.search("grap"), "The word 'grap' should not be found.");
    }

    @Test
    public void testInsertAndCheckPrefixes() {
        trie.insert("apricot");
        trie.insert("appliance");
        assertTrue(trie.startsWith("ap"), "The prefix 'ap' should be found.");
        assertTrue(trie.startsWith("apri"), "The prefix 'apri' should be found.");
        assertFalse(trie.startsWith("apl"), "The prefix 'apl' should not be found.");
    }
}