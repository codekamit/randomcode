package com.learning.random;

import ch.qos.logback.core.joran.sanity.Pair;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {

    public static class Pair {
        int element;
        int frequency;
        public Pair(int element, int frequency) {
            this.element = element;
            this.frequency = frequency;
        }
    }

    public static List<List<Integer>> generateQueries() {
        List<List<Integer>> nums = new ArrayList<>();
        nums.add(new ArrayList<>());
        nums.get(0).add(1);
        nums.get(0).add(1);
        nums.add(new ArrayList<>());
        nums.get(1).add(1);
        nums.get(1).add(1);
        nums.add(new ArrayList<>());
        nums.get(2).add(1);
        nums.get(2).add(2);
        nums.add(new ArrayList<>());
        nums.get(3).add(1);
        nums.get(3).add(6);
        nums.add(new ArrayList<>());
        nums.get(4).add(1);
        nums.get(4).add(7);
        nums.add(new ArrayList<>());
        nums.get(5).add(1);
        nums.get(5).add(3);
        nums.add(new ArrayList<>());
        nums.get(6).add(1);
        nums.get(6).add(2);
        nums.add(new ArrayList<>());
        nums.get(7).add(1);
        nums.get(7).add(2);
        nums.add(new ArrayList<>());
        nums.get(8).add(1);
        nums.get(8).add(5);
        nums.add(new ArrayList<>());
        nums.get(9).add(1);
        nums.get(9).add(9);
        nums.add(new ArrayList<>());
        nums.get(10).add(1);
        nums.get(10).add(1);
        nums.add(new ArrayList<>());
        nums.get(11).add(1);
        nums.get(11).add(5);
        nums.add(new ArrayList<>());
        nums.get(12).add(1);
        nums.get(12).add(6);
        nums.add(new ArrayList<>());
        nums.get(13).add(1);
        nums.get(13).add(2);
        nums.add(new ArrayList<>());
        nums.get(14).add(1);
        nums.get(14).add(1);
        nums.add(new ArrayList<>());
        nums.get(15).add(2);
        nums.get(15).add(-1);
        nums.add(new ArrayList<>());
        nums.get(16).add(2);
        nums.get(16).add(-1);
        nums.add(new ArrayList<>());
        nums.get(17).add(2);
        nums.get(17).add(-1);
        nums.add(new ArrayList<>());
        nums.get(18).add(2);
        nums.get(18).add(-1);
        return nums;
    }

    public static Comparator<Pair> customComparator() {
        return new Comparator<Pair>() {
            @Override
            public int compare(Pair pair1, Pair pair2) {
                if(pair1.frequency == pair2.frequency) {
                    return pair1.frequency - pair2.frequency;
                }
                return pair2.frequency - pair1.frequency;
            }
        };
    }

    public static List<Integer> getRemovedElements(List<List<Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        PriorityQueue<Pair> pqueue = new PriorityQueue<>(customComparator());
        for(List<Integer> query : queries) {
            int operation = query.get(0);
            if(operation == 1) {
                int element = query.get(1);
                map.put(element, map.getOrDefault(element, 0) + 1);
                int frequency = map.get(element);
                pqueue.add(new Pair(element, frequency));
            }
            else {
                int exitElement = pqueue.poll().element;
                if(map.get(exitElement) == 1) {
                    map.remove(exitElement);
                }
                else {
                    map.put(exitElement, map.get(exitElement) - 1);
                }
                result.add(exitElement);
            }
        }
        return result;
    }
    public static void main(String[] args) {
        List<List<Integer>> queries = generateQueries();
        System.out.println(getRemovedElements(queries));
        // 1,1,2,6,7,3,2,2,5,9,1,5,6,2,1
        //1-4, 2-4, 3-1, 5-2, 6-2, 7-1, 9-1
    }
}
