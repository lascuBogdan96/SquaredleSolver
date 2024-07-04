package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SquerdleSolver {
    Trie trie;
    private static final String WORD_LIST_FILE_PATH = "C:\\Users\\bogdan_george.lascu\\IdeaProjects\\SqueardleSolver\\src\\main\\resources\\wordList.txt";
    private static final int WORDS_MIN_LENGTH = 4;

    private final int x;
    private final int y;
    private final boolean[][] visited;
    private final char [][] grid;
    private final Set<String> availableChars;

    private record Pair(int i, int j) {}

    private boolean isSuitableWord(String word) {
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!availableChars.contains(String.valueOf(c))) {
                return false;
            }
        }
        return true;
    }

    public SquerdleSolver(char [][] grid) {
        trie = new Trie();
        x = grid.length;
        y = grid[0].length;
        visited = new boolean[x][y];
        this.grid = grid;
        availableChars = new HashSet<>();

        for (var row : grid) {
            for (var el : row) {
                availableChars.add(String.valueOf(el));
            }
        }

        Path filePath = Paths.get(WORD_LIST_FILE_PATH);

        try (Stream<String> lines = Files.lines(filePath)) {
            lines.filter(s -> s.length() >= WORDS_MIN_LENGTH)
                    .map(String::toLowerCase)
                    .filter(this::isSuitableWord)
                    .forEach(s -> trie.insert(s));
        } catch (IOException e) {
            System.out.println(WORD_LIST_FILE_PATH + " not found");
        }

    }

    private boolean isValid(int i, int j) {
        return i >= 0 && j >= 0 && i < x && j < x && !visited[i][j];
    }

    private void getIfValid(List<Pair> list, int i, int j) {
        if (isValid(i, j)) {
            list.add(new Pair(i, j));
        }
    }

    private List<Pair> getNeighbours(int i, int j) {
        List<Pair> result = new ArrayList<>();

        getIfValid(result, i - 1, j - 1);
        getIfValid(result, i - 1, j + 1);
        getIfValid(result, i - 1, j);
        getIfValid(result, i + 1, j + 1);
        getIfValid(result, i + 1, j - 1);
        getIfValid(result, i + 1, j);
        getIfValid(result, i, j - 1);
        getIfValid(result, i, j + 1);

        return result;
    }

    private void solve(List<String> result, int i, int j, StringBuilder word) {
        word.append(grid[i][j]);
        if (trie.search(word.toString())) {
            result.add(word.toString());
        }

        visited[i][j] = true;

        if (trie.startsWith(word.toString())) {
            var neighbours = getNeighbours(i, j);

            for (var n : neighbours) {
                solve(result, n.i, n.j, word);
            }
        }

        visited[i][j] = false;
        word.deleteCharAt(word.length() - 1);
    }

    public List<String> solve() {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                solve(result, i, j, new StringBuilder());
            }
        }

        return result.stream().distinct().collect(Collectors.toList());
    }

}
