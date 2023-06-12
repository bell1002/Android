package com.hothikieulinh.models;

import java.io.Serializable;

public class Student implements Serializable {
    String Name, Classs, Id;

    public Student(String name, String classs, String id) {
        Name = name;
        Classs = classs;
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getClasss() {
        return Classs;
    }

    public void setClasss(String classs) {
        Classs = classs;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    @Override
    public String toString() {
        return
                        "Ten sinh vien: " + Name + "\n" +
                        "Lop hoc: " +Classs + "\n" +
                        "Ma sinh vien: " + Id ;
    }
}
