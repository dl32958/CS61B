package aoa.choosers;

import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class RandomChooser implements Chooser {
    private final String chosenWord;
    private String pattern;
    private List<Character> guesses = new ArrayList<>();

    public RandomChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in/change this constructor.
        /** String dictionaryFile: A filename containing the dictionary to select a word from,
         * with one word on each line.*/
        if (wordLength < 1){
            throw new IllegalArgumentException();
        }

        int maxWordLength = 0;
        int count = 0;
        for (String word : FileUtils.readWords(dictionaryFile)) {
            if (word.length() > maxWordLength) {
                maxWordLength = word.length();
            }
            if (word.length() == wordLength){
                count += 1;
            }
        }
        if (count == 0){
            throw new IllegalStateException();
        }
        if (maxWordLength < wordLength){
            throw new IllegalStateException();
        }
        int numWords = FileUtils.readWords(dictionaryFile).size();
        if (numWords == 0){
            throw new IllegalStateException();
        }
        int randomlyChosenWordNumber = StdRandom.uniform(numWords);
        chosenWord = FileUtils.readWords(dictionaryFile).get(randomlyChosenWordNumber);
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        /** Return the number of occurrences of the guessed letter in the secret word.
         * Assume all guesses passed are valid, i.e. they are lowercase letters and have
         * not been guessed before. */
        if (chosenWord.contains(Character.toString(letter))){
            guesses.add(letter);
            getPattern();
        }
        int count = 0;
        for (int i=0; i < chosenWord.length(); i++){
            if (chosenWord.charAt(i) == letter){
                count += 1;
            }
        }return count;

    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        /** Return the current pattern to be displayed for the game using the guesses that have been made.
         * Letters that have not yet been guessed should be displayed as a dash (-).
         * There should be no leading or trailing spaces.
         */
        pattern = chosenWord;
        for (int i=0; i<chosenWord.length(); i++){
            if (guesses.contains(chosenWord.charAt(i))){
                char c = chosenWord.charAt(i);
                pattern = pattern.substring(0,i) + c + pattern.substring(i+1);
            }else {
                pattern = pattern.substring(0,i) + '-' + pattern.substring(i+1);
            }
        }return pattern;
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        // This method should return the secret word being considered by the RandomChooser.
        return chosenWord;
    }
}
