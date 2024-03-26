package com.example.doan.admin;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.Size;

import com.example.doan.admin.model.BrandModel;
import com.example.doan.admin.model.CategoryModel;
import com.example.doan.admin.model.CustomerModel;
import com.example.doan.admin.model.PhoneModel;
import com.example.doan.admin.model.SpecificationModel;
import com.example.doan.admin.model.UserModel;


import java.sql.Blob;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;


public class DatabaseHelper extends SQLiteOpenHelper{

    //User
    public static final String USER_TABLE = "USER_TABLE", COLUMN_USER_ID = "UserID", COLUMN_USER_NAME = "UserName", COLUMN_PASSWORD = "Password", COLUMN_ROLE = "Role", COLUMN_STATUS = "Status";
    public static final String CONFIRM_PASSWORD = "Confirm Password";
    //Customer
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE", COLUMN_CUSTOMER_ID = "CustomerID", COLUMN_CUSTOMER_NAME = "CustomerName", COLUMN_NUMBER = "Number", COLUMN_AVATAR = "Avatar", COLUMN_BIRTH_DATE = "BirthDate", COLUMN_ADDRESS = "Address", COLUMN_CUSTOMER_USER_ID = "UserID";
    public static final String FOREIGN_KEY_USER_ID = " FOREIGN KEY(" + COLUMN_CUSTOMER_USER_ID + ") REFERENCES " + USER_TABLE + "(" + COLUMN_CUSTOMER_USER_ID + ")";

    //Brand
    public static final String BRAND_TABLE = "BRAND_TABLE", COLUMN_BRAND_ID = "BrandID", COLUMN_BRAND_NAME = "BrandName", COLUMN_BRAND_DESCRIPTION = "BrandDescription";
    //Category
    public static final String CATEGORY_TABLE = "CATEGORY_TABLE", COLUMN_CATEGORY_ID = "CategoryID", COLUMN_CATEGORY_NAME = "CategoryName", COLUMN_CATEGORY_DESCRIPTION = "CategoryDescription";
    //Specification
    public static final String SPECIFICATION_TABLE = "SPECIFICATION_MODEL", COLUMN_SPECIFICATION_ID = "SpecificationID", COLUMN_OS = "OS", COLUMN_CHIP = "Chip", COLUMN_RAM = "RAM", COLUMN_ROM = "ROM", COLUMN_BATTERY = "Battery", COLUMN_SCREEN = "Screen", COLUMN_SIZE = "Size";

    //Phone
    public static final String PHONE_TABLE = "PHONE_TABLE", COLUMN_PHONE_ID = "PhoneID", COLUMN_PHONE_NAME = "PhoneName", COLUMN_PHONE_DESCRIPTION = "PhoneDescription", COLUMN_PHONE_IMAGE = "PhoneImage", COLUMN_PRICE = "Price", COLUMN_DISCOUNT = "Discount",COLUMN_VIEWS = "Views", COLUMN_BOUGHT = "Bought", COLUMN_PHONE_CATEGORY_ID = "CategoryID", COLUMN_PHONE_BRAND_ID = "BrandID",COLUMN_PHONE_SPECIFICATION_ID = "SpecificationID";
    public static final String FOREIGN_KEY_BRAND_ID = " FOREIGN KEY(" + COLUMN_PHONE_BRAND_ID + ") REFERENCES " + BRAND_TABLE + "(" + COLUMN_PHONE_BRAND_ID + ")";
    public static final String FOREIGN_KEY_CATEGORY_ID = " FOREIGN KEY(" + COLUMN_PHONE_CATEGORY_ID + ") REFERENCES " + CATEGORY_TABLE + "(" + COLUMN_PHONE_CATEGORY_ID + ")";
    public static final String FOREIGN_KEY_SPECIFICATION_ID = " FOREIGN KEY(" + COLUMN_PHONE_SPECIFICATION_ID + ") REFERENCES " + SPECIFICATION_TABLE + "(" + COLUMN_PHONE_SPECIFICATION_ID + ")";



    private static final String PREFERENCES_NAME = "user_session";
    private static final String PREFERENCES_ROLE = "user_role";

