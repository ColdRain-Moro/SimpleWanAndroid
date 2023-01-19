package team.redrock.rain.simplewanandroid.ui.adapter;

import android.annotation.SuppressLint;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import team.redrock.rain.simplewanandroid.databinding.ItemHomeBinding;
import team.redrock.rain.simplewanandroid.repo.network.model.ArticlesData;

/**
 * team.redrock.rain.simplewanandroid.ui.adapter
 * SimpleWanAndroid
 *
 * 这里我就简单写一下，不做分页了。如果你感兴趣的话可以琢磨一下如何实现分页。
 * RecyclerView.Adapter应该都会用了吧，这里再介绍更好用的ListAdapter，它是RecyclerView.Adapter的子类，基于其封装而来
 *
 * @author 寒雨
 * @since 2023/1/19 下午3:05
 */
public class HomeRvAdapter extends ListAdapter<ArticlesData.ArticleData, HomeRvAdapter.ViewHolder> {

    public HomeRvAdapter() {
        super(new ItemDiffCallback());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                ItemHomeBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false)
        );
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ArticlesData.ArticleData item = getItem(position);
        if (item.getFresh()) {
            // 显示
            holder.binding.tvNew.setVisibility(View.VISIBLE);
        }
        holder.binding.homeRvTitle.setText(Html.fromHtml(item.getTitle()));
        holder.binding.homeRvDate.setText(item.getNiceDate());
        holder.binding.tvAuthor.setText(item.getAuthor().equals("") ? item.getShareUser() : item.getAuthor());
        holder.binding.homeRvLabel.setText(item.getSuperChapterName() + "/" + item.getChapterName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        ItemHomeBinding binding;

        public ViewHolder(@NonNull ItemHomeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    static class ItemDiffCallback extends DiffUtil.ItemCallback<ArticlesData.ArticleData> {

        @Override
        public boolean areItemsTheSame(@NonNull ArticlesData.ArticleData oldItem, @NonNull ArticlesData.ArticleData newItem) {
            // 这里是判断两个item是不是一样的，一般直接比较id即可
            return oldItem.getId().intValue() == newItem.getId().intValue();
        }

        @Override
        public boolean areContentsTheSame(@NonNull ArticlesData.ArticleData oldItem, @NonNull ArticlesData.ArticleData newItem) {
            // 比较两个item内容是否一致，这里直接重写这个实体类的equals即可，建议使用idea自带的一键生成
            return oldItem.equals(newItem);
        }
    }
}
