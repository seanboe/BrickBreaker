import java.util.ArrayList;

public class BrickFormats {

	
	private String[][] level1Format = new String[][] {
		{"r", "r", "r", "r", "r"},
		{"r", "g", "g", "g", "r"},
		{"r", "g", "y", "g", "r"} 
	};
	
	private ArrayList<String> level1;
	
	
	public BrickFormats() {
//		level1 = getConvertedToArrayList(level1Format);
	};

//	public ArrayList<String> getConvertedToArrayList(String[] format) {
//		ArrayList<String> arraylistFormat = new ArrayList<String>();
//		for (int x = 0; x < format.length; x++) {
//			arraylistFormat.add(format[x]);
//		}
//		
//		return arraylistFormat;
//
//	}
	
	public String[][] getLevelFormat(int level) {
		return level1Format;
//		switch (level) {
//			case 1: return level1Format;
//			default: return level1Format;
//		}
	}
	
}