    private SharedPreferences sharedPreferences;
    private Context context;

    public DatabaseHelper(@Nullable Context context){
        super(context, "customer.db", null, 1);
        this.context = context;
        //this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        String createUserTableStatement = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_STATUS + " TEXT)";
        db.execSQL(createUserTableStatement);

        String createCustomerTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (" +
                COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_AVATAR + " TEXT, " +
                COLUMN_BIRTH_DATE + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_CUSTOMER_USER_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_CUSTOMER_USER_ID + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USER_ID + "))";
        db.execSQL(createCustomerTableStatement);

        String createBrandTableStatement = "CREATE TABLE " + BRAND_TABLE + " (" +
                COLUMN_BRAND_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BRAND_NAME + " TEXT, " +
                COLUMN_BRAND_DESCRIPTION + " TEXT)";
        db.execSQL(createBrandTableStatement);

        String createCategoryTableStatement = "CREATE TABLE " + CATEGORY_TABLE + " (" +
                COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CATEGORY_NAME + " TEXT, " +
                COLUMN_CATEGORY_DESCRIPTION + " TEXT)";
        db.execSQL(createCategoryTableStatement);

        String createSpecificationTableStatement = "CREATE TABLE " + SPECIFICATION_TABLE + " (" +
                COLUMN_SPECIFICATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_OS + " TEXT, " +
                COLUMN_CHIP + " TEXT, " +
                COLUMN_RAM + " TEXT, " +
                COLUMN_ROM + " TEXT, " +
                COLUMN_BATTERY + " TEXT, " +
                COLUMN_SCREEN + " TEXT, " +
                COLUMN_SIZE + " TEXT)";
        db.execSQL(createSpecificationTableStatement);

        String createPhoneTableStatement = "CREATE TABLE " + PHONE_TABLE + " (" +
                COLUMN_PHONE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PHONE_NAME + " TEXT, " +
                COLUMN_PHONE_DESCRIPTION + " TEXT, " +
                COLUMN_PHONE_IMAGE + " BLOB, " +
                COLUMN_PRICE + " INTEGER, " +
                COLUMN_DISCOUNT + " INTEGER, " +
                COLUMN_VIEWS + " INTEGER, " +
                COLUMN_BOUGHT + " INTEGER, " +
                COLUMN_PHONE_CATEGORY_ID + " INTEGER, " +
                COLUMN_PHONE_BRAND_ID + " INTEGER, " +
                COLUMN_PHONE_SPECIFICATION_ID + " INTEGER, " +
                FOREIGN_KEY_BRAND_ID + ", " +
                FOREIGN_KEY_CATEGORY_ID + ", " +
                FOREIGN_KEY_SPECIFICATION_ID + ")";
        db.execSQL(createPhoneTableStatement);


        for(int index = 0; index < 5; index++)
        {
            dumpdata(db);
        }


        // Dummy data for BrandModel
        for (int i = 0; i < 5; i++) {
            ContentValues brandValues = new ContentValues();
            brandValues.put(COLUMN_BRAND_NAME, "Brand " + (i + 1));
            brandValues.put(COLUMN_BRAND_DESCRIPTION, "Description " + (i + 1));
            long brandRowId = db.insert(BRAND_TABLE, null, brandValues);
        }

        // Dummy data for CategoryModel
        for (int i = 0; i < 5; i++) {
            ContentValues categoryValues = new ContentValues();
            categoryValues.put(COLUMN_CATEGORY_NAME, "Category " + (i + 1));
            categoryValues.put(COLUMN_CATEGORY_DESCRIPTION, "Description " + (i + 1));
            long categoryRowId = db.insert(CATEGORY_TABLE, null, categoryValues);
        }



    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void dumpdata(SQLiteDatabase db){
        ContentValues userValues = new ContentValues();
        userValues.put(COLUMN_USER_NAME, "Do Huy");
        userValues.put(COLUMN_PASSWORD, "password123");
        userValues.put(COLUMN_ROLE, "customer");
        userValues.put(COLUMN_STATUS, "online");

        long userRowId = db.insert(USER_TABLE, null, userValues);

        ContentValues customerValues = new ContentValues();
        customerValues.put(COLUMN_CUSTOMER_NAME, "Do Huy");
        customerValues.put(COLUMN_NUMBER, "1234567890");
        customerValues.put(COLUMN_AVATAR, "avatar_url");
        customerValues.put(COLUMN_BIRTH_DATE, "1990-01-01");
        customerValues.put(COLUMN_ADDRESS, "123 Dong Nai");
        customerValues.put(COLUMN_CUSTOMER_USER_ID, userRowId);

        long customerRowId = db.insert(CUSTOMER_TABLE, null, customerValues);

    }

    public String readPreference(String key, String defaultValue) {
        return sharedPreferences.getString(key, defaultValue);
    }

    public void writePreference(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    //////////////////////////                USER               ////////////////////////////
    public boolean userRegister(UserModel userModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER_NAME,userModel.getUserName());
        cv.put(COLUMN_PASSWORD,userModel.getPassword());
        cv.put(COLUMN_ROLE, "customer");
        cv.put(COLUMN_STATUS, "online");

        long insert = db.insert(USER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else
            return true;
    }

    public List<UserModel> getUser(String search, int filter,int userID) {
        List<UserModel> returnList = new ArrayList<>();
        String queryString;
        if (search != null)
            queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_NAME + " LIKE '%" + search + "%'";
        else if (userID != -1)
            queryString = "SELECT * FROM " + USER_TABLE + " WHERE " + COLUMN_USER_ID + " = " + userID;
        else
            queryString = "SELECT * FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ID        = cursor.getInt(0);
                String NAME   = cursor.getString(1);
                String PASS   = cursor.getString(2);
                String ROLE   = cursor.getString(3);
                String STATUS = cursor.getString(4) ;

                UserModel newUser = new UserModel(ID,NAME,PASS,ROLE,STATUS);
                returnList.add(newUser);
            } while (cursor.moveToNext());
            cursor.close();
        }
        else {
            db.close();
            return null;
        }
        db.close();

        return returnList;
    }


    public boolean addUser(String newName, String newPassword, String confirmPassword){
        String name_regex = "^[a-zA-Z][a-zA-Z0-9_-]{3,15}$";
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        if (!newName.matches(name_regex)) {
            Toast.makeText(context, "Invalid username. Username must start with a letter and be 4-16 characters long, consisting of letters, numbers, underscores, or hyphens.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(context, "Confirm password must match Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPassword.matches(password_regex)){
            Toast.makeText(context, "Invalid password. Password must be 8-20 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(context, "Create Success", Toast.LENGTH_SHORT).show();

        UserModel newUser = new UserModel(-1,newName,newPassword,"customer","online");
        userRegister(newUser);

        return true;
    }



    public boolean updateUser(int ID, String newName, String newPassword, String newRole) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, newName);
        values.put(COLUMN_PASSWORD, newPassword);
        values.put(COLUMN_ROLE, newRole);

        String whereClause = COLUMN_USER_ID + " = " + ID;

        int rowsUpdated = db.update(USER_TABLE, values, whereClause, null);

        db.close();

//        String bread = String.valueOf(rowsUpdated);
//        Toast.makeText(context, bread, Toast.LENGTH_SHORT).show();

        return rowsUpdated > 0;
    }

    public boolean Register(String newName,String newPassword, String confirmPassword, String Number , String Address){
        String name_regex = "^[a-zA-Z][a-zA-Z0-9_-]{3,15}$";
        String password_regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";

        if (!newName.matches(name_regex)) {
            Toast.makeText(context, "Invalid username. Username must start with a letter and be 4-16 characters long, consisting of letters, numbers, underscores, or hyphens.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPassword.equals(confirmPassword)) {
            Toast.makeText(context, "Confirm password must match Password", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!newPassword.matches(password_regex)){
            Toast.makeText(context, "Invalid password. Password must be 8-20 characters long and contain at least one uppercase letter, one lowercase letter, one digit, and one special character.", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(context, "Create Success", Toast.LENGTH_SHORT).show();

        UserModel newUser = new UserModel(-1,newName,newPassword,"customer","online");
        userRegister(newUser);
        List<UserModel> currentUser = getUser(newName,-1,-1);

        CustomerModel newCustomer = new CustomerModel(currentUser.get(0).getUserID(),"Khách Hàng Mới",Number,"null","01/01/1999",Address,currentUser.get(0).getUserID());
        addCustomer(newCustomer);

        return true;

    }

    public Boolean Login(String userName , String password) {
        List<UserModel> CurrentUser = getUser(userName,-1,-1);
        if (CurrentUser == null){
            Toast.makeText(context, "Invalid Username!", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (!Objects.equals(CurrentUser.get(0).getPassword(), password)) {
            Toast.makeText(context, "Invalid Password!", Toast.LENGTH_SHORT).show();
            return false;
        }

        updateUserStatus(CurrentUser.get(0).getUserID(), "Online");

        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("logged_in_user", userName);

        if (Objects.equals(CurrentUser.get(0).getRole(), "admin")) {
            editor.putString(PREFERENCES_ROLE, "admin");
        } else {
            editor.putString(PREFERENCES_ROLE, "customer");
        }


        editor.apply();

        return true;
    }

    public int updateUserStatus(int ID,String status){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_STATUS, status);

        String whereClause = COLUMN_USER_ID + " = " + ID;

        int rowsUpdated = db.update(USER_TABLE, values, whereClause, null);

        db.close();

        return rowsUpdated;
    }

    public void Logout(){

    }
    //////////////////////////                CUSTOMER               ////////////////////////////
    public List<CustomerModel> getCustomer(String search, int filter,int customerID) {
        List<CustomerModel> returnList = new ArrayList<>();
        String queryString;
        if (search != null)
            queryString = "SELECT * FROM " + CUSTOMER_TABLE + COLUMN_CUSTOMER_NAME + " LIKE '%" + search + "%'";
        else if (customerID != -1)
            queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE " + COLUMN_CUSTOMER_ID + " = " + customerID;
        else
            queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ID        = cursor.getInt(0);
                String NAME   = cursor.getString(1);
                String NUMBER   = cursor.getString(2);
                String AVATAR   = cursor.getString(3);
                String BIRTHDATE = cursor.getString(4);
                String ADDRESS = cursor.getString(5);
                int USERID = cursor.getInt(6);

                CustomerModel newUser = new CustomerModel(ID,NAME,NUMBER,AVATAR,BIRTHDATE,ADDRESS,USERID);
                returnList.add(newUser);
            } while (cursor.moveToNext());
            cursor.close();
        }
        else {
            db.close();
            return null;
        }
        db.close();

        return returnList;
    }
    public boolean updateCustomer(int ID, String newName, String Number, String Avatar, String BirthDate, String Address) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_NAME, newName);
        values.put(COLUMN_PASSWORD, Number);
        values.put(COLUMN_ROLE, Avatar);
        values.put(COLUMN_ROLE, BirthDate);
        values.put(COLUMN_ROLE, Address);

        String whereClause = COLUMN_CUSTOMER_ID + " = " + ID;

        int rowsUpdated = db.update(CUSTOMER_TABLE, values, whereClause, null);

        db.close();

//        String bread = String.valueOf(rowsUpdated);
//        Toast.makeText(context, bread, Toast.LENGTH_SHORT).show();

        return rowsUpdated > 0;
    }
    public boolean addCustomer(CustomerModel customerModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_ID,customerModel.getCustomerID());
        cv.put(COLUMN_CUSTOMER_NAME,customerModel.getCustomerName());
        cv.put(COLUMN_NUMBER,customerModel.getNumber());
        cv.put(COLUMN_AVATAR, customerModel.getAvatar());
        cv.put(COLUMN_BIRTH_DATE, customerModel.getBirthDate());
        cv.put(COLUMN_ADDRESS, customerModel.getAddress());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }
    //////////////////////////                CATEGORY               ////////////////////////////
    public List<CategoryModel> getCategory(String search, int filter, int categoryID) {
        List<CategoryModel> returnList = new ArrayList<>();
        String queryString;
        if (search != null)
            queryString = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " + COLUMN_CATEGORY_NAME + " LIKE '%" + search + "%'";
        else if (categoryID != -1)
            queryString = "SELECT * FROM " + CATEGORY_TABLE + " WHERE " + COLUMN_CATEGORY_ID + " = " + categoryID;
        else
            queryString = "SELECT * FROM " + CATEGORY_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ID        = cursor.getInt(0);
                String NAME   = cursor.getString(1);
                String DESCRIPTION   = cursor.getString(2);


                CategoryModel newCategory = new CategoryModel(ID,NAME,DESCRIPTION);
                returnList.add(newCategory);
            } while (cursor.moveToNext());
            cursor.close();
        }
        else {
            db.close();
            return null;
        }
        db.close();

        return returnList;
    }
    public boolean addCategory(CategoryModel categoryModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CATEGORY_NAME,categoryModel.getCategoryName());
        cv.put(COLUMN_CATEGORY_DESCRIPTION,categoryModel.getCategoryDescription());

