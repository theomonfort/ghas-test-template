package com.octodemo.vuln;

import java.util.ArrayList; // used below
import java.util.List;      // used below
import java.util.Map;       // java/unused-import - unused import

/**
 * Code-quality smells (NOT security) detected by CodeQL `security-and-quality`.
 * Triggers maintainability alerts: unused imports, unused locals,
 * identical comparisons, container-size comparison, and reference equality on strings.
 */
public class QualitySmells {

    public boolean identicalComparison(int x) {
        // java/comparison-of-identical-expressions: always true
        if (x == x) {
            return true;
        }
        return false;
    }

    public int unusedLocal(List<Integer> values) {
        int unused = 42; // java/unused-local-variable
        int total = 0;
        for (int v : values) {
            total += v;
        }
        return total;
    }

    public boolean stringReferenceEquality(String a) {
        String b = "hello";
        // java/reference-equality-of-boxed-types / string == comparison
        return a == b;
    }

    public boolean sizeCheck(List<String> items) {
        // java/inefficient-empty-string-test style: comparing size() instead of isEmpty()
        if (items.size() == 0) {
            return true;
        }
        return false;
    }

    public List<String> makeList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        return list;
    }

    public boolean usesMap(Map<String, String> ignored) {
        return false;
    }
}
