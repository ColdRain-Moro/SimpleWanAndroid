package team.redrock.rain.simplewanandroid.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import team.redrock.rain.simplewanandroid.R;
import team.redrock.rain.simplewanandroid.base.BaseFragment;
import team.redrock.rain.simplewanandroid.databinding.ActivityMainBinding;
import team.redrock.rain.simplewanandroid.ui.adapter.MainVpAdapter;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        // 将toolbar设置为当前activity的actionbar
        setSupportActionBar(binding.toolbar);
        // 设置actionbar左侧的按钮图标
        ActionBar supportActionBar = getSupportActionBar();
        assert supportActionBar != null;
        supportActionBar.setDisplayHomeAsUpEnabled(true);
        supportActionBar.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        // 不让滑
        binding.vp2Main.setUserInputEnabled(false);
        // 设置 ViewPager2 的 adpater
        binding.vp2Main.setAdapter(new MainVpAdapter(getSupportFragmentManager(), getLifecycle()));
        // 设置底部导航栏label始终显示
        binding.bottomNav.setLabelVisibilityMode(BottomNavigationView.LABEL_VISIBILITY_LABELED);
        // 设置底部导航栏点击跳转 viewpager2 页面
        binding.bottomNav.setOnItemSelectedListener(item -> {
            // 页面正在滑动时点击无效，防止闪退
            if (binding.vp2Main.getScrollState() != ViewPager2.SCROLL_STATE_IDLE) return false;
            switch (item.getItemId()) {
                case R.id.bottom_nav_home: {
                    binding.vp2Main.setCurrentItem(0);
                    break;
                }
                case R.id.bottom_nav_marketplace: {
                    binding.vp2Main.setCurrentItem(1);
                    break;
                }
                case R.id.bottom_nav_wechat: {
                    binding.vp2Main.setCurrentItem(2);
                    break;
                }
                case R.id.bottom_nav_system: {
                    binding.vp2Main.setCurrentItem(3);
                    break;
                }
                case R.id.bottom_nav_project: {
                    binding.vp2Main.setCurrentItem(4);
                    break;
                }
            }
            return true;
        });
        // 找到vp2里面存放的当前fragment 调用其scrollToTop方法
        binding.floatingActionBtn.setOnClickListener(btn -> ((BaseFragment) getSupportFragmentManager().findFragmentByTag("f" + binding.vp2Main.getCurrentItem())).scrollToTop());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 点击toolbar左侧按钮打开侧边栏
        if (item.getItemId() == android.R.id.home) {
            binding.drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}