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

public class MainActivity extends AppCompatActivity {
    TextView text;
    String url;
    ProgressDialog dialog;
    Button btn_submit;
    String apiUrl="https://abhiandroid.com/programming/asynctask";
    Map<String ,List<String>> headers=new HashMap<>() ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dialog=new ProgressDialog(this);
        text=findViewById(R.id.text);

        btn_submit=findViewById(R.id.submit);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyAsyncTasks tasks= new MyAsyncTasks();
                tasks.execute();
            }
        });




    }

    public class MyAsyncTasks extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog.show();
            dialog.setMessage("Please Wait");
            dialog.setCancelable(false);
        }

        @Override
        protected String doInBackground(String... strings) {
            Log.d("datass","came here");

            String current = "hi";
            try {
                Log.d("tryblock","camera");
                URL url;

                HttpURLConnection urlConnection = null;
                try {
                    Log.d("tryblock","camera2");
                    url = new URL(apiUrl);


                    urlConnection = (HttpURLConnection) url
                            .openConnection();


                    Log.d("connection",urlConnection.getRequestMethod());


                    headers=urlConnection.getHeaderFields();

                    for (Map.Entry<String,List<String>> entry : headers.entrySet())
                    {
                        System.out.println("Key = " + entry.getKey() +
                                ", Value = " + entry.getValue());
                        Log.d("headerss", entry.getKey()+"    " +entry.getValue());
                    }


                    // return the data to onPostExecute method
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s) {

            dialog.cancel();
            callAnotherActivity();

        }
    }

    private void callAnotherActivity() {
        Intent intent=new Intent(this,Main2Activity.class);
        intent.putExtra("headers", (Serializable) headers);
        startActivity(intent);

    }
}
