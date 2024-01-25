package expenseTracker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import category.Category;
import constants.Constants;
import date.Date;
import month.Month;
import transaction.Transaction;
import types.TransactionType;

public class DisplayExpenseTracker {

    // create a new ExpenseTracker object
    static ExpenseTracker tracker = ExpenseTrackerFactory.getExpenseTrackerWithDefaultData();
    static String outlineColor = Constants.COLOR_BLUE;
    static String currentMonthName = LocalDate.now().getMonth().name().toLowerCase();

    public static void viewTracker() {
        displayMainView();
        currentMonthName = currentMonthName.substring(0, 1).toUpperCase() + currentMonthName.substring(1);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMake your selection: ");

            while(scanner.hasNextLine()){
                String choice = scanner.nextLine();

                switch (choice) {
                    case "1":
                        displayMainView();
                        break;
                    case "2":
                        transactionMenuExecution();
                        break;
                    case "3":
                        categoryMenuExecution();
                        break;
                    case "4":
                        budgetMenuExecution();
                        break;
                    case "5":
                        changeCurrentMonth(false);
                        displayMainView();
                        break;
                    case "6":
                        changeCurrentMonth(true);
                        displayMainView();
                        break;
                    case "7":
                    default:
                        displayMainView();
                        return;
                }
                // this will clear the previously displayed console data
                clearConsole();
            }
            // this will clear the previously displayed console data


        }
    }

    public static void displayMainView() {
        printNewHeader();
        showSpendings();
        viewFooter();
    }

    public static void transactionMenuExecution() {
        displayTransactionListView();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMake your selection: ");

            String choice = scanner.nextLine();
            clearConsole();
                switch (choice) {
                    case "1":
                        displayTransactionCreateView();
                        break;
                    case "2":
                        displayTransactionListView();
                        break;
                    case "3":
                        displayTransactionUpdateView();
                        break;
                    case "4":
                        displayTransactionDeleteView();
                        break;
                    case "5":
                        changeCurrentMonth(false);
                        displayTransactionListView();
                        break;
                    case "6":
                        changeCurrentMonth(true);
                        displayTransactionListView();
                        break;
                    default:
                        displayMainView();
                        return;
                }
            }
    }

    public static void displayTransactionCreateView() {
        printNewHeader();
        Scanner scanner = new Scanner(System.in);
        List<Category> categoryList = new ArrayList<Category>();
        System.out.print("\tEnter Transaction Type: ");
        System.out.println("\t   1. " + TransactionType.EXPENSE + "   2. " + TransactionType.INCOME);
        System.out.print("\t");
        int trType = scanner.nextInt();
        while (true) {
            if (trType == 1) {
                categoryList = tracker.getCategories(TransactionType.EXPENSE);
                break;
            } else if (trType == 2) {
                categoryList = tracker.getCategories(TransactionType.INCOME);
                break;
            } else {
                System.out.println("\tInvalid Transaction Type ! Enter Again .");
                trType = scanner.nextInt();
            }
        }
        System.out.println("\n\tEnter Category ID From Below List: \n");
        System.out.println("\033[" + outlineColor + "m   No\t|\033[0m \033[" + outlineColor + "mID\033[0m\t\033[" + outlineColor + "m|\033[0m \033[" + outlineColor + "mName\033[0m");
        System.out.println("\033[" + outlineColor + "m\t--------------------------------\033[0m");
        for (int i = 0; i < categoryList.size(); i++) {
            Category category = tracker.getCategories().get(i);
            System.out.println(
                    String.format(" %-5d\t\033[%s" + "m|\033[0m %-5s\t\033[%s" + "m|\033[0m %s",    i + 1, outlineColor, category.getId(), outlineColor, category.getName()));
        }
        scanner.nextLine();
        System.out.print("\t   ");
        String catId = scanner.nextLine();
        System.out.print("\n\tEnter Transaction Note:  ");
        String trNote = scanner.nextLine();
        System.out.print("\n\tEnter Transaction Amount:  ");
        double trAmount = scanner.nextDouble();
        System.out.print("\n\tEnter Transaction Date (yyyy-MM-dd):  ");
        scanner.nextLine();
        String trDate = scanner.nextLine();
        LocalDate date = LocalDate.parse(trDate);
        Date customeDate = new Date(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
        tracker.addTransaction(trAmount, catId, trNote, customeDate, false);
        System.out.println("\n\tTransaction Added Successfully!");
    }

    public static void displayTransactionDeleteView() {
        viewTransactionsListTable();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tEnter Transaction ID To Delete : ");
        String id = scanner.nextLine();
        tracker.deleteTransaction(id);
        System.out.println("\n\tTransaction Successfully Deleted!");
        transactionMenuFooter();
    }

    public static void displayTransactionUpdateView() {
        viewTransactionsListTable();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\n\tEnter Transaction ID To Update : ");
        String id = scanner.nextLine();
        System.out.print("\n\tEnter Transaction Note : ");
        String note = scanner.nextLine();
        System.out.print("\n\tEnter Transaction Amount : ");
        double amount = scanner.nextDouble();
        tracker.updateTransaction(id, note, amount);
        System.out.println("\n\tTransaction Successfully Updated !");
        transactionMenuFooter();
    }

    public static void categoryMenuExecution() {
        displayCategoryListView();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMake your selection: ");
            String choice = scanner.nextLine();
            clearConsole();
            switch (choice) {
                case "1":
                    displayCategoryCreateView(false);
                    break;
                case "2":
                    displayCategoryCreateView(true);
                    break;
                case "3":
                    displayCategoryListView();
                    break;
                case "<":
                    changeCurrentMonth(false);
                    displayCategoryListView();
                    break;
                case ">":
                    changeCurrentMonth(true);
                    displayCategoryListView();
                    break;
                case "4":
                default:
                    displayMainView();
                    return;
            }
        }
    }

    public static void displayCategoryListView() {
        printNewHeader();
        System.out.println("\033[" + Constants.COLOR_BLUE + "m\t                        CATEGORY LIST \033[0m\n");
        for (int i = 0; i < tracker.getCategories().size(); i++) {
            Category category = tracker.getCategories().get(i);
            System.out.println(String.format("%-5d%s", i + 1, category.getName()));
        }
        categoryMenuFooter();
    }

    public static void displayCategoryCreateView(boolean isIncomeCategory) {
        printNewHeader();
        Scanner scanner = new Scanner(System.in);
        System.out.print("\tAdd New Category: ");
        String name = scanner.nextLine();
        tracker.addCategory(name, 0, isIncomeCategory ? TransactionType.INCOME : TransactionType.EXPENSE);
        System.out.println("\n\tCategory Added Successfully!");
        categoryMenuFooter();
    }

    public static void showSpendings() {
        var result = tracker.getSummaryForMonth(currentMonthName);

        double totalIncomeAmount = 0;
        double totalExpenseAmount = 0;
        double currentMonthBudget = tracker.getMonth(currentMonthName).getBudget();

        if (!result.isEmpty()) {
            for (DtoMonthlySummaryData value : result) {
                if (value.category.getType() == TransactionType.INCOME)
                    totalIncomeAmount += value.totalAmount;
            }
            System.out.println(String.format("%-35s %-25s %-16s %-16s", "\t Category",
                    " \033[" + outlineColor + "m|\033[0m Amount", "  Budget", "  Remaining"));
            System.out.println("\033[" + outlineColor + "m"
                    + "\t.....................................................................................\033[0m");
            for (DtoMonthlySummaryData value : result) {
                if (value.category.getType() == TransactionType.EXPENSE)
                    totalExpenseAmount += value.totalAmount;
            }
            System.out.println("\t\033[" + Constants.COLOR_BLUE + "mExpenses\033[0m");
            for (DtoMonthlySummaryData data : result) {
                if (data.category.getType() == TransactionType.EXPENSE)
                    System.out.println(String.format("%-35s %-25s %-16s %-16s", "\t   " + data.category.getName(),
                            " \033[" + outlineColor + "m|\033[0m  " + data.totalAmount,
                            (data.category.getBudget() > 0 ? ("   " + data.category.getBudget())
                                    : ("\033[" + outlineColor + "m\t-\033[0m")),
                            (data.category.getBudget() > 0 ? ("   " + (data.category.getBudget() - data.totalAmount))
                                    : ("\033[" + outlineColor + "m\t\t-\033[0m"))));
            }
            System.out.println(String.format("%-35s %s", "\t\033[" + Constants.COLOR_PURPLE + "mTotal Expenses\033[0m",
                    "\t        \033[" + outlineColor + "m=\033[0m " + "\033[" + Constants.COLOR_WHITE + "m "
                            + totalExpenseAmount + "\033[0m   "));
            System.out.println("\t\033[" + Constants.COLOR_BLUE + "mIncome\033[0m");
            for (DtoMonthlySummaryData data : result) {
                if (data.category.getType() == TransactionType.INCOME)
                    System.out.println(
                            String.format("%-35s %s", "\t   " + data.category.getName(),
                                    " \033[" + outlineColor + "m|\033[0m  " + data.totalAmount));
            }
            System.out.println(String.format("%-35s %s", "\t\033[" + Constants.COLOR_PURPLE + "mTotal Income\033[0m",
                    "\t        \033[" + outlineColor + "m=\033[0m " + "\033[" + Constants.COLOR_WHITE + "m "
                            + totalIncomeAmount + "\033[0m  "));

            System.out.println("\033[" + outlineColor + "m"
                    + "\t.....................................................................................\033[0m");
            System.out.println(String.format("%-35s %s", "\t\033[" + Constants.COLOR_BLUE + "mMonthly Budget\033[0m",
                    "\033[" + Constants.COLOR_PURPLE + "m\t        \033[" + outlineColor + "m|\033[0m "+"\033[" + Constants.COLOR_PURPLE + "m "
                            + currentMonthBudget + "\033[0m  "));
            System.out.println(String.format("%-35s %s", "\t\033[" + Constants.COLOR_BLUE + "mRemaining\033[0m",
                    "\033[" + Constants.COLOR_PURPLE + "m\t        \033[" + outlineColor + "m|\033[0m "+"\033[" + Constants.COLOR_PURPLE + "m "
                            + (currentMonthBudget - totalExpenseAmount)
                            + "\033[0m  "));
        } else {
            System.out.println("                       No Transactions to Display                            ");
        }
    }

    public static void displayTransactionListView() {
        viewTransactionsListTable();
        transactionMenuFooter();
    }

    public static void viewTransactionsListTable() {
        List<Transaction> tList = tracker.getTransactionsForMonth(currentMonthName);
        tList.sort((a, b) -> a.getType().compareTo(b.getType()));
        printNewHeader();
        System.out.println("\033[" + Constants.COLOR_BLUE + "m\t                        TRANSACTION LIST \033[0m\n");
        System.out.println(
                String.format("%-60s %s", "\t   " + "Transaction Id   " + "\033[" + outlineColor + "m|\033[0m Transaction",
                        "\033[" + outlineColor + "m |\033[0m Amount"));
        System.out.println("\033[" + outlineColor + "m"
                + "\t   ---------------------------------------------------------------\033[0m"); // TODO: character
        // length must be less
        // than 40
        tList.forEach(transaction -> {
            if (transaction.getType() == TransactionType.INCOME) {
                System.out.println(
                        String.format("%-60s %s",
                                "\t               " + transaction.getId() + " \033[" + outlineColor + "m|\033[0m "
                                        + transaction.getNote(),
                                " \033[" + outlineColor
                                        + "m|\033[0m \033[" + Constants.COLOR_GREEN
                                        + "mRs. " + transaction.getAmount() + "\033[0m"));
            } else {
                System.out.println(
                        String.format("%-60s %s",
                                "\t               " + transaction.getId() + " \033[" + outlineColor + "m|\033[0m "
                                        + transaction.getNote(),
                                " \033[" + outlineColor
                                        + "m|\033[0m \033[" + Constants.COLOR_RED
                                        + "mRs. " + transaction.getAmount() + "\033[0m"));
            }
        });
    }

    public static void budgetMenuExecution() {
        displayBudgetListView();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("\nMake your selection: ");
            String choice = scanner.nextLine();
            clearConsole();
            switch (choice) {
                case "1":
                    displayBudgetCreateView();
                    break;
                case "2":
                    displayBudgetListView();
                    break;
                case "<":
                    changeCurrentMonth(false);
                    displayBudgetListView();
                    break;
                case ">":
                    changeCurrentMonth(true);
                    displayBudgetListView();
                    break;
                case "3":
                default:
                    displayMainView();
                    return;
            }
        }
    }

    public static void displayBudgetListView() {
        printNewHeader();
        System.out.println("\t                          \033[" + Constants.COLOR_BLUE + "mMONTHLY BUDGET LIST\n\033[0m");
        for(int i=0; i < tracker.getMonths().size(); i++) {
            Month month = tracker.getMonths().get(i);
            System.out.println(String.format("%-5d %-35s %s", i + 1, month.getName(), month.getBudget()));
        }

        System.out.println("\n\t                         \033[" + Constants.COLOR_BLUE + "mCATEGORY BUDGET LIST\n\033[0m");
        for (int i = 0; i < tracker.getCategories().size(); i++) {
            Category category = tracker.getCategories().get(i);
            System.out.println(String.format("%-5d %-35s %s", i + 1, category.getName(), category.getBudget()));
        }
        budgetMenuFooter();
    }

    public static void displayBudgetCreateView() {
        printNewHeader();
        Scanner scanner = new Scanner(System.in);
        List<Category> categoryList = new ArrayList<Category>();
        List<Month> monthList = new ArrayList<Month>();
        System.out.println("\tEnter Budget Type From Below List: ");
        System.out.println("\t   1. " + "Monthly");
        System.out.println("\t   2. " + "Category");
        System.out.print("\t");
        int trType = scanner.nextInt();
        while (true) {
            if (trType == 1) {
                monthList = tracker.getMonths();
                System.out.println("\n\tSelect Month From Below List: \n");
                for (Month month : monthList) {
                    System.out.println("\t   " + month.getName());
                }

                scanner.nextLine();
                System.out.print("\t   ");
                String monthName = scanner.nextLine();
                System.out.print("\n\tAdd Budget Amount: ");
                double budget = scanner.nextDouble();
                tracker.setMonthlyBudget(monthName, budget);
                System.out.println("\n\tBudget Added Successfully!");
                break;
            } else if (trType == 2) {
                categoryList = tracker.getCategories();
                System.out.println("\n\tEnter Category ID From Below List: \n");
                System.out.println("\033[" + outlineColor + "m   No\t|\033[0m \033[" + outlineColor + "mID\033[0m\t\033[" + outlineColor + "m|\033[0m \033[" + outlineColor + "mName\033[0m");
                System.out.println("\033[" + outlineColor + "m\t--------------------------------\033[0m");
                for (int i = 0; i < categoryList.size(); i++) {
                    Category category = tracker.getCategories().get(i);
                    System.out.println(
                            String.format(" %-5d\t\033[%s" + "m|\033[0m %-5s\t\033[%s" + "m|\033[0m %s", i + 1, outlineColor, category.getId(), outlineColor, category.getName()));
                }
                scanner.nextLine();
                System.out.print("\t   ");
                String catId = scanner.nextLine();
                System.out.print("\n\tEnter Budget Amount: ");
                double budget = scanner.nextDouble();
                tracker.setCategoryBudget(catId, budget);
                System.out.println("\n\tBudget Added Successfully!");
                break;
            } else {
                System.out.println("\tInvalid Budget Type! Enter Again.");
                trType = scanner.nextInt();
            }
        }
        budgetMenuFooter();
    }



    public static void viewFooter() {
        printDottedLine();
        printMainMenu();
    }

    public static void transactionMenuFooter() {
        printNewLine();
        printDottedLine();
        printTransactionsViewMenu();
    }

    public static void categoryMenuFooter() {
        printNewLine();
        printDottedLine();
        printCategoriesViewMenu();
    }

    public static void budgetMenuFooter() {
        printNewLine();
        printDottedLine();
        printBudgetViewMenu();
    }

    public static void changeCurrentMonth(boolean isNext) {
        if (tracker.getMonths().size() == 0) {
            return;
        }
        tracker.getMonths().sort((a, b) -> {
            String[] monthOrder = {
                    "January", "February", "March", "April", "May", "June", "July", "August", "September", "October",
                    "November", "December"
            };
            return Integer.compare(
                    Arrays.asList(monthOrder).indexOf(a.getName()),
                    Arrays.asList(monthOrder).indexOf(b.getName()));
        });
        Month month = tracker.getMonths().stream().filter(m -> m.getName().equals(currentMonthName)).findFirst().get();
        if (!isNext) {
            int index = tracker.getMonths().indexOf(month);
            if (index == 0) {
                currentMonthName = tracker.getMonths().get(tracker.getMonths().size() - 1).getName();
            } else {
                currentMonthName = tracker.getMonths().get(index - 1).getName();
            }
        } else {
            int index = tracker.getMonths().indexOf(month);
            if (index == tracker.getMonths().size() - 1) {
                currentMonthName = tracker.getMonths().get(0).getName();
            } else {
                currentMonthName = tracker.getMonths().get(index + 1).getName();
            }
        }
    }

    public static void printDottedLine() {
        System.out.println(
                "\033[" + Constants.COLOR_RED
                        + "m-------------------------------------------------------------------------------------------\033[0m");
    }

    public static void printDoubleLine() {
        System.out.println(
                "\033[" + outlineColor
                        + "m==================================================================================================\033[0m");
    }

    public static void printCurrentMonthName() {
        System.out.println("\t         < Prev                     \033[" + Constants.COLOR_PURPLE + "m"
                + currentMonthName.substring(0, 1).toUpperCase() + currentMonthName.substring(1)
                + "\033[0m                      Next >           ");
    }

    public static void printMainMenu() {
        System.out.println("Selections");
        System.out.println(" 1. Spending");
        System.out.println(" 2. Transactions");
        System.out.println(" 3. Categories");
        System.out.println(" 4. Budget");
        System.out.println(" 5. Previous Month");
        System.out.println(" 6. Next Month");
        System.out.println(" 7. Exit");
    }

    public static void printCategoriesViewMenu() {
        System.out.println("Selections");
        System.out.println(" 1. Add Expense Category");
        System.out.println(" 2. Add Income Category");
        System.out.println(" 3. View Categories");
        System.out.println(" 4. Main Screen");
    }

    public static void printTransactionsViewMenu() {
        System.out.println("Selections");
        System.out.println(" 1. Add Transaction");
        System.out.println(" 2. View Transaction");
        System.out.println(" 3. Update Transaction");
        System.out.println(" 4. Delete Transaction");
        System.out.println(" 5. Previous Month");
        System.out.println(" 6. Next Month");
        System.out.println(" 7. Main Screen");
    }

    public static void printBudgetViewMenu() {
        System.out.println("Selections");
        System.out.println(" 1. Add New Budget");
        System.out.println(" 2. View Budget");
        System.out.println(" 3. Main Screen");
    }

    public static void printNewLine() {
        System.out.println("\n");
    }

    public static void clearConsole() {
        System.out.print("\033c");
    }


    public static void printNewHeader() {
        int width = 90;
        int totalHeight = 3;  // Adjust the total height as needed
        String text = "F I N A N C I A L    T R A C K E R";

        // Ensure an odd total height for a clear center row
        int height = totalHeight % 2 == 0 ? totalHeight + 1 : totalHeight;

        // Nested loops to print the rectangle with text in the middle
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (i == 0 || i == height - 1 || j == 0 || j == width - 1) {
                    // Print '-' for the border
                    System.out.print("\033[" + Constants.COLOR_RED + "m-\033[0m");
                } else if (i == height / 2) {
                    // Print the text in the middle row
                    int textStart = (width - text.length()) / 2;
                    int textEnd = textStart + text.length();
                    if (j >= textStart && j < textEnd) {
                        System.out.print("\033[" + Constants.COLOR_YELLOW + "m"+ text.charAt(j - textStart)+"\033[0m");
                    } else {
                        System.out.print(" ");
                    }
                } else {
                    // Print space for the middle
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next line after printing each row
        }
        printNewLine();
        printCurrentMonthName();
        printNewLine();
    }

}