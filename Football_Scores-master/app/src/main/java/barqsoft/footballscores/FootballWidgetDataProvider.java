package barqsoft.footballscores;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Binder;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by willwallis on 2/7/16.
 */
public class FootballWidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    Context context;
    Intent intent;

    // Cursor variables
    Cursor data;
    Uri scoreWithDateUri = DatabaseContract.scores_table.buildScoreWithDate();
    private static final String [] SCORES_COLUMNS = {
            DatabaseContract.scores_table.HOME_COL,
            DatabaseContract.scores_table.AWAY_COL,
            DatabaseContract.scores_table.HOME_GOALS_COL,
            DatabaseContract.scores_table.AWAY_GOALS_COL,
            DatabaseContract.scores_table.TIME_COL,
    };

    public static final int COL_HOME = 0;
    public static final int COL_AWAY = 1;
    public static final int COL_HOME_GOALS = 2;
    public static final int COL_AWAY_GOALS = 3;
    public static final int COL_MATCHTIME = 4;


    public FootballWidgetDataProvider(Context context, Intent intent) {
        this.context = context;
        this.intent = intent;
    }

    private void initFootballData() {
        Date systemDate = new Date(System.currentTimeMillis());
        SimpleDateFormat mformat = new SimpleDateFormat("yyyy-MM-dd");
        String todayDate = mformat.format(systemDate);

        final long identityToken = Binder.clearCallingIdentity(); // Allows widget to call internal class

        data = context.getContentResolver().query(scoreWithDateUri,
                SCORES_COLUMNS,
                null,
                new String[] { todayDate },
                DatabaseContract.scores_table.HOME_GOALS_COL + " ASC");
        Binder.restoreCallingIdentity(identityToken); // Resets
    }



    @Override
    public void onCreate() {
        initFootballData();
    }

    @Override
    public void onDataSetChanged() {
        initFootballData();
    }

    @Override
    public void onDestroy() {
        data.close();
    }

    @Override
    public int getCount() {
        return data.getCount();
    }

    // Generates view per list item
    @Override
    public RemoteViews getViewAt(int position) {
        // Get the layout for the App Widget
        RemoteViews view = new RemoteViews(context.getPackageName(), R.layout.widgetsmallitem);

        data.moveToPosition(position);
        view.setTextViewText(R.id.home_name, data.getString(COL_HOME));
        view.setTextViewText(R.id.away_name, data.getString(COL_AWAY));
        view.setTextViewText(R.id.home_score, data.getString(COL_HOME_GOALS));
        view.setTextViewText(R.id.away_score, data.getString(COL_AWAY_GOALS));
        view.setTextViewText(R.id.time, data.getString(COL_MATCHTIME));
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
