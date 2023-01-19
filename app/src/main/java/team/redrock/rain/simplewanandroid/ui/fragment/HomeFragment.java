package team.redrock.rain.simplewanandroid.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import team.redrock.rain.simplewanandroid.R;
import team.redrock.rain.simplewanandroid.base.BaseFragment;
import team.redrock.rain.simplewanandroid.databinding.FragmentHomeBinding;
import team.redrock.rain.simplewanandroid.repo.HomeRepository;
import team.redrock.rain.simplewanandroid.ui.adapter.HomeRvAdapter;

/**
 * team.redrock.rain.simplewanandroid.view.fragment.null.java
 * SimpleWanAndroid
 *
 * @author 寒雨
 * @since 2023/1/19 下午2:32
 */
public class HomeFragment extends BaseFragment {

    FragmentHomeBinding binding;
    HomeRvAdapter adapter;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.rvHome.setLayoutManager(new LinearLayoutManager(requireContext()));
        this.adapter = new HomeRvAdapter();
        binding.rvHome.setAdapter(adapter);
        // 来条分隔线
        binding.rvHome.addItemDecoration(new DividerItemDecoration(requireContext(), RecyclerView.VERTICAL));
        // 来个淡入动画
        binding.rvHome.setLayoutAnimation(new LayoutAnimationController(AnimationUtils.loadAnimation(requireContext(), R.anim.home_fade_in)));
        // 没有拉取完成的时候让他转
        binding.srvHome.setRefreshing(true);
        binding.srvHome.setOnRefreshListener(this::refresh);
        refresh();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void scrollToTop() {
        if (((LinearLayoutManager) binding.rvHome.getLayoutManager()).findFirstVisibleItemPosition() > 20) {
            // 直接瞬移
            binding.rvHome.scrollToPosition(0);
        } else {
            // 滑动动画
            binding.rvHome.smoothScrollToPosition(0);
        }
    }

    // 拉取网络数据 更新页面
    private void refresh() {
        HomeRepository.getArticles(list -> {
            binding.srvHome.setRefreshing(false);
            // 使用post方法切换到主线程
            binding.rvHome.post(() -> adapter.submitList(list));
        });
    }
}
