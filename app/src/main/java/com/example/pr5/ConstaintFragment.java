package com.example.pr5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class ConstaintFragment extends Fragment
{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.constaint_layout, container, false);
        ComixCard data = (ComixCard) getArguments().getSerializable(ComixCard.class.getSimpleName());
        TextView name = v.findViewById(R.id.comix_name);
        name.setText(data.getName());

        TextView engName = v.findViewById(R.id.comix_engname);
        engName.setText(data.getEngName());

        ImageView logo = v.findViewById(R.id.comix_logo);
        logo.setImageResource(data.getLogo());

        Button btn = (Button) v.findViewById(R.id.readbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putString("name", name.getText().toString());
                Navigation.findNavController(view).navigate(R.id.action_comix_to_profile, result);
            }
        });
        return v;
    }
}
