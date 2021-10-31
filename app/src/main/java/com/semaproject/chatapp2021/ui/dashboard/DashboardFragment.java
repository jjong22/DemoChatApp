package com.semaproject.chatapp2021.ui.dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.semaproject.chatapp2021.ChatActivity;
import com.semaproject.chatapp2021.R;
import com.semaproject.chatapp2021.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private DashboardViewModel dashboardViewModel;
    private FragmentDashboardBinding binding;

    Button bt1;


    public View onCreateView(@NonNull LayoutInflater inflater,
            ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                new ViewModelProvider(this).get(DashboardViewModel.class);

    binding = FragmentDashboardBinding.inflate(inflater, container, false);
    View root = binding.getRoot();


    View rootView = inflater.inflate(R.layout.fragment_dashboard,
            container, false);
    Button bt1 = (Button) rootView.findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
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