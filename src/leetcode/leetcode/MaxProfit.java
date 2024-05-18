package leetcode;

public class MaxProfit {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int minValue = prices[0];
        int maxValue = prices[0];
        
        for (int i = 1; i < prices.length; i++){
            if (maxValue < prices[i]) {
                maxValue = prices[i];
                System.out.println("maxValue changed to: " + maxValue);
            }
            if (minValue > prices[i]) {
                //if current prices is smaller than the minValue
                minValue = prices[i];
                maxValue = minValue;
                System.out.println("minValue changed to: " + minValue);
            }
            if (maxValue - minValue > profit) profit = maxValue - minValue;
        }
        
        return profit;
    }
}
