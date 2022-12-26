package com.javarush.task.task36.task3611;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CopyOnWriteArraySet;

/* 
Сколько у человека потенциальных друзей?
*/

public class Solution {
    private boolean[][] humanRelationships;

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.humanRelationships = generateRelationships();

        Set<Integer> allFriendsAndPotentialFriends = solution.getAllFriendsAndPotentialFriends(4, 2);
        System.out.println(allFriendsAndPotentialFriends);                              // Expected: [0, 1, 2, 3, 5, 7]
        Set<Integer> potentialFriends = solution.removeFriendsFromSet(allFriendsAndPotentialFriends, 4);
        System.out.println(potentialFriends);                                           // Expected: [2, 5, 7]
    }

    public Set<Integer> getAllFriendsAndPotentialFriends(int index, int deep) {
        Set<Integer> friends = new TreeSet<>(getAllFriends(index));
        friends.addAll(getPotentialFriends(friends, index, deep));
        return friends;
    }

    private Set<Integer> getAllFriends(int index) {
        Set<Integer> humans = new CopyOnWriteArraySet<>();
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                humans.add(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                humans.add(i);
            }
        }
        return humans;
    }

    private Set<Integer> getPotentialFriends(Set<Integer> friends, int exclude, int deep) {
        Set<Integer> potentialFriends = new CopyOnWriteArraySet<>(friends);
        for (int i = 1; i < deep; i++) {
            for (Integer potentialFriend : potentialFriends) {
                potentialFriends.addAll(getAllFriends(potentialFriend));
            }
        }
        potentialFriends.remove(exclude);
        return potentialFriends;
    }

    // Remove from the set the people with whom you already have a relationship
    public Set<Integer> removeFriendsFromSet(Set<Integer> set, int index) {
        for (int i = 0; i < humanRelationships.length; i++) {
            if ((i < index) && (index < humanRelationships.length) && humanRelationships[index][i]) {
                set.remove(i);
            } else if ((i > index) && humanRelationships[i][index]) {
                set.remove(i);
            }
        }
        return set;
    }

    // Return test data
    private static boolean[][] generateRelationships() {
        return new boolean[][]{
                {true},                                                                 //0
                {true, true},                                                           //1
                {false, true, true},                                                    //2
                {false, false, false, true},                                            //3
                {true, true, false, true, true},                                        //4
                {true, false, true, false, false, true},                                //5
                {false, false, false, false, false, true, true},                        //6
                {false, false, false, true, false, false, false, true}                  //7
        };
    }


}