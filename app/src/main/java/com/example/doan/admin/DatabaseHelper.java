package com.example.doan.admin;

import android.content.ContentValues;
import android.content.Context;
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


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper{

    //User
    public static final String USER_TABLE = "USER_TABLE", COLUMN_USER_ID = "UserID", COLUMN_USER_NAME = "UserName", COLUMN_PASSWORD = "Password", COLUMN_ROLE = "Role", COLUMN_STATUS = "Status";
    //Customer
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE", COLUMN_CUSTOMER_ID = "CustomerID", COLUMN_CUSTOMER_NAME = "CustomerName", COLUMN_NUMBER = "Number", COLUMN_AVATAR = "Avatar", COLUMN_BIRTH_DATE = "BirthDate", COLUMN_ADDRESS = "Address", COLUMN_CUSTOMER_USER_ID = "UserID";
    public static final String FOREIGN_KEY_USER_ID = " FOREIGN KEY(" + COLUMN_CUSTOMER_USER_ID + ") REFERENCES " + USER_TABLE + "(" + COLUMN_CUSTOMER_USER_ID + ")";


    private Context context;

    public DatabaseHelper(@Nullable Context context){
        super(context, "customer.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // User Table
        String createTableStatement = "CREATE TABLE " + USER_TABLE + " (" +
                COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USER_NAME + " TEXT, " +
                COLUMN_PASSWORD + " TEXT, " +
                COLUMN_ROLE + " TEXT, " +
                COLUMN_STATUS + " TEXT)";
        // Customer Table
        createTableStatement += "\nCREATE TABLE " + CUSTOMER_TABLE + " (" +
                COLUMN_CUSTOMER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CUSTOMER_NAME + " TEXT, " +
                COLUMN_NUMBER + " TEXT, " +
                COLUMN_AVATAR + " TEXT, " +
                COLUMN_BIRTH_DATE + " TEXT, " +
                COLUMN_ADDRESS + " TEXT, " +
                COLUMN_CUSTOMER_USER_ID + " INTEGER, " +
                "FOREIGN KEY(" + COLUMN_CUSTOMER_USER_ID + ") REFERENCES " + USER_TABLE + "(" + COLUMN_USER_ID + "))";

        db.execSQL(createTableStatement);
        dumpdata(db);
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

    public List<UserModel> getUser(String search, int filter) {
        List<UserModel> returnList = new ArrayList<>();
        String queryString;
        if (search == null)
            queryString = "SELECT * FROM " + USER_TABLE;
        else
            queryString = "SELECT " + search + " FROM " + USER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int ID        = cursor.getInt(0);
                String NAME   = cursor.getString(1);
                String PASS   = cursor.getString(2);
                String ROLE   = cursor.getString(3);
                String STATUS = cursor.getString(4) ;

                UserModel newUser = new UserModel(ID,NAME,PASS,ROLE,STATUS);
                returnList.add(newUser);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
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

    /*
    public boolean updateCustomer(int ID, String newName, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_PASSWORD, newPassword);

        String whereClause = "ID = " + ID;

        int rowsUpdated = db.update(CUSTOMER_TABLE, values, whereClause, null);

        db.close();

        String bread = String.valueOf(rowsUpdated);
        Toast.makeText(context, bread, Toast.LENGTH_SHORT).show();

        return rowsUpdated > 0;
    }
*/


    public ArrayList<String> getUserColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_PASSWORD,COLUMN_ROLE));
    }
    public ArrayList<String> getCustomerColumns() {
        return new ArrayList<>(Arrays.asList(COLUMN_USER_ID, COLUMN_USER_NAME, COLUMN_PASSWORD,COLUMN_ROLE));
    }
}
