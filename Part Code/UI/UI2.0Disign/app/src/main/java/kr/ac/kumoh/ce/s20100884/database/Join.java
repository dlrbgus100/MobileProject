package kr.ac.kumoh.ce.s20100884.database;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class Join extends AppCompatActivity {
    EditText eId, ePass, eName, eSex, eAge, eEmail, eNumber, eAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        eId = (EditText)findViewById(R.id.joinID);
        ePass = (EditText)findViewById(R.id.joinPass);
        eName = (EditText)findViewById(R.id.joinName);
        eSex = (EditText)findViewById(R.id.joinSex);
        eAge = (EditText)findViewById(R.id.joinAge);
        eNumber = (EditText)findViewById(R.id.joinNumber);
        eEmail = (EditText)findViewById(R.id.joinEmail);
        eAddress = (EditText)findViewById(R.id.joinLocate);

        Button idcheckBtn = (Button)findViewById(R.id.idcheckBtn);
        Button joinOK = (Button)findViewById(R.id.joinBtn);
        idcheckBtn.setOnClickListener(buttonClick);
        joinOK.setOnClickListener(buttonClick);
    }
    Button.OnClickListener buttonClick = new Button.OnClickListener() {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.idcheckBtn:
                    //아이디 체크 함수
                    break;
                case R.id.joinBtn:
                    String id = eId.getText().toString();
                    String pass = ePass.getText().toString();
                    String name = eName.getText().toString();
                    String sex = eSex.getText().toString();
                    String age = eAge.getText().toString();
                    String email = eEmail.getText().toString();
                    String number = eNumber.getText().toString();
                    String address = eAddress.getText().toString();

                    insertToDatabase(id, pass, name, sex, age, email, number, address);


                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
            }
        }
    };
    private void insertToDatabase(String id, String pass, String name, String sex, String age, String email, String number, String address) {

        class InsertData extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
//                loading = ProgressDialog.show(getApplicationContext(), "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
//                loading.dismiss();
                Toast.makeText(getApplicationContext(), "완료", Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String id = (String) params[0];
                    String pass = (String) params[1];
                    String name = (String) params[2];
                    String sex = (String) params[3];
                    String age = (String) params[4];
                    String email = (String)params[5];
                    String number = (String)params[6];
                    String address = (String)params[7];

                    String link = "http://117.20.203.48/inserta.php";

                    String data = URLEncoder.encode("id", "euc-kr") + "=" + URLEncoder.encode(id, "euc-kr");
                    data += "&" + URLEncoder.encode("pass", "euc-kr") + "=" + URLEncoder.encode(pass, "euc-kr");
                    data += "&" + URLEncoder.encode("name","euc-kr") + "=" + URLEncoder.encode(name,"euc-kr");
                    data += "&" + URLEncoder.encode("sex","euc-kr") + "=" + URLEncoder.encode(sex,"euc-kr");
                    data += "&" + URLEncoder.encode("age","euc-kr") + "=" + URLEncoder.encode(age,"euc-kr");
                    data += "&" + URLEncoder.encode("number","euc-kr") + "=" + URLEncoder.encode(number,"euc-kr");
                    data += "&" + URLEncoder.encode("email","euc-kr") + "=" + URLEncoder.encode(email,"euc-kr");
                    data += "&" + URLEncoder.encode("address","euc-kr") + "=" + URLEncoder.encode(address,"euc-kr");


                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(),"euc-kr");

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(),"euc-kr"));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(id, pass, name, sex, age, email, number, address);
    }

}
