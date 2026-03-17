
public class Power {

	public static void main(String[] args) {
		int a = 2;
		int b = -2;
		int ans = 1; 
		while(b > 0) {
			ans = ans*a;
			b--;
		}
		
		System.out.println(ans);
		System.out.println(power(2, -3));
	}

	// X n = (X n/2)2
	public static double power(double x, int n) {
        if (n == 0) {
            return 1;
        }
        double temp = power(x, n/2);
        if(n%2 == 0)
        	return temp*temp;
        else {
        	if(n > 0)
        		return x*temp*temp;
        	else
        		return (temp*temp)/x;
        }
    }
}
