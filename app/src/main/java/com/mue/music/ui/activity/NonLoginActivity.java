package com.mue.music.ui.activity;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentManager;

import com.mue.music.R;
import com.mue.music.ui.fragment.GreetingFragment;
import com.mue.music.ui.fragment.LoginFragment;
import com.mue.music.ui.fragment.SignupFragment;
import com.mue.music.util.RenderFragmentUtil;

public class NonLoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        EdgeToEdge.enable(this);
        this.setContentView(R.layout.activity_greeting);

        // Load GreetingFragment ngay trong lần đầu tiên,
        // kiểm tra trạng thái trước đó từ param Bundle để xem fragment này đã load lần đầu tiên hay chưa
        if (savedInstanceState == null) {
            // Truyền activity hiện tại (Là con của 1 AppCompatActivity), instance của Fragment cần tạo
            // và id container của fragment đó trong activity tương ứng
            RenderFragmentUtil.loadFragment(this, new GreetingFragment(), R.id.fragment_non_login_container);
        }

        // Đăng ký callback để xử lý nút "Back"
        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                FragmentManager fragmentManager = getSupportFragmentManager();
                // Ít nhất có 1 fragment nằm trên activity ( > 1 để đảm bảo điều đó).
                // Nếu không còn fragment nào thì out luôn khỏi activity
                if (fragmentManager.getBackStackEntryCount() > 1) {
                    fragmentManager.popBackStack();  // Lấy fragment ra khỏi back stack
                } else {
                    // Thực hiện hành động mặc định của nút "Back"
                    finish();
                }
            }
        });
    }

    public void onButtonClick(View view) {
        // Note: view là object đại diện cho thành phần đã phát sinh sự kiện
        if (view.getId() == R.id.signup_button) {
            RenderFragmentUtil.loadFragment(this, new SignupFragment(), R.id.fragment_non_login_container);
        } else if (view.getId() == R.id.login_button) {
            RenderFragmentUtil.loadFragment(this, new LoginFragment(), R.id.fragment_non_login_container);
        }
    }
}
