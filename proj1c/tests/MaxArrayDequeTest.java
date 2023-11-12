import deque.MaxArrayDeque;
import org.junit.jupiter.api.*;

import java.util.Comparator;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDequeTest{

    private static class MaxArrayDequeComp<T extends Comparable<T>> implements Comparator<T> {
        @Override
        public int compare(T o1, T o2) {
            return o1.compareTo(o2);
        }
    }

    @Test
    void max() {
        Comparator<Integer> c = new MaxArrayDequeComp<>();
        MaxArrayDeque<Integer> mad = new MaxArrayDeque<>(c);
        mad.addLast(1);
        mad.addLast(2);
        mad.addLast(3);
        mad.addLast(4);
        mad.addLast(5);
        mad.addLast(6);
        assertThat(mad.max()).isEqualTo(6);
    }

    @Test
    public void maxTest2(){
        Comparator<String> c = new MaxArrayDequeComp<>();
        MaxArrayDeque<String> mad1 = new MaxArrayDeque<>(c);
        mad1.addLast("a");
        mad1.addLast("b");
        mad1.addLast("c");
        assertThat(mad1.max()).isEqualTo("c");
    }
}


