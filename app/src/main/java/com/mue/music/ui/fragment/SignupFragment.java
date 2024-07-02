package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mue.music.R;
import com.mue.music.util.RenderFragmentUtil;

public class SignupFragment extends Fragment {
    private TextView txtLogin;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_signup, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtLogin = view.findViewById(R.id.txt_login);
        txtLogin.setOnClickListener(v -> {
            if (this.getActivity() != null) {
                RenderFragmentUtil.loadFragment(this.getActivity(), new LoginFragment(), R.id.fragment_non_login_container);
            } else {
                Log.e("SignupFragment", "Activity is null");
            }
        });
    }
}
