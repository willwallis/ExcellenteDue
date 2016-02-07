package barqsoft.footballscores;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.RemoteViews;

import barqsoft.footballscores.MainActivity;
import barqsoft.footballscores.R;

/**
 * Created by willwallis on 2/6/16.
 */
public class FootballAppWidgetProvider extends AppWidgetProvider {

    public static final String ACTION_DATA_UPLOADED = "barqsoft.footballscores.app.ACTION_DATA_UPLOADED";
    public static String LOG_TAG = "Widget Provider";


    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        context.startService(new Intent(context, FootballWidgetIntentService.class));
        Log.v(LOG_TAG, "onUpdate");
    }

 //   @Override
 //   public void onAppWidgetOptionsChanged(Context context, AppWidgetManager appWidgetManager,
 //                                         int appWidgetId, Bundle newOptions) {
 //       context.startService(new Intent(context, TodayWidgetIntentService.class));
 //   }

    @Override
    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        Log.v(LOG_TAG, "onReceive");
        super.onReceive(context, intent);
        if (ACTION_DATA_UPLOADED.equals(intent.getAction())) {
            context.startService(new Intent(context, FootballWidgetIntentService.class));
        }
    }

//    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
//        final int N = appWidgetIds.length;
//
//        // Perform this loop procedure for each App Widget that belongs to this provider
//        for (int i=0; i<N; i++) {
//            int appWidgetId = appWidgetIds[i];
//
//            // Create an Intent to launch ExampleActivity
//            Intent intent = new Intent(context, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
//
//            // Get the layout for the App Widget and attach an on-click listener
//            // to the widget
//            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_main);
//            views.setOnClickPendingIntent(R.id.widget, pendingIntent);
//
//            // Tell the AppWidgetManager to perform an update on the current app widget
//            appWidgetManager.updateAppWidget(appWidgetId, views);
//        }

}