package com.learning.random;

import java.lang.reflect.Type;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {

    public static void main(String[] args) {
        Stream<Integer> intStream = Stream.of(1,2,3,4,5,6,7);
        Map<Boolean, List<Integer>> map = intStream.collect(Collectors.partitioningBy(x -> (x > 4 && x % 2 == 0)));
        System.out.println(map.get(true));
        System.out.println(map.get(false));
        String str = "abcdefghijklmnopqrstuvwxyz";

        //Example2
        Map<Boolean, List<Character>> map2 = str.chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.partitioningBy(x -> x.equals('a') || x.equals('e') || x.equals('i') || x.equals('o') || x.equals('u')));
        System.out.println(map2.get(true));
        System.out.println(map2.get(false));

        //Frequency of elements in an array
        int[] nums = {1,1,4,4,4,2,3,11,9,19,10,11,5,4,4,3,3,1,2,9};
        List<Integer> list = new ArrayList<>(Arrays.asList(1,1,4,4,4,2,3,11,9,19,10,11,5,4,4,3,3,1,2,9));
        List<Integer> distinctList = Arrays.stream(nums).distinct().boxed().collect(Collectors.toList());
        List<Integer> distinctArraylist = list.stream().distinct().collect(Collectors.toList());
        System.out.println(distinctList);
        System.out.println(distinctArraylist);

//        Map<Integer, Long> mapFreq = list.stream()
//                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//
//        for(Integer num : mapFreq.keySet()) {
//            System.out.println(num + " " + mapFreq.get(num));
//        }

        Map<Character, Long> mapString = str.chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        for(Character ch : mapString.keySet()) {
            System.out.println(ch + " " + mapString.get(ch));
        }
    }
}
