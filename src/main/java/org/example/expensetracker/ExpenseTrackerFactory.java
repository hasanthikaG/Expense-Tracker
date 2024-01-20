package org.example.expensetracker;

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
            String catID2 = expenseTracker.addCategory("Eating Out", 0, TransactionType.EXPENSE);
            String catID3 = expenseTracker.addCategory("Credit", 0, TransactionType.EXPENSE);
            String catID4 = expenseTracker.addCategory("Debit", 0, TransactionType.EXPENSE);
            String catID5 = expenseTracker.addCategory("Fuel", 0, TransactionType.EXPENSE);
            String catID6 = expenseTracker.addCategory("General", 0, TransactionType.EXPENSE);
            String catID7 = expenseTracker.addCategory("Holidays", 0, TransactionType.EXPENSE);
            String catID8 = expenseTracker.addCategory("Kids", 0, TransactionType.EXPENSE);
            String catID9 = expenseTracker.addCategory("Shopping", 0, TransactionType.EXPENSE);
            String catID10 = expenseTracker.addCategory("Sports", 0, TransactionType.EXPENSE);
            String catID11 = expenseTracker.addCategory("Travel", 0, TransactionType.EXPENSE);

            // add sample dates
            Date date1 = new Date(2022, 12, 05);
            Date date2 = new Date(2022, 12, 07);
            Date date3 = new Date(2022, 12, 12);
            Date date4 = new Date(2022, 12, 18);
            Date date5 = new Date(2022, 12, 27);

            Date date6 = new Date(2023, 01, 01);
            Date date7 = new Date(2023, 01, 03);
            Date date8 = new Date(2023, 01, 7);
            Date date9 = new Date(2023, 01, 10);
            Date date10 = new Date(2023, 01, 13);

            Date date11 = new Date(2023, 02, 13);

            // add all the default transactions to expense tracker transaction list
            expenseTracker.addTransaction(1000, catID2, "My Birthday treat for family members", date1, false);
            expenseTracker.addTransaction(5000, catID5, "Filled my car up with petrol", date2, false);
            expenseTracker.addTransaction(12400, catID2, "Had party with friends", date3, false);
            expenseTracker.addTransaction(23000, catID9, "Shopping with the family", date4, false);
            expenseTracker.addTransaction(9500, catID11, "Trip to yala with the friends", date5, true);
            expenseTracker.addTransaction(115000, catID1, "Salary received", date5, true);

            expenseTracker.newMonth("January", 55000);
            
            expenseTracker.addTransaction(4800, catID2, "Dinner from PizzaHut", date6, false);
            expenseTracker.addTransaction(10000, catID5, "Filled my car up with petrol", date7, false);
            expenseTracker.addTransaction(3000, catID8, "Bought Toys for kids", date8, false);
            expenseTracker.addTransaction(1400, catID10, "Badminton Practices", date9, false);
            expenseTracker.addTransaction(2500, catID10, "Swimming Practices", date10, false);

            expenseTracker.addTransaction(8000, catID3, "Paid credit to Nuwan", date11, false);

            
        }
        return expenseTracker;
    }
}
