package com.zipcodewilmington.arrayutility;

import com.sun.tools.javac.util.ArrayUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by leon on 3/6/18.
 */
public class ArrayUtility<T> {
    T[] array;

    public ArrayUtility(T[] array){
        this.array = array;
    }

    public Integer countDuplicatesInMerge(T[] arrayToMerge, T valueToEvaluate) {
        Stream<T> s = Stream.concat(Stream.of(array), Stream.of(arrayToMerge));
        int dups = s.filter(value -> value == valueToEvaluate)
                .toArray()
                .length;
        return dups;
    }

    public T getMostCommonFromMerge(T[] arrayToMerge) {
        HashMap<T, Integer> occurrencesOfElements = new HashMap<>();
        Stream<T> streamOfMergedArrays = Stream.concat(Stream.of(array), Stream.of(arrayToMerge));
        streamOfMergedArrays.forEach(element ->
                occurrencesOfElements.put(element, occurrencesOfElements.getOrDefault(element, 0)+1));

        Integer highestOccurrences = occurrencesOfElements.values()
                .stream()
                .max(Integer::compareTo)
                .get();

        Stream<Map.Entry<T, Integer>> streamOfOccurrencesOfElements =
                occurrencesOfElements.entrySet().stream();

        T mostCommon = streamOfOccurrencesOfElements
                .filter(entry -> entry.getValue() >= highestOccurrences)
                .findFirst()
                .get()
                .getKey();

        return mostCommon;
    }

    public Integer getNumberOfOccurrences(T valueToEvaluate) {
        Stream<T> arrayStream = Stream.of(array);
        Integer numberOfOccurrences = arrayStream
                .filter(entry -> entry == valueToEvaluate)
                .toArray()
                .length;
        return numberOfOccurrences;
    }

    public T[] removeValue(T valueToRemove) {
        Stream<T> arrayStream = Stream.of(array);
        List<T> newArrayList = arrayStream.filter(entry -> entry != valueToRemove).collect(Collectors.toList());
        T[] newArray = Arrays.copyOf(array, newArrayList.size());
        for (int i = 0; i < newArrayList.size(); i++){
            newArray[i] = newArrayList.get(i);
        }
        return newArray; // is there a better way to do this?
    }
}
