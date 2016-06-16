package kr.ac.kumoh.ce.s20100884.database;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    Button btn, btn2;
    EditText eId, ePass;
    TextView test;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            String id = eId.getText().toString();
            String pass = ePass.getText().toString();

            checkLogin(id, pass);
        }

    };
    private void checkLogin(final String id, String pass) {

        class checkData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

                if(s.equals(id)){
                    Intent intent = new Intent(getApplicationContext(),activity_Selection.class);
                    startActivity(intent);

                    eId.setText("");
                    ePass.setText("");
                }
                else if(s.equals("noInput"))
                    Toast.makeText(getApplicationContext(),"아이디 또는 비밀번호를 입력하세요.",Toast.LENGTH_LONG).show();

                else {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 틀렸습니다.", Toast.LENGTH_LONG).show();
                    ePass.setText("");
                }
            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String id = (String) params[0];
                    String pass = (String)params[1];

                    String link = "http://117.20.203.48/login.php";

                    String data = URLEncoder.encode("id", "euc-kr") + "=" + URLEncoder.encode(id, "euc-kr");
                    data += "&" + URLEncoder.encode("pass","euc-kr") + "=" + URLEncoder.encode(pass, "euc-kr");

                    URL url = new URL(link);
                    URLConnection conn = url.openConnection();

                    conn.setDoOutput(true);
                    conn.setDoInput(true);

                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream(), "euc-kr");

                    wr.write(data);
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "euc-kr"));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                        break;
                    }
                    Member member = (Member)getApplicationContext();

                    member.setId(sb.toString());
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }
            }
        }

        checkData task = new checkData();
        task.execute(id, pass);
    }
}

