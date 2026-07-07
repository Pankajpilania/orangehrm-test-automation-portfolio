package com.orangehrm.utils;

import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelDataProvider {

    @DataProvider(name = "employeeData")
    public Object[][] getEmployeeData() {
        String filePath = "src/test/resources/employees.csv";
        List<Object[]> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if(values.length >= 2) {
                    dataList.add(new Object[]{values[0].trim(), values[1].trim()});
                }
            }
        } catch (IOException e) {
             dataList.add(new Object[]{"DefaultFirst", "DefaultLast"});
        }

        Object[][] data = new Object[dataList.size()][];
        for(int i=0; i<dataList.size(); i++){
            data[i] = dataList.get(i);
        }
        return data;
    }
}
