package leetcode;

/**
 *
 */
public class ReadNCharactersGivenRead4_I_II {
    /*
    The API: int read4(char *buf) reads 4 characters at a time from a file.

The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.

By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

Note:
The read function will only be called once for each test case.
     */
    int read4(char[] t) {
        return 3;
    }

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        int tot = 0;
        char[] tmp = new char[4];
        boolean eof = false;

        while (!eof && tot < n) {
            int sz = read4(tmp);

            if (sz < 4) eof = true; // check if file ends
            int rb = Math.min(sz, n - tot); // check if n is reached

            System.arraycopy(tmp, 0, buf, tot, rb); // save new read to buf
            tot += rb;
        }

        return tot;
    }

    /*
     NEW: The read function may be called multiple times.
     */
    private char[] buffer = new char[4];    // shared buffer for multiple reads
    int offset = 0;     // last read
    int bufsize = 0;    // bytes left to be consumed in last read buffer

    public int readII(char[] buf, int n) {
        int tot = 0;
        boolean eof = false;

        while (!eof && tot < n) {
            int sz = (bufsize > 0) ? bufsize : read4(buffer);

            if (bufsize == 0 && sz < 4) eof = true;

            int rb = Math.min(sz, n - tot);
            System.arraycopy(buffer, offset, buf, tot, rb);

            offset = (offset + rb) % 4;
            bufsize = sz - rb;
            tot += rb;
        }
        return tot;
    }
}
