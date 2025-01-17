package amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BalancedServer {

    public static void main(String[] args) {
        int[] capacity = new int[]{9,3,3,3,9};
        System.out.println(getTotalBalanced(capacity));
    }

    public static int getTotalBalanced(int[] capacity) {
        int n = capacity.length;
        int[] prefixSum = new int[n + 1];
        Map<Integer, List<Integer>> capacityMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + capacity[i]; // Compute prefix sum
            int cap = capacity[i];
            capacityMap.putIfAbsent(cap, new ArrayList<>());
            capacityMap.get(cap).add(i); // Store indices where each capacity appears
        }

        int balanced_count = 0;
        // Step 2: Iterate over unique capacity values and their corresponding indices
        for (Map.Entry<Integer, List<Integer>> entry : capacityMap.entrySet()) {
            int capValue = entry.getKey();
            List<Integer> indices = entry.getValue();
            // Step 3: Map to track occurrences of aps values
            Map<Integer, Integer> aps_map = new HashMap<>();
            System.out.println("\ncapacity=" + capValue + " with " + indices.size() + " indices");
            for (int right : indices) {
                //Calculate APS (adjusted prefix sum)
                //capValue is one item in the capacity value
                //right is one of this value's index

                //capValue should be total sum of all itesm between right and left (another index of capValue)
                //prefixSum[right + 1] - prefixSum[left + 1] = 2 * apVvalue (in between items + left)
                //in this code, left (key) is the lower index, right is the higher index

                //right_aps = prefixSum[right + 1] - 2 * capVvalue
                //left_aps = prefixSum[left + 1] - 2 * capValue
                //prefixSum[right + 1] = prefixSum[right + 1] + 2 * capVvalue
                //right_aps - left_aps = 2 * capVvalue
                //left_aps = right_aps - 2 * capValue
                int right_aps = prefixSum[right + 1] - 2 * capValue;
                int left_aps = right_aps - 2 * capValue;//the key for the other index of current capVAlue that meet the requirement
                System.out.println("right=" + right + " right_aps="+right_aps + " left_aps="+left_aps);

                // Add to answer based on previously seen keys
                balanced_count += aps_map.getOrDefault(left_aps, 0);
                if(aps_map.getOrDefault(left_aps, 0) != 0) System.out.println("!!!find one: left_aps=" + left_aps + "-" + aps_map.get(left_aps) + " capacity[" + right + "]=" + capValue);

                // Update the occurrence count of current APS
                aps_map.put(right_aps, aps_map.getOrDefault(right_aps, 0) + 1);
                System.out.println("put in the aps map:" + right_aps + "-" + aps_map.get(right_aps));
            }
        }
        return balanced_count;
    }
}
