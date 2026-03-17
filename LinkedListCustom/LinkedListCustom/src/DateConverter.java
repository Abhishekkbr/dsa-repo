import java.util.Date;

public class DateConverter {
	public static void main(String[] args) {
		Date d = new Date("2021-08-25T14:32:41.151Z");
		System.out.println(d.getDay()+"  "+d.getMonth());
	}
}
