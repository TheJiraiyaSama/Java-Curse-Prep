package training;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class write {

	public static void main(String[] args) {
		try {
		BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("C:\\Users\\Rohit V\\Desktop\\tw.txt"));
		bufferedWriter.write("This is me learning and preparing for TWU!");
		bufferedWriter.write(System.lineSeparator());
		bufferedWriter.write("I'm from TW88!");
		bufferedWriter.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
		BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\\\Users\\\\Rohit V\\\\Desktop\\\\tw.txt"));
		String line=bufferedReader.readLine();
		
		while (line!=null) {
			System.out.println(line);
			line=bufferedReader.readLine();	
		}
		bufferedReader.close();
		} catch(IOException e) {
		e.printStackTrace();
		}
	
	}
}
