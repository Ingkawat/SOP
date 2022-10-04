package com.example.week6.pojo;

import com.vaadin.flow.component.template.Id;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Wizard")
public class Wizard {
    @Id
    private String _id;
    private char sex;
    private String name;
    private int money;
    private String school;
    private String position;
    private String house;

    public Wizard() {}
    public Wizard(String _id, String name, char sex, int money, String school, String position, String house) {
        this._id = _id;
        this.name = name;
        this.sex = sex;
        this.money = money;
        this.school = school;
        this.position = position;
        this.house = house;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}
