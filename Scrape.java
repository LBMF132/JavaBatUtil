import java.awt.List;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTable;
import com.gargoylesoftware.htmlunit.html.HtmlTableCell;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow;
import com.gargoylesoftware.htmlunit.html.HtmlTableRow.CellIterator;
import com.gargoylesoftware.htmlunit.javascript.host.Iterator;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Scrape {
	/**
	 * 
	 */
	private static HtmlPage current;
	private static String username = "";
	private static String password = "";
	
	public static void getData() throws Exception {
		final WebClient browser = new WebClient();
		HtmlPage page1 = null;
		ReadFile.getUP();
		try {
			page1 = browser.getPage("http://codingbat.com/report");
			System.out.println("went to page");
		} catch (FailingHttpStatusCodeException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HtmlForm form = page1.getFirstByXPath("//form[@action='/login']");
		HtmlInput u = form.getInputByName("uname");
		HtmlInput p = form.getInputByName("pw");
		u.setValueAttribute(username);
		p.setValueAttribute(password);
		HtmlSubmitInput button = form.getInputByName("dologin");
		/*try {
			button.click();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.print("Pre-click");
		HtmlPage page2 = button.click();
		System.out.print("cliked");
		current = page2;
		browser.close();
	}
	public static HtmlTable handleData() throws Exception {
		//implement differently
		HtmlTable table = (HtmlTable) current.getElementsByTagName("table").get(2);
		HtmlTableRow top = table.getRow(0);
		ArrayList<HtmlTableCell> list = new ArrayList<HtmlTableCell>(7);
		CellIterator over = top.getCellIterator();
		boolean b=true;
		while(b) {
			list.add(over.next());
			b=over.hasNext();
		}
		
		return table;
		
			
		
		//System.out.print(table.asText());
	}
	public static String getUsername() {
		return username;
	}
	public static void setUsername(String username) {
		Scrape.username = username;
	}
	public static String getPassword() {
		return password;
	}
	public static void setPassword(String password) {
		Scrape.password = password;
	}

}
