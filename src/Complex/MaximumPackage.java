package Complex;

import java.util.HashMap;
import java.util.Map;

public class MaximumPackage {

    public static void main(String[] args) {
        System.out.println(findMaximumPackages(new int[]{2,1,3}));
    }

    /*
     * return: the maximum number of packages that can be made having the same cost,
     * where each package consists at most 2 items
     */
    public static int findMaximumPackages(int[] costs) {
        int max_packages_count = 0;
        int item_min_cost = 0, item_max_cost = 0;
        Map<Integer, Integer> frequencyMap = new HashMap<>(); //or you can use array instead of map
        // Calculate frequencies
        for (int cost : costs) {
            frequencyMap.put(cost, frequencyMap.getOrDefault(cost, 0) + 1);
            item_min_cost = Math.min(cost, item_min_cost);
            item_max_cost = Math.max(cost, item_max_cost);
        }
        //for each package(at most 2 items), the range of cost will be [item_min_cost, item_max_cost * 2]
        for(int package_cost = item_min_cost; package_cost <= item_max_cost * 2; package_cost++){
            //i = the cost of the package, each package contains at most 2 items
            int pair_count = 0;//check how many pairs can be made with total cost = i
            // For each possible pair (c, d) such that c + d = i where c <= d
            // c from 1 to package_cost / 2 (because for a valid sum, c and d must satisfy c <= d).
            for(int c = 1; c <= package_cost / 2; c++){
                if(c > item_max_cost || !frequencyMap.containsKey(c))  continue;
                int d = package_cost - c;
                if(d > item_max_cost || !frequencyMap.containsKey(d)) continue;

                if(c == d){
                    pair_count += frequencyMap.get(c) / 2;
                } else if(c < d){
                    pair_count += Math.min(frequencyMap.get(c), frequencyMap.get(d));
                }
            }

            int single_count = frequencyMap.containsKey(package_cost) ? frequencyMap.get(package_cost) : 0;
            max_packages_count = Math.max(max_packages_count, single_count + pair_count);
        }

        
        return max_packages_count;
    }
}
