package testScripts;

public class DataReader {

	public static void main(String[] args) {
		readExcel();

	}
	
	public static void readExcel() {
		final String filename = "C:\\Users\\shwar\\Documents\\Automation\\AccountInfo.xls";
		String[][] records = utilities.ExcelReader.get(filename);
		for(String[] record : records) {
			for (String field : record) {
				System.out.println(field);
			}
		}
	}

}
