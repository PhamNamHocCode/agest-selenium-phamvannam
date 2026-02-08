package Railway;

import Constant.Constant;

public class TableHelper {
	private TableHelper() {
		
	}
	public static String getRowBy2Cols(int col1Index, String col1Value,int col2Index, String col2Value) {
		String xpath = String.format(Constant.ROW_BY_2_COLS, col1Index, col1Value, col2Index, col2Value);
		return xpath;
	}
	
}
