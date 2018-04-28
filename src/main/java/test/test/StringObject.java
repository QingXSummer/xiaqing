package test.test;

public class StringObject {
	
	String s1="";
	String s2=new String("123")+new String("456");
	String s3=new String("123")+new String("456");
	
	public static void main(String[] args) {
		StringObject object = new StringObject();
		System.out.println(object.s1 == object.s2);
		System.out.println(object.s2 == object.s2.intern());
		System.out.println(object.s2 == object.s3.intern());
	}
}
