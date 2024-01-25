package category;

import types.TransactionType;

public class CategoryFactory {
    public static Category createCategory(String name, double budget, TransactionType type) {
        if (type == TransactionType.EXPENSE) {
            var cat = new ExpenseCategoryImpl(name);
            cat.setBudget(budget);
            return cat;
        }
        else {
            var cat = new IncomeCategoryImpl(name);
            cat.setBudget(budget);
            return new IncomeCategoryImpl(name);
        }
    }
}
