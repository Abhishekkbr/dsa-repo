
// abc -> abc, acb....
public class Test {
    public static void main(String[] args) {
        String word = "abc";
        permutation("", word);
    }

    private static void permutation(String base, String word) {
        if (word.isEmpty()) {
            System.out.println(base);
        }
        for (int i = 0 ; i < word.length() ; i++) {
            permutation(base + word.charAt(i), word.substring(0, i) + word.substring(i+1));
        }
    }

    //a   bc
    //ab   c
    //abc ""

}
