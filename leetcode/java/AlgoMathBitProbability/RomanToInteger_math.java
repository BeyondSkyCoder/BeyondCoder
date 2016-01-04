public class RomanToInteger_math {

    public int romanToInt(String s) {
        int res = 0;
        int t;
        for (int i = 0; i < s.length(); i++) {
            t = c2n(s.charAt(i));
            if (i > 0 && t > c2n(s.charAt(i - 1))) {
                res = res + t - 2 * c2n(s.charAt(i - 1));
            } else {
                res += t;
            }
        }
        return res;
    }

    public static int c2n(Character c) {
        switch (c) {
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
