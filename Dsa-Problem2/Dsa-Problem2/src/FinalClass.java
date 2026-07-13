
public class FinalClass {
	static class Test{
		int id;
		final String name;
		
		public Test(int id, String name) {
			super();
			this.id = id;
			this.name = name;
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
//		public void setName(String name) {
//			this.name = name;
//		}
		@Override
		public String toString() {
			return "Test [id=" + id + ", name=" + name + "]";
		}
		
	}
	public static void main(String[] args) {
		final Test test = new Test(1, "Abhishek");
		final Test test1 = new Test(1, "Abhishekds");
		System.out.println(test);
		test.setId(3);
		System.out.println(test1);
	}
}
