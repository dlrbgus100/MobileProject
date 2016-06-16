package kr.ac.kumoh.ce.s20100884.database;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    EditText eId, ePass;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, splash.class);
        startActivity(intent);
        btn = (Button)findViewById(R.id.join);
        btn2 = (Button)findViewById(R.id.login);
        btn.setOnClickListener(buttonClick);
        btn2.setOnClickListener(buttonClick2);

    }

    Button.OnClickListener buttonClick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(),Join.class);
            startActivity(intent);

        }
    };
    Button.OnClickListener buttonClick2 = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            eId = (EditText)findViewById(R.id.inputId);
            ePass = (EditText)findViewById(R.id.inputPass);

            if(eId.getText().toString().equals("")||ePass.getText().toString().equals(""))
                Toast.makeText(getApplicationContext(),"ID 또는 Password를 입력하세요",Toast.LENGTH_SHORT).show();
            else{
                Intent intent = new Intent(getApplicationContext(),activity_Selection.class);
                startActivity(intent);
            }
        }
    };
}

