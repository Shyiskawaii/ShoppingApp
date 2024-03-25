package com.example.doan.admin.Phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.BrandModel;
import com.example.doan.admin.model.CategoryModel;
import com.example.doan.admin.model.CustomerModel;
import com.example.doan.admin.model.PhoneModel;
import com.example.doan.admin.model.SpecificationModel;
import com.example.doan.admin.model.UserModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.Principal;
import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.List;

public class AddPhoneActivity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    LinearLayout linearlayout;
    private Spinner categorySpinner;
    private Spinner brandSpinner;
    private ActivityResultLauncher<String> imagePickerLauncher;

    private byte[] imageByteArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);

        linearlayout = findViewById(R.id.edit_layout);

        databaseHelper = new DatabaseHelper(this);



        // Add ImageView for image display
        ImageView imageView = new ImageView(this);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        imageView.setId(View.generateViewId());
        linearlayout.addView(imageView);

// Initialize the ActivityResultLauncher
        imagePickerLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri uri) {
                        if (uri != null) {
                            try {
                                // Set the selected image URI to the ImageView
                                imageView.setImageURI(uri);

                                // Convert the image URI to byte array
                                InputStream inputStream = getContentResolver().openInputStream(uri);
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] buffer = new byte[1024];
                                int length;
                                while ((length = inputStream.read(buffer)) != -1) {
                                    byteArrayOutputStream.write(buffer, 0, length);
                                }
                                imageByteArray = byteArrayOutputStream.toByteArray();

                                inputStream.close();
                                byteArrayOutputStream.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });

// Add Button for image selection
        Button btnSelectImage = new Button(this);
        btnSelectImage.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btnSelectImage.setText("Select Image");
        linearlayout.addView(btnSelectImage);

// Set click listener for image selection button
        btnSelectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });




        ArrayList<String> fields = databaseHelper.getNewPhoneColumns();



        for (String field : fields) {

            TextView textView = new TextView(this);
            textView.setText(field);
            linearlayout.addView(textView);

            EditText editText = new EditText(this);
            editText.setId(View.generateViewId());
            linearlayout.addView(editText);
        }

        LinearLayout linearLayout = new LinearLayout(this   );
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setGravity(Gravity.CENTER);
        linearLayout.setOrientation(LinearLayout.VERTICAL);




        // Create a new LinearLayout to hold spinners and buttons
        linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        linearLayout.setOrientation(LinearLayout.VERTICAL); // Set orientation to vertical
        linearLayout.setGravity(Gravity.CENTER);

        // Create layout parameters for spinners
        LinearLayout.LayoutParams spinnerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        // Create category spinner
        categorySpinner = new Spinner(this);
        categorySpinner.setLayoutParams(spinnerParams);
        linearLayout.addView(categorySpinner);

        // Create brand spinner
        brandSpinner = new Spinner(this);
        brandSpinner.setLayoutParams(spinnerParams);
        linearLayout.addView(brandSpinner);


        // Populate the category spinner
        List<CategoryModel> categories = databaseHelper.getCategory(null, -1, -1);
        ArrayAdapter<CategoryModel> categoryAdapter = new ArrayAdapter<CategoryModel>(this,
                android.R.layout.simple_spinner_item, categories) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getCategoryName());
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(getItem(position).getCategoryName());
                return view;
            }
        };
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);

        // Populate the brand spinner
        List<BrandModel> brands = databaseHelper.getBrand(null, -1, -1);
        ArrayAdapter<BrandModel> brandAdapter = new ArrayAdapter<BrandModel>(this,
                android.R.layout.simple_spinner_item, brands) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setText(getItem(position).getBrandName());
                return view;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                TextView view = (TextView) super.getDropDownView(position, convertView, parent);
                view.setText(getItem(position).getBrandName());
                return view;
            }
        };
        brandAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        brandSpinner.setAdapter(brandAdapter);







        // Create a horizontal LinearLayout to hold cancel and save buttons
        LinearLayout buttonLayout = new LinearLayout(this);
        buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        buttonLayout.setOrientation(LinearLayout.HORIZONTAL); // Set orientation to horizontal
        buttonLayout.setGravity(Gravity.CENTER);

        // Add the horizontal layout containing buttons to the parent layout
        linearLayout.addView(buttonLayout);

        // Create cancel button
        Button btnCancel = new Button(this);
        btnCancel.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btnCancel.setId(View.generateViewId());
        btnCancel.setText("Cancel");
        buttonLayout.addView(btnCancel);

        // Create save button
        Button btnSave = new Button(this);
        btnSave.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        btnSave.setId(View.generateViewId());
        btnSave.setText("Save");
        buttonLayout.addView(btnSave);

        // Add the parent layout containing spinners and buttons to the main layout
        linearlayout.addView(linearLayout);


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> editTextValues = getEditTextValues();
                SpecificationModel newSpec = new SpecificationModel(-1,
                                                                    editTextValues.get(4),
                                                                    editTextValues.get(5),
                                                                    editTextValues.get(6),
                                                                    editTextValues.get(7),
                                                                    editTextValues.get(8),
                                                                    editTextValues.get(9),
                                                                    editTextValues.get(10));
                boolean success = databaseHelper.addSpecification(newSpec);
//                if (success)
//                    Toast.makeText(AddPhoneActivity.this, "Got specs!", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(AddPhoneActivity.this, "ded spec", Toast.LENGTH_SHORT).show();


                CategoryModel selectedCategory = (CategoryModel) categorySpinner.getSelectedItem();
                BrandModel selectedBrand = (BrandModel) brandSpinner.getSelectedItem();

                PhoneModel newPhone = new PhoneModel(-1,
                        editTextValues.get(0),
                        editTextValues.get(1),
                        imageByteArray,
                        Integer.parseInt(editTextValues.get(2)),
                        Integer.parseInt(editTextValues.get(3)),
                        0,
                        0,
                        selectedCategory.getCategoryID(),
                        selectedBrand.getBrandID(),
                        1);
                success = databaseHelper.addPhone(newPhone);

                if (success)
                    Toast.makeText(AddPhoneActivity.this, "got phone!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(AddPhoneActivity.this, "Ded phone", Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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
    private void openImagePicker() {
        imagePickerLauncher.launch("image/*");
    }
}

