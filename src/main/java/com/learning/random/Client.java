package com.learning.random;

import ch.qos.logback.core.joran.sanity.Pair;

import javax.crypto.spec.PSource;
import javax.sound.midi.SysexMessage;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
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

    public static String joinStringArray(List<String> strList) {
        return strList.stream()
                .collect(Collectors.joining(":", "[", "]"));
    }

    public static List<Integer> sortList(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static List<Character> getCharArrayFromString(String str) {
        Map<Character, Long> map = str
                .chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        return str.chars().mapToObj(x -> (char)x).collect(Collectors.toList());
    }

    public static int[] nextGreaterElement(int[] arr, int n) {
        int[] result = new int[arr.length];
        Stack<Integer> stk = new Stack<>();
        for(int idx = n - 1; idx >= 0; idx--) {
            int curr = arr[idx];
            if(stk.isEmpty()) {
                stk.push(curr);
                result[idx] = -1;
            }
            else {
                if(curr >= stk.peek()) {
                    while(!stk.isEmpty() && curr >= stk.peek()) {
                        stk.pop();
                    }
                    if(stk.isEmpty()) {
                        idx = idx + 1;
                    }
                    else {
                        result[idx] = stk.peek();
                        stk.push(curr);
                    }
                }
                else {
                    result[idx] = stk.peek();
                    stk.push(curr);
                }
            }
        }
        return result;
    }

    public static boolean checkIfArmstrongNumber(String num) {
        int value = num.chars()
                .mapToObj(x -> (char)x)
                .map(x -> Integer.parseInt(String.valueOf(x)))
                .map(x -> (int)Math.pow(x, 3))
                .reduce(0, (a, b) -> a + b);
        return String.valueOf(value).equals(num);
    }

    public static List<Integer> findAllNumbersStartingWithOne(List<Integer> nums) {
        return nums.stream()
                .map(x -> String.valueOf(x))
                .filter(x -> x.startsWith("1"))
                .map(x -> Integer.parseInt(x))
                .collect(Collectors.toList());
    }

    public static List<Integer> findDuplicateElements(List<Integer> nums) {
        return nums.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1)
                .map(x -> x.getKey())
                .collect(Collectors.toList());
    }

    public static Integer findFirstElementInList(List<Integer> nums) {
        return nums.stream()
                .findFirst()
                .orElse(null);
    }

    public static Long findTotalElementsInList(List<Integer> nums) {
        return nums.stream().count();
    }

    public static int findMaxValueInList(List<Integer> nums) {
//        return nums.stream().reduce(0, (a, b) -> a > b ? a : b);
        return nums.stream()
                .collect(Collectors.summarizingInt(Integer::intValue))
                .getMax();
    }

    public static Character findFirstNonRepeatingCharacter(String str) {
        return str.chars()
                .mapToObj(x -> (char)x)
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, counting()))
                .entrySet()
                .stream()
                .filter(x -> x.getValue() > 1L)
                .map(x -> x.getKey())
                .findFirst()
                .orElse(null);
    }

    public static boolean findIfListContainsDuplicates(List<Integer> nums) {
        return nums.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .map(x -> x.getValue() > 1 ? true : false)
                .reduce(false, (a, b) -> a || b);
    }

    public static void testConcurrency(Map<String, String> languageMap) {
        languageMap.put("Maaike", "Java");
        languageMap.put("Seán", "C#");
        for (String key : languageMap.keySet()) {
            System.out.println(key + " loves coding");
            languageMap.remove(key);
        }
    }

    public static void testConcurrency(ConcurrentMap<String, String> languageMap) {
        languageMap.put("Maaike", "Java");
        languageMap.put("Seán", "C#");
        for (String key : languageMap.keySet()) {
            System.out.println(key + " loves coding");
            languageMap.remove(key);
        }
    }

    public static void testConcurrency(ArrayList<Integer> nums) {
        for(int num : nums) {
            nums.remove(0);
        }
    }

    private static ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static Future<Integer> getVotes(int voteId) {
        return executorService.submit(() -> {
            Thread.sleep(1000);
            System.out.println("Vote Id : "+voteId+ "; Printed by : "+Thread.currentThread().getName());
            return voteId;
        });
    }

    public static void recersive(String str, int index, HashSet<Integer> map, List<String> result, StringBuilder sb) {
        if(index == str.length()) {
            result.add(sb.toString());
            return;
        }
        for(int idx = 0; idx < str.length(); idx++) {
            if(!map.contains(idx)) {
                sb.append(str.charAt(idx));
                map.add(idx);
                recersive(str, index + 1, map, result, sb);
                sb.deleteCharAt(sb.length() - 1);
                map.remove(idx);
            }
        }
    }

    private static void swap(char[] chars, int first, int second) {
        char temp = chars[first];
        chars[first] = chars[second];
        chars[second] = temp;
    }

    public static String generateStringFromCharArray(char[] chars) {
        StringBuilder sb = new StringBuilder();
        for(int idx = 0; idx < chars.length; idx++) {
            sb.append(chars[idx]);
        }
        return sb.toString();
    }
    public static void recursiveNoSpace(char[] chars, int index, List<String> result) {
        if(index == chars.length) {
            String str = generateStringFromCharArray(chars);
            result.add(str);
            return;
        }

        for(int idx = index; idx < chars.length; idx++) {
            swap(chars, index, idx);
            recursiveNoSpace(chars, index + 1, result);
            swap(chars, index, idx);
        }
    }

    public static List<String> allStringPermutations(String str) {
        List<String> result = new ArrayList<>();
        char[] chars = str.toCharArray();
        recursiveNoSpace(chars, 0, result);
        return result;
    }

    public static String reverseWordsInString(String str) {
        List<String> list = Stream.of(str.split(" ")).collect(Collectors.toList());
        String reversedString = IntStream.rangeClosed(1, list.size())
                .boxed()
                .map(x -> list.get(list.size() - x))
                .collect(Collectors.joining(" "));
        System.out.println(reversedString);
        return reversedString;
    }







    private static String getSubstring(String str, int startIdx, int endIdx) {
        StringBuilder sb = new StringBuilder();
        sb.append("");
        for(int idx = startIdx; idx <= endIdx; idx++) {
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }


    public static String reverseWords(String str) {
        //"  This is a good   weather"
        int right = str.length() - 1;
        int left = str.length() - 1;
        StringBuilder sb = new StringBuilder();
        while(left >= 0) {
            //Check for space at the end
            while(str.charAt(left) == ' ') {
                left--;
            }
            //Adding trailing space
            sb.append(getSubstring(str, left + 1, right));
            right = left;
            while(str.charAt(left) != ' ') {
                left--;
            }
            //Adding words
            sb.append(getSubstring(str, left + 1, right));
            if(left < 0) {
                break;
            }
        }
        return sb.toString();
    }

    private static String reversedAgain(String str) {
        StringBuilder sb = new StringBuilder();
        int left = str.length() - 1;
        int right = str.length() - 1;
        while(str.charAt(left) == ' ') {
            left--;
        }
        right = left;

        while(left >= 0) {
            while(left >= 0 && str.charAt(left) != ' ') {
                left--;
            }
            sb.append(str.substring(left + 1, right + 1));
            if(left < 0) {
                break;
            }
            right  = left;
            while(left >= 0 && str.charAt(left) == ' ') {
                left--;
            }
            if(left < 0) {
                break;
            }
            sb.append(str.substring(left + 1, right + 1));
            right = left;
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        System.out.println(reversedAgain("This is called the most     ultimate   man      "));
    }
}
