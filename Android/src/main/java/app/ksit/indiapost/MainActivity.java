package app.ksit.indiapost;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;


public class MainActivity extends AppCompatActivity {
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;
    TextView t1,t2,t3;
    Button b1,b2,b3,b4,b5,b6,b9;
    String Up,Pin,Rname,Remail,Rtel="",Rloc="",P_type,Sname,Stel,sum="",s1,s2,loc,tel;
    String[] arr;
    SharedPreferences.Editor editor;
    String PREFE = "datagamebs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        //Remove notification bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_main);

        SharedPreferences pref = getSharedPreferences(PREFE, Context.MODE_PRIVATE);
        editor = pref.edit();

        t1 = (TextView) findViewById(R.id.textView);
       // t2 = (TextView) findViewById(R.id.textView2);
        t3 = (TextView) findViewById(R.id.textView3);
        b2 = (Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });

        b3 = (Button) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "geo:0,0?q=" + loc;
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });

        b4 = (Button) findViewById(R.id.b4);
        try {
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + tel));
                    startActivity(intent);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, "Error getting phone", Toast.LENGTH_LONG).show();
        }
    }
    // Get the results:
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {

                b3=(Button) findViewById(R.id.b3);
                b4=(Button) findViewById(R.id.b4);
                b5=(Button) findViewById(R.id.b5);
                 b9=(Button) findViewById(R.id.b9);
                b5.setVisibility(View.VISIBLE);

                t1=(TextView) findViewById(R.id.textView);
                t1.setVisibility(View.VISIBLE);
                s1 = result.getContents();
                s2=result.getFormatName();


                if(s2.equals("PDF_417")) {
                    //Toast.makeText(this, "PDF417", Toast.LENGTH_SHORT).show();

                    arr= s1.split(";;");
                    if(arr[0].equals("0")) {

                        loc=arr[8];
                        tel=arr[5];

                        Up="UPU: "+arr[1]+"\n";
                        Pin="PIN: "+arr[2]+"\n";
                        Rname="Reciever name: "+arr[3]+"\n";
                        Remail="Reciever email: "+arr[4]+"\n";
                        Rtel="Reicever telephone: "+arr[5]+"\n";
                        Sname="\nSender name: "+arr[6]+"\n";
                        Stel="Sender phone: "+arr[7]+"\n";
                        Rloc="Address: "+arr[8]+"\n";
                        P_type="Postage type: "+arr[9]+"\n";


                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);

                        s1=Up+Pin+Rname+Remail+Rtel+Rloc+Sname+Stel+P_type;

                        t1.setText(s1);
                        b9.setVisibility(View.GONE);
                        t3.setVisibility(View.VISIBLE);


                    }
                    else {
                        Toast.makeText(this, "Not our pdf!!", Toast.LENGTH_LONG).show();
                       // s1 = "Scanned result is: \n " + s1;
                        b3.setVisibility(View.GONE);
                        b4.setVisibility(View.GONE);
                        t3.setVisibility(View.VISIBLE);

                        t1.setText(s1);

                    }
                }
                else {
                    Toast.makeText(this, "DIFF BARCODE !!"+s2, Toast.LENGTH_LONG).show();
                  t3.setText("Not post barcode");
                    b3.setVisibility(View.GONE);

                    t1.setText(s1);
                }


                Toast.makeText(this, result.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}