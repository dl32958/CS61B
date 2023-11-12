import deque.ArrayDeque;
import deque.Deque;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDequeTest {

    @Test
    public void addFirstTestBasic(){
        Deque<String> ad1 = new ArrayDeque<>();

        ad1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(ad1.toList()).containsExactly("back").inOrder();

        ad1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(ad1.toList()).containsExactly("middle", "back").inOrder();

        ad1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addLastTestBasic(){
        Deque<String> ad1 = new ArrayDeque<>();
        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    public void addFirstAndAddLastTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        ad1.addLast(0);   // [0]
        ad1.addLast(1);   // [0, 1]
        ad1.addFirst(-1); // [-1, 0, 1]
        ad1.addLast(2);   // [-1, 0, 1, 2]
        ad1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(ad1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    @Test
    public void isEmptyTest(){
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.isEmpty()).isEqualTo(true);
        ad1.addFirst(0);
        assertThat(ad1.isEmpty()).isEqualTo(false);
        ad1.addLast(1);
        assertThat(ad1.isEmpty()).isEqualTo(false);
    }

    @Test
    public void sizeTest(){
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.size()).isEqualTo(0);
        ad1.addFirst(1);
        assertThat(ad1.size()).isEqualTo(1);
        ad1.addFirst(2);
        assertThat(ad1.size()).isEqualTo(2);
        ad1.addLast(3);
        assertThat(ad1.size()).isEqualTo(3);
    }

    @Test
    public void getTest(){
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.get(0)).isEqualTo(null);
        assertThat(ad1.get(-5)).isEqualTo(null);
        ad1.addLast(0);                               //[0]
        assertThat(ad1.get(0)).isEqualTo(0);
        ad1.addLast(1);                               //[0, 1]
        assertThat(ad1.get(1)).isEqualTo(1);
        ad1.addLast(2);                               //[0, 1, 2]
        assertThat(ad1.get(2)).isEqualTo(2);
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque<Integer> ad1 = new ArrayDeque<>();
        assertThat(ad1.removeFirst()).isEqualTo(null);
        assertThat(ad1.removeLast()).isEqualTo(null);
        ad1.addLast(0);
        ad1.addLast(1);
        ad1.addLast(2);
        ad1.addLast(3);
        ad1.addLast(4);  //[0, 1, 2, 3, 4]
        int first = ad1.removeFirst();  //[1, 2, 3. 4] 0
        int last = ad1.removeLast();    //[1, 2, 3] 4
        assertThat(first).isEqualTo(0);
        assertThat(last).isEqualTo(4);
        assertThat(ad1.toList()).containsExactly(1, 2, 3).inOrder();
    }

    @Test
    // addLast without toList()
    public void iteratorTest() {
        Deque<String> ad1 = new ArrayDeque<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1).containsExactly("front", "middle", "back");
    }

    @Test
    public void testEqualDeques() {
        Deque<String> ad1 = new ArrayDeque<>();
        Deque<String> ad2 = new ArrayDeque<>();

        ad1.addLast("front");
        ad1.addLast("middle");
        ad1.addLast("back");

        ad2.addLast("front");
        ad2.addLast("middle");
        ad2.addLast("back");

        assertThat(ad1.equals(ad2)).isEqualTo(true);
    }

    @Test
    public void toStringTest(){
        Deque<String> ad1 = new ArrayDeque<>();

        ad1.addLast("front"); // after this call we expect: ["front"]
        ad1.addLast("middle"); // after this call we expect: ["front", "middle"]
        ad1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(ad1).containsExactly("front", "middle", "back");
    }

}
