package app.ksit.postal;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {
    private static final int ZXING_CAMERA_PERMISSION = 1;
    private Class<?> mClss;
    TextView t1;Button b1,b2,b3,b4;
    String s1,s2,s3,s4,s5;
    String l1,l2,l3,l4,l5,l6;
    String[] arr;

    @Override
    public void onCreate(Bundle state) {
        super.onCreate(state);
        setContentView(R.layout.activity_main);
        b1=(Button) findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Custom.class);
                startActivity(i);
            }
        });
        t1=(TextView) findViewById(R.id.textView);
        b2=(Button) findViewById(R.id.b2);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(MainActivity.this).initiateScan();
            }
        });

        b3=(Button) findViewById(R.id.b3);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String uri = "geo:0,0?q="+s3;
                startActivity(new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri)));
            }
        });
     /*   b4=(Button) findViewById(R.id.b4);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0123456789"));
                startActivity(intent);
            });
*/
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
                t1=(TextView) findViewById(R.id.textView);
                t1.setVisibility(View.VISIBLE);
                s1 = result.getContents();
                s2=result.getFormatName();


                if(s2.equals("PDF_417")) {
                    Toast.makeText(this, "PDF417", Toast.LENGTH_SHORT).show();
                    arr = s1.split(";;");
                    if(arr[0].equals("")) {
                        Toast.makeText(this, "Ours", Toast.LENGTH_LONG).show();
                        s3=arr[2];
                        l2="Name: " +arr[1]+"\n";
                        l3="Address: "+s3+"\n";
                        l4="UPU: "+arr[3]+"\n";
                        l5="Type: "+arr[4]+"\n";
                        l6="PIN: "+arr[5]+"\n";
                        l1="Phone"+arr[6];

                        b3.setVisibility(View.VISIBLE);
                        b4.setVisibility(View.VISIBLE);
                        s1=l2+l3+l4+l5+l6;
                        t1.setText(s1);

                    }
                    else {
                        Toast.makeText(this, "Not our pdf!!", Toast.LENGTH_LONG).show();
                        s1 = "Not post office barcode but pdf417-Scanned result= \n" + s1;
                        b3.setVisibility(View.GONE);
                        t1.setText(s1);

                    }
                }
                else {
                    Toast.makeText(this, "DIFF BARCODE !!"+s2, Toast.LENGTH_LONG).show();
                    s1 = "Not post office barcode-Scanned result= \n" + s1;
                    b3.setVisibility(View.GONE);
                    t1.setText(s1);
                }


                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_SHORT).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}