import java.util.HashMap;
import java.util.List;

public class ContiguosLenght {

    // arr[1,2,4,6,7,8,10,12]

    public static void main(String[] args) {
        int[] arr = {1,2,4,6,7,8,10,12};
        List<Integer> result = maxContiguous(arr);
    }

    private static List<Integer> maxContiguous(int[] arr) {
        int i = 0;
        int j = 0;
        int max = 0;
        HashMap<Integer, List<Integer>> hm = new HashMap<>();
        while (i < arr.length-1) {
            if (arr[i+1] - arr[i] != 1) {
                //hm
            }
        }
        return null;
    }
}
