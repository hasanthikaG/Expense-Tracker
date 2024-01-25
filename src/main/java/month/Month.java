package month;

public class Month {
    private String name;
    private double budget;  // 0 if no budget

    public Month(String name, double budget) {
        this.name = name;
        this.budget = budget;
    }

    public Month(String name) {
        this.name = name;
        this.budget = 0;
    }

    public String getName() {
        return name;
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
}