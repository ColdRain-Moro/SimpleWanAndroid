package team.redrock.rain.simplewanandroid.ui.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import team.redrock.rain.simplewanandroid.ui.fragment.BlankFragment;
import team.redrock.rain.simplewanandroid.ui.fragment.HomeFragment;

/**
 * team.redrock.rain.simplewanandroid.view.adapter.null.java
 * SimpleWanAndroid
 *
 * @author 寒雨
 * @since 2023/1/19 下午12:18
 */
public class MainVpAdapter extends FragmentStateAdapter {


    public MainVpAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: {
                return HomeFragment.newInstance();
            }
            // 以下页面没有实现，如果要实现的话直接修改返回的Fragment实例即可
            case 1: {
                return BlankFragment.newInstance();
            }
            case 2: {
                return BlankFragment.newInstance();
            }
            case 3: {
                return BlankFragment.newInstance();
            }
            case 4: {
                return BlankFragment.newInstance();
            }
            default: {
                throw new RuntimeException("unreachable statement");
            }
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
