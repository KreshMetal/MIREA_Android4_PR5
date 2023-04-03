package com.example.pr5;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleViewFragment extends Fragment
{
    private static final String TAG = "Recycle";
    private static final int REQUEST_CODE = 911;
    ArrayList<ComixCard> cards;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.recycle_list_frg, container, false);

        InitList();

        RecyclerView lst = (RecyclerView) view.findViewById(R.id.RecycleList);

        ComixCardAdapterR.OnCardClickListener comixCardListener = new ComixCardAdapterR.OnCardClickListener()
        {
            @Override
            public void onCardClick(ComixCard card, int position)
            {
                ComixCard comixCard = cards.get(position);
                Bundle result = new Bundle();
                result.putSerializable(ComixCard.class.getSimpleName(), comixCard);
                //Navigation.findNavController(view).navigate(R.id.action_home_to_comix, result);
                if (Settings.canDrawOverlays(getContext())) {
                    Intent intent = new Intent(getActivity().getApplicationContext(), MyService.class);
                    intent.putExtra(ComixCard.class.getSimpleName(), comixCard);
                    getActivity().startService(intent);
                } else {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getActivity().getPackageName()));
                    startActivityForResult(intent, REQUEST_CODE);
                }

            }
        };

        ComixCardAdapterR adapterR = new ComixCardAdapterR(getActivity().getApplicationContext(), cards, comixCardListener);
        lst.setAdapter(adapterR);

        return view;
    }
    private void InitList()
    {
        cards = new ArrayList<>();

        cards.add(new ComixCard("Токийский Гуль", "Тестовое описание", R.drawable.tg_logo, "Tokyo Ghoul"));
        cards.add(new ComixCard("Поднятие уровня в одиночку", "Тестовое описание", R.drawable.sl_logo, "Sololeveling"));
        cards.add(new ComixCard("Наруто", "Тестовое описание", R.drawable.naruto_logo, "Naruto"));
        cards.add(new ComixCard("Эльфийская песнь", "Тестовое описание", R.drawable.el_logo, "Elfien Lied"));
        cards.add(new ComixCard("Человек бензопила", "Тестовое описание", R.drawable.cm_logo, "Chainsaw Man"));
        cards.add(new ComixCard("Гомункул", "Тестовое описание", R.drawable.homu_logo, "Homunkulus"));
    }
}
