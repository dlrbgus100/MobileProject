package kr.ac.kumoh.ce.s20100884.database;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ListItem extends AppCompatActivity {
    TextView pListType, pListAddress, pListTerm;
    ImageView pListImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_item);
    }
}
