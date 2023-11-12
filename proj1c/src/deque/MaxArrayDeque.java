package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    public Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.c = c;
    }

    public T max(){
        if (this.isEmpty()){
            return null;
        }
        int cur = pickFirst();
        int maxIndex = cur;
        while (get(cur) != null){
            if (this.c.compare(get(cur), get(maxIndex)) > 0){
                maxIndex = cur;
            }
            cur = plusOne(cur);
        }
        return get(maxIndex);
    }

    public T max(Comparator<T> c){
        if (this.isEmpty()){
            return null;
        }
        int cur = pickFirst();
        int maxIndex = cur;
        while (get(cur) != null){
            if (c.compare(get(cur), get(maxIndex)) > 0){
                maxIndex = cur;
            }
            cur = plusOne(cur);
        }
        return get(maxIndex);
    }
}
