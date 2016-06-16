package kr.ac.kumoh.ce.s20100884.database;

import android.app.TabActivity;
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
import android.widget.TabHost;
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

public class Sitter_Search extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sitter__search);
        TabHost tabHost = getTabHost();

        tabHost.addTab(tabHost.newTabSpec("tap1").setIndicator("펫 주인").
                setContent(new Intent(this, SitterInfomation.class)));
        tabHost.addTab(tabHost.newTabSpec("tap2").setIndicator("펫 시터").
                setContent(new Intent(this, PetInfomation.class)));

    }
}
