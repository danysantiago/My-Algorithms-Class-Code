import java.util.HashMap;


public class RightCycleShift {


	public static void main(String[] args) {
		String T = "LEAP";
		String S = "APLE";
		
		//System.out.println(isBFRCS(T,S));
		System.out.println(isIERCS(T,S));

	}

	//is Brute Frce Right Cycle Shift
	private static boolean isBFRCS(String t, String s) {
		for(int i = 0; i < t.length(); i++){
			t = t.charAt(t.length()-1) + t.substring(0, t.length()-1);
			int count = 0;
			for(int j = 0; j < t.length(); j++){
				if(t.charAt(j) != s.charAt(j))
					break;
				else
					count++;
			}
			if(count == t.length())
				return true;
		}
		
		return false;
	}
	
	// is Input Enchanced Right Cycle Shift
	private static boolean isIERCS(String t, String s){
		HashMap<Character, Character> map = new HashMap<Character, Character>();
		for(int i = 0; i < t.length(); i ++){
			map.put(t.charAt(i), t.charAt((i+1)%t.length()));
		}
		int count = 0;
		for(int i = 0; i < t.length(); i++){
			if(map.get(s.charAt(i)) == s.charAt((i+1)%t.length()))
					count++;
			else
				break;
		}
		if(count == t.length())
			return true;
		
		return false;
	}

}
