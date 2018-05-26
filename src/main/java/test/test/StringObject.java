package test.test;import java.sql.Date;

public class StringObject {
	
	
	public static void main(String[] args) {
//		StringObject object = new StringObject();
//		System.out.println(object.s1 == object.s2);
//		System.out.println(object.s2 == object.s2.intern());
//		System.out.println(object.s2 == object.s3.intern());
		
		
		System.out.println(new Date(1526745600000L).toLocaleString());
		System.out.println(new Date(1526518407293L).toLocaleString());
		
		
	}
}
