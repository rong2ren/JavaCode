package Complex;

public class TruckDistance {
    public static void main(String[] args) {
        int[] position = new int[]{3, 6, 10, 15, 20};
        int[] extraGasStations = new int[]{2, 4};
        long distance = calculateTruckDistanceAfterRefueling(position,extraGasStations);
        System.out.println("distance=" + distance);

    }

    public static long calculateTruckDistanceAfterRefueling(int[] position, int[] extraGasStations) {
        //gas station at extraGasStations[0][i] and position[n-1]
        int n = position.length;
        int m = extraGasStations.length;

        int current_station = -1;
        int current_truck = 0;
        long distance = 0;
        for(int i = 0; i <= m; i++){
            if(i == m) current_station = n - 1;
            else current_station = extraGasStations[i] - 1;
            while(current_truck <= current_station){
                distance += (position[current_station] - position[current_truck]);
                System.out.println("current truck:" + current_truck + " travel to station:" + current_station);
                current_truck++;
            }
        }

        return distance;
    }
}
