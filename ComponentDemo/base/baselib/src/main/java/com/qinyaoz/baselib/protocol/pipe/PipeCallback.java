package com.qinyaoz.baselib.protocol.pipe;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 此通用Callback回调用于在组件间通过pipe通道访问异步操作时。使用统一回调方式来做通信。
 *
 * <p>比如对于如下定义通信协议接口的。其中通信协议方法包含此回调时：
 * <pre>
 * public interface CallbackPipe extends Pipe {
 *      void callbackMethod(Object arg, PipeCallback callback);
 * }
 * </pre>
 *
 *
 * <p>当通过{@link PipeManager}调用callbackMethod方法时。根据是否此协议接口已注册使用。有两种方式反馈：
 * <ol>
 *     <li>对于未注册时。框架直接创建一个{@link PipeException}实例。通过{@link PipeCallback#onError(Throwable)}方法反馈用户。</li>
 *     <li>对于已注册使用时。即应根据具体协议实现类的调用为准。具体协议实现应将数据转换为此类定义时泛型所指的类型数据。传递回来。</li>
 * </ol>
 *
 * <p>对于注册使用时。协议接口实现实体应该传入实体数据所转义的json字符串:
 * <pre>
 * public class CallbackPipeImpl implements CallbackPipe {
 *      void callbackMethod(Object arg, PipeCallback callback) {
 *          ...// 其他操作
 *          // 操作完成。传递json数据返回。
 *          callback.parse(json)
 *          // 或者操作异常。通知失败
 *          callback.onError(e)
 *      }
 * }
 * </pre>
 *
 * @param <T> 需要返回的实例
 */
public abstract class PipeCallback<T> {

    private Type clz;
    private T response;

    public PipeCallback() {
        try {

            clz = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        } catch (Throwable t) {
            // 在未定义泛型时，此处会类转换异常，做一层过滤
            clz = Void.class;
        }
    }

    /**
     * 通信成功。反馈具体数据对象
     * @param t 需要反馈的数据
     */
    protected abstract void onResponse(T t);

    /**
     * 通信失败
     */
    public abstract void onError(Throwable t);
}
