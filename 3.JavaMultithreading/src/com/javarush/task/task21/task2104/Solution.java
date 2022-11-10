package com.javarush.task.task21.task2104;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/* 
Equals and HashCode
*/

public class Solution {
    private final String first, last;

    public Solution(String first, String last) {
        this.first = first;
        this.last = last;
    }

//    public boolean equals(Object o) {
//        if (this == o) return false;
//        if (o == null || getClass() != o.getClass()) return false;
//        Solution s = (Solution) o;
//
//        boolean result = false;
//        if (first != null && s.first != null && last != null && s.last != null)
//            result = first.equals(s.first) && last.equals(s.last);
//        return result;
//    }

//    public int hashCode() {
//        return 31 * first.hashCode() + last.hashCode();
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!(o instanceof Solution)) return false;
        Solution solution = (Solution) o;
        return Objects.equals(first, solution.first) && Objects.equals(last, solution.last);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last);
    }

    public static void main(String[] args) {
        Set<Solution> s = new HashSet<>();
        s.add(new Solution("Donald", "Duck"));
        System.out.println(s.contains(new Solution("Donald", "Duck")));

        System.out.println(new Solution(null, "Donald").equals(new Solution(null,"Donald")));
    }
}
