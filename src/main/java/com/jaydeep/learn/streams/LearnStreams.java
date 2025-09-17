package com.jaydeep.learn.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LearnStreams {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 10, 11, 9, 8, 7, 6));
        System.out.println(list);

        // map
        System.out.println("------- map -------");
        System.out.println(list.stream().map(x -> x * 2).collect(Collectors.toList()));

        // filter
        System.out.println("------- filter -------");
        System.out.println(list.stream().filter(x -> x % 2 == 0).collect(Collectors.toList()));

        // sorted
        System.out.println("------- sorted -------");
        System.out.println(list.stream().sorted().toList());
        System.out.println(list.stream().sorted(Comparator.reverseOrder()).toList());

        // sorted(comparator)
        System.out.println("------- sorted(Coparator) -------");
        System.out.println(list.stream().sorted((a, b) -> a % 10 - b % 10).toList());

        // flatMap
        List<List<Integer>> listOfList = new ArrayList<>();
        listOfList.add(Arrays.asList(1, 2, 3, 4));
        listOfList.add(Arrays.asList(5, 6, 7, 8));
        listOfList.add(Arrays.asList(9, 10, 11, 12, 13));
        System.out.println("------- flatMap -------");
        System.out.println(listOfList.stream().flatMap(List::stream).toList());
        System.out.println(listOfList.stream().flatMap(a -> a.stream().map(b -> b * 10)).toList());

        // distinct
        list.clear();
        list.addAll(Arrays.asList(1, 2, 3, 4, 4, 3, 2, 1));
        System.out.println("------- distinct -------");
        System.out.println(list.stream().distinct().toList());

        // peek
        list.clear();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5));
        System.out.println("------- peek -------");

        list.stream()
                .map(a -> a * 10)
                .filter(a -> a / 10 % 2 == 0)
                .peek(System.out::println)
                .toList(); // stream is lazy, nothing would be calculated till you terminate the stream

        // skip
        list.clear();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println("------- skip -------");
        System.out.println(list.stream().skip(2).toList());

        // limit
        System.out.println("------- limit -------");
        System.out.println(list.stream().limit(4).toList());

        // boxed
        // Converts a primitive stream (IntStream, LongStream, DoubleStream) into a Stream of wrapper objects (Stream<Integer>, Stream<Long>, Stream<Double>).
        System.out.println("------- boxed -------");
        System.out.println(IntStream.range(1, 5)
                .boxed()
                .collect(Collectors.toList()));

        // mapToInt / mapToLong / mapToDouble
        // avoids overhead of boxing and provides faster results on sum, average, min, max etc.
        System.out.println("------- mapToInt -------");
        List<String> strings = new ArrayList<>();
        strings.addAll(Arrays.asList("1", "2", "3", "4"));
        System.out.println(strings.stream().mapToInt(Integer::parseInt).average().orElse(0.0));

        // count
        System.out.println("------- count -------");
        System.out.println(list.stream().filter(x -> x % 2 == 0).count());

        // min(comparator)
        System.out.println("------- min(comparator) -------");
        strings.clear();
        strings.addAll(Arrays.asList("jaydeep", "harsh", "prabhav", "palak"));
        System.out.println(strings.stream().min((a, b) -> {
            if (a.length() == b.length())
                return b.charAt(0) - a.charAt(0);
            else
                return a.length() - b.length();
        }).orElse(""));

        // max(comparator)
        System.out.println("------- max(comparator) -------");
        System.out.println(strings.stream().max(Comparator.comparingInt(String::length)).orElse(""));

        // forEach
        System.out.println("------- forEach -------");
        System.out.println(strings);
        strings.parallelStream().forEach(name -> System.out.println("Name:" + name));

        // forEachOrdered
        System.out.println("------- forEachOrdered -------");
        System.out.println(strings);
        strings.parallelStream().forEachOrdered(name -> System.out.println("Name:" + name));

        // toArray
        System.out.println("------- toArray -------");
        Object[] arr = strings.stream().toArray();
        System.out.println(Arrays.toString(arr));

        // reduce
        System.out.println("------- reduce -------");
        System.out.println(strings.stream().reduce("", (a, b) -> a + "|" + b));

        // findFirst
        System.out.println("------- findFirst -------");
        System.out.println(strings.stream().findFirst().orElse("None"));

        // findAny
        System.out.println("------- findAny -------");
        System.out.println(strings.stream().findAny().orElse("None"));
        System.out.println(strings.parallelStream().findAny().orElse("None"));

        // anyMatch
        System.out.println("------- anyMatch -------");
        System.out.println(strings.stream().anyMatch(name -> name.contains("a")));

        // allMatch
        System.out.println("------- allMatch -------");
        System.out.println(strings.stream().allMatch(name -> name.contains("ay")));

        // noneMatch
        System.out.println("------- noneMatch -------");
        System.out.println(strings.stream().noneMatch(name -> name.contains("a")));

        // collect
        System.out.println("------- Collectors.toMap -------");
        System.out.println(strings.stream().collect(Collectors.toMap(String::toString, String::length)));
        System.out.println(strings.stream().collect(Collectors.toMap(String::length, String::toString, (a, b) -> a + "," + b)));

        // partitionBy
        System.out.println("------- Collectors.partitioningBy -------");
        Map<Boolean, List<String>> map = strings.stream().collect(Collectors.partitioningBy(name -> name.length() < 6));
        System.out.println(map);
        System.out.println(strings.stream().collect(Collectors.partitioningBy(name -> name.length() < 6, Collectors.counting())));

        // groupingBy
        System.out.println("------- Collectors.groupingBy -------");
        System.out.println(strings.stream().collect(Collectors.groupingBy(String::length)));
        System.out.println(strings.stream().collect(Collectors.groupingBy(String::length, Collectors.counting())));

        // joining
        System.out.println("------- Collectors.joining -------");
        System.out.println(strings.stream().collect(Collectors.joining()));
        System.out.println(strings.stream().collect(Collectors.joining("|")));
        System.out.println(strings.stream().collect(Collectors.joining("|", "<-", "->")));

        // summarizingInt / summarizingLong / summarizingDouble
        System.out.println("------- Collectors.summarizingInt/summarizingLong/summarizingDouble -------");
        System.out.println(strings.stream().collect(Collectors.summarizingInt(String::length)));
        System.out.println(strings.stream().collect(Collectors.summarizingLong(String::length)));
        System.out.println(strings.stream().collect(Collectors.summarizingDouble(String::length)));

        // summingInt / averagingInt
        System.out.println("------- Collectors.summingInt -------");
        System.out.println(strings.stream().collect(Collectors.summingInt(String::length)));

    }
}
