package com.learning.random;

import java.util.List;
import java.util.Map;
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
    }
}
