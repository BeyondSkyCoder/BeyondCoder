package DataStructureIterator;

import java.util.*;

public class Flattern2DVector_iterator {
    /*
        implement iterator for a two dimension array
    */

    private Iterator<ArrayList<Integer>> outerIterator;
    private Iterator<Integer> innerIterator;
    private Iterator<Integer> lastInnerIterator;

    public Flattern2DVector_iterator(ArrayList<ArrayList<Integer>> vec2d) {
        outerIterator = vec2d.iterator();
        moveInnerToNextValid();
    }

    public void moveInnerToNextValid() {
        while ((innerIterator == null || !innerIterator.hasNext()) && outerIterator.hasNext()) {
            innerIterator = outerIterator.next().iterator();
        }
    }

    public int next() {
        lastInnerIterator = innerIterator;
        int result = lastInnerIterator.next();

        moveInnerToNextValid();     // move to next valid one
        return result;
    }

    public boolean hasNext() {
        return (innerIterator != null) && innerIterator.hasNext();
    }


    public void remove() {
        lastInnerIterator.remove();
    }


    public static void main(String[] args) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        ArrayList<Integer> l0 = new ArrayList<>();
        ArrayList<Integer> l1 = new ArrayList<>(Arrays.asList(1, 2, 3));
        ArrayList<Integer> l2 = new ArrayList<>(Arrays.asList(4, 5));
        ArrayList<Integer> l3 = new ArrayList<>(Arrays.asList(6));
        ArrayList<Integer> l4 = new ArrayList<>(Arrays.asList(7, 8));
        ArrayList<Integer> l5 = new ArrayList<>(Arrays.asList(9, 10));
        res.add(l0);
        res.add(l1);
        res.add(l2);
        res.add(l0);
        res.add(l3);
        res.add(l0);

        System.out.println(res);

        System.out.println("first testcase ==== ");
        Flattern2DVector_iterator sc = new Flattern2DVector_iterator(res);
	    while(sc.hasNext()) {
	      System.out.println(sc.next());
	    }

        System.out.println("second testcase ==== ");

        sc = new Flattern2DVector_iterator(res);
        while (sc.hasNext()) {
            sc.hasNext();
            int tmp = sc.next();
            System.out.println(tmp);
            if (tmp % 2 == 0) {
                sc.hasNext();
                sc.remove();
            }
            sc.hasNext();
        }

        System.out.println(res);
    }
}

class Flattern2DVector_design_array {

    int cur;
    List<Integer> data; // 1-D array to simplify the logic

    public Flattern2DVector_design_array(List<List<Integer>> vec2d) {
        data = new ArrayList<>();

        if (vec2d == null)
            return;

        // to simplify logic, lattern 2d array into 1d continuously
        int row = vec2d.size();

        for (List<Integer> aVec2d : vec2d) {
            int col = aVec2d.size();
            for (Integer anAVec2d : aVec2d) {
                data.add(anAVec2d);
            }
        }

        cur = 0;
    }

    public int next() {
        int tmp = data.get(cur);
        cur++;
        return tmp;
    }

    public boolean hasNext() {
        return (cur < data.size());
    }

}

class Flattern2DVector_design_queue {
    Queue<Iterator<Integer>> q = new LinkedList<>();
    Integer num = null;

    public Flattern2DVector_design_queue(List<List<Integer>> vec2d) {
        for (List<Integer> aVec2d : vec2d) {
            q.add(aVec2d.iterator());
        }
    }

    public int next() {
        if (hasNext()) {
            int val = num;
            num = null;
            return val;
        } else
            return -1;
    }

    public boolean hasNext() {
        if (num != null) return true;

        while (!q.isEmpty()) {
            Iterator<Integer> iter = q.peek();
            if (iter.hasNext()) {
                num = iter.next();
                return true;
            } else {
                // if this one is out, remove it from q
                q.poll();
            }
        }
        return false;
    }
}
