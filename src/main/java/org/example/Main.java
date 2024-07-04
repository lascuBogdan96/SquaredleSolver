package org.example;

import java.util.List;

public class Main {

    static void sortList(List<String> list) {
        list.sort((s1, s2) -> {
            int lenCompare = Integer.compare(s1.length(), s2.length());

            // If lengths are the same, compare alphabetically
            if (lenCompare == 0) {
                return s1.compareTo(s2);
            } else {
                return lenCompare;
            }
        });
    }

    static void printResult(List<String> list) {
        char currentStartLetter = list.getFirst().charAt(0);
        System.out.print(list.getFirst().length() + " ");
        for (String str : list) {
            char startLetter = str.charAt(0);

            // Print newline if starting letter changes
            if (startLetter != currentStartLetter) {
                System.out.println();
                System.out.print(str.length() + " ");
                currentStartLetter = startLetter;
            }

            // Print the string
            System.out.print(str + " ");
        }

    }

    public static void main(String[] args) {
        var ssNormal = new SquerdleSolver(new char[][] {
                {'s', 'h', 'r', 'i'},
                {'t', 'u', 'f', 'e'},
                {'a', 'r', 'o', 'w'},
                {'k', 'i', 'p', 'e'}
        });

        var ssExpress = new SquerdleSolver(new char[][] {
                {'y', 'e', 'm'},
                {'c', 'd', 'o'},
                {'a', 'r', 'c'}
        });

        var ss = ssExpress;

        var list = ss.solve();

        sortList(list);
        printResult(list);

    }
}