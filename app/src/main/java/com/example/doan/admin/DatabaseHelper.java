package com.example.doan.admin;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.doan.admin.model.CustomerModel;
import com.example.doan.admin.model.UserModel;
import com.example.doan.admin.recycler.CustomerRecycler;


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

    private static final String PREFERENCES_NAME = "user_session";
    private static final String PREFERENCES_ROLE = "user_role";

    private SharedPreferences sharedPreferences;
    private Context context;

    public DatabaseHelper(@Nullable Context context){
        super(context, "customer.db", null, 1);
        this.context = context;
        this.sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
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

        for(int index = 0; index < 5; index++)
        {
            dumpdata(db);
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
            return false;
        } else
            return true;
    }


    /*
    public CustomerModel getCustomer(int ID){
        String queryString = "SELECT * FROM " + CUSTOMER_TABLE + " WHERE ID = ?";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, new String[] { String.valueOf(ID) });

        CustomerModel customer = null;

        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String password = cursor.getString(2);

            customer = new CustomerModel(ID, name, password);
        }

        cursor.close(); // Close the cursor to release resources

        return customer;
    }
*/



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
}
