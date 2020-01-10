package Class09;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AnalyzeFileWithNumbers {

	public AnalyzeFileWithNumbers() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		if(args.length > 0) {
			String filePath = args[0];
			File file = new File(filePath);
			if(file.exists()) {
				try {
					Scanner sc = new Scanner(file);
					while(sc.hasNext()) {
						String token = sc.next();
						String numberPattern = "^[0-9]+$";
						
						if(token.matches(numberPattern)) {
							System.out.println(token);
						}
						
					}
					sc.close();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				System.out.println("File not found.");
				return;
			}
		} else {
			System.out.println("File path not provided.");
			return;
		}
		// TODO Auto-generated method stub

	}

}