        long insert = db.insert(CATEGORY_TABLE, null, cv);
        if (insert == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }

    public boolean updateCategory(int ID, String newName, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_CATEGORY_NAME, newName);
        values.put(COLUMN_CATEGORY_DESCRIPTION, description);


        String whereClause = COLUMN_CATEGORY_ID + " = " + ID;

        int rowsUpdated = db.update(CATEGORY_TABLE, values, whereClause, null);

        db.close();

//        String bread = String.valueOf(rowsUpdated);
//        Toast.makeText(context, bread, Toast.LENGTH_SHORT).show();

        return rowsUpdated > 0;
    }

    //////////////////////////                BRAND               ////////////////////////////


    public boolean addBrand(BrandModel brandModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_BRAND_NAME,brandModel.getBrandName());
        cv.put(COLUMN_BRAND_DESCRIPTION,brandModel.getBrandDescription());

        long insert = db.insert(BRAND_TABLE, null, cv);
        if (insert == -1) {
            db.close();
            return false;
        } else {
            db.close();
            return true;
        }
    }
    public List<BrandModel> getBrand(String search, int filter, int brandID) {
        List<BrandModel> returnList = new ArrayList<>();
        String queryString;
        if (search != null)
            queryString = "SELECT * FROM " + BRAND_TABLE + " WHERE " + COLUMN_BRAND_NAME + " LIKE '%" + search + "%'";
        else if (brandID != -1)
            queryString = "SELECT * FROM " + BRAND_TABLE + " WHERE " + COLUMN_BRAND_ID + " = " + brandID;
        else
            queryString = "SELECT * FROM " + BRAND_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                int ID        = cursor.getInt(0);
                String NAME   = cursor.getString(1);
                String DESCRIPTION   = cursor.getString(2);

                BrandModel newBrand = new BrandModel(ID,NAME,DESCRIPTION);
                returnList.add(newBrand);
            } while (cursor.moveToNext());
            cursor.close();
        }
        else {
            db.close();
            return null;
        }
        db.close();

        return returnList;
    }
    public boolean updateBrand(int ID, String newName, String description) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_BRAND_NAME, newName);
        values.put(COLUMN_BRAND_DESCRIPTION, description);


        String whereClause = COLUMN_BRAND_ID + " = " + ID;

        int rowsUpdated = db.update(BRAND_TABLE, values, whereClause, null);

        db.close();

