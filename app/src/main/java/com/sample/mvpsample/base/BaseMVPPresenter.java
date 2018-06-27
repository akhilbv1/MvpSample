package com.sample.mvpsample.base;

/**
 * Created by akhil on 10/4/18.
 */

public interface BaseMVPPresenter<V extends BaseView> {

    /**
     * Called when view attached to presenter
     *
     * @param view
     */
    void attach(V view);

    /**
     * Called when view is detached from presenter
     */
    void detach();

    /**
     * @return true if view is attached to presenter
     */
    boolean isAttached();
}
