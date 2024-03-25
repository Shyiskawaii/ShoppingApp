package com.example.doan.admin.model;

public class SpecificationModel {
    private int SpecificationID;
    private String OS;
    private String Chip;
    private String RAM;
    private String ROM;
    private String Battery;
    private String Screen;
    private String Size;

    public SpecificationModel(int specificationID, String os, String chip, String ram, String rom, String battery, String screen, String size) {
        SpecificationID = specificationID;
        OS = os;
        Chip = chip;
        RAM = ram;
        ROM = rom;
        Battery = battery;
        Screen = screen;
        Size = size;

    }

    public int getSpecificationID() {
        return SpecificationID;
    }

    public void setSpecificationID(int specificationID) {
        SpecificationID = specificationID;
    }

    public String getOS() {
        return OS;
    }

    public void setOS(String OS) {
        this.OS = OS;
    }

    public String getChip() {
        return Chip;
    }

    public void setChip(String chip) {
        Chip = chip;
    }

    public String getRAM() {
        return RAM;
    }

    public void setRAM(String RAM) {
        this.RAM = RAM;
    }

    public String getROM() {
        return ROM;
    }

    public void setROM(String ROM) {
        this.ROM = ROM;
    }

    public String getBattery() {
        return Battery;
    }

    public void setBattery(String battery) {
        Battery = battery;
    }

    public String getScreen() {
        return Screen;
    }

    public void setScreen(String screen) {
        Screen = screen;
    }

    public String getSize() {
        return Size;
    }

    public void setSize(String size) {
        Size = size;
    }


}
