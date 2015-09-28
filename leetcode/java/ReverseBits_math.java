package leetcode;

public class ReverseBits_math {
    // you need treat n as an unsigned value
    public static int reverseBits(int n) {
        int newval = 0;
        for (int i = 0; i < 32; i++) {
        	newval <<= 1;
            newval |= n & 1;
            n >>= 1;
        }
        return newval;
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int m = 0xE0000000;
		System.out.println(reverseBits(m));
	}

}
