import org.junit.Test;

import date.Date;
import expenseTracker.ExpenseTracker;
import expenseTracker.ExpenseTrackerFactory;
import types.TransactionType;

public class ExpenseTrackerTest {
    @Test
    public void testMonthAdd() {
        // get a tracker without default data
        ExpenseTracker tracker = ExpenseTrackerFactory.getExpenseTrackerWithoutDefaultData();
        // add a new month
        tracker.newMonth("January", 350000);
        // get the month
        var month = tracker.getMonth("January");
        // assert the response
        assert(month.getBudget() == 350000);
    }

    @Test
    public void testAddCategory() {
        // get a tracker without default data
        ExpenseTracker tracker = ExpenseTrackerFactory.getExpenseTrackerWithoutDefaultData();
        // add a new category
        tracker.addCategory("Salary", 0, TransactionType.INCOME);
        // get the categories
        var resp = tracker.getCategories();
        // assert the response
        assert(resp.size() == 1 && resp.get(0).getName().equals("Salary"));
    }

    @Test
    public void testAddTransaction() {
        // get a tracker without default data
        ExpenseTracker tracker = ExpenseTrackerFactory.getExpenseTrackerWithoutDefaultData();
        // add a new category
        String catID = tracker.addCategory("Salary", 0, TransactionType.INCOME);
        // add a new transaction
        tracker.addTransaction(350000, catID, "Salary Received",new Date(2022, 1, 10), true);
        // get the transactions for month
        var resp = tracker.getTransactionsForMonth("January");
        // assert the response
        assert(resp.size() == 1 && resp.get(0).getAmount() == 350000);
        assert(resp.get(0).getCategory().getId().equals(catID));
        assert(resp.get(0).getCategory().getType() == TransactionType.INCOME);
    }
}
