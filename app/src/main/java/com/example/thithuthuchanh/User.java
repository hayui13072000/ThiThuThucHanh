package com.example.thithuthuchanh;

public class User {
    private String id;
    private String name;
    private int age;
    private String iclass;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public User(String id, String name, int age, String iclass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.iclass = iclass;
    }

    public String getIclass() {
        return iclass;
    }

    public void setIclass(String iclass) {
        this.iclass = iclass;
    }
}
