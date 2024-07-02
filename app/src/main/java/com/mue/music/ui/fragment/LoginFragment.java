package com.mue.music.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mue.music.R;
import com.mue.music.util.RenderFragmentUtil;

public class LoginFragment extends Fragment {
    TextView txtSignUp;
    TextView txtForgotPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        txtSignUp = view.findViewById(R.id.txtSignUp);
        txtForgotPassword = view.findViewById(R.id.txtForgotPassword);

        txtSignUp.setOnClickListener(v -> {
            if (this.getActivity() != null) {
                RenderFragmentUtil.loadFragment(this.getActivity(), new SignupFragment(), R.id.fragment_non_login_container);
            } else {
                Log.e("LoginFragment", "Activity is null");
            }
        });

        txtForgotPassword.setOnClickListener(v -> {
            Toast.makeText(getContext(), "Forgot password clicked!", Toast.LENGTH_SHORT).show();
        });
    }
}
