import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

public class WriteFile {
	// public static
	private static String filename = "C:\\Users\\19lfreeman\\Desktop\\CSII\\IA\\Proj\\history.txt";
	private static PrintStream writer;

	public static String writeDate() {
		Date now = new Date();
		DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
		return (formatter.format(now));
	}

	public static String writeNumber(HtmlTable table) {
		List<HtmlTableRow> rows = table.getRows();
		int num = rows.size();
		return "" + (num - 2);
	}

	public static String writeHeaders(HtmlTable table) {
		String s = "";
		HtmlTableRow top = table.getRow(0);
		List<HtmlTableCell> cells = top.getCells();
		int k = 0;
		for (HtmlTableCell h : cells) {
			if (k > 1) {
				s += h.asText() + "|";
			}
			k++;
		}
		s += "~";
		return s;
	}

	public static String writeHistory(HtmlTableRow row, int i) throws IOException {
		String total = "";
		int studentNum = i;
		total += (studentNum + "|");
		List<HtmlTableCell> cells = row.getCells();
		for (HtmlTableCell h : cells)
			if (h.asText().equals("")) {
				total += "0|";
			} else {
				total += (h.asText() + "|");
			}
		total += "!";
		return total;
	}

	public static void handle(HtmlTable table) throws Exception {
		Date now = new Date();
		writer = new PrintStream(new FileOutputStream("C:\\Users\\19lfreeman\\Desktop\\CSII\\IA\\Proj\\history.txt", true));
		writer.println("asdf");
		writer.println(writeDate());
		writer.println(writeNumber(table));
		writer.println(writeHeaders(table));
		
		List<HtmlTableRow> s = table.getRows();
		List<HtmlTableRow> rows = new ArrayList<HtmlTableRow>(10);
		rows.addAll(s);
		int count = 1;
		rows.remove(0);
		rows.remove(0);
		for (HtmlTableRow r : rows) {
			writer.println(writeHistory(r, count));
			count++;
		}
		writer.println("END");

	}
}
