package team.redrock.rain.simplewanandroid.repo.network.impl;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import team.redrock.rain.simplewanandroid.repo.network.HomeService;
import team.redrock.rain.simplewanandroid.repo.network.model.ApiWrapper;
import team.redrock.rain.simplewanandroid.repo.network.model.ArticlesData;

/**
 * team.redrock.rain.simplewanandroid.repo.network.impl.null.java
 * SimpleWanAndroid
 *
 * @author 寒雨
 * @since 2023/1/19 下午3:19
 */
public class HomeServiceImpl implements HomeService {

    OkHttpClient client = new OkHttpClient.Builder().build();
    Gson gson = new Gson();

    @Override
    public void getArticles(int page, int pageSize, Consumer<ArticlesData> callback) {
        Request request = new Request.Builder()
                // GET 请求
                .get()
                // 设置 url
                .url("https://www.wanandroid.com/article/list/" + page + "/json?pageSize=" + pageSize)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("HomeServiceImpl", "网络请求异常");
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                String json = response.body().string();
                // 使用gson直接把json字符串转为实体类实例
                ApiWrapper<ArticlesData> obj = gson.fromJson(json, new TypeToken<ApiWrapper<ArticlesData>>(){}.getType());
                // 回调
                callback.accept(obj.getData());
            }
        });
    }
}
