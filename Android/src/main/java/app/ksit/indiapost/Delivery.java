package app.ksit.indiapost;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static android.R.attr.name;

/**
 * Created by Shravan on 02-04-2017.
 */

public class Delivery extends Activity {
    EditText t1,t2;TextView t3,t4;
    String id;
    JSONObject jsonObject2,jsonObject;
    String gum;
    JSONArray ja;
    JsonObjectRequest jor;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_del);
        t1= (EditText) findViewById(R.id.editText);

        t2= (EditText) findViewById(R.id.editText2);

        t3= (EditText) findViewById(R.id.editText3);
        t4= (TextView) findViewById(R.id.texta4);


        Intent in = getIntent();
        id = in.getStringExtra("id");
        t2.setText(id);

        Toast.makeText(Delivery.this, id, Toast.LENGTH_SHORT).show();


        final Button b9 = (Button) findViewById(R.id.b9);
        try {
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    requestQueue = Volley.newRequestQueue(Delivery.this);
                    if(t2.getText().equals(""))
                    {
                        t3.setText("Error-No input given");
                    }
                    else
                    {
                        String url ="http://192.168.21.168/pdf/del/del-updater.php?id="+id+"&error="+t1.getText();


                     Toast.makeText(Delivery.this, url, Toast.LENGTH_LONG).show();
                    Log.d("TAG",url);

        // Request a string response from the provided URL.
                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // Display the first 500 characters of the response string.
                               //     t2.setText("Response is: "+ response.substring(0,500));
                                    t3.setText(Html.fromHtml(response));
                                    t2.setVisibility(View.INVISIBLE);
                                    t1.setVisibility(View.INVISIBLE);
                                    t4.setVisibility(View.INVISIBLE);
                                    b9.setVisibility(View.INVISIBLE);
                                }

                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            t2.setText(error.toString());
                        }
                    });
            // Add the request to the RequestQueue.
                    requestQueue.add(stringRequest);



                }}
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error getting phone", Toast.LENGTH_LONG).show();
        }

        final Button b10 = (Button) findViewById(R.id.b10);
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.21.168/pdf/del/get-part.php?nae=" + t2.getText()));
                    startActivity(myIntent);
    }});
    }
}


    /*  else if(arr[0].equals("ud")) {

                        try {
                            o1=arr[1];
                            Toast.makeText(MainActivity.this, o1, Toast.LENGTH_SHORT).show();

                            s3 = arr[2];
                            l2 = "UID: " + arr[1] + "\n";
                            l3 = "Phone: " + s3 + "\n";
                            l4 = "From: " + arr[3] + "\n";
                            l5 = "To: " + arr[4] + "\n";
                            l6 = "Method: " + arr[5] + "\n";
                            l1 = "Date arrving: " + arr[6]+"\n";
                            l7 = "Date leaving: " + arr[7];

                            o = arr[6];

                            b3.setVisibility(View.GONE);
                            b4.setVisibility(View.GONE);
                            b9.setVisibility(View.VISIBLE);
                            s1 = l2 + l3 + l4 + l5 + l6 + l1 + l7;
                            t1.setText(s1);
                        }
                        catch (Exception E)
                        {
                            t1.setText("Error reading barcode");
                        }
                    }*/


       /* b9 = (Button) findViewById(R.id.b9);
        try {
            b9.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(MainActivity.this, Delivery.class);
                    Toast.makeText(MainActivity.this, o1, Toast.LENGTH_SHORT).show();
                    editor.putString(o1,"");
                    i.putExtra("id",o1);
                    startActivity(i);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error getting phone", Toast.LENGTH_LONG).show();
        }*/