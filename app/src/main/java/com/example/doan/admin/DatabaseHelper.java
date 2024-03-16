package com.example.doan.admin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import com.example.doan.admin.model.CustomerModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper{
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_NAME = "NAME";
    public static final String COLUMN_PASSWORD = "PASSWORD";
    private Context context;

    public DatabaseHelper(@Nullable Context context){
        super(context, "customer.db", null, 1);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +  COLUMN_NAME + " TEXT, " + COLUMN_PASSWORD + " TEXT)";

        db.execSQL(createTableStatement);

        ContentValues contentValues = new ContentValues();
        contentValues.put("ID","1");
        contentValues.put(COLUMN_NAME, "Test");
        contentValues.put(COLUMN_PASSWORD, "password123");
        db.insert(CUSTOMER_TABLE, null, contentValues);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_NAME,customerModel.getCusName());
        cv.put(COLUMN_PASSWORD,customerModel.getPassword());

        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else
            return true;
    }

    public List<CustomerModel> getEveryone() {
        List<CustomerModel> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerPassword = cursor.getString(2);

                CustomerModel newCustomer = new CustomerModel(customerID,customerName,customerPassword);
                returnList.add(newCustomer);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return returnList;
    }

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


    public boolean updateCustomer(int ID, String newName, String newPassword) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, newName);
        values.put(COLUMN_PASSWORD, newPassword);

        String whereClause = ID + " = ?";
        String[] whereArgs = { String.valueOf(ID) };

        int rowsUpdated = db.update(CUSTOMER_TABLE, values, whereClause, whereArgs);

        // Close the database
        db.close();

        // Return true if at least one row was updated, false otherwise
        return rowsUpdated > 0;
    }


    public ArrayList<String> getCustomerColumns() {
        return new ArrayList<>(Arrays.asList("ID", "Name", "Password"));
    }
}
