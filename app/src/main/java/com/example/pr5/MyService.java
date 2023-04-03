package com.example.pr5;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class MyService extends Service {

    View view;
    TextView name;
    TextView desc;
    ImageView logo;
    WindowManager mWindowManager;

    @Override
    public void onCreate()
    {
        super.onCreate();
        view = LayoutInflater.from(this).inflate(R.layout.comix_card, null);
        name = (TextView) view.findViewById(R.id.ComicCardName);
        desc = (TextView) view.findViewById(R.id.ComicCardDesc);
        logo = (ImageView) view.findViewById(R.id.ComixCardLogo);
        view.setBackgroundColor(Color.rgb(0,0,0));
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = getPackageManager().getLaunchIntentForPackage("com.example.pr5");
                mWindowManager.removeView(view);
                startActivity(intent);
            }
        });
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d("Test", "Вошёл");
        ComixCard card = (ComixCard) intent.getExtras().getSerializable(ComixCard.class.getSimpleName());
        name.setText(card.getName());
        desc.setText(card.getDesc());
        logo.setImageResource(card.getLogo());

        WindowManager.LayoutParams params = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        mWindowManager.addView(view, params);
        params.gravity = Gravity.TOP | Gravity.CENTER;
        mWindowManager.updateViewLayout(view, params);
        Log.d("Test", "Added");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Удаление View из WindowManager
        if (view != null) {
            mWindowManager.removeView(view);
        }
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }
}