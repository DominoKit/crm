package org.dominokit.samples;

public class Opportunity {

    private String title;
    private String customerName;
    private double expectedRevenue;
    private int priority;

    public static Opportunity of(String title, String customerName, double expectedRevenue, int priority){
        return new Opportunity(title, customerName, expectedRevenue, priority);
    }

    public Opportunity() {
    }

    public Opportunity(String title, String customerName, double expectedRevenue, int priority) {
        this.title = title;
        this.customerName = customerName;
        this.expectedRevenue = expectedRevenue;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getExpectedRevenue() {
        return expectedRevenue;
    }

    public void setExpectedRevenue(double expectedRevenue) {
        this.expectedRevenue = expectedRevenue;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
