package lab9;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTester {
	private File file;
	private Pattern regex;

	enum Command {
		SEARCH, LIST, EXIT
	}

	public RegexTester(String filePath, String regexRaw) {
		this.file = parseFilePath(filePath);
		this.regex = parseRegexExpr(regexRaw);
	}

	private File parseFilePath(String token) {
		File file = null;
		try {
			file = new File(token);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Invalid file path.");
		}
		return file;
	}

	private Pattern parseRegexExpr(String token) {
		Pattern regex = null;
		try {
			regex = Pattern.compile(token);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Invalid regex expression.");
		}
		return regex;
	}

	private Command parseRawCommand(String token) {
		String preparedToken = token.trim().toUpperCase();
		Command cmd = null;
		try {
			cmd = Command.valueOf(preparedToken);
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println("Unknown command.");
		}
		return cmd;
	}

	private boolean areFieldsValid() {
		if (this.file == null || this.regex == null) {
			return false;
		}
		return true;
	}

	public String executeCommand(String token) {
		Command cmd = parseRawCommand(token);
		return resolveCommand(cmd);
	}

	public String resolveCommand(Command cmd) {
		if (areFieldsValid() == false || cmd == null) {
			return ("Invalid file, regex or command."
					+ resolveCommand(Command.EXIT));

		}

		switch (cmd) {
		case SEARCH: {
			return searchRegex();
		}
		case LIST: {
			return listRegex();
		}
		default: {
			return "Exit.";
		}
		}
	}

	private String[] getMatches(String token, Pattern regex) {
		ArrayList<String> allMatches = new ArrayList<String>();
		Matcher m = regex.matcher(token);
		while (m.find()) {
			allMatches.add(m.group());
		}
		return allMatches.toArray(new String[0]);
	}

	private String arrToString(String[] arr) {
		String result = "";
		for (String str : arr) {
			result += str + "\t";
		}
		return result;
	}

	private String searchRegex() {
		String output = "[SEARCH]\n";
		try {
			Scanner fileSc = new Scanner(this.file);
			int lineIndex = 1;
			while (fileSc.hasNextLine()) {
				String currLine = fileSc.nextLine();
				String[] matches = getMatches(currLine, this.regex);
				if (matches.length > 0) {
					output += ("[" + lineIndex + "] " + arrToString(matches)
							+ "\n");
				}

				lineIndex++;
			}
			fileSc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output += ("File not found.\n");
		}
		return output;
	}

	private String listRegex() {
		String output = "[LIST]\n";
		try {
			Scanner fileSc = new Scanner(this.file);
			int lineIndex = 1;
			while (fileSc.hasNextLine()) {
				output += ("[" + lineIndex + "] " + fileSc.nextLine() + "\n");
				lineIndex++;
			}
			fileSc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			output += ("File not found.\n");
		}
		return output;
	}

	public static void main(String[] args) {
		try {
			String regexToken = "in";
			RegexTester regexTester = new RegexTester(
					"/home/arach/Repositories/university-java/Laboratories/src/lab9/file",
					regexToken);
			System.out.println(regexTester.executeCommand("LIST"));
			System.out.println(regexTester.executeCommand("SEARCH"));
			System.out.println(regexTester.executeCommand("EXIT"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
