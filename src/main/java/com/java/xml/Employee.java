package com.java.xml;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述    :员工类
 * Author :Qing_X
 * Date   :2019-04-27 19:32
 */
public class Employee {
    private String name;
    private int age;
    private List<Office> list = new ArrayList <>();

    public List<Office> getOffices(){
        return list;
    }
    public void addOffice(Office office){
        list.add(office);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
