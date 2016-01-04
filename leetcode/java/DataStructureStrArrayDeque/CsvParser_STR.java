package DataStructureStrArrayDeque;

import java.util.ArrayList;
import java.util.List;

public class CsvParser_STR {
    /*
     * How to parse csv file：
        input file
            “some_name|some_address|some_phone|some_job”，
        output
            Json format “{name:some_name, address:some_addres,phone:some_phone, job:some_job}”
        Note:
            need to consider quote, escape \ cases. The quote between quote is escaped
     */

    /*
      Parse CSV file: a simplified version

        John,                        Smith,                john.smith@gmail.com,        Los Angeles,                1
        Jane,                        Roberts,        janer@msn.com,                "San Francisco, CA",        0
        "Alexandra ""Alex""",        Menendez,        alex.menendez@gmail.com,        Miami,                        1
        """Alexandra Alex"""

        John|                        Smith |                john.smith@gmail.com |         Los Angeles |                1
        Jane|                        Roberts|        janer@msn.com|                San Francisco, CA|        0
        Alexandra "Alex"|        Menendez|        alex.menendez@gmail.com|        Miami|                        1
        "Alexandra Alex"
    */

    // Algorithm: String with state machine flag

    public List<String> parseLine(String line) {
        List<String> res = new ArrayList<>();
        StringBuilder tokenSB = new StringBuilder();
        boolean inquote = false;

        String l = line.trim();

        for (int i = 0; i < l.length(); i++) {
            char c = l.charAt(i);
            if (c == ',') {
                if (inquote) {
                    tokenSB.append(',');
                } else {
                    res.add(tokenSB.toString());
                    tokenSB.setLength(0);    // clean up
                }

            } else if (c == '\"') {
                if (!inquote) {
                    inquote = true;
                } else {
                    if (l.charAt(i + 1) == '\"') {
                        System.out.println("here i " + i);
                        tokenSB.append('\"');
                        i++;
                    } else {
                        inquote = false;
                    }
                }
            } else {
                tokenSB.append(c);
            }
        }
        res.add(tokenSB.toString());
        return res;
    }

    public static void main(String[] args) {
        String s = " \"Alexandra \"\"Alex\"\"\", Menendez, alex.menendez@gmail.com, \"Miami, FL\", 1";
        
        CsvParser_STR outer = new CsvParser_STR();
        System.out.println(outer.parseLine(s));
    }
}
