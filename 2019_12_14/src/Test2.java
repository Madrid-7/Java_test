import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int[] arr = new int[grid.length * grid[0].length];
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                int pos = (i * grid[0].length + j + k) % arr.length;
                arr[pos] = grid[i][j];
            }
        }
        List<List<Integer>> lists = new ArrayList<>(grid.length);
        for(int i = 0; i < grid.length; i++) {
            List<Integer> tmp = new ArrayList<>(grid[0].length);
            for(int j = 0; j < grid[0].length; j++) {
                tmp.add(arr[i * grid[0].length + j]);
            }
            lists.add(tmp);
        }
        return lists;
    }
}
public class Test2 {
}
