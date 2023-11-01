package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PatternAwareLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public PatternAwareLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Returns the most common letter in the set of valid words based on the current
     *  PATTERN. */
    //[ally, beta, cool, deal, else, flew, good, hope, ibex]
    public char getGuess(String pattern, List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> map = this.getFreqMapThatMatchesPattern(pattern);
        if (map.isEmpty()){
            return '?';
        }
        int max = 0;
        char maxkey = 'a';
        for (Character letter : map.keySet()){
            if (guesses.contains(letter)){
                continue;
            }
            if (map.get(letter) > max){
                max = map.get(letter);
                maxkey = letter;
            }
        }return maxkey;
    }

    public Map<Character, Integer> getFreqMapThatMatchesPattern(String pattern){
        Map<Character, Integer> map = new TreeMap<>();
        List<String> matchedWords = new ArrayList<>();

        for (String word : words){
            if (word.length() != pattern.length()){
                continue;
            }else {
                boolean flag = true;
                for (int i=0; i<pattern.length(); i++){
                    if ((word.charAt(i) != pattern.charAt(i)) && (pattern.charAt(i) != '-')){
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    matchedWords.add(word);
                }
            }
        }

        for (String word : matchedWords){
            for (Character letter : word.toCharArray()){
                if (map.containsKey(letter)){
                    map.put(letter, map.get(letter) + 1);
                }else {
                    map.put(letter, 1);
                }
            }
        }return map;
    }


    public static void main(String[] args) {
        PatternAwareLetterFreqGuesser palfg = new PatternAwareLetterFreqGuesser("data/example.txt");
        System.out.println(palfg.getGuess("-e--", List.of('e')));
    }
}