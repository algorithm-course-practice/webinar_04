package org.example;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Фиксация результатов задачи со звездочкой
 */
public class Results {
    public boolean check_1(String sentence) {
        HashSet<Character> letters = new HashSet<>();

        for (char c : sentence.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters.add(c);
            }
        }

        return letters.size() == 26;
    }

    public boolean check_2(String sentence) {
        if (sentence == null) {
            throw new NullPointerException("Нельзя передавать null");
        }

        if (sentence.isEmpty()) {
            return false;
        }

        Set<Character> uniqueChars = new HashSet<>();
        for (char ch : sentence.toLowerCase().replaceAll("\\d|\\s", "").toCharArray()) {
            if (ch >= 'a' && ch <= 'z') {
                uniqueChars.add(ch);
            }
        }
        return uniqueChars.size() == 26;
    }

    public boolean check_3(String sentence){
        int size = 26;
        int startCharCode = 97;
        int[] alphabet = new int[size];

        if (sentence == null) {
            return false;
        }

        sentence = sentence.replaceAll(" ", "");
        if (sentence.length() < size) {
            return false;
        }

        sentence = sentence.toLowerCase();

        for (int i = 0; i < sentence.length(); i++) {
            int index = (int) sentence.charAt(i) - startCharCode;
            if (index < 0 || index > size) {
                continue;
            }
            alphabet[index] = 1;
        }

        return Arrays.stream(alphabet).sum() == size;
    }

    public boolean check_4(String sentence){
        String replaced = sentence.replaceAll("\\s+", "");
        Set<Character> set = new HashSet<>();
        for (Character ch : replaced.toLowerCase(Locale.ENGLISH).toCharArray()) {
            set.add(ch);
        }
        if(set.size() != 26) return false;

        for(char letter = 'a'; letter <= 'z'; letter++) {
            if(!set.contains(letter)) return false;
        }

        return true;
    }

    public boolean check_5(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        Set<Character> letters = new HashSet<>();

        for (char c : str.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                letters.add(c);
            }
        }
        //english alphabet has 26 letters
        return letters.size() == 26;
    }

    public boolean check_6(String sentence){
        var set = sentence.toLowerCase().chars().boxed().collect(Collectors.toSet());
        for (int i = 'a'; i <= 'z'; i++) {
            if (set.add(i)) {
                return false;
            }
        }
        return true;
    }

    public boolean check_7(String sentence){
        String sen = sentence.replaceAll("\\s+","").toLowerCase();
        if (sen.matches("[a-z]*")){
            char[] charStr = sen.toCharArray();
            Set<Character> temp = new HashSet<>();
            for(Character c  : charStr){
                temp.add(c);
            }
            if (temp.size() == 26){
                return true;
            }
        }
        return false;
    }

    public boolean check_8(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("sentence is null");
        }

        Set<String> set = new HashSet<>();
        char[] chars = sentence.replaceAll("\\s+", "").toCharArray();

        for (char c : chars) {
            set.add(String.valueOf(c).toLowerCase(Locale.ROOT));
        }

        return set.size() == 26;
    }

    public boolean check_9(String sentence) {
        final Map<Character, Integer> map = Stream.iterate('a', x -> (char) (x + 1))
                .limit(26)
                .collect(
                        Collectors.toMap(Function.identity(), x -> 0)
                );

        for (int i = 0; i < sentence.length(); ++i) {
            char c = Character.toLowerCase(sentence.charAt(i));
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            }
        }

        for (Integer value : map.values()) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean check_10(String sentence){
        boolean[] alphabet = new boolean[26];

        for (char c : sentence.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                alphabet[c - 'a'] = true;
            }
        }

        for (boolean present : alphabet) {
            if (!present) {
                return false;
            }
        }

        return true;
    }

    public boolean check_11(String sentence) {
        return 26 ==
                sentence.toUpperCase().chars()
                        .mapToObj(e -> (char) e)
                        .filter(x -> x >= 'A' && x <= 'Z')
                        .distinct()
                        .count();
    }

    public boolean check_12(String sentence){
        return sentence.chars().map(Character::toLowerCase).filter(Character::isLetter).distinct().count() == 26;
    }
}
