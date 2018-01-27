
public class Test3 {
	public static void main(String args[]) throws Exception{
		Scrape.getData();
		Parse.parse(Scrape.handleData(), new AllData());
	}
}