//        String bread = String.valueOf(rowsUpdated);
//        Toast.makeText(context, bread, Toast.LENGTH_SHORT).show();

        return rowsUpdated > 0;
    }
//////////////////////////////////     SPECIFICATION     ////////////////////////////////////
public List<SpecificationModel> getSpecification(int filter, int specID) {
    List<SpecificationModel> returnList = new ArrayList<>();
    String queryString;
    if (specID != -1)
        queryString = "SELECT * FROM " + SPECIFICATION_TABLE + " WHERE " + COLUMN_SPECIFICATION_ID + " = " + specID;
    else
        queryString = "SELECT * FROM " + SPECIFICATION_TABLE;

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery(queryString, null);

    if (cursor != null && cursor.moveToFirst()) {
        do {
            int ID        = cursor.getInt(0);
            String OS   = cursor.getString(1);
            String Chip   = cursor.getString(2);
            String RAM   = cursor.getString(3);
            String ROM   = cursor.getString(4);
            String Battery = cursor.getString(5);
            String Screen   = cursor.getString(6);
            String Size   = cursor.getString(7);



            SpecificationModel newSpec = new SpecificationModel(ID,OS,Chip,RAM,ROM,Battery,Screen,Size);
            returnList.add(newSpec);
        } while (cursor.moveToNext());
        cursor.close();
    }
    else {
        db.close();
        return null;
    }
    db.close();

    return returnList;
}
    public boolean addSpecification(SpecificationModel specificationModel){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_OS,specificationModel.getOS());
        cv.put(COLUMN_CHIP,specificationModel.getChip());
        cv.put(COLUMN_RAM,specificationModel.getRAM());
        cv.put(COLUMN_ROM,specificationModel.getROM());
        cv.put(COLUMN_BATTERY,specificationModel.getBattery());
        cv.put(COLUMN_SCREEN,specificationModel.getScreen());
        cv.put(COLUMN_SIZE,specificationModel.getSize());

        long insert = db.insert(SPECIFICATION_TABLE, null, cv);
        if (insert == -1) {
            db.close();
            return false;
        } else
            db.close();
        return true;
    }

    public boolean updateSpecification(int ID, String os, String chip, String ram, String rom, String battery, String screen, String size) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_OS, os);
        values.put(COLUMN_CHIP, chip);
        values.put(COLUMN_RAM, ram);
        values.put(COLUMN_ROM, rom);
        values.put(COLUMN_BATTERY, battery);
        values.put(COLUMN_SCREEN, screen);
        values.put(COLUMN_SIZE, size);

        String whereClause = COLUMN_SPECIFICATION_ID + " = ?";
        String[] whereArgs = {String.valueOf(ID)};

        int rowsUpdated = db.update(SPECIFICATION_TABLE, values, whereClause, whereArgs);

        db.close();

        return rowsUpdated > 0;
    }

