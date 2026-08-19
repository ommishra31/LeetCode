import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for only the affected rows
        Map<Integer, Integer> map = new HashMap<>();

        // Convert seat number to bit
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Only seats 2 to 9 affect the answer
            if (col >= 2 && col <= 9) {
                int mask = map.getOrDefault(row, 0);

                mask |= (1 << col);

                map.put(row, mask);
            }
        }

        // All unaffected rows can accommodate 2 groups
        int answer = (n - map.size()) * 2;

        // Check affected rows
        for (int mask : map.values()) {

            // Seats 2,3,4,5
            boolean left = (mask & (1 << 2)) == 0
                        && (mask & (1 << 3)) == 0
                        && (mask & (1 << 4)) == 0
                        && (mask & (1 << 5)) == 0;

            // Seats 4,5,6,7
            boolean middle = (mask & (1 << 4)) == 0
                           && (mask & (1 << 5)) == 0
                           && (mask & (1 << 6)) == 0
                           && (mask & (1 << 7)) == 0;

            // Seats 6,7,8,9
            boolean right = (mask & (1 << 6)) == 0
                          && (mask & (1 << 7)) == 0
                          && (mask & (1 << 8)) == 0
                          && (mask & (1 << 9)) == 0;

            if (left && right) {
                answer += 2;
            } else if (left || middle || right) {
                answer += 1;
            }
        }

        return answer;
    }
}