
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class RecurringDenominator {
	public static void main(String[] args) {
		int num = 93;
		int den = 7;
		StringBuilder sb = new StringBuilder();
		int q = num/den;
		int r = num%den;
		sb.append(q);
		if(r!=0) {
			sb.append(".");
			HashMap<Integer, Integer> hm = new HashMap<>();
			while(r!=0) {
				if(hm.containsKey(r)) {
					sb.insert(hm.get(r), "(");
					sb.append(")");
					break;
				}else {
					hm.put(r,  sb.length());
					r *= 10;
					q = r/den;
					r = r%den;
					sb.append(q);
				}
			}
		}
		System.out.println(sb);
	}
}
