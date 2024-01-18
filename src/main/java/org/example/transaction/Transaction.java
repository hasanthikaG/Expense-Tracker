package org.example.transaction;

import java.util.UUID;

import org.example.category.Category;
import org.example.date.Date;
import org.example.types.TransactionType;

public class Transaction {
    
    private String id;
    private double amount;
    private Category category;
    private String note;
    private Date date;
    private boolean isRecurring = false;
    private boolean isActive = true;
    
    public Transaction(double amount, Category category, String note, Date date, boolean isRecurring) {
        this.id = UUID.randomUUID().toString().substring(0, 4);
        this.amount = amount;
        this.category = category;
        this.note = note;
        this.date = date;
    }

    // getters and setters
    public boolean isRecurring() {
        return isRecurring;
    }

    public double getAmount() {
        if (isActive) {
            return amount;
        }
        return 0.0;
    }

    public double getRawAmount() {
        return amount;
    }

    public double getAmountForTotal() {
        if (isActive) {
            return amount * this.category.getOperator();
        } else {
            return 0;
        }
    }

    public TransactionType getType() {
        return this.category.getType()
    }

    public Category getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setRecurring(boolean recurring) {
        isRecurring = recurring;
    }

    public void setActive(boolean active) {
        isActive = active;
 
}
