package com.example.uptoyou.Entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Food")
public class Food {
    @PrimaryKey(autoGenerate = true)
    private int foodId;

    private int preferenceId;
    private boolean american;
    private boolean bbq;
    private boolean chinese;
    private boolean french;
    private boolean hamburger;
    private boolean indian;
    private boolean italian;
    private boolean japanese;
    private boolean mexican;
    private boolean pizza;
    private boolean seafood;
    private boolean steak;
    private boolean sushi;
    private boolean thai;

    public Food(int foodId, int preferenceId, boolean american, boolean bbq, boolean chinese, boolean french, boolean hamburger, boolean indian, boolean italian, boolean japanese, boolean mexican, boolean pizza, boolean seafood, boolean steak, boolean sushi, boolean thai) {
        this.foodId = foodId;
        this.preferenceId = preferenceId;
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

    public int getPreferenceId() {
        return preferenceId;
    }

    public void setPreferenceId(int preferenceId) {
        this.preferenceId = preferenceId;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public boolean isBbq() {
        return bbq;
    }

    public void setBbq(boolean bbq) {
        this.bbq = bbq;
    }

    public boolean isChinese() {
        return chinese;
    }

    public void setChinese(boolean chinese) {
        this.chinese = chinese;
    }

    public boolean isFrench() {
        return french;
    }

    public void setFrench(boolean french) {
        this.french = french;
    }

    public boolean isHamburger() {
        return hamburger;
    }

    public void setHamburger(boolean hamburger) {
        this.hamburger = hamburger;
    }

    public boolean isIndian() {
        return indian;
    }

    public void setIndian(boolean indian) {
        this.indian = indian;
    }

    public boolean isItalian() {
        return italian;
    }

    public void setItalian(boolean italian) {
        this.italian = italian;
    }

    public boolean isJapanese() {
        return japanese;
    }

    public void setJapanese(boolean japanese) {
        this.japanese = japanese;
    }

    public boolean isMexican() {
        return mexican;
    }

    public void setMexican(boolean mexican) {
        this.mexican = mexican;
    }

    public boolean isPizza() {
        return pizza;
    }

    public void setPizza(boolean pizza) {
        this.pizza = pizza;
    }

    public boolean isSeafood() {
        return seafood;
    }

    public void setSeafood(boolean seafood) {
        this.seafood = seafood;
    }

    public boolean isSteak() {
        return steak;
    }

    public void setSteak(boolean steak) {
        this.steak = steak;
    }

    public boolean isSushi() {
        return sushi;
    }

    public void setSushi(boolean sushi) {
        this.sushi = sushi;
    }

    public boolean isThai() {
        return thai;
    }

    public void setThai(boolean thai) {
        this.thai = thai;
    }
}
