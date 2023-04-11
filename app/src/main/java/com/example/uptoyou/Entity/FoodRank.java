package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "FoodRank")
public class FoodRank {
    @PrimaryKey(autoGenerate = true)
    private int foodId;

    private int american;
    private int bbq;
    private int chinese;
    private int french;
    private int hamburger;
    private int indian;
    private int italian;
    private int japanese;
    private int mexican;
    private int pizza;
    private int seafood;
    private int steak;
    private int sushi;
    private int thai;

    public FoodRank(int american, int bbq, int chinese, int french, int hamburger, int indian, int italian, int japanese, int mexican, int pizza, int seafood, int steak, int sushi, int thai) {
        this.american = american;
        this.bbq = bbq;
        this.chinese = chinese;
        this.french = french;
        this.hamburger = hamburger;
        this.indian = indian;
        this.italian = italian;
        this.japanese = japanese;
        this.mexican = mexican;
        this.pizza = pizza;
        this.seafood = seafood;
        this.steak = steak;
        this.sushi = sushi;
        this.thai = thai;
    }

    public int getFoodId() {
        return foodId;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public int getAmerican() {
        return american;
    }

    public void setAmerican(int american) {
        this.american = american;
    }

    public int getBbq() {
        return bbq;
    }

    public void setBbq(int bbq) {
        this.bbq = bbq;
    }

    public int getChinese() {
        return chinese;
    }

    public void setChinese(int chinese) {
        this.chinese = chinese;
    }

    public int getFrench() {
        return french;
    }

    public void setFrench(int french) {
        this.french = french;
    }

    public int getHamburger() {
        return hamburger;
    }

    public void setHamburger(int hamburger) {
        this.hamburger = hamburger;
    }

    public int getIndian() {
        return indian;
    }

    public void setIndian(int indian) {
        this.indian = indian;
    }

    public int getItalian() {
        return italian;
    }

    public void setItalian(int italian) {
        this.italian = italian;
    }

    public int getJapanese() {
        return japanese;
    }

    public void setJapanese(int japanese) {
        this.japanese = japanese;
    }

    public int getMexican() {
        return mexican;
    }

    public void setMexican(int mexican) {
        this.mexican = mexican;
    }

    public int getPizza() {
        return pizza;
    }

    public void setPizza(int pizza) {
        this.pizza = pizza;
    }

    public int getSeafood() {
        return seafood;
    }

    public void setSeafood(int seafood) {
        this.seafood = seafood;
    }

    public int getSteak() {
        return steak;
    }

    public void setSteak(int steak) {
        this.steak = steak;
    }

    public int getSushi() {
        return sushi;
    }

    public void setSushi(int sushi) {
        this.sushi = sushi;
    }

    public int getThai() {
        return thai;
    }

    public void setThai(int thai) {
        this.thai = thai;
    }
}
