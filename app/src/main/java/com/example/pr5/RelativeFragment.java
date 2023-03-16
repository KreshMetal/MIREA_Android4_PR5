package com.example.pr5;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.navigation.Navigation;

public class RelativeFragment extends Fragment
{
    TextView comixName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.relative_layout, container, false);
        comixName = v.findViewById(R.id.state);
        Bundle data = getArguments();
        if (data != null)
        {
            comixName.setText(data.getString("name"));
        }
        return v;
    }
}
