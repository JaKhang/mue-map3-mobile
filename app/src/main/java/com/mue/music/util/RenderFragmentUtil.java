package com.mue.music.util;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

// getSupportFragmentManager() là phương thức của FragmentActivity + các lớp con (Bao gồm AppCompatActivity)
public class RenderFragmentUtil {
    public static void loadFragment(FragmentActivity activity, Fragment fragment, int fragmentContainerId) {
        FragmentManager fragmentManager = activity.getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(fragmentContainerId);
        if (currentFragment != null && currentFragment.getClass().equals(fragment.getClass())) {
            // Hàm if kiểm tra xem fragment class muốn load có phải là fragment class hiện tại hay không,
            // nếu có thì không load fragment class lên (Tối ưu việc load fragment)
            return;
        }

        fragmentManager.beginTransaction()
                .replace(fragmentContainerId, fragment)
                .addToBackStack(null)
                .commit();
    }
}
