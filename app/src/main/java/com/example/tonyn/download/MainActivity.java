package com.example.tonyn.download;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    private EditText editText;
    private Button start;
    private Button pause;
    private Button stop;
    private DownloadListener downloadListener;
    private DownloadService.DownloadBinder downloadBinder;
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            downloadBinder = (DownloadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = (Button) findViewById(R.id.button);
        pause = (Button) findViewById(R.id.button2);
        stop = (Button) findViewById(R.id.button3);
        editText = (EditText) findViewById(R.id.editText);
        Intent intent = new Intent(this,DownloadService.class);
        startService(intent);//启动服务
        bindService(intent,connection,BIND_AUTO_CREATE);//绑定服务
    }

    public void start(View view){
        if (downloadBinder == null){
            return;
        }
        String url = editText.getText().toString();
        downloadBinder.startDownload(url);
    }

    public void pause(View view){
        if (downloadBinder == null){
            return;
        }
        downloadBinder.pauseDownload();
    }

    public void stop(View view){
        if (downloadBinder == null){
            return;
        }
        downloadBinder.cancelDownload();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }
}
