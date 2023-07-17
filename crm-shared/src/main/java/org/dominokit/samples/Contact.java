package org.dominokit.samples;

public class Contact {

    private String name;
    private String nickName;
    private int age;
    private boolean employee;

    public static Contact of(String name, String nickName, int age, boolean employee){
        return new Contact(name, nickName, age, employee);
    }

    public Contact(String name, String nickName, int age, boolean employee) {
        this.name = name;
        this.nickName = nickName;
        this.age = age;
        this.employee = employee;
    }

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEmployee() {
        return employee;
    }

    public void setEmployee(boolean employee) {
        this.employee = employee;
    }
}
