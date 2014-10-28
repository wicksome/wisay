package kr.opid.wisay.appwidget;

import kr.opid.wisay.Item;
import kr.opid.wisay.R;
import kr.opid.wisay.contorl.ItemContorl;
import kr.opid.wisay.view.MainActivity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

public class WidgetModule extends AppWidgetProvider {
	final static String ACTION_DISPLAY_FULLTIME = "WidgetModule.DisplayFullTime";
	final static String TAG = "widgetModule";

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// super.onUpdate(context, appWidgetManager, appWidgetIds);
		Log.d(TAG, "onUpdate, length = " + appWidgetIds.length + ", id = "
				+ appWidgetIds[0]);
		for (int i = 0; i < appWidgetIds.length; i++) {
			UpdateItem(context, appWidgetManager, appWidgetIds[i]);
		}
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (action != null && action.equals(ACTION_DISPLAY_FULLTIME)) {
			int id = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
					AppWidgetManager.INVALID_APPWIDGET_ID);
			UpdateItem(context, AppWidgetManager.getInstance(context), id);
			Log.d(TAG, "onReceive(CHANGE), id = " + id);
			return;

		}
		super.onReceive(context, intent);
	}

	static void UpdateItem(Context context, AppWidgetManager appWidgetManager,
			int widgetId) {
		RemoteViews views = new RemoteViews(context.getPackageName(),
				R.layout.widget);
		Item item = new ItemContorl(context).getRandomData();

		// 언어설정
		views.setTextViewText(R.id.widget_content, item.getContent_kor());
		views.setTextViewText(R.id.widget_person, item.getPerson_kor());

		Log.d(TAG, "UpdateItem, id = " + widgetId);

		// background click
		Intent intent_background = new Intent(context, MainActivity.class);
		PendingIntent pending_background = PendingIntent.getActivity(context,
				widgetId, intent_background, 0);
		views.setOnClickPendingIntent(R.id.widgetlayout, pending_background);

		// renew click
		Intent intent_renew = new Intent(context, WidgetModule.class);
		intent_renew.setAction(ACTION_DISPLAY_FULLTIME);
		intent_renew.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, widgetId);
		PendingIntent pending_renew = PendingIntent.getBroadcast(context, 0,
				intent_renew, PendingIntent.FLAG_UPDATE_CURRENT);
		views.setOnClickPendingIntent(R.id.widget_btn_renew, pending_renew);

		appWidgetManager.updateAppWidget(widgetId, views);
		// Toast.makeText(context, item.getContent_kor(), 0).show();

		item = null;

	}

	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		Log.d(TAG, "onDeleted");
	}

	@Override
	public void onEnabled(Context context) {
		Log.d(TAG, "onEnabled");
	}

	@Override
	public void onDisabled(Context context) {
		Log.d(TAG, "onDisabled");
	}
}
