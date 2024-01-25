package expenseTracker;

import date.Date;
import types.TransactionType;

public class ExpenseTrackerFactory {

    static boolean hasAddedDefaultData = false;

    public static ExpenseTracker getExpenseTrackerWithoutDefaultData() {
        return new ExpenseTrackerImpl();
    }

    public static ExpenseTracker getExpenseTrackerWithDefaultData() {

        ExpenseTracker expenseTracker = new ExpenseTrackerImpl();
        if (!hasAddedDefaultData) {
            hasAddedDefaultData = true;

            expenseTracker.newMonth("December", 35000);

            // add default categories to the expense tracker
            String catID1 = expenseTracker.addCategory("Salary", 0, TransactionType.INCOME);
            String catID2 = expenseTracker.addCategory("Rent", 0, TransactionType.EXPENSE);
            String catID3 = expenseTracker.addCategory("Groceries", 0, TransactionType.EXPENSE);
            String catID4 = expenseTracker.addCategory("Transportation", 0, TransactionType.EXPENSE);
            String catID5 = expenseTracker.addCategory("Insurance", 0, TransactionType.EXPENSE);
            String catID6 = expenseTracker.addCategory("Debt", 0, TransactionType.EXPENSE);
            String catID7 = expenseTracker.addCategory("Education", 0, TransactionType.EXPENSE);
            String catID8 = expenseTracker.addCategory("Medicine", 0, TransactionType.EXPENSE);
            String catID9 = expenseTracker.addCategory("Taxes", 0, TransactionType.EXPENSE);
            String catID10 = expenseTracker.addCategory("Shopping", 0, TransactionType.EXPENSE);
            String catID11 = expenseTracker.addCategory("Kids", 0, TransactionType.EXPENSE);
            String catID12 = expenseTracker.addCategory("Bonuses", 0, TransactionType.INCOME);

            // add sample dates
            Date date1 = new Date(2023, 12, 05);
            Date date2 = new Date(2023, 12, 07);
            Date date3 = new Date(2023, 12, 12);
            Date date4 = new Date(2023, 12, 18);
            Date date5 = new Date(2023, 12, 27);

            Date date6 = new Date(2024, 01, 01);
            Date date7 = new Date(2024, 01, 03);
            Date date8 = new Date(2024, 01, 7);
            Date date9 = new Date(2024, 01, 10);
            Date date10 = new Date(2024, 01, 13);

            Date date11 = new Date(2024, 02, 13);
            Date date12 = new Date(2024, 01, 20);

            // add all the default transactions to expense tracker transaction list
            expenseTracker.addTransaction(1000, catID2, "Birthday treat", date1, false);
            expenseTracker.addTransaction(5000, catID5, "Groceries", date2, false);
            expenseTracker.addTransaction(12400, catID2, "Birthday party", date3, false);
            expenseTracker.addTransaction(23000, catID9, "Wedding", date4, false);
            expenseTracker.addTransaction(9500, catID11, "Family trip", date5, true);
            expenseTracker.addTransaction(115000, catID1, "Salary received", date5, true);

            expenseTracker.newMonth("January", 100000);

            expenseTracker.addTransaction(3600, catID2, "Dinner out", date6, false);
            expenseTracker.addTransaction(4000, catID5, "3l of petrol", date7, false);
            expenseTracker.addTransaction(7000, catID8, "Kids day out", date8, false);
            expenseTracker.addTransaction(5000, catID10, "Dancing Class", date9, false);
            expenseTracker.addTransaction(75000, catID10, "School fees", date10, false);

            expenseTracker.addTransaction(50000, catID3, "Credit Card Fee", date11, false);
            expenseTracker.addTransaction(115000, catID1, "Salary received", date12, true);
            expenseTracker.addTransaction(35000, catID12, "Bonus received", date12, false);

        }
        return expenseTracker;
    }
}
