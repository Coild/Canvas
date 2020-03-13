package com.example.myapplication;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

/**
 * Created by dedykuncoro on 14/01/2016.
 * www.dedydedykuncoro.com
 */
public class MainActivity extends AppCompatActivity {

    public static final int SIGNATURE_ACTIVITY = 1;
    Toolbar toolbar;
    FloatingActionButton fab;
    Bitmap bitmap;
    ImageView img_signature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        img_signature = (ImageView) findViewById(R.id.img_signature);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                img_signature.setImageResource(0);
                Intent intent = new Intent(getApplicationContext(), Signature.class);
                startActivityForResult(intent, SIGNATURE_ACTIVITY);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case SIGNATURE_ACTIVITY:
                if (resultCode == RESULT_OK) {

                    Bundle bundle = data.getExtras();
                    String status = bundle.getString("status");
                    String path_image = bundle.getString("image");
                    Uri filePath = Uri.parse(path_image);

                    if (status.equalsIgnoreCase("done")) {
                        Toast.makeText(this, "Signature saved to gallery.", Toast.LENGTH_SHORT).show();

                        try {
                            //Getting the Bitmap from Gallery
                            bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                            //Setting the Bitmap to ImageView
                            img_signature.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                break;
        }

    }

}

