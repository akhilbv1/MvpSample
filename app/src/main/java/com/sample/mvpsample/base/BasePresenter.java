package com.sample.mvpsample.base;

/**
 * Created by akhil on 10/4/18.
 */

public class BasePresenter<V extends BaseView> implements BaseMVPPresenter<V> {

    /**
     * Attached view
     */
    public V mView;


    @Override
    public void attach(V view) {
        mView = view;
    }

    @Override
    public void detach() {
        mView = null;
    }

    @Override
    public boolean isAttached() {
        return mView != null;
    }

    public V getView() {
        return mView;
    }
}
