package com.hothikieulinh.models;

import java.io.Serializable;

public class Employee  implements Serializable{
    String Id;
    String Name;
    int Age;

    public Employee(String id, String name, int age) {
        Id = id;
        Name = name;
        Age = age;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    @Override
    public String toString() {
        return this.Name;
    }
}
