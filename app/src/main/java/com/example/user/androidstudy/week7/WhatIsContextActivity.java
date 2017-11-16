package com.example.user.androidstudy.week7;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.user.androidstudy.R;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by USER on 2017-10-18.
 */

public class WhatIsContextActivity extends AppCompatActivity {

    private Button accessStorageReadButton;
    private LinearLayout container;
    private Button inflateButton;
    private Button getResourceButton;
    private Button accessStorageWriteButton;
    private Button accessStorageDeleteButton;
    private Button getSystemServiceButton;
    private static final String FILE_NAME = "new_file.txt";

    private final int LOCATION_PERMISSON_REQUEST = 2017;
    private final int LOCATION_PERMISSON_INTERVAL = 1000;


    private Button startActivityButton;
    private Button sendBroadcastButton; // context를 이용해서 발신수신다 가능
    private BroadcastReceiver receiver;

    private static final String ACTION_TEST = "osori.test_action";
    private static final String INTENT_TEST = "osori.test_key";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_context);

        startActivityButton = (Button) findViewById(R.id.context_start_activity);
        startActivityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewActivity();
            }
        });

        sendBroadcastButton = (Button) findViewById(R.id.context_send_broadcast);
        sendBroadcastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMyBraodcast();
            }
        });
        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(WhatIsContextActivity.this,
                        intent.getAction() + "!!" + String.valueOf(intent.getStringExtra(INTENT_TEST)), Toast.LENGTH_SHORT).show();
            }
        };
        IntentFilter intentfilter = new IntentFilter();
        intentfilter.addAction(ACTION_TEST);
        registerReceiver(receiver, intentfilter);

        container = (LinearLayout) findViewById(R.id.what_is_context_container);

        inflateButton = (Button) findViewById(R.id.context_inflate_button);
        inflateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inflateLayout();
            }
        });
        getResourceButton = (Button) findViewById(R.id.context_get_resource_button);
        getResourceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getStringResource();
            }
        });
        accessStorageReadButton = (Button) findViewById(R.id.context_get_device_storage_read_button);
        accessStorageWriteButton = (Button) findViewById(R.id.context_get_device_storage_write_button);
        accessStorageDeleteButton = (Button) findViewById(R.id.context_get_device_storage_delete_button);

        accessStorageReadButton.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                readStorage();

            }

        });
        accessStorageWriteButton.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                writeOnStorage();

            }

        });
        accessStorageDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override


            public void onClick(View v) {
                deleteFileAtStorage();

            }

        });

        getSystemServiceButton = (Button) findViewById(R.id.context_system_service_button);
        getSystemServiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] permissions = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
                ActivityCompat.requestPermissions(WhatIsContextActivity.this, permissions, LOCATION_PERMISSON_REQUEST);
            }
        });


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void startNewActivity() {

        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    private void sendMyBraodcast() {

        Intent intent = new Intent(ACTION_TEST);
        intent.putExtra(INTENT_TEST, "hello world");

        sendBroadcast(intent);
    }

    private void inflateLayout() {

        View view = LayoutInflater.from(this).inflate(R.layout.item_inflate_test, null);

        container.addView(view);
    }

    private void getStringResource() {
        String appName = getString(R.string.app_name);

        Toast.makeText(this, appName, Toast.LENGTH_SHORT).show();
    }

    private void readStorage() {
        // getExternalFilesDir method 는 Activity 가 가지고 있는 method 이며
        // Context 에 abstract method 로 정의 되어 있다.
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        if (file.exists()) {
            try {
                FileReader reader = new FileReader(file);
                BufferedReader bReader = new BufferedReader(reader);

                String text = bReader.readLine();
                Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

                reader.close();
            } catch (FileNotFoundException e) {

            } catch (IOException e) {

            }
        } else {
            Toast.makeText(this, "Write plz", Toast.LENGTH_SHORT).show();
        }
    }

    private void writeOnStorage() {
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write("fee gone");
            Toast.makeText(this, "Success on Write", Toast.LENGTH_SHORT).show();
            writer.close();
        } catch (IOException e) {

        }
    }

    private void deleteFileAtStorage() {
        File docsDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File file = new File(docsDir, FILE_NAME);

        if (file.exists()) {
            if (file.delete()) {
                Toast.makeText(this, "Success on delete", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failure on delete", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "There is no file", Toast.LENGTH_SHORT).show();
        }
    }

    private void getMySystemService() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Android Device system service 를 context 내의 getSystemService method 를 이용해서 가져올 수 있다.
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Toast.makeText(WhatIsContextActivity.this,
                String.format("lat: %f, lng: %f", location.getLatitude(), location.getLongitude()),
                Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults.length == 0)
            return;

        switch (requestCode) {
            case LOCATION_PERMISSON_REQUEST:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    getMySystemService();
                }
        }
    }


}


