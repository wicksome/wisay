package kr.opid.wisay.view;

import kr.opid.wisay.R;
import kr.opid.wisay.contorl.BackPressCloseHandler;
import kr.opid.wisay.contorl.Setting;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends ActivityGroup {
	private BackPressCloseHandler backPressCloseHandler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 두번 종료

		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE); // 메인 액티비티 타이틀제거

		// 로딩(스플래시) 액티비티
		// startActivity(new Intent(this, SplashActivity.class));

		setContentView(R.layout.main);

		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,
				R.layout.wisay_titlebar);

		// //////////////////////////////////////////////////////////
		Setting setting = new Setting(this.getApplicationContext());
		// //////////////////////////////////////////////////////////
		createTab();
		setting();

	}

	// ////////////////////////////////////////////////////////////
	// tab setting
	private void createTab() {
		TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
		tabHost.setup(getLocalActivityManager());

		// tabSpecAll.setIndicator("ALL",getResources().getDrawable(R.drawable.tab_all));

		tabHost.addTab(tabHost.newTabSpec("RANDOM").setIndicator("RANDOM")
				.setContent(new Intent(this, TabRandomActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("ALL").setIndicator("All")
				.setContent(new Intent(this, TabAllListActivity.class)));
		tabHost.addTab(tabHost.newTabSpec("CATEGORY").setIndicator("CATEGORY")
				.setContent(new Intent(this, TabCategoryActivity.class)));

		// tabHost color change
		for (int cnt = 0; cnt < tabHost.getTabWidget().getChildCount(); cnt++) {
			tabHost.getTabWidget().getChildAt(cnt)
					.setBackgroundColor(Color.parseColor("#ff702C"));
		}

	}

	// 환경설정 버튼
	private void setting() {
		// title setting button
		ImageButton btn_title_setting = (ImageButton) findViewById(R.id.title_btn_setting);
		btn_title_setting.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent intent1 = new Intent(getApplicationContext(),
						SettingActivity.class);
				try {
					startActivity(intent1);
				} catch (android.content.ActivityNotFoundException ex) {
					Toast.makeText(MainActivity.this, "Not Implemented.",
							Toast.LENGTH_SHORT).show();
				}
			}

		});

	}

	// /////////////////////////////////////////////////////
	// menu

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, 1, 0, "wisay 정보");
		menu.add(0, 2, 0, "개발자");

		return true;

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

		case 1:
			AlertDialog.Builder info = new AlertDialog.Builder(
					MainActivity.this);
			info.setTitle("wisay?");
			info.setMessage(Html.fromHtml("WISAY(wise saying)" + "<br>"
					+ "명언위젯"));
			info.setPositiveButton("닫기", null);
			info.show();
			return true;
		case 2:
			AlertDialog.Builder opidInfo = new AlertDialog.Builder(
					MainActivity.this);
			opidInfo.setTitle("who?");
			opidInfo.setMessage(Html.fromHtml("Dongguk University" + "<br>"
					+ "Gyeongju Campus." + "<br>"
					+ "Computer Engineering. Student" + "<br>"
					+ "Kim Yeong Jun"));
			opidInfo.setPositiveButton("닫기", null);
			opidInfo.show();

			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}
}
