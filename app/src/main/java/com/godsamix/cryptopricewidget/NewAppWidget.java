package com.godsamix.cryptopricewidget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Implementation of App Widget functionality.
 */
public class NewAppWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        // Construct the RemoteViews object
        RemoteViews views1 = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        String coingekousd = "https://api.coingecko.com/api/v3/simple/price?ids=0x%2Czcoin%2Cicon%2Cbitcoin%2Cethereum&vs_currencies=usd";

        RequestQueue queue = Volley.newRequestQueue(context);
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, coingekousd, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.w("WidgetExample",
                                "Response => " + response.toString());
                            views1.setTextViewText(R.id.appwidget_text,response.getJSONObject("bitcoin").getString("usd") + " U$D");
                            views1.setTextViewText(R.id.appwidget_text2,response.getJSONObject("ethereum").getString("usd") + " U$D");
                            views1.setTextViewText(R.id.appwidget_text3,response.getJSONObject("zcoin").getString("usd") + " U$D");
                            views1.setTextViewText(R.id.appwidget_text4,response.getJSONObject("0x").getString("usd") + " U$D");
                            views1.setTextViewText(R.id.appwidget_text5,response.getJSONObject("icon").getString("usd") + " U$D");

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        appWidgetManager.updateAppWidget(appWidgetId, views1);
                    }
                }, error -> {
            // TODO Auto-generated method stub
        });
        queue.add(jsObjRequest);




        // open main activity from button
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views1.setOnClickPendingIntent(R.id.openMainButton, pendingIntent);

        // update widget on btn update
        Intent intentUpdate = new Intent(context, NewAppWidget.class);
        intentUpdate.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] idArray = new int[]{appWidgetId};
        intentUpdate.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, idArray);
        PendingIntent pendingUpdate = PendingIntent.getBroadcast(
                context, appWidgetId, intentUpdate,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views1.setOnClickPendingIntent(R.id.button_update, pendingUpdate);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views1);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created

    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled

    }
}