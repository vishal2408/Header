package developer.vishal.myapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {

    TextView txt;
    HashMap<String ,List<String>> map=new HashMap();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txt=findViewById(R.id.text);

        map = (HashMap<String, List<String>>) getIntent().getSerializableExtra("headers");

        for (Map.Entry<String ,List<String>> entry : map.entrySet()){
            Log.d("entry",entry.getKey()+"   "+ entry.getValue());
        }
        txt.setText(map.toString());

    }
}
