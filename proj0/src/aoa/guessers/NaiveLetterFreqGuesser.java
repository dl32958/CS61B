package aoa.guessers;

import aoa.utils.FileUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class NaiveLetterFreqGuesser implements Guesser {
    private final List<String> words;

    public NaiveLetterFreqGuesser(String dictionaryFile) {
        words = FileUtils.readWords(dictionaryFile);
    }

    @Override
    /** Makes a guess which ignores the given pattern. */
    public char getGuess(String pattern, List<Character> guesses) {
        return getGuess(guesses);
    }

    /** Returns a map from a given letter to its frequency across all words.
     *  This task is similar to something you did in hw0b! */
    public Map<Character, Integer> getFrequencyMap() {
        // TODO: Fill in this method.
        Map<Character, Integer> map = new HashMap<>();
        for (String word : words){
            for (char letter : word.toCharArray()){
                if (map.containsKey(letter)){
                    map.put(letter, map.get(letter) + 1);
                }else {
                    map.put(letter, 1);
                }
            }
        }return map;
    }

    /** Returns the most common letter in WORDS that has not yet been guessed
     *  (and therefore isn't present in GUESSES). */
    public char getGuess(List<Character> guesses) {
        // TODO: Fill in this method.
        Map<Character, Integer> treemap = new TreeMap<>();
        treemap = this.getFrequencyMap();
        if (treemap.isEmpty()){
            return '?';
        }
        int max = 0;
        char maxkey = 'a';
        for (Character letter : treemap.keySet()){    //for letter in map.keys()
            if (guesses.contains(letter)){
                continue;
            }
            if (treemap.get(letter) > max){
                max = treemap.get(letter);      //map[letter]
                maxkey = letter;
            }
        }return maxkey;
    }

    public static void main(String[] args) {
        NaiveLetterFreqGuesser nlfg = new NaiveLetterFreqGuesser("data/example.txt");
        System.out.println("list of words: " + nlfg.words);
        System.out.println("frequency map: " + nlfg.getFrequencyMap());

        List<Character> guesses = List.of('e', 'l');
        System.out.println("guess: " + nlfg.getGuess(guesses));
    }
}
