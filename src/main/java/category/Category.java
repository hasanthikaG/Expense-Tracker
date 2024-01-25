package category;

import types.TransactionType;

import java.util.UUID;

public abstract class Category {

    private String name;
    private String id;
    private double budget; // 0 if no budget

    public Category(String name) {
        // create a unique id for this category length of 4
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract int getOperator();

    public abstract TransactionType getType();
}
