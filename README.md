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
```java
String filePath = "testingDataDriven.xlsx"; // target file, must specify full path
String sheetName = "Sheet1"; // name of the sheet to access

List<Map<String, String>> testData = DataDrivenWithExcel.readTestDataFromExcel(filePath, sheetName);

for (Map<String, String> row : testData) {
    System.out.println("Username: " + row.get("username"));
    System.out.println("Password: " + row.get("password"));
}
