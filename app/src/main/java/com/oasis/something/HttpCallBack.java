package com.oasis.something;

import org.xutils.common.Callback;

/**
 * Created by liling on 2015/11/30.
 */
public interface HttpCallBack {

    public void onSuccess(String result) ;
    public void onFinished() ;
    public void onCancelled(Callback.CancelledException cex) ;
    public void onError(Throwable ex, boolean isOnCallback) ;

}
