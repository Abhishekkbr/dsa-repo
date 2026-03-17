public class ABDeletions {

    public static void main(String[] args) {
        System.out.println(minDeletionsToMakeABeforeB("BAAAB")); // Output: 1
        System.out.println(minDeletionsToMakeABeforeB("ABAB"));  // Output: 1
        System.out.println(minDeletionsToMakeABeforeB("AAABBB")); // Output: 0
        System.out.println(minDeletionsToMakeABeforeB("BBBAAA")); // Output: 3
    }

    private static int minDeletionsToMakeABeforeB(String word) {
        int countB = 0;
        int deletions = 0;

        for (char ch :  word.toCharArray()) {
            if (ch == 'A') {
                deletions =  Math.min(deletions+1, countB);
            }
            else {
                countB++;
            }
        }
        return deletions;
    }
}
