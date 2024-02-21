package com.learning.random;

import ch.qos.logback.core.joran.sanity.Pair;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

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

    public static Integer getDigitArray(int num) {
        return String
                .valueOf(num)
                .chars()
                .mapToObj(x -> Character.getNumericValue((char)x))
                .reduce(0, (a,b) -> a > b ? a : b);
    }

    public static List<List<Integer>> seperateOddandEvenDigits(int num) {
        Map<Boolean, List<Integer>> map = String.valueOf(num)
                .chars()
                .mapToObj(x -> Character.getNumericValue((char)x))
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        List<List<Integer>> result = new ArrayList<>();
        result.add(map.get(true));
        result.add(map.get(false));
        return result;
    }

    public static Map<Character, Long> getFrequencyOfDigits(String number) {
        return number
                .chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public static List<Integer> getDistinctDigits(String number) {
        return number
                .chars()
                .mapToObj(x -> Character.getNumericValue((char)x))
                .distinct()
                .collect(Collectors.toList());
    }

    public static List<Integer> sortedDigitsInReverse(String number) {
        return number
                .chars()
                .mapToObj(x -> Character.getNumericValue((char)x))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    public static String getStringFromCharArray(List<Character> chars) {
//        return chars.stream().map(x -> String.valueOf(x)).collect(Collectors.joining());
        return chars.stream().map(x -> String.valueOf(x)).reduce("", (a, b) -> String.valueOf(a) + String.valueOf(b));
    }

    public static Map<String, String> extractPropertiesFromURL(String url) {
        int right = 0;
        while(right < url.length()) {
            if(url.charAt(right) == '?') {
                right = right + 1;
                break;
            }
            right++;
        }
        String str = url.substring(right, url.length());
        return Stream.of(str.split("&"))
                .map(x -> x.split("="))
                .collect(toMap(x -> x[0], x -> x[1]));
    }

    public static Map<String, String> arrayToMap(String[] arrayOfString) {
        return Arrays.asList(arrayOfString)
                .stream()
                .map(str -> str.split(":"))
                .collect(toMap(str -> str[0], str -> str[1]));
    }

    public static Map<String, String> extractPropsFromURL(String url) {
        return Stream.of(url.split("\\?")[1].split("&"))
                .map(x -> x.split("="))
                .collect(toMap(x -> x[0], x -> x[1]));
    }

    public static List<String> extractUsernameAndDomainFromEmail(String email) {
        return Stream.of(email.split("@"))
                .collect(Collectors.toList());
    }

    public static void printMap(Map<String, String> map) {
        for(String str : map.keySet()) {
            System.out.println(str + " : " + map.get(str));
        }
    }

    public static void main(String[] args) {
        String url = "https://www.example.com/messages?token=jhg334jgh345hjg345j&name=kamitstar&age=32&email=kamitstar@icloud.com";
//        System.out.println(Stream.of('a', 'b'));
//        Map<String, String> map = Stream.of(url.split("\\?")[1].split("&"))
//                .map(x -> x.split("="))
//                .collect(toMap(x -> x[0], x -> x[1]));
    }
}