//////////////////////////////////     PHONE     ////////////////////////////////////////////
public List<PhoneModel> getPhones(String search ,int filter, int phoneID) {
    List<PhoneModel> returnList = new ArrayList<>();
    String queryString;
    if (search != null)
        queryString = "SELECT * FROM " + PHONE_TABLE + " WHERE " + COLUMN_PHONE_NAME + " LIKE '%" + search + "%'";
    else if (phoneID != -1)
        queryString = "SELECT * FROM " + PHONE_TABLE + " WHERE " + COLUMN_PHONE_ID + " = " + phoneID;
    else
        queryString = "SELECT * FROM " + PHONE_TABLE;

    SQLiteDatabase db = this.getReadableDatabase();

    Cursor cursor = db.rawQuery(queryString, null);

    if (cursor != null && cursor.moveToFirst()) {
        do {
            int ID = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            byte[] image = cursor.getBlob(3);
            int price = cursor.getInt(4);
            int discount = cursor.getInt(5);
            int views = cursor.getInt(6);
            int bought = cursor.getInt(7);
            int categoryID = cursor.getInt(8);
            int brandID = cursor.getInt(9);
            int specificationID = cursor.getInt(10);

            PhoneModel newPhone = new PhoneModel(ID, name, description, image, price, discount,views, bought, categoryID, brandID, specificationID);
            returnList.add(newPhone);
        } while (cursor.moveToNext());
        cursor.close();
    } else {
        if (cursor != null) cursor.close();
        db.close();
        return null;
    }
    db.close();
    return returnList;
}
    public boolean addPhone(PhoneModel phoneModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_PHONE_NAME, phoneModel.getPhoneName());
        cv.put(COLUMN_PHONE_DESCRIPTION, phoneModel.getPhoneDescription());
        cv.put(COLUMN_PHONE_IMAGE, phoneModel.getPhoneImage());
        cv.put(COLUMN_PRICE, phoneModel.getPrice());
        cv.put(COLUMN_DISCOUNT, phoneModel.getDiscount());
        cv.put(COLUMN_VIEWS, phoneModel.getViews());
        cv.put(COLUMN_BOUGHT, phoneModel.getBought());
        cv.put(COLUMN_PHONE_CATEGORY_ID, phoneModel.getCategoryID());
        cv.put(COLUMN_PHONE_BRAND_ID, phoneModel.getBrandID());
        cv.put(COLUMN_PHONE_SPECIFICATION_ID, phoneModel.getSpecificationID());

        long insert = db.insert(PHONE_TABLE, null, cv);
        db.close();

        return insert != -1;
    }

    public boolean updatePhone(int ID, String name, String description,int price,int discount) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PHONE_NAME, name);
        values.put(COLUMN_PHONE_DESCRIPTION, description);
        values.put(COLUMN_PRICE,price);
        values.put(COLUMN_DISCOUNT,discount);


        String whereClause = COLUMN_PHONE_ID + " = ?";
        String[] whereArgs = {String.valueOf(ID)};

        int rowsUpdated = db.update(PHONE_TABLE, values, whereClause, whereArgs);
        db.close();

        return rowsUpdated > 0;
    }



    public ArrayList<String> getUserColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_PASSWORD,COLUMN_ROLE));
    }
    public ArrayList<String> getNewUserColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_USER_NAME, COLUMN_PASSWORD, CONFIRM_PASSWORD));
    }


    public ArrayList<String> getCustomerColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_CUSTOMER_ID, COLUMN_CUSTOMER_NAME, COLUMN_NUMBER,COLUMN_AVATAR,COLUMN_BIRTH_DATE,COLUMN_ADDRESS));
    }
    public ArrayList<String> getNewCustomerColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_CUSTOMER_ID, COLUMN_CUSTOMER_NAME, COLUMN_NUMBER,COLUMN_AVATAR,COLUMN_BIRTH_DATE,COLUMN_ADDRESS));
    }

    public ArrayList<String> getCategoryColumns(){
        return new ArrayList<>(Arrays.asList(COLUMN_CATEGORY_ID,COLUMN_CATEGORY_NAME,COLUMN_CATEGORY_DESCRIPTION));
    }
    public ArrayList<String> getNewCategoryColumns(){
        return new ArrayList<>(Arrays.asList(COLUMN_CATEGORY_NAME,COLUMN_CATEGORY_DESCRIPTION));
    }

    public ArrayList<String> getBrandColumns(){
        return new ArrayList<>(Arrays.asList(COLUMN_BRAND_ID,COLUMN_BRAND_NAME,COLUMN_BRAND_DESCRIPTION));
    }
    public ArrayList<String> getNewBrandColumns(){
        return new ArrayList<>(Arrays.asList(COLUMN_BRAND_NAME,COLUMN_BRAND_DESCRIPTION));
    }

    public ArrayList<String> getPhoneColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_PHONE_ID,
                COLUMN_PHONE_NAME,
                COLUMN_PHONE_DESCRIPTION,
                COLUMN_PRICE,
                COLUMN_DISCOUNT,
                COLUMN_OS,
                COLUMN_CHIP,
                COLUMN_RAM,
                COLUMN_ROM,
                COLUMN_BATTERY,
                COLUMN_SCREEN,
                COLUMN_SIZE));
    }
    public ArrayList<String> getNewPhoneColumns(){
        return new ArrayList<>(Arrays.asList(COLUMN_PHONE_NAME,
                COLUMN_PHONE_DESCRIPTION,
                COLUMN_PRICE,
                COLUMN_DISCOUNT,
                COLUMN_OS,
                COLUMN_CHIP,
                COLUMN_RAM,
                COLUMN_ROM,
                COLUMN_BATTERY,
                COLUMN_SCREEN,
                COLUMN_SIZE));
    }
}
