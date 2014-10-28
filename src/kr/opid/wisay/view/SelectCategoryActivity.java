package kr.opid.wisay.view;

import java.util.ArrayList;

import kr.opid.wisay.Item;
import kr.opid.wisay.R;
import kr.opid.wisay.contorl.BackPressCloseHandler;
import kr.opid.wisay.contorl.ItemAdapter;
import kr.opid.wisay.contorl.ItemContorl;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

public class SelectCategoryActivity extends Activity {
	private ArrayList<Item> seletedCategoryList;
	private String selectedCategory;

	// tab_allList
	ListView sayingListView;
	ItemContorl dataContorl;
	ItemAdapter m_adapter;
	Item clickItem;
	Dialog dlg;
	View itemDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		itemDialog = (View) View.inflate(SelectCategoryActivity.this,
				R.layout.item_click_dialog, null);
		dlg = new Dialog(this);
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dlg.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dlg.setContentView(itemDialog);

		Intent intent = getIntent();
		selectedCategory = intent.getStringExtra("selectedCategory");

		setContentView(R.layout.select_category);
		setTitle(selectedCategory);


		categoryView();
	}

	private void categoryView() {
		dataContorl = new ItemContorl(getApplicationContext());
		seletedCategoryList = new ArrayList<Item>(
				dataContorl.getSelectedCategoryList(selectedCategory));

		m_adapter = new ItemAdapter(this, R.layout.listview_item,
				seletedCategoryList);

		sayingListView = (ListView) findViewById(R.id.list);
		sayingListView.setAdapter(m_adapter);

		sayingListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						clickItem = new Item();
						clickItem = seletedCategoryList.get(arg2);

						// 다이얼로그, 대화박스
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

						dlg_category_eng.setText(clickItem.getCategory_eng());
						dlg_category_kor.setText(clickItem.getCategory_kor());
						dlg_person_eng.setText(clickItem.getPerson_eng());
						dlg_person_kor.setText(clickItem.getPerson_kor());
						dlg_content_eng.setText(clickItem.getContent_eng());
						dlg_content_kor.setText(clickItem.getContent_kor());

						dlg.show();

					}
				});
	}

}
