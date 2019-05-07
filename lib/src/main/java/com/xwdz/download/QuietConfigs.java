/*
 * Copyright 2018 xwdz
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.xwdz.download;

import android.content.Context;

import com.xwdz.download.core.EventIntercept;

import java.io.File;
import java.util.ArrayList;

/**
 * @author 黄兴伟 (xwdz9989@gamil.com)
 */
public class QuietConfigs {

    public boolean isDebug = true;

    private int mMaxDownloadTasks = 3;
    private int mMaxDownloadThreads = 3;
    private File mDownloadDir = null;
    private int mMinOperateInterval = 1000;
    private boolean mRecoverDownloadWhenStart = true;

    private final ArrayList<EventIntercept> mEventIntercepts = new ArrayList<>();

    //  todo: no imp
    // todo 处理 jar 包重复问题
    private int mMaxRetryCount = 2;

    private QuietConfigs() {
    }

    private static class HolderClass {
        private static final QuietConfigs INSTANCE = new QuietConfigs();
    }

    public synchronized static QuietConfigs getImpl() {
        return HolderClass.INSTANCE;
    }


    public QuietConfigs with(Context context) {
        mDownloadDir = new File(context.getCacheDir() + File.separator + "QuietDownloader");
        checkDownloadFileExists(mDownloadDir);
        return this;
    }


    public int getMaxDownloadTasks() {
        return mMaxDownloadTasks;
    }

    public QuietConfigs setMaxDownloadTasks(int maxDownloadTasks) {
        this.mMaxDownloadTasks = maxDownloadTasks;
        return this;
    }

    public int getMaxDownloadThreads() {
        return mMaxDownloadThreads;
    }

    public QuietConfigs setMaxDownloadThreads(int maxDownloadThreads) {
        this.mMaxDownloadThreads = maxDownloadThreads;
        return this;
    }

    public File getDownloadDir() {
        return mDownloadDir;
    }

    public QuietConfigs setDownloadDir(File downloadDir) {
        this.mDownloadDir = downloadDir;
        checkDownloadFileExists(mDownloadDir);
        return this;
    }

    private void checkDownloadFileExists(File file) {
        mDownloadDir = file;
        if (!mDownloadDir.exists()) {
            mDownloadDir.mkdir();
        }
    }

    public QuietConfigs setDebug(boolean debug) {
        isDebug = debug;
        return this;
    }

    public int getMinOperateInterval() {
        return mMinOperateInterval;
    }

    public QuietConfigs setMinOperateInterval(int minOperateInterval) {
        this.mMinOperateInterval = minOperateInterval;
        return this;
    }

    public boolean isRecoverDownloadWhenStart() {
        return mRecoverDownloadWhenStart;
    }

    public QuietConfigs setRecoverDownloadWhenStart(boolean recoverDownloadWhenStart) {
        this.mRecoverDownloadWhenStart = recoverDownloadWhenStart;
        return this;
    }

    public int getMaxRetryCount() {
        return mMaxRetryCount;
    }

    public QuietConfigs setMaxRetryCount(int maxRetryCount) {
        this.mMaxRetryCount = maxRetryCount;
        return this;
    }

    public File getDownloadFile(String name) {
        return new File(mDownloadDir, name);
    }

    public void setEventIntercepts(ArrayList<EventIntercept> list) {
        mEventIntercepts.addAll(list);
    }

    public void setEventIntercepts(EventIntercept intercept) {
        mEventIntercepts.add(intercept);
    }

    public ArrayList<EventIntercept> getEventIntercepts() {
        return mEventIntercepts;
    }


    private HandlerNetworkListener mHandlerNetworkListener;

    public HandlerNetworkListener getHandlerNetworkListener() {
        return mHandlerNetworkListener;
    }

    /**
     * @see HandlerNetworkListener 处理网络情况
     */
    public QuietConfigs setHandlerNetworkListener(HandlerNetworkListener handlerNetworkListenerListener) {
        this.mHandlerNetworkListener = handlerNetworkListenerListener;
        return this;
    }

    private int mConnTimeMillis = 30 * 1000;
    private int mReadTimeoutMillis = 30 * 1000;

    public int getConnTimeMillis() {
        return mConnTimeMillis;
    }

    public QuietConfigs setConnTimeMillis(int connTimeMillis) {
        mConnTimeMillis = connTimeMillis;
        return this;
    }

    public int getReadTimeoutMillis() {
        return mReadTimeoutMillis;
    }

    public QuietConfigs setReadTimeoutMillis(int readTimeoutMillis) {
        mReadTimeoutMillis = readTimeoutMillis;
        return this;
    }


    public interface HandlerNetworkListener {
        /**
         * 处理网络状况接口
         *
         * @return true:  消费该事件终止运行下载任务
         * false: 正常执行下载任务
         */
        boolean onHandlerNetworkStatus();
    }

}
