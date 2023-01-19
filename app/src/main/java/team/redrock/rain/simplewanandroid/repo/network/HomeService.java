package team.redrock.rain.simplewanandroid.repo.network;

import java.util.function.Consumer;

import team.redrock.rain.simplewanandroid.repo.network.impl.HomeServiceImpl;
import team.redrock.rain.simplewanandroid.repo.network.model.ArticlesData;

/**
 * team.redrock.rain.simplewanandroid.repo.network
 * SimpleWanAndroid
 *
 * @author 寒雨
 * @since 2023/1/19 下午3:18
 */
public interface HomeService {

    /**
     * 为什么要使用回调，而不是阻塞等待到直接返回？因为性能更好:D
     * 当然现阶段其实也可以不考虑，你想直接堵塞等待也是可以的
     *
     * @param page 页数
     * @param pageSize 每页有多少条
     * @param callback 接收请求结果的回调函数，注意，这个回调运行在okhttp的调度线程上，进行视图相关操作时需要使用 runOnUiThread 切回主线程
     */
    void getArticles(int page, int pageSize, Consumer<ArticlesData> callback);

    default void getArticles(Consumer<ArticlesData> callback) {
        getArticles(1, 20, callback);
    }

    HomeService impl = new HomeServiceImpl();

    static HomeService impl() {
        return impl;
    }
}
