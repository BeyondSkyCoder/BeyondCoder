package leetcode;

public class ExcelSheetColumnTitle_math {
    public String convertToTitle(int n) {
        // LSB: A-Z, 26
        // RSB: A-Z, 26
        StringBuilder sb = new StringBuilder();
        
        while (n > 0) {
            n--;    // it starts from 1, need to adjust to start from 0
            sb.append( (char)(n%26 + 'A') );
            n /= 26;
        }
        return sb.reverse().toString();
    }
    
    public int titleToNumber(String s) {
        int res= 0;
        for (int i=0; i < s.length(); i++) {
            res *= 26;
            res += (s.charAt(i) - 'A') + 1;
        }
        return res;
    }
}
