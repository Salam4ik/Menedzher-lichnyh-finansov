package ExportStatistics;

import ImportExpenses.ImportExpenses;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.accessibility.Accessible;
import java.io.*;

class ExportStatisticsTest {
    @Test
    public void maxCategory() throws IOException {
        ImportExpenses importExpenses = new ImportExpenses();
        importExpenses.getExpensesMap().put("одежда", 950L);
        importExpenses.getExpensesMap().put("еда", 1550L);
        importExpenses.getExpensesMap().put("Другое", 150L);
        importExpenses.getExpensesMap().put("финансы", 5000L);
        importExpenses.getExpensesMap().put("быт", 50L);
        ExportStatistics exportStatistics = new ExportStatistics();
        exportStatistics.maxCategory(importExpenses);

        Assertions.assertEquals("финансы",exportStatistics.getMaxOfCategory().getCategory());
        Assertions.assertEquals(5000L,exportStatistics.getMaxOfCategory().getSum());
    }

    @Test
    public  void writeMaxCategory() throws IOException {
        File file = new File("maxCategory.json");
        if (!file.exists()) file.createNewFile();
        ImportExpenses importExpenses = new ImportExpenses();
        importExpenses.getExpensesMap().put("одежда", 950L);
        importExpenses.getExpensesMap().put("еда", 1550L);
        importExpenses.getExpensesMap().put("Другое", 150L);
        importExpenses.getExpensesMap().put("финансы", 5000L);
        importExpenses.getExpensesMap().put("быт", 50L);
        ExportStatistics exportStatistics = new ExportStatistics();
        exportStatistics.maxCategory(importExpenses);

        exportStatistics.writeMaxCategory();

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null){
            builder.append(line);
        }
        String expected = "{\"maxCategory\":{\"category\":\"финансы\",\"sum\":5000}}";
        String actual = String.valueOf(builder);
        Assertions.assertEquals(expected, actual);
    }
}