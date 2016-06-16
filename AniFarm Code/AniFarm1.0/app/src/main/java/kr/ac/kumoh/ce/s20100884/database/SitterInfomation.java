package kr.ac.kumoh.ce.s20100884.database;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class SitterInfomation extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView mList;
    ArrayList<UserInfo> mArray = new ArrayList<UserInfo>();
    UserAdapter mAdapter;
    String[] uInfo;
    String response;
    Button mButton;
    String mapInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter_infomation);

        mList = (ListView) findViewById(R.id.listView);
        mAdapter = new UserAdapter(this, R.layout.activity_list_item, mArray);
        mList.setAdapter(mAdapter);
        mList.setOnItemClickListener(this);
        mButton = (Button) findViewById(R.id.mapButton);
        mButton.setOnClickListener(mMapButton);
        Member member = (Member) getApplicationContext();
        String id = member.getId();
        checkDatabase(id);
        mapInfo = "";
        StringTokenizer st = new StringTokenizer(response, "*");
        uInfo = new String[st.countTokens()];

        for (int i = 0; st.hasMoreElements(); i++) {
            uInfo[i] = st.nextToken();
        }
        try {
            for (int i = 0; i < uInfo.length; i += 8) {
                mArray.add(new UserInfo(uInfo[i], uInfo[i + 1], uInfo[i + 2], uInfo[i + 3], uInfo[i + 4],
                        uInfo[i + 5], uInfo[i + 6], uInfo[i + 7]));
                mapInfo =mapInfo + "/" + uInfo[i+4];
            }
        } catch (Exception e) {

        }

    }

    Button.OnClickListener mMapButton = new Button.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getApplicationContext(), MapActivity.class);
            intent.putExtra("map", mapInfo);
            startActivity(intent);

        }
    };


    public class UserInfo {
        String petType;
        String petSize;
        String price;
        String term;
        String address;
        String email;
        String number;
        String memo;

        public UserInfo(String petType, String petSize, String price, String term,
                        String address, String email, String number, String memo) {
            this.petType = petType;
            this.petSize = petSize;
            this.price = price;
            this.term = term;
            this.address = address;
            this.email = email;
            this.number = number;
            this.memo = memo;
        }

        public String getPetType() {
            return petType;
        }

        public String getPetSize() {
            return petSize;
        }

        public String getPrice() {
            return price;
        }

        public String getTerm() {
            return term;
        }

        public String getAddress() {
            return address;
        }

        public String getEmail() {
            return email;
        }

        public String getNumber() {
            return number;
        }

        public String getMemo() {
            return memo;
        }
    }

    static class UserViewHolder {
        TextView mListType;
        TextView mListaddress;
        TextView mListTerm;
    }


    public class UserAdapter extends ArrayAdapter<UserInfo> {

        private LayoutInflater mInflater = null;

        public UserAdapter(Context context, int resource, List<UserInfo> objects) {
            super(context, resource, objects);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mArray.size();
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            UserViewHolder viewHolder;
            if (v == null) {
                v = mInflater.inflate(R.layout.activity_list_item, parent, false);

                viewHolder = new UserViewHolder();

                viewHolder.mListType = (TextView) v.findViewById(R.id.petListType);
                viewHolder.mListaddress = (TextView) v.findViewById(R.id.petListAddress);
                viewHolder.mListTerm = (TextView) v.findViewById(R.id.petListTerm);

                v.setTag(viewHolder);
            } else {
                viewHolder = (UserViewHolder) v.getTag();
            }

            UserInfo info = mArray.get(position);
            if (info != null) {
                viewHolder.mListType.setText("펫 종류: " + info.getPetType());
                viewHolder.mListaddress.setText("사는곳: " + info.getAddress());
                viewHolder.mListTerm.setText("기간 :" + info.getTerm());
            }

            return v;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent detail = new Intent(this, DetailPet.class);

        detail.putExtra("type", mArray.get(position).getPetType());
        detail.putExtra("size", mArray.get(position).getPetSize());
        detail.putExtra("price", mArray.get(position).getPrice());
        detail.putExtra("term", mArray.get(position).getTerm());
        detail.putExtra("address", mArray.get(position).getAddress());
        detail.putExtra("email", mArray.get(position).getEmail());
        detail.putExtra("number", mArray.get(position).getNumber());
        detail.putExtra("memo", mArray.get(position).getMemo());

        startActivity(detail);
    }


    private void checkDatabase(String id) {

        class checkData extends AsyncTask<String, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);

            }

            @Override
            protected String doInBackground(String... params) {

                try {
                    String id = (String) params[0];

                    String link = "http://117.20.203.48/petdetail.php";

                    String data = URLEncoder.encode("id", "euc-kr") + "=" + URLEncoder.encode(id, "euc-kr");

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
        checkData task = new checkData();
        task.execute(id);
        try {
            response = new checkData().execute(id).get();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
