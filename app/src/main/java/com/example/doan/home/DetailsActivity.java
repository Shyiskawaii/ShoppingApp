package com.example.doan.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.doan.R;
import com.example.doan.admin.DatabaseHelper;
import com.example.doan.admin.model.PhoneModel;
import com.example.doan.admin.model.SpecificationModel;

import org.w3c.dom.Text;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    TextView txtName,txtPrice,txtPriceOrg,txtDetail;

    TextView txtROM,txtRAM,txtChip,txtCam,txtOS,txtScreen,txtBattery;

    ImageView imgView;
    Button btnTvgh;

    Toolbar toolbar;
    DatabaseHelper db = new DatabaseHelper(this);

    int phoneID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();
        phoneID = getIntent().getIntExtra("phoneID", -1);

        initView();
        ActionToolBar();
        initData();

//        btnTvgh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Intent intent=new Intent(DetailsActivity.this, CartActivity.class);
//            }
//        });
    }

    private void initData() {
        List<PhoneModel> phone = db.getPhones(null,-1,phoneID);
        txtName.setText(phone.get(0).getPhoneName());
        txtPrice.setText("Giá: " + phone.get(0).getDiscount());
        txtPriceOrg.setText("Giá gốc: " + phone.get(0).getPrice());
        txtDetail.setText(phone.get(0).getPhoneDescription());
        imgView.setImageBitmap(byteArrayToBitmap(phone.get(0).getPhoneImage()));

        List<SpecificationModel> specs = db.getSpecification(-1,phoneID);
        txtROM.setText(specs.get(0).getROM());
        txtRAM.setText(specs.get(0).getRAM());
        txtCam.setText(specs.get(0).getRAM());
        txtOS.setText(specs.get(0).getOS());
        txtScreen.setText(specs.get(0).getScreen());
        txtBattery.setText(specs.get(0).getBattery());
        txtChip.setText(specs.get(0).getChip());
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView(){
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtPriceOrg = findViewById(R.id.txtPriceOrg);
        txtDetail = findViewById(R.id.txtDetail);
        toolbar = findViewById(R.id.toobar);

        txtROM = findViewById(R.id.txtROM);
        txtRAM= findViewById(R.id.txtRAM);
        txtChip= findViewById(R.id.txtChip);
        txtCam= findViewById(R.id.txtCam);
        txtOS= findViewById(R.id.txtOS);
        txtScreen= findViewById(R.id.txtScreen);
        txtBattery= findViewById(R.id.txtBattery);

        imgView = findViewById(R.id.imgChitiet);
    }
    public Bitmap byteArrayToBitmap(byte[] byteArray) {
        if (byteArray != null && byteArray.length > 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        } else {
            return null;
        }
    }
}
