package DataStructureStrArrayDeque;

public class CompareVersionNumbers_STR {
    /*
     * Compare two version numbers version1 and version2.
        If version1 > version2 return 1, if version1 < version2 return -1, otherwise return 0.

        You may assume that the version strings are non-empty and contain only digits and the . character.
        The . character does not represent a decimal point and is used to separate number sequences.
        For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

        Here is an example of version numbers ordering:

        0.1 < 1.1 < 1.2 < 13.37
     *  Note, trailing 0 doesn't make the longer number win: 1.1.0.0.0 is the same as 1.1
     */

    // Algorithm: use split("\\.") to convert it to array. then compare each digits

    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int len1 = v1.length;
        int len2 = v2.length;
        int i;

        // compare from MSB to LSB, return if not equal
        for (i = 0; i < len1 && i < len2; i++) {
            int t1 = Integer.parseInt(v1[i]);
            int t2 = Integer.parseInt(v2[i]);
            if (t1 > t2) return 1;
            else if (t1 < t2) return -1;
        }

        // By here, all digits are equal and one string ended
        // check if the other remaining digits against 0
        for (; i < len1; i++) {
            if (Integer.parseInt(v1[i]) != 0) return 1;
        }
        for (; i < len2; i++) {
            if (Integer.parseInt(v2[i]) != 0) return -1;
        }
        return 0;
    }
}