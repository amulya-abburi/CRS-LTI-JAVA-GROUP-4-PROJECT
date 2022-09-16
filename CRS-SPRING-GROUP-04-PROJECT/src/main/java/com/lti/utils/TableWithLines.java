/**
 * 
 */
package com.lti.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 10710128
 *
 */
public class TableWithLines {

	public static String getCharString(char ch, int n) {
		String s = "";
		for (int i = 0; i < n; i++) {
			s += ch;
		}
		return s;
	}

	public static Map<Integer, Integer> getMaxColumnLength(String[][] table) {
		Map<Integer, Integer> columnLengths = new HashMap<>();

		for (int i = 0; i < table.length; i++) {
			for (int j = 0; j < table[i].length; j++) {
				if (columnLengths.get(j) == null) {
					columnLengths.put(j, 0);
				}
				if (columnLengths.get(j) < table[i][j].length()) {
					columnLengths.put(j, table[i][j].length());
				}
			}
		}

		return columnLengths;
	}

	public static Map<Integer, Integer> fillMapWithColumnsLength(int[] colsLength) {
		Map<Integer, Integer> columnLengths = new HashMap<>();

		for (int i = 0; i < colsLength.length; i++) {

			if (columnLengths.get(i) == null) {
				columnLengths.put(i, 0);
			}
			if (columnLengths.get(i) < colsLength[i]) {
				columnLengths.put(i, colsLength[i]);
			}
		}

		return columnLengths;
	}

	public static StringBuilder getFormatString(Map<Integer, Integer> columnLengths, boolean leftJustifiedRows) {
		final StringBuilder formatString = new StringBuilder("");
		String flag = leftJustifiedRows ? "-" : "";

		for (Map.Entry<Integer, Integer> entry : columnLengths.entrySet()) {
			formatString.append("| %" + flag + entry.getValue() + "s ");
		}
		formatString.append("|\n");

		return formatString;
	}

	public static String getBorderLine(Map<Integer, Integer> columnLengths) {
		String line = "";

		for (Map.Entry<Integer, Integer> entry : columnLengths.entrySet()) {
			line += "+-" + getCharString('-', entry.getValue()) + "-";
		}

		line += "+\n";

		return line;
	}

	public static void tableWithLines(String[][] table, boolean leftJustifiedRows) {

		// find max column length
		Map<Integer, Integer> columnLengths = getMaxColumnLength(table);

		// Prepare format String
		final StringBuilder formatString = getFormatString(columnLengths, leftJustifiedRows);

		// Prepare line for top, bottom & below header row
		String line = getBorderLine(columnLengths);

		// Print table
		System.out.print(line);
		System.out.printf(formatString.toString(), (Object[]) table[0]);
		System.out.print(line);
		for (int i = 1; i < table.length; i++) {
			System.out.printf(formatString.toString(), (Object[]) table[i]);
		}
		System.out.print(line);
	}

	public static String getTitleLine(String title, int rowLength) {
		String titleLine = "";

		int gap = (rowLength - title.length()) / 2;

		if ((rowLength - title.length()) % 2 == 0) {
			String gapString = getCharString(' ', gap - 1);
			titleLine = "|" + gapString + title + gapString + "|\n";
		} else {
			String gapStringL = getCharString(' ', gap - 1);
			String gapStringR = getCharString(' ', gap);
			titleLine = "|" + gapStringL + title + gapStringR + "|\n";
		}

		return titleLine;

	}

	// function for printing table with title
	public static void tableWithLinesAndTitle(String[][] table, boolean leftJustifiedRows, String title) {

		// find max column length
		Map<Integer, Integer> columnLengths = getMaxColumnLength(table);

		// Prepare format String
		final StringBuilder formatString = getFormatString(columnLengths, leftJustifiedRows);

		// Prepare line for top, bottom & below header row
		String line = getBorderLine(columnLengths);

		if (title == null || title == "") {
			title = "No Title Provided";
		}

		String titleLine = getTitleLine(title, line.length() - 1);
		System.out.println("border line length:" + (line.length() - 1));
		System.out.println("title line length:" + (titleLine.length() - 1));
		System.out.println("title length:" + title.length());

		// Print table
		System.out.print(line);
		System.out.print(titleLine);
		System.out.print(line);
		System.out.printf(formatString.toString(), (Object[]) table[0]);
		System.out.print(line);
		for (int i = 1; i < table.length; i++) {
			System.out.printf(formatString.toString(), (Object[]) table[i]);
		}
		System.out.print(line);
	}

	// function for printing table with title and given column lengths
	public static void tableWithGivenColumnsLength(String[][] table, boolean leftJustifiedRows, String title,
			int[] colsLength) {

		// fill map with columns length
		Map<Integer, Integer> columnLengths = fillMapWithColumnsLength(colsLength);

		// Prepare format String
		final StringBuilder formatString = getFormatString(columnLengths, leftJustifiedRows);

		// Prepare line for top, bottom & below header row
		String line = getBorderLine(columnLengths);

		if (title == null || title == "") {
			title = "No Title Provided";
		}

		String titleLine = getTitleLine(title, line.length() - 1);

		// Print table
		System.out.print(line);
		System.out.print(titleLine);
		System.out.print(line);
		System.out.printf(formatString.toString(), (Object[]) table[0]);
		System.out.print(line);
		for (int i = 1; i < table.length; i++) {
			System.out.printf(formatString.toString(), (Object[]) table[i]);
		}
		System.out.print(line);
	}
}
