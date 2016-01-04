package DataStructureHashMap;

import java.util.HashMap;
import java.util.Map;

public class TwoSum_design_HM {
    /*
     * Design and implement a TwoSum class. It should support the following operations: add and find.
     *
        add - Add the number to an internal data structure.
        find - Find if there exists any pair of numbers which sums to the value.
     */
    // Algorithm: HashMap[number] -> count
    //      find will scan through keySet()

    Map<Integer, Integer> hm = new HashMap<>();

    public void add(int number) {
        if (hm.containsKey(number)) {
            hm.put(number, hm.get(number) + 1);
        } else {
            hm.put(number, 1);
        }
    }

    // find two numbers from HashMap for target
    public boolean find(int target) {

        for (int i : hm.keySet()) {
            int j = target - i;

            if (hm.containsKey(j)) {
                if (j != i) {
                    return true;
                } else {
                    // if that's itself, make sure it has more than 2
                    if (hm.get(i) > 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        TwoSum_design_HM outer = new TwoSum_design_HM();
        outer.add(0);
        outer.add(0);
        System.out.println(outer.find(0));
    }
}
