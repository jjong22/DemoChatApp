package com.semaproject.chatapp2021.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.FirebaseDatabase;
import com.semaproject.chatapp2021.Chat;
import com.semaproject.chatapp2021.ChatActivity;
import com.semaproject.chatapp2021.LoginActivity;
import com.semaproject.chatapp2021.MainActivity;
import com.semaproject.chatapp2021.MyAdapter;
import com.semaproject.chatapp2021.R;
import com.semaproject.chatapp2021.TapActivity;
import com.semaproject.chatapp2021.databinding.FragmentHomeBinding;
import com.semaproject.chatapp2021.ui.dashboard.DashboardViewModel;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;

    Button btChatStart;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }

        });

        View rootView = inflater.inflate(R.layout.fragment_home,
                container, false);
        Button btChatStart = (Button) rootView.findViewById(R.id.btChatStart);
        btChatStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateDetail();
            }
        });
        return rootView;
    }

    public void updateDetail() {
        Intent intent = new Intent(getActivity(), ChatActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
