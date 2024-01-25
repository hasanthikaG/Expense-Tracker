package category;

import types.TransactionType;

public class ExpenseCategoryImpl extends Category {
    public ExpenseCategoryImpl(String name){
        super(name);
    }

    @Override
    public int getOperator() {
        return -1;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.EXPENSE;
    }
}
