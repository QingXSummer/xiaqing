package com.java.servlet;

public class ClassLoader {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("unchecked")
		Class<Human> human = (Class<Human>) ClassLoader.class.getClassLoader()
				.loadClass("com.java.servlet.ClassLoader$Man");
		
		human.newInstance().say();
		
	}
	
	
	static interface Human{
		void say();
	}
	
	
	static class Man implements Human{

		@Override
		public void say() {
			System.out.println("man");			
		}
		
	}
	
}


