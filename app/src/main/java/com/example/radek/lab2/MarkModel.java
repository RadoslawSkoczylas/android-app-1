package com.example.radek.lab2;


public class MarkModel {
    private String name;
    private int mark;


    public MarkModel(String name){
        this.name = name;
    }
    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Ocena ";
    }


}
