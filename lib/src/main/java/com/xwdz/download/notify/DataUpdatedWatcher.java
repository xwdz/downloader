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

package com.xwdz.download.notify;


import com.xwdz.download.core.DownloadEntry;

import java.util.Observable;
import java.util.Observer;

/**
 * @author xwdz     (xwdz9989@gmail.com)
 * this class receiver data changed
 */
public abstract class DataUpdatedWatcher implements Observer {
    @Override
    public void update(Observable observable, Object data) {
        if (data instanceof DownloadEntry) {
            notifyUpdate((DownloadEntry) data);
        }
    }

    public abstract void notifyUpdate(DownloadEntry data);
}
