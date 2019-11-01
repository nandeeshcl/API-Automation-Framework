package com.impact.API.TestUtils;
import java.lang.reflect.Method;
import java.util.Hashtable;

import org.testng.annotations.DataProvider;

import com.impact.API.TestSetup.TestSetup;



	
	public class Data extends TestSetup {
		
		@DataProvider(name ="data")
		public static Object[][] getData(Method m) {

			String sheetName = configProperty.getTestDataSheetName();
			int rowsCount = excel.getRowCount(sheetName);// 13
			String testName = m.getName();
			int testCaseRowNum = 1;

			for (testCaseRowNum = 1; testCaseRowNum <= rowsCount; testCaseRowNum++) {

				String testCaseNameInExcelFile = excel.getCellData(sheetName, 0, testCaseRowNum);
				// System.out.println("TestCase name in excel-->"+testCaseName);
				if (testCaseNameInExcelFile.equalsIgnoreCase(testName)) {
					break;
				}

			}
			// Checking total rows in test case
			//System.out.println("TestCase starts from:- " + testCaseRowNum);

			int dataStartRowNum = testCaseRowNum + 2;// dataStartRowNum=3

			int testRows = 0;
			while (!excel.getCellData(sheetName, 0, dataStartRowNum + testRows).equalsIgnoreCase("endOfTestData")) {

				testRows++;// 1
			}
			// Checking total cols in test case

			// System.out.println("Total no of rows:"+testRows);
			int colStartColNum = testCaseRowNum + 1;// 2
			int testCols = 0;

			while (!excel.getCellData(sheetName, testCols, colStartColNum).equals("")) {

				testCols++;

			}
			// [2][1]

			Object[][] data = new Object[testRows][1];// [2][3]
			// object[][] data= new Object[2][1];
			// data[0][0]=
			// data[1][0]=

			int i = 0;
			for (int rNum = dataStartRowNum; rNum < (dataStartRowNum + testRows); rNum++) {

				Hashtable<String, String> table = new Hashtable<String, String>();

				for (int cNum = 0; cNum < testCols; cNum++) {

					String colName = excel.getCellData(sheetName, cNum, colStartColNum);
					String testData = excel.getCellData(sheetName, cNum, rNum);

					table.put(colName, testData);

				}

				data[i][0] = table;
				i++;

			}

			return data;
		}

	}



