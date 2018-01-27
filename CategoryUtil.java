
public class CategoryUtil {
	public static String numToString(int i) {
		/*legit - num - parse
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
		 * */
		switch(i) {
		case 13:
			return "Total";
		case 0:
			return "Warmup 1";
		case 1:
			return "Warmup 2";
		case 2:
			return "Logic 1";
		case 3:
			return "String 1";
		case 4:
			return "Array 1";
		case 5:
			return "Logic 2";
		case 6:
			return "String 2";
		case 7:
			return "Array 2";
		case 8:
			return "AP 1";
		case 9:
			return "String 3";
		case 10:
			return "Array 3";
		case 11:
			return "Recursion 1";
		case 12:
			return "Recursion 2";
		default:
			System.exit(2);
			
		}
		return null;
	}
	public static int stringToInt(String s) {
		for(int k=0;k<=13;k++) {
			String test = CategoryUtil.numToString(k);
			if(test.equals(s)) {
				return k;
			}
		}
		return -1;		
	}
	
}
