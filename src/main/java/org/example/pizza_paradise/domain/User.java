package org.example.pizza_paradise.domain;

public class User {
    private String name;
    private String email;
    private String address;
    private int points;

    public User(){
    }

    public User(String name, String email, String address){
        this.name = name;
        this.email = email;
        this.address = address;
        this.points = 0;
    }

    public User(String name, String email, String address, int points){
        this.name = name;
        this.email = email;
        this.address = address;
        this.points = points;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }

    public void addBonusPoints(int i) {
        if(i<0){
            throw new IllegalArgumentException("Bonus points can't be negative");
        }
        this.points += i;
    }
}
