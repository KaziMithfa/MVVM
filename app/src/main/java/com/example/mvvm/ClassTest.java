package com.example.mvvm;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Class_Test")

public class ClassTest {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int Roll;
    private double number;


public ClassTest(String name,int Roll,double number)
{
    this.name = name;
    this.Roll = Roll;
    this.number = number;
}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRoll() {
        return Roll;
    }

    public double getNumber() {
        return number;
    }
}
