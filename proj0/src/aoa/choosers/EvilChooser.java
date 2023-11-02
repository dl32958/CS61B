package aoa.choosers;

import java.util.List;
import edu.princeton.cs.algs4.StdRandom;
import aoa.utils.FileUtils;

public class EvilChooser implements Chooser {
    private String pattern;
    private List<String> wordPool;

    /** Always choose the family with the largest number of words.
     *  Always choose the family with the pattern that comes alphabetically earlier. */
    public EvilChooser(int wordLength, String dictionaryFile) {
        // TODO: Fill in this constructor.
        if (wordLength < 1){
            throw new IllegalArgumentException();
        }
        wordPool = FileUtils.readWordsOfLength(dictionaryFile, wordLength);
        int numWords = wordPool.size();
        if (numWords == 0){
            throw new IllegalStateException();
        }
    }

    @Override
    public int makeGuess(char letter) {
        // TODO: Fill in this method.
        /** find all the possible word families split on the guessed letter
         *  and pick the one with the most words. */
        return 0;
    }

    @Override
    public String getPattern() {
        // TODO: Fill in this method.
        return "";
    }

    @Override
    public String getWord() {
        // TODO: Fill in this method.
        return "";
    }
}
