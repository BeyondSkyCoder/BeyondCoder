public class GasStation_greedy {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length)
            return -1;

        int len = gas.length;
        int delta = 0;
        int total = 0;
        int start_station = -1;
        
        for (int i=0; i<len; i++) {
        	delta += gas[i] - cost[i];
        	total += gas[i] - cost[i];
        	if (delta < 0) {		// if not enough for next station, reset the starting point
        		start_station = i;  // last station failed
        		delta = 0;
        	}
        }
        
        if (total < 0) return -1;	// no way to circle
        else return start_station + 1;	// because the logic above, 
        							// after start_station, all has surplus and enough total to circle
    }
	
	
	// Brute-force, TLE
    public int canCompleteCircuitSlow(int[] gas, int[] cost) {
        if (gas == null || cost == null || gas.length == 0 || cost.length == 0 || gas.length != cost.length)
            return -1;
            
        int len = gas.length;
        int curgas = 0, index;
        
        for (int i=0; i<len; i++) {
            curgas = gas[i];
            index = i;
            
            while (curgas >= cost[index]) {
                // reach next station
                curgas -= cost[index];
 
                index++;
                index = index % len;
                curgas += gas[index];
          
                if (index == i) {   // if we can reach starting point, return success
                    return i;
                }
            }
        }
        
        return -1;
        
    }
}
