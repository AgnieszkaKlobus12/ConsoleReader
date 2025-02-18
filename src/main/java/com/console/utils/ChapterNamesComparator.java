package com.console.utils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChapterNamesComparator implements Comparator<String> {
    private static final Pattern SPLIT_PATTERN = Pattern.compile("(\\d+|\\D+)");

    @Override
    public int compare(String s1, String s2) {
        List<String> parts1 = splitParts(s1);
        List<String> parts2 = splitParts(s2);

        int minLength = Math.min(parts1.size(), parts2.size());

        for (int i = 0; i < minLength; i++) {
            String part1 = parts1.get(i);
            String part2 = parts2.get(i);

            boolean isDigit1 = part1.matches("\\d+");
            boolean isDigit2 = part2.matches("\\d+");

            if (isDigit1 && isDigit2) {
                long num1 = Long.parseLong(part1);
                long num2 = Long.parseLong(part2);
                if (num1 != num2)
                    return Long.compare(num1, num2);
            } else if (isDigit1) {
                return -1; // Numbers come before words
            } else if (isDigit2) {
                return 1;
            } else {
                int cmp = part1.compareToIgnoreCase(part2);
                if (cmp != 0)
                    return cmp;
            }
        }
        return Integer.compare(parts1.size(), parts2.size());
    }

    private List<String> splitParts(String s) {
        List<String> parts = new ArrayList<>();
        Matcher matcher = SPLIT_PATTERN.matcher(s);
        while (matcher.find()) {
            parts.add(matcher.group());
        }
        return parts;
    }
}

