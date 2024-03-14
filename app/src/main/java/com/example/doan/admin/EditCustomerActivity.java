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

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class EditCustomerActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    int customerId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        customerId = getIntent().getIntExtra("customerId", -1);

        databaseHelper = new DatabaseHelper(this);


        CustomerModel customer = databaseHelper.getCustomer(customerId);
        try {
            String text = String.valueOf(customer.getCusName());
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e){
            String text = String.valueOf(e);
            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
        }

        if (customer != null) {

            ArrayList<String> fields = databaseHelper.getCustomerColumns();


            LinearLayout layout = findViewById(R.id.edit_layout);


            for (String field : fields) {

                TextView textView = new TextView(this);
                textView.setText(field);
                layout.addView(textView);


                EditText editText = new EditText(this);
                editText.setText(getValueForField(customer, field));
                editText.setId(View.generateViewId()); // Generate unique ID for EditText
                layout.addView(editText);
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
            layout.addView(linearLayout);

            List<String> editTextValues = new ArrayList<>();

            for (int i = 0; i < layout.getChildCount(); i++) {
                View childView = layout.getChildAt(i);
                if (childView instanceof EditText) {
                    EditText editText = (EditText) childView;
                    String editTextValue = editText.getText().toString();
                    editTextValues.add(editTextValue);
                }
            }

            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    for (int i = 0; i < fields.size(); i++) {
//                        if (editTextValues.get(i) != getValueForField(customer,fields.get(i).toString()))
//                        {
//                        }
//                    }
                    boolean success = databaseHelper.updateCustomer(customerId,editTextValues.get(1),editTextValues.get(2));
                    if (success)
                        Toast.makeText(EditCustomerActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EditCustomerActivity.this, "Fail!", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

    private String getValueForField(CustomerModel customer, String field) {
        // This method should retrieve the value for the specified field from the customer object
        // For simplicity, let's assume field names match with getter methods of CustomerModel
        switch (field) {
            case "ID":
                return String.valueOf(customer.getId());
            case "Name":
                return customer.getCusName();
            case "Password":
                return customer.getPassword();
            default:
                return "";
        }
    }


}

