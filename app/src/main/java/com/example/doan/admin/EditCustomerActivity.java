package com.example.doan.admin;

import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.doan.R;
import com.example.doan.admin.model.CustomerModel;

import java.security.Principal;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class EditCustomerActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    int customerId;
    LinearLayout linearlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        customerId = getIntent().getIntExtra("customerID", -1);

        databaseHelper = new DatabaseHelper(this);

        linearlayout = findViewById(R.id.edit_layout);

        List<CustomerModel> customer = databaseHelper.getCustomer(null,-1,customerId);
        try {
            String text = String.valueOf(customer.get(0).getCustomerName());
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            String text = String.valueOf(e);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }

        if (customer != null) {

            ArrayList<String> fields = databaseHelper.getCustomerColumns();



            for (String field : fields) {

                TextView textView = new TextView(this);
                textView.setText(field);
                linearlayout.addView(textView);


                EditText editText = new EditText(this);
                editText.setText(getValueForField(customer.get(0), field));
                editText.setId(View.generateViewId()); // Generate unique ID for EditText
                linearlayout.addView(editText);
            }

            LinearLayout linearLayout = new LinearLayout(this   );
            linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            linearLayout.setGravity(Gravity.CENTER);

            Button btnCancel = new Button(this);
            btnCancel.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            btnCancel.setId(View.generateViewId());
            btnCancel.setText("Cancel");
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) btnCancel.getLayoutParams();
            params.setMarginEnd((int) TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 50, this.getResources().getDisplayMetrics())
            );
            btnCancel.setLayoutParams(params);
            linearLayout.addView(btnCancel);

            Button btnSave = new Button(this);
            btnSave.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            ));
            btnSave.setId(View.generateViewId());
            btnSave.setText("Save");
            linearLayout.addView(btnSave);
            linearlayout.addView(linearLayout);


            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    List<String> editTextValues = getEditTextValues();
                    boolean success = databaseHelper.updateCustomer(customerId, editTextValues.get(1),
                                                                                editTextValues.get(2),
                                                                                editTextValues.get(3),
                                                                                editTextValues.get(4),
                                                                                editTextValues.get(5));
                    if (success)
                        Toast.makeText(EditCustomerActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EditCustomerActivity.this, editTextValues.get(1), Toast.LENGTH_SHORT).show();
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

    }

    private List<String> getEditTextValues(){
        List<String> EditTextValues = new ArrayList<>();
        for (int i = 0; i < linearlayout.getChildCount(); i++) {
            View childView = linearlayout.getChildAt(i);
            if (childView instanceof EditText) {
                EditText editText = (EditText) childView;
                String editTextValue = editText.getText().toString();
                EditTextValues.add(editTextValue);
            }
        }
        return EditTextValues;
    }
    private String getValueForField(CustomerModel customer, String field) {
        //
        //    COLUMN_CUSTOMER_ID = "CustomerID",
        //    COLUMN_CUSTOMER_NAME = "CustomerName",
        //    COLUMN_NUMBER = "Number", COLUMN_AVATAR = "Avatar",
        //    COLUMN_BIRTH_DATE = "BirthDate",
        //    COLUMN_ADDRESS = "Address",
        //    COLUMN_CUSTOMER_USER_ID = "UserID";
        switch (field) {
            case "ID":
                return String.valueOf(customer.getCustomerID());
            case "CustomerName":
                return customer.getNumber();
            case "Number":
                return customer.getNumber();
            case "Avatar":
                return customer.getAvatar();
            case "BirthDate":
                return customer.getBirthDate();
            case "Address":
                return customer.getAddress();
            default:
                return "";
        }
    }


}

