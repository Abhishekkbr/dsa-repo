import java.util.HashMap;

class Parent {
    String name;
 
    // A method which prints the
    // signature of the parent class
    
    Parent() {
    	System.out.println("parent class");
    }
    public void method1()
    {
        System.out.println("Method1 from Parent");
    }
    public void method2()
    {
        System.out.println("Method2 from Parent");
    }
}

//Child class
	class Child extends Parent {
	    int id;
	 
	    Child (){
	    	System.out.println("Child class");
	    }
	    // Overriding the parent method
	    // to print the signature of the
	    // child class
	    //@Override
	    public void method1()
	    {
	    	System.out.println("Method1 from Child");
	    }
	    
	    @Override 
	    public void method2()
	    {
	        System.out.println("Method2 from Child");
	    }
	    void method3()
	    {
	        System.out.println("Method3 from Child");
	    }
	}

public class UpcastingDowncasting {	 
	
	public static void main(String[] args) {
//		HashMap<Integer, Integer> mp = new HashMap<>();
//		mp.put(1, 1);
//		mp.put(2, 2);
//		System.out.println(mp);
		//Parent p = new Child(); //throws error
		Parent p = new Child();
		//Child cd = (Child) new Parent();
		Child c = (Child)p;
		p.method1();
		p.method2();
        c.method1();
//		c.method2();
//		c.method3();
	}
}
