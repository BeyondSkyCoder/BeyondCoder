package DataStructureStrArrayDeque;

public class ImplementStrStr_STR {
    /*
     * Implement strStr().
     * Returns a pointer to the first occurrence of needle in haystack,
     * or null if needle is not part of haystack.
     */
    // Algorithm:
    //      outer and inner loops
    //      Java can use String method: substring(), equals()
    //      be careful for the outer loop termination condition
    //      no need to mention KMP algorithm

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (needle.equals("") || haystack.equals(needle)) {
            return 0;
        }

        // below <= is critical
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            if (haystack.substring(i, i + needle.length()).equals(needle))
                return i;
        }

        return -1;
    }
}
