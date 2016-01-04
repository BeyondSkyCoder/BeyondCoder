package DataStructureLinkedList;

import java.util.ArrayList;
import java.util.List;

public class PascalTriangle_I_II_LL {
    /*
        Given numRows, generate the first numRows of Pascal's triangle.
        For example, given numRows = 5,
        Return

        [
             [1],
            [1,1],
           [1,2,1],
          [1,3,3,1],
         [1,4,6,4,1]
        ]
     */
    // Algorithm: grow one row each time, generate element using the Math condition above

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows == 0) return res;

        for (int i = 0; i < numRows; i++) {

            List<Integer> row = new ArrayList<>();

            if (i == 0) {   // row 0 is simple
                row.add(1);
                res.add(row);
                continue;
            }

            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    row.add(1);
                    continue;
                }
                List<Integer> prev = res.get(i - 1);
                row.add(prev.get(j - 1) + prev.get(j));
            }
            res.add(row);
        }
        return res;
    }

    /*
        Given an index k, return the kth row of the Pascal's triangle.

        For example, given k = 3,
        Return [1,3,3,1].

        Note:
        Could you optimize your algorithm to use only O(k) extra space?
     */

    // Algorithm
    //      to save space, only use two lists, one for previous row, one for current row
    //      Note: the input rowIndex start from 0, not 1

    public List<Integer> getRow(int rowIndex) {

        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++) {

            if (i == 0) {   // row 0 is simple
                l1.add(1);
                continue;
            }

            List<Integer> t1 = (i % 2 == 1) ? l1 : l2;
            List<Integer> t2 = (i % 2 == 1) ? l2 : l1;
            t2.clear();
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    t2.add(1);
                    continue;
                }
                t2.add(t1.get(j - 1) + t1.get(j));
            }
        }

        return (rowIndex % 2 == 0) ? l1 : l2;
    }
}
