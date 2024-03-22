package com.example.doan.admin.model;

public class CategoryModel {
    private int CategoryID;
    private String CategoryName;
    private String CategoryDescription;

    public CategoryModel(int categoryID, String categoryName, String categoryDescription) {
        CategoryID = categoryID;
        CategoryName = categoryName;
        CategoryDescription = categoryDescription;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String categoryName) {
        CategoryName = categoryName;
    }

    public String getCategoryDescription() {
        return CategoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        CategoryDescription = categoryDescription;
    }
}
