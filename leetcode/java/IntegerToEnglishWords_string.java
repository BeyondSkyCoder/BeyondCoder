package leetcode;

/*
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 231 - 1.

For example,
123 -> "One Hundred Twenty Three"
12345 -> "Twelve Thousand Three Hundred Forty Five"
1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"
 */

public class IntegerToEnglishWords_string {
	final String[] oTo19 = {"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
	final String[] twentyToNinety = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
		final int[] tmb_limit = {9, 6, 3};
		final String[] tmb = { "Billion", "Million", "Thousand"};
		String res = "";
		int limit;
		
		for (int i = 0; i < tmb_limit.length; i++) {
			limit = (int) Math.pow(10,  tmb_limit[i]);
			if (num >= limit) {
				res += convert( num / limit ) + " " + tmb[i] + " ";
				num = num % limit;
				// System.out.println("num is " + num);
			}
		}
		// below thousand, do it again
		res += convert(num);
		res = res.trim();

		return res.isEmpty() ? "Zero" : res;
	}
	
	public String convert(int threedigits) {
		String str ="";
		int a = threedigits / 100;
		int b = threedigits % 100;
		int c = threedigits % 10;
		
		if (a > 0) str += oTo19[a] + " Hundred";
		
		if (b >= 20) { 
			str += " " + twentyToNinety[b/10];
			if (c != 0)
				str += " " + oTo19[c];
		} else if (b > 0) {
			str += " " + oTo19[b];
		} else if (c > 0)
			str += " " + oTo19[c];
		
		return str.trim();
	}
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n = 1001;
		IntegerToEnglishWords_string tt = new IntegerToEnglishWords_string();
		System.out.println(n + " is " + tt.numberToWords(n));
	}

}
