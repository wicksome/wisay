package kr.opid.wisay.view;

import kr.opid.wisay.Item;
import kr.opid.wisay.R;
import kr.opid.wisay.R.layout;
import kr.opid.wisay.R.menu;
import kr.opid.wisay.contorl.BackPressCloseHandler;
import kr.opid.wisay.contorl.ItemContorl;
import android.os.Bundle;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TabRandomActivity extends Activity {
	private BackPressCloseHandler backPressCloseHandler;

	// tab_random
	private TextView tvRandomContent;
	private TextView tvRandomCategory;
	private TextView tvRandomPerson;
	private ImageButton btn_renew;
	private ItemContorl dataContorl;
	private Item randomItem;
	private Dialog dlg;
	private View itemDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 두번 종료

		setContentView(R.layout.tab_random);

		widgetInit();
		tabRandom();
	}

	// widget Initialization (초기화)
	private void widgetInit() {
		tvRandomContent = (TextView) findViewById(R.id.random_content);
		tvRandomCategory = (TextView) findViewById(R.id.random_category);
		tvRandomPerson = (TextView) findViewById(R.id.random_person);
		btn_renew = (ImageButton) findViewById(R.id.random_imgbtn_renew);

		dataContorl = new ItemContorl(getApplicationContext());
		randomItem = dataContorl.getRandomData();

	}

	private void tabRandom() {
		// 대화상자 설정
		itemDialog = (View) View.inflate(TabRandomActivity.this,
				R.layout.item_click_dialog, null);
		dlg = new Dialog(this);
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);// 타이틀바X
		dlg.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));// 투명
		dlg.setContentView(itemDialog);

		tvRandomContent.setText(randomItem.getContent());
		tvRandomCategory.setText(randomItem.getCategory());
		tvRandomPerson.setText(randomItem.getPerson());

		btn_renew.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				randomItem = dataContorl.getRandomData();

				tvRandomContent.setText(randomItem.getContent());
				tvRandomCategory.setText(randomItem.getCategory());
				tvRandomPerson.setText(randomItem.getPerson());
			}
		});
		// ////////////////////////////////////////

		// 랜덤 명언 클릭이벤트리스너
		tvRandomContent.setOnTouchListener(new View.OnTouchListener() {
			public boolean onTouch(View v, MotionEvent event) {
				TextView dlg_category_eng, dlg_category_kor;
				TextView dlg_person_eng, dlg_person_kor;
				TextView dlg_content_eng, dlg_content_kor;

				dlg_category_eng = (TextView) itemDialog
						.findViewById(R.id.dlg_category_eng);
				dlg_category_kor = (TextView) itemDialog
						.findViewById(R.id.dlg_category_kor);
				dlg_person_eng = (TextView) itemDialog
						.findViewById(R.id.dlg_person_eng);
				dlg_person_kor = (TextView) itemDialog
						.findViewById(R.id.dlg_person_kor);
				dlg_content_eng = (TextView) itemDialog
						.findViewById(R.id.dlg_content_eng);
				dlg_content_kor = (TextView) itemDialog
						.findViewById(R.id.dlg_content_kor);

				dlg_category_eng.setText(randomItem.getCategory_eng());
				dlg_category_kor.setText(randomItem.getCategory_kor());
				dlg_person_eng.setText(randomItem.getPerson_eng());
				dlg_person_kor.setText(randomItem.getPerson_kor());
				dlg_content_eng.setText(randomItem.getContent_eng());
				dlg_content_kor.setText(randomItem.getContent_kor());

				dlg.show();
				return false;
			}
		});
	}

	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}

}
