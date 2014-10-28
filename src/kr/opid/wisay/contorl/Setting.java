package kr.opid.wisay.contorl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;

public class Setting {
	private static int theCurrentLanguage;
	private static int theWidgetLanguage;
	private Context mContext;
	private final String TAG = "Setting";

	public Setting(Context context) {
		mContext = context;
		SharedPreferences settingPref = mContext.getSharedPreferences(
				"SettingPref", 0);

		theCurrentLanguage = settingPref.getInt("Language", 0);
		theWidgetLanguage = settingPref.getInt("WidgetLanguage", 0);
	}

	public static int getCurrentLanguage() {
		return theCurrentLanguage;
	}

	public static int getWidgetLanguage() {
		return theWidgetLanguage;
	}

	private void versionChk() {
	}

	public boolean isNewVersion() {

		PackageInfo pi = null;
		try {
			pi = mContext.getPackageManager().getPackageInfo(
					mContext.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		int savedVersionCode;
		int deviceVersionCode;
		deviceVersionCode = pi.versionCode;

		return true;
	}
}
