package lab9;

import java.io.File;
import java.io.FileNotFoundException;
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

	public void executeCommand(String token) {
		Command cmd = parseRawCommand(token);
		executeCommand(cmd);
	}

	public void executeCommand(Command cmd) {
		if (areFieldsValid() == false || cmd == null) {
			System.out.println("Invalid file, regex or command.");
			executeCommand(Command.EXIT);
			return;
		}

		switch (cmd) {
		case SEARCH: {
			searchRegex();
			break;
		}
		case LIST: {
			listRegex();
			break;
		}
		default: {
			System.out.println("Exit.");
			return;
		}
		}
	}

	private String[] getMatches(String token, Pattern regex) {
		List<String> allMatches = new ArrayList<String>();
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

	private void searchRegex() {
		try {
			Scanner fileSc = new Scanner(this.file);
			int index = 0;
			while (fileSc.hasNextLine()) {
				String currLine = fileSc.nextLine();
				System.out.println("[" + index + "] "
						+ arrToString(getMatches(currLine, this.regex)));
				index++;
			}
			fileSc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
	}

	private void listRegex() {
		try {
			Scanner fileSc = new Scanner(this.file);
			int index = 0;
			while (fileSc.hasNextLine()) {
				System.out.println("[" + index + "] " + fileSc.nextLine());
				index++;
			}
			fileSc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File not found.");
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
