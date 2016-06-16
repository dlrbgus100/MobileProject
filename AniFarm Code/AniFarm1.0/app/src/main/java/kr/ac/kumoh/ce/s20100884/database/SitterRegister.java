package kr.ac.kumoh.ce.s20100884.database;

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

public class SitterRegister extends AppCompatActivity {
    EditText eTerm, ePrice, eBack, ePet, ePetSize, eMemo;
    Button sitterRegisterBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_register);

        eTerm = (EditText)findViewById(R.id.sitterTerm);
        ePrice = (EditText)findViewById(R.id.sitterPrice);
        eBack = (EditText)findViewById(R.id.sitterBack);
        ePet = (EditText)findViewById(R.id.sitterPet);
        ePetSize = (EditText)findViewById(R.id.sitterSize);
        eMemo = (EditText)findViewById(R.id.sitterMemo);

        sitterRegisterBtn = (Button)findViewById(R.id.sitterRegisterBtn);

        sitterRegisterBtn.setOnClickListener(buttonClick);

    }

    Button.OnClickListener buttonClick = new Button.OnClickListener(){
        @Override
        public void onClick(View v){
            Member member = (Member) getApplication();
            String id = member.getId();
            String term = eTerm.getText().toString();
            String price = ePrice.getText().toString();
            String back = eBack.getText().toString();
            String pet = ePet.getText().toString();
            String petsize = ePetSize.getText().toString();
            String memo = eMemo.getText().toString();

            insertToDatabase(id, term, price, back, pet, petsize, memo);
        }
    };

    private void insertToDatabase(final String id, String term, String price, String back, String pet, String petsize, String memo) {

        class InsertData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s.equals("dup"))
                    Toast.makeText(getApplicationContext(), "이미 등록되어있습니다",Toast.LENGTH_SHORT).show();
                else if(s.equals("noInput"))
                    Toast.makeText(getApplicationContext(), "모두 입력하세요",Toast.LENGTH_SHORT).show();
                else {
                    Toast.makeText(getApplicationContext(), "등록완료", Toast.LENGTH_SHORT).show();
                    finish();
                }

            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String id = (String) params[0];
                    String term = (String) params[1];
                    String price = (String) params[2];
                    String back = (String) params[3];
                    String pet = (String) params[4];
                    String petsize = (String) params[5];
                    String memo = (String) params[6];

                    String link = "http://117.20.203.48/registerSitter.php";

                    String data = URLEncoder.encode("id", "euc-kr") + "=" + URLEncoder.encode(id, "euc-kr");
                    data += "&" + URLEncoder.encode("term", "euc-kr") + "=" + URLEncoder.encode(term, "euc-kr");
                    data += "&" + URLEncoder.encode("price", "euc-kr") + "=" + URLEncoder.encode(price, "euc-kr");
                    data += "&" + URLEncoder.encode("back", "euc-kr") + "=" + URLEncoder.encode(back, "euc-kr");
                    data += "&" + URLEncoder.encode("pet", "euc-kr") + "=" + URLEncoder.encode(pet, "euc-kr");
                    data += "&" + URLEncoder.encode("petsize","euc-kr") + "=" + URLEncoder.encode(petsize,"euc-kr");
                    data += "&" + URLEncoder.encode("memo", "euc-kr") + "=" + URLEncoder.encode(memo, "euc-kr");

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
                    return sb.toString();
                } catch (Exception e) {
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(id, term, price, back, pet, petsize, memo);
    }

}
