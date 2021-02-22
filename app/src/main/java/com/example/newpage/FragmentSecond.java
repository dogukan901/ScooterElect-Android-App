package com.example.newpage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSecond extends Fragment {

    private Button buttonDo;
    private TextView textViewSonuc;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_second_layout, container,false);

        buttonDo = rootView.findViewById(R.id.buttonDo);
        textViewSonuc = rootView.findViewById(R.id.textViewResult);


        buttonDo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textViewSonuc.setText("You are rocking with second fragment now");

            }
        });

        return rootView;

    }

}
