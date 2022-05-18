package com.example.quizzie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class DashboardActivity extends AppCompatActivity {
    private View decorView;
    TabLayout tabLayout;
    ViewPager viewPager;
    DrawerLayout drawerLayout;
    NavigationView sidemenu;
    ImageView logout;
    ImageView menu;
    ImageView create;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(i -> {
            if(i == 0) {
                decorView.setSystemUiVisibility(hideSystem());
            }
        });

        setContentView(R.layout.dashboard_atv);

        tabLayout = findViewById(R.id.switch_list);
        viewPager = findViewById(R.id.scroll_layout_pager);

        tabLayout.addTab(tabLayout.newTab().setText("Ongoing"));
        tabLayout.addTab(tabLayout.newTab().setText("Upcoming"));
        tabLayout.addTab(tabLayout.newTab().setText("History"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdapterDashList adapter = new AdapterDashList(getSupportFragmentManager(), this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        logout = findViewById(R.id.logout_button);
        logout.setOnClickListener(view -> new AlertDialog.Builder(view.getContext())
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", (dialog, id) -> {
                    Intent i = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(i);
                    finish();
                })
                .setNegativeButton("No", (dialog, id) -> dialog.cancel())
                .setCancelable(false)
                .show());

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                | WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        menu = findViewById(R.id.menu_open);
        drawerLayout = findViewById(R.id.drawer_dash);
        sidemenu = findViewById(R.id.menu_dashboard);
        menu.setOnClickListener(view -> drawerLayout.open());

        create = findViewById(R.id.create_new);
        create.setOnClickListener(view -> createButtonDialog());
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystem());
        }
    }

    private int hideSystem() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    public void createButtonDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View popupView = getLayoutInflater().inflate(R.layout.create_popup, null);
        final View popupViewClass = getLayoutInflater().inflate(R.layout.create_class_popup, null);
        final View popupViewQuiz = getLayoutInflater().inflate(R.layout.create_quiz_popup, null);

        builder.setView(popupView);
        AlertDialog dialog = builder.create();
        dialog.show();

        Button createClass = popupView.findViewById(R.id.create_class_btn);
        Button createQuiz = popupView.findViewById(R.id.create_quiz_btn);

        createClass.setOnClickListener(view -> {
            builder.setView(popupViewClass);
            AlertDialog dialogC = builder.create();
            dialogC.show();
            dialog.cancel();
        });

        createQuiz.setOnClickListener(view -> {
            builder.setView(popupViewQuiz);
            AlertDialog dialogQ = builder.create();
            dialogQ.show();
            dialog.cancel();
        });
    }
}
