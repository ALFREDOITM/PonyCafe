package org.example.ponycafe;

import java.io.Serializable;

public class MenuModal2 implements Serializable {
    private String name;
    private  String img;
    private String desc;
    private int cost;

    public MenuModal2(){

    }
    public MenuModal2(String name, String img, String desc, int cost) {
        this.name = name;
        this.img = img;
        this.desc = desc;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
