package lab9;

import static org.junit.Assert.assertEquals;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.junit.BeforeClass;
import org.junit.Test;

public class RegexTesterTest {
	RegexTester regexTester = null; 
	
	@Test
	public void testRegexTesterExit() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			String regexToken = "i";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("EXIT"), "Exit.");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	

	@Test
	public void testRegexTesterList() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			String regexToken = "i";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("LIST"), "[LIST]\n[1] This is the temporary file content\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegexTesterSearch() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			String regexToken = "i";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n[1] i	i	i	\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegexTesterListHarder() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content\nsecond line of the file");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			String regexToken = "file";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("LIST"), "[LIST]\n[1] This is the temporary file content\n[2] second line of the file\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegexTesterSearchHarder() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content\nsecond line of the file");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			String regexToken = "file";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n" + 
					"[1] file	\n" + 
					"[2] file	\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegexTesterSearchComplex() throws Exception {
		try {
			File tempFile = File.createTempFile("hello", ".tmp");
			BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
    	    bw.write("This is the temporary file content\nsecond line of the file");
    	    bw.close();
    	    
			String path = tempFile.getAbsolutePath();
			
			// match words with both uppercase and lowercase letters Y and T
			String regexToken = "\\b\\w*[YyTt]\\w*\\b";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n" + 
					"[1] This	the	temporary	content	\n" + 
					"[2] the	\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBigFileLowercase() throws Exception {
		try {
			String path = "/home/arach/Repositories/university-java/Laboratories/src/lab9/big_file";
		
			String regexToken = "nostromo";
			regexTester = new RegexTester(path, regexToken);

			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBigFileUppercase() throws Exception {
		try {
			String path = "/home/arach/Repositories/university-java/Laboratories/src/lab9/big_file";
		
			String regexToken = "NOSTROMO";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n" + 
					"[1] NOSTROMO	\n" + 
					"[11] NOSTROMO	\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testBigFileIgnoreCase() throws Exception {
		try {
			String path = "/home/arach/Repositories/university-java/Laboratories/src/lab9/big_file";
		
			String regexToken = "(?i)he";
			regexTester = new RegexTester(path, regexToken);
			
			assertEquals(regexTester.executeCommand("SEARCH"), "[SEARCH]\n" + 
					"[2] HE	\n" + 
					"[11] he	he	\n" + 
					"[12] he	he	he	\n" + 
					"[15] he	\n" + 
					"[16] he	\n" + 
					"[17] he	\n" + 
					"[19] he	he	he	he	\n" + 
					"[20] he	he	\n" + 
					"[22] he	he	\n" + 
					"[23] he	he	\n" + 
					"[26] he	he	\n" + 
					"[27] he	\n" + 
					"[30] he	he	\n" + 
					"[31] he	he	\n" + 
					"[32] he	he	\n" + 
					"[34] he	he	he	\n" + 
					"[37] he	he	\n" + 
					"[40] he	\n" + 
					"[42] he	\n" + 
					"[43] he	he	\n" + 
					"[45] he	he	he	\n" + 
					"[46] he	\n" + 
					"[47] he	\n" + 
					"[48] he	\n" + 
					"[51] he	\n" + 
					"[52] he	\n" + 
					"[54] he	he	\n" + 
					"[55] he	\n" + 
					"[56] he	he	\n" + 
					"[57] he	\n" + 
					"[60] He	\n" + 
					"[62] he	\n" + 
					"[65] he	he	he	he	\n" + 
					"[66] he	\n" + 
					"[70] he	he	He	\n" + 
					"[71] he	\n" + 
					"[73] he	\n" + 
					"[74] he	he	\n");
			regexTester.executeCommand("EXIT");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
