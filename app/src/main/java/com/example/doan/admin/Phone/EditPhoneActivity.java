package com.example.doan.admin.Phone;

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

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.BrandModel;
import com.example.doan.admin.model.PhoneModel;
import com.example.doan.admin.model.SpecificationModel;

import java.util.ArrayList;
import java.util.List;

public class EditPhoneActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    int phoneID;
    LinearLayout linearlayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        phoneID = getIntent().getIntExtra("phoneID", -1);

        databaseHelper = new DatabaseHelper(this);

        linearlayout = findViewById(R.id.edit_layout);

        List<PhoneModel> phone = databaseHelper.getPhones(null,-1, phoneID);
        List<SpecificationModel> specs = databaseHelper.getSpecification(-1, phoneID);

//        try {
//            String text = String.valueOf(phone.get(0).getPhoneName());
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//        }
//        catch (Exception e){
//            String text = String.valueOf(e);
//            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
//        }

        if (phone != null) {

            ArrayList<String> fields = databaseHelper.getPhoneColumns();
            for (String field : fields) {

                TextView textView = new TextView(this);
                textView.setText(field);
                linearlayout.addView(textView);


                EditText editText = new EditText(this);
                editText.setText(getValueForField(phone.get(0),specs.get(0), field));
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
                    boolean success = databaseHelper.updatePhone(phoneID,
                            editTextValues.get(1),
                            editTextValues.get(2),
                            Integer.parseInt(editTextValues.get(3)),
                            Integer.parseInt(editTextValues.get(4)));

                    databaseHelper.updateSpecification(phoneID,
                            editTextValues.get(5),
                            editTextValues.get(6),
                            editTextValues.get(7),
                            editTextValues.get(8),
                            editTextValues.get(9),
                            editTextValues.get(10),
                            editTextValues.get(11));

                    if (success)
                        Toast.makeText(EditPhoneActivity.this, "Success!", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(EditPhoneActivity.this, editTextValues.get(1), Toast.LENGTH_SHORT).show();
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
    private String getValueForField(PhoneModel phone, SpecificationModel specs, String field) {
//                COLUMN_PHONE_NAME,
//                COLUMN_PHONE_DESCRIPTION,
//                COLUMN_PRICE,
//                COLUMN_DISCOUNT,
//                COLUMN_OS,
//                COLUMN_CHIP,
//                COLUMN_RAM,
//                COLUMN_ROM,
//                COLUMN_BATTERY,
//                COLUMN_SCREEN,
//                COLUMN_SIZE));
        switch (field) {
            case "PhoneID":
                return String.valueOf(phone.getPhoneID());
            case "PhoneName":
                return phone.getPhoneName();
            case "PhoneDescription":
                return phone.getPhoneDescription();
            case "Price":
                return String.valueOf(phone.getPrice());
            case "Discount":
                return String.valueOf(phone.getDiscount());
            case "OS":
                return specs.getOS();
            case "Chip":
                return specs.getChip();
            case "RAM":
                return specs.getRAM();
            case "ROM":
                return specs.getROM();
            case "Battery":
                return specs.getBattery();
            case "Screen":
                return specs.getScreen();
            case "Size":
                return specs.getSize();

            default:
                return "";
        }
    }


}

