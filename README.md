# DriveByDataXCel
DriveByDataXCel is a lightweight, flexible plugin designed for automation testing tools like Selenium, Katalon, and others, enabling seamless data-driven testing using Excel (.xlsx) files. It reads structured data from Excel, maps it into an array of key-value pairs (array of map), and feeds the data into your automation scripts for reliable and repeatable test execution.

Whether you're managing test inputs, form submissions, or multiple test cases with dynamic data, DriveByDataXCel simplifies the process of integrating Excel-based test data into your automated workflows.

# Key Features
📄 Reads Excel (.xlsx) files and parses them into an array of maps

🔄 Supports data-driven testing with tools like Selenium, Katalon, WebDriver, etc.

⚡ Improves test reusability by separating test logic and test data

🔌 Easy integration into existing automation frameworks or CI/CD pipelines

🖥️ Cross-platform support (Windows, macOS, Linux)

# Use Case Examples
- Fill web forms using Excel data in Selenium
- Loop through multiple test cases using different Excel rows
- Parameterize test inputs in Katalon using structured Excel maps
- Use Excel as an external test data provider for consistent automation

# How to Use
## General Use
```java
String filePath = "testingDataDriven.xlsx"; // target file, must specify full path
String sheetName = "Sheet1"; // name of the sheet to access

List<Map<String, String>> testData = DriveByDataXCel.readTestDataFromExcel(filePath, sheetName);

int rowNum = 1;
for (Map<String, String> row : testData) {
    if (isRowValid(row, rowNum)) {
      System.out.println("Username: " + row.get("username"));
      System.out.println("Password: " + row.get("password"));
    } else {
      System.out.println("[Warning] Skipping row " + rowNum + " due to missing or empty fields.");
    }
    rowNum++;
}

private boolean isRowValid(Map<String, String> row, int rowNum) {
    String[] requiredFields = {"username", "password"};

        for (String field : requiredFields) {
            String value = row.get(field);
            if (value == null || value.trim().isEmpty()) {
                System.err.println("[Error] Missing or empty value for '" + field + "' in row " + rowNum);
                return false;
            }
        }
        return true;
    }
```

## TestNG / Unit Test
#### DataDrivenProvider.java

```java
import com.coedotzmagic.drivebydataxcel.DriveByDataXCel;

String filePath = "testingDataDriven.xlsx"; // target file, must specify full path
String sheetName = "Sheet1"; // name of the sheet to access

public class DataDrivenProvider {

    public static Object[][] getDataFromExcel(String filePath, String sheetName) {
        List<Map<String, String>> testData = DriveByDataXCel.readTestDataFromExcel(filePath, sheetName);
        Object[][] data = new Object[testData.size()][1];
        for (int i = 0; i < testData.size(); i++) {
            data[i][0] = testData.get(i);
        }
        return data;
    }

    @DataProvider(name = "TestData")
    public static Object[][] getTestData() {
        return getDataFromExcel(filePath, sheetName);
    }
}
```
#### File Testing
```java
@Test(priority = 1, dataProvider = "TestData", dataProviderClass = DataDrivenProvider.class)
public void ExecuteTest(Map<String, String> data) throws Exception {
    System.out.println("Username: " + data.get("username"));
    System.out.println("Password: " + data.get("password"));
}

```
