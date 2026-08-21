import java.util.ArrayList;
import java.util.List;

public class GenerateCombinatio {
    public static void main(String[] args) {
        String n = "abcd";
        int length = 3;
        List<String> result = checkCombination(n, length);
        System.out.println(result);
    }

    private static List<String> checkCombination(String n, int length) {
        StringBuilder current = new StringBuilder();
        List<String> result = new ArrayList<>();
        checkCombination(n, length, current, result, 0);
        return result;
    }

    private static void checkCombination(String n, int length, StringBuilder current, List<String> result, int index) {
        if (current.length() == length) {
            result.add(current.toString());
            return;
        }

        for (int i = index ; i < n.length() ; i++) {
            current.append(n.charAt(i));
            checkCombination(n, length, current, result, i+1);
            current.deleteCharAt(current.length()-1);
        }
    }
}
