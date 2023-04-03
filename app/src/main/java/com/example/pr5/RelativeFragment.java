package com.example.pr5;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

import java.security.Permission;

public class RelativeFragment extends Fragment {
    private static final String CHANNEL_ID = "CHANNEL_ID";
    private final int NOTIFY_ID = 555;
    private final int PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE = 105;
    private TextView comixName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.relative_layout, container, false);
        comixName = v.findViewById(R.id.state);
        Bundle data = getArguments();
        if (data != null) {
            comixName.setText(data.getString("name"));
        }


        ((Button) v.findViewById(R.id.addfriendbtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder builder = new NotificationCompat.Builder(getContext(), CHANNEL_ID)
                        .setSmallIcon(R.drawable.baseline_person_24)
                        .setContentTitle(getString(R.string.add_to_friend_notify))
                        .setContentText("Вас добавил в друзья: " + ((TextView) v.findViewById(R.id.nickname)).getText().toString())
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(getContext());
                managerCompat.createNotificationChannel(new NotificationChannel(CHANNEL_ID, "add_friend_channel", NotificationManager.IMPORTANCE_DEFAULT));
                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(getActivity(),
                            new String[]{android.Manifest.permission.POST_NOTIFICATIONS},
                            PERMISSION_POST_NOTIFICATIONS_REQUEST_CODE);
                }
                managerCompat.notify(NOTIFY_ID, builder.build());

            }
        });

        return v;
    }
}
