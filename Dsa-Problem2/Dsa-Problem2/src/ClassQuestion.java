
public class ClassQuestion {

	public ClassQuestion(int a) {
		// TODO Auto-generated constructor stub
		System.out.println("inside Integer");
	}
	
	public ClassQuestion(Object a) {
		// TODO Auto-generated constructor stub
		System.out.println("Inside Object");
	}
	
	public ClassQuestion(String a) {
		// TODO Auto-generated constructor stub
		System.out.println("Inside String");
	}
	
	public static void main(String[] args) {
		ClassQuestion  c = new ClassQuestion(null);
		String s = "abc";	 // string pool
		String s2 = "abc";   // string pool
		String s1 = new String("abc");  // string heap    hashcode calculated based on characters
		System.out.println(s.hashCode());
		System.out.println(s1.hashCode());
		System.out.println(s==s1);
		System.out.println(s==s2);
		System.out.println(s.equals(s1));
	}
}
