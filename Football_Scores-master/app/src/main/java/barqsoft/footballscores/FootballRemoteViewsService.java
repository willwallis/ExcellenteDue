package barqsoft.footballscores;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import java.lang.annotation.Target;
import java.util.concurrent.ExecutionException;

/**
 * Created by willwallis on 2/7/16.
 */
public class FootballRemoteViewsService extends RemoteViewsService {
    public final String LOG_TAG = FootballRemoteViewsService.class.getSimpleName();

    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        // return a new instance of the remote view factory
        return new FootballWidgetDataProvider(this, intent);
    }
}

