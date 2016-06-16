package kr.ac.kumoh.ce.s20100884.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailPet extends AppCompatActivity {
    TextView dType, dSize, dPrice, dTerm, dAddress, dEmail, dNumber, dMemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pet);

        Intent intent = getIntent();
        dType = (TextView)findViewById(R.id.detailPetType);
        dSize = (TextView)findViewById(R.id.detailPetSize);
        dPrice = (TextView)findViewById(R.id.detailPetPrice);
        dTerm = (TextView)findViewById(R.id.detailPetTerm);
        dAddress = (TextView)findViewById(R.id.detailPetAddress);
        dEmail = (TextView)findViewById(R.id.detailPetEmail);
        dNumber = (TextView)findViewById(R.id.detailPetNumber);
        dMemo = (TextView)findViewById(R.id.detailPetMemo);

        dType.setText("종류 : " + intent.getStringExtra("type"));
        dSize.setText("크기 : " + intent.getStringExtra("size"));
        dPrice.setText("가격 : " + intent.getStringExtra("price"));
        dTerm.setText("기간 : " + intent.getStringExtra("term"));
        dAddress.setText("사는 곳 : " + intent.getStringExtra("address"));
        dEmail.setText("E-mail : " + intent.getStringExtra("email"));
        dNumber.setText("Phone : " + intent.getStringExtra("number"));
        dMemo.setText("하고싶은말 : " + intent.getStringExtra("memo"));
    }
}
