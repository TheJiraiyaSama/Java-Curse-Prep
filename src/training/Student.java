package training;

public class Student {
	private int age;
	private String name;
	
	public Student (int age, String name) {
		this.age=age;
		this.name=name;
	}
	public void display() {
		System.out.println("The age is "+age);
		System.out.println("The name is "+name);
	}
}
