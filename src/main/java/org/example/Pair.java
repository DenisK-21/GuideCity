package org.example;

public class Pair {
    private int value1;
    private int value2;
    public Pair(){
        this.value1 = 0;
        this.value2 = 0;
    }

    public int getValue1() {
        return value1;
    }

    public int getValue2() {
        return value2;
    }

    public void setValue1(int value1) {
        this.value1 = value1;
    }

    public void setValue2(int value2) {
        this.value2 = value2;
    }
}
