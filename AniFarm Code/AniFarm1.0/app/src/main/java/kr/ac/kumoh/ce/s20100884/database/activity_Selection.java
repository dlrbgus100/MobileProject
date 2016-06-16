package kr.ac.kumoh.ce.s20100884.database;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class activity_Selection extends AppCompatActivity {
    Button petRegisterbtn, petSitterbtn, petSearchbtn, setbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        petRegisterbtn = (Button)findViewById(R.id.petRegisterBtn);
        petSitterbtn = (Button)findViewById(R.id.petSitterBtn);
        petSearchbtn = (Button)findViewById(R.id.petSerchBtn);
        setbtn = (Button)findViewById(R.id.settingBtn);
        petRegisterbtn.setOnClickListener(buttonClick);
        petSitterbtn.setOnClickListener(buttonClick);
        petSearchbtn.setOnClickListener(buttonClick);

    }





    Button.OnClickListener buttonClick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.petRegisterBtn:
                    Intent intentRegister = new Intent(getApplicationContext(),PetRegister.class);
                    startActivity(intentRegister);
                    break;
                case R.id.petSitterBtn:
                    Intent intentSitter = new Intent(getApplicationContext(),SitterRegister.class);
                    startActivity(intentSitter);
                    break;
                case R.id.petSerchBtn:
                    Intent intentSearch = new Intent(getApplicationContext(),Sitter_Search.class);
                    startActivity(intentSearch);
                    break;
                case R.id.settingBtn:

            }

        }
    };


}
