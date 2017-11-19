package com.zxg.tongxunlu.model;

/**
 *
 * @author zxg
 * @date 2017/10/23
 */
public class Relation {
    private int id;
    private String name;
    private String number;
    private String location;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Relation(int id, String name, String number, String location) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.location = location;
    }

    public Relation(int id, String name, String number) {
        this.id = id;
        this.name = name;
        this.number = number;
    }

    public Relation(String string) {
        this.name = string;
    }

    public Relation() {
    }

    @Override
    public String toString() {
        return "Relation{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
