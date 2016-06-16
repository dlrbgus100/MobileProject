package kr.ac.kumoh.ce.s20100884.database;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailSitter extends AppCompatActivity {
    TextView sPrice, sAddress, sTerm, sPet, sBack, sPetSize, sEmail, sNumber, sMemo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sitter);
        Intent intent = getIntent();

        sPrice = (TextView)findViewById(R.id.detailSitterPrice);
        sAddress = (TextView)findViewById(R.id.detailSitterAddress);
        sTerm = (TextView)findViewById(R.id.detailSitterTerm);
        sPet = (TextView)findViewById(R.id.detailSitterPet);
        sBack = (TextView)findViewById(R.id.detailSitterBack);
        sPetSize = (TextView)findViewById(R.id.detailSitterSize);
        sEmail = (TextView)findViewById(R.id.detailSitterEmail);
        sNumber = (TextView)findViewById(R.id.detailSitterNumber);
        sMemo = (TextView)findViewById(R.id.detailSitterMemo);

        sPrice.setText("가격 : " + intent.getStringExtra("price"));
        sAddress.setText("사는 곳 : " + intent.getStringExtra("address"));
        sTerm.setText("기 간 : " + intent.getStringExtra("term"));
        sPet.setText("키우는 펫 : " + intent.getStringExtra("pet"));
        sBack.setText("주거환경 : " + intent.getStringExtra("back"));
        sPetSize.setText("가능한 펫 크기 : " + intent.getStringExtra("size"));
        sEmail.setText("E-mail : " + intent.getStringExtra("email"));
        sNumber.setText("Phone : " + intent.getStringExtra("number"));
        sMemo.setText("하고싶은말 : " + intent.getStringExtra("memo"));


    }
}
