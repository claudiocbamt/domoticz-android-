/*
 * Copyright (C) 2015 Domoticz - Mark Heinis
 *
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */

package nl.hnogames.domoticzapi.Parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import nl.hnogames.domoticzapi.Containers.ExtendedStatusInfo;
import nl.hnogames.domoticzapi.Interfaces.JSONParserInterface;
import nl.hnogames.domoticzapi.Interfaces.StatusReceiver;

public class StatusInfoParser implements JSONParserInterface {

    private static final String TAG = StatusInfoParser.class.getSimpleName();
    private StatusReceiver statusReceiver;

    public StatusInfoParser(StatusReceiver receiver) {
        this.statusReceiver = receiver;
    }

    @Override
    public void parseResult(String result) {
        // Change the result data here so the view class gets the ready data

        try {

            JSONArray jsonArray = new JSONArray(result);
            JSONObject jsonObject = jsonArray.getJSONObject(0);

            statusReceiver.onReceiveStatus(new ExtendedStatusInfo(jsonObject));

        } catch (JSONException error) {
            Log.d(TAG, "StatusInfoParser onError");
            statusReceiver.onError(error);
        }
    }

    @Override
    public void onError(Exception error) {
        Log.d(TAG, "StatusInfoParser onError");
        statusReceiver.onError(error);
    }
}