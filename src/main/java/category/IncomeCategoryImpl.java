package category;

import types.TransactionType;

public class IncomeCategoryImpl extends Category {
    
    public IncomeCategoryImpl(String name) {
        super(name);
    }

    @Override
    public int getOperator() {
        return 1;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.INCOME;
    }
}
