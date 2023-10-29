import java.util.List;
import java.util.ArrayList;
import java.util.TreeMap;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        int total = 0;
        if (L.size() == 0) {
            return total;
        } else {
            for (int i = 0; i < L.size(); i++) {
                total += L.get(i);
            }
            return total;
        }
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> lst = new ArrayList<>();
        if (L.size() == 0) {
            return null;
        } else {
            for (int i = 0; i < L.size(); i++) {
                if (L.get(i) % 2 == 0) {
                    lst.add(L.get(i));
                }
            }
            return lst;
        }
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> lst = new ArrayList<>();
        for (int i = 0; i < L1.size(); i++) {
            for (int m = 0; m < L2.size(); m++) {
                if (L1.get(i) == L2.get(m)) {
                    lst.add(L1.get(i));
                }
            }
        } return lst;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int helper = 0;
        for (String item : words) {
            for (int i = 0; i < item.length(); i++) {
                if (item.charAt(i) == c) {
                    helper += 1;
                }
            }
        }
        return helper;
    }
}

