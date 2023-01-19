package team.redrock.rain.simplewanandroid.repo;

import java.util.List;
import java.util.function.Consumer;

import team.redrock.rain.simplewanandroid.repo.network.HomeService;
import team.redrock.rain.simplewanandroid.repo.network.model.ArticlesData;

/**
 * team.redrock.rain.simplewanandroid.repo
 * SimpleWanAndroid
 *
 * Repository层用于对网络请求或数据库返回的数据进行进一步处理与加工
 *
 * @author 寒雨
 * @since 2023/1/19 下午5:43
 */
public class HomeRepository {
    public static void getArticles(Consumer<List<ArticlesData.ArticleData>> callback) {
        HomeService.impl().getArticles(data -> callback.accept(data.getDatas()));
    }
}
