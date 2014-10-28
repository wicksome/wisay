package kr.opid.wisay.view;

import kr.opid.wisay.R;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.text.Html;
import android.util.Log;

public class SettingActivity extends PreferenceActivity {
	private final String TAG = "SETTIGN";
	private String appVersion;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.setting);

		// 어플 버전 가져오기
		PackageInfo pi = null;
		try {
			pi = getPackageManager().getPackageInfo(getPackageName(), 0);
		} catch (NameNotFoundException e) {
			Log.e(TAG, e.getMessage());
		}
		appVersion = pi.versionName;

		// 어플 정보
		Preference appInfo = (Preference) findPreference("setting_activity_app_info");
		appInfo.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				AlertDialog.Builder dlg = new AlertDialog.Builder(
						SettingActivity.this);
				dlg.setMessage(Html.fromHtml("카테고리와 맞지않은 명언이 있다면 메일 부탁드립니다."
						+ "<br>" + "opid911@gmail.com" + "<br>" + "version : "
						+ appVersion));
				dlg.setPositiveButton("닫기", null);
				dlg.show();
				return false;
			}
		});

		// 어플 한글,영어 변경
		Preference settingLanguage = (Preference) findPreference("setting_activity_language");
		settingLanguage
				.setOnPreferenceClickListener(new OnPreferenceClickListener() {
					private int temp;

					SharedPreferences settingPref = getSharedPreferences(
							"SettingPref", 0);
					SharedPreferences.Editor edit = settingPref.edit();

					@Override
					public boolean onPreferenceClick(Preference preference) {
						int currentLange = settingPref.getInt("Language", 0);

						String items[] = { "kor", "eng" };
						temp = 0;

						AlertDialog.Builder dlg = new AlertDialog.Builder(
								SettingActivity.this);
						dlg.setSingleChoiceItems(items, currentLange,
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										switch (which) {
										case 0:
											Log.v("setting",
													"select language = kor");
											temp = which;
											break;
										case 1:
											Log.v("setting",
													"select language = eng");
											temp = which;
											break;
										}
									}
								}).setPositiveButton("변경",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog,
											int which) {
										edit.putInt("Language", temp);
										edit.commit();
									}
								});
						dlg.show();
						return false;
					}
				});

		// 블로그 이동
		Preference blog = (Preference) findPreference("setting_activity_blog");
		blog.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Uri uri = Uri.parse("http://blog.opid.kr");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
				return false;
			}
		});

		// 문의하기
		Preference contact = (Preference) findPreference("setting_activity_contact");
		contact.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Intent it = new Intent(Intent.ACTION_SEND);
				String[] mailaddr = { "opid911@gmail.com" };

				it.setType("plaine/text");
				it.putExtra(Intent.EXTRA_EMAIL, mailaddr);
				it.putExtra(Intent.EXTRA_SUBJECT, "[wisay]");
				it.putExtra(Intent.EXTRA_TEXT, "\n\n" + "v" + appVersion);

				startActivity(it);
				return false;
			}
		});

		// 평가하기
		Preference assess = (Preference) findPreference("setting_activity_app_assess");
		assess.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			@Override
			public boolean onPreferenceClick(Preference preference) {
				Uri uri = Uri
						.parse("https://play.google.com/store/apps/details?id=kr.opid.wisay");
				Intent it = new Intent(Intent.ACTION_VIEW, uri);
				startActivity(it);
				return false;
			}
		});

	}

}
