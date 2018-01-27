import java.util.ArrayList;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;

//make sure they're all in the "ALL" group
//set all of their scores
//add all the people
/* legit - num - parse
 * Warmup 1 - 0 - Warmup1 
 * Warmup 2 - 1 - Warmup2
 * Logic 1 - 2 - Logic1
 * String 1 - 3 - String1
 * Array 1 - 4 - Array1
 * Logic 2 - 5 - Logic2
 * String 2 - 6 - String2
 * Array 2 - 7 - Array2
 * AP 1 - 8 - AP1
 * String 3 - 9 - String3 
 * Array 3 - 10 - Array3
 * Recursion 1 - 11 - Recursion1
 * Recursion 2 - 12 - Recursion2
 * All - 13 - All
 */
public class Parse {
	public static int convert(String s) {
		// TODO convert category to score #
		String toSwitch = s.toLowerCase();
		switch (toSwitch) {
		case "warmup1":

			break;
		case "warmup2":

			break;
		case "logic1":

			break;
		case "string1":

			break;
		case "array1":

			break;
		case "logic2":

			break;
		case "":
			
			break;
		default:
			return 13;
			
		}
		return 0;
	}

	public static void parse(HtmlTable table, AllData all) {
		List<HtmlTableRow> a = table.getRows();
		ArrayList<HtmlTableRow> rows = new ArrayList<HtmlTableRow>(10);
		rows.addAll(a);
		if (all.hasAll == false) {
			// no groups so we need to add the "all" group
			all.addGroup(new Group("All"));
			all.hasAll = true;
		}
		for (int i = 2; i < rows.size(); i++) {
			HtmlTableRow currentRow = rows.get(i);
			HtmlTableCell id = currentRow.getCell(0);
			if (all.getGroups().get(0).hasPerson(id.asText())) {

			} else {
				Person p = new Person(id.asText(), currentRow.getCell(1).asText());
				all.getGroups().get(0).add(p);
			}
		}
		for (int i = 2; i < rows.size(); i++) {
			// iterate over the rows
			HtmlTableRow row = rows.get(i);
			int[] resultNums = new int[13];
			int filled = 0;
			List<HtmlTableCell> f = row.getCells();
			ArrayList<HtmlTableCell> cells = new ArrayList<HtmlTableCell>(10);
			cells.addAll(f);
			for (int k = 2; k < 16; k++) {
				if (k == 10 || k == 11)
					continue;
				String txt = cells.get(k).asText();
				if (txt.equals(""))
					txt = "0";
				int result = Integer.parseInt(txt);
				resultNums[filled] = result;
				filled++;
			}
			Score newScore = new Score();
			newScore.setScores(resultNums);
			Person thisPerson = all.getGroups().get(0).getPerson(cells.get(0).asText());
			thisPerson.addScore(newScore);
		}
		// now we've added the students
		// now we need to update all of their scores

		// TODO do all of the types and code around that
		// rows now only has scores

	}

	public static void evaluateAssignments() {

	}

}
