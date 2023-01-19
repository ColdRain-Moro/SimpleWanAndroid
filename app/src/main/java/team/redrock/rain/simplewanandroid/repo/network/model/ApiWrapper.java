package team.redrock.rain.simplewanandroid.repo.network.model;

/**
 * team.redrock.rain.simplewanandroid.repo.network.model
 * SimpleWanAndroid
 *
 * 一切返回数据的通用结构
 *
 * @author 寒雨
 * @since 2023/1/19 下午3:23
 */
public class ApiWrapper<T> {
    T data;
    int errorCode;
    String errorMsg;

    public int getErrorCode() {
        return errorCode;
    }

    public T getData() {
        return data;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
