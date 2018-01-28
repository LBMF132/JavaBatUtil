import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;
public class ProgUtil {
	public static long getDateDiff(Date date1, Date date2, TimeUnit timeUnit) {
	    long diffInMillies = date2.getTime() - date1.getTime();
	    return timeUnit.convert(diffInMillies,TimeUnit.MILLISECONDS);
	}
	public static Score[] progress(AllData a,String id,int progDays) {
		Person p = a.getGroup("All").getPerson(id);
		ArrayList<Date> dates = new ArrayList<Date>(10);
		Score[] ss = new Score[2];
		ss[0]=p.getScore(p.numScores()-1);
		int idx=-1;
		long lowestDiff=Long.MAX_VALUE;
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, -progDays);
		Date progDate = cal.getTime();
		for(int i=0;i<p.numScores();i++) {
			Score testScore = p.getScore(i);
			DateFormat formatter = new SimpleDateFormat("DDD/yyyy");
			Date bfr=null;
			try {
				bfr = formatter.parse(testScore.getDate());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			long tDiff = lowestDiff;
			lowestDiff = Math.abs(getDateDiff(bfr,progDate,TimeUnit.HOURS));
			if(Math.abs(lowestDiff)<tDiff) {
				idx=i;
			}
		}
		Score pastScore = p.getScore(idx);
		ss[1] = pastScore;
		return ss;
	}
}
