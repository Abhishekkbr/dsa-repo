import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {
	public static void main(String[] args) {
		String s = "abcdefghaddefgh";
		System.out.println(maxLen(s));
	}

	private static int maxLength(String s) {
		// TODO Auto-generated method stub
		int max = 0;
		Set<Character> set = new HashSet<>();
		int i = 0;
		int j = 0;
		while (i < s.length()) {
			while(set.contains(s.charAt(i))) {
				set.remove(s.charAt(j));
				j++;
			}
			set.add(s.charAt(i));
			max = Math.max(max, i-j+1);
			i++;
		}
		return max;
	}
	
	
	public static int maxLen(String s) {
		int max = 0;
		HashSet<Character> hs = new HashSet<>();
		int i = 0;
		int j = 0;
		while(i < s.length()) {
			while(hs.contains(s.charAt(i))) {
				hs.remove(s.charAt(j));
				j++;
			}
			hs.add(s.charAt(i));
			max = Math.max(max, i-j+1);
			i++;
		}
		return max;
	}
}
