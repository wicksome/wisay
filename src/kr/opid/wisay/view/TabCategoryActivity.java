package kr.opid.wisay.view;

import java.util.ArrayList;

import kr.opid.wisay.R;
import kr.opid.wisay.contorl.BackPressCloseHandler;
import kr.opid.wisay.contorl.ItemContorl;
import kr.opid.wisay.contorl.CategoryAdapter;
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
import android.widget.Toast;

public class TabCategoryActivity extends Activity {
	private BackPressCloseHandler backPressCloseHandler;
	private ArrayList<String> allCategory;

	// tab_allList
	private ListView categoryListView;
	private ItemContorl dataContorl;
	private CategoryAdapter m_adapter;
	private Dialog dlg;
	private View itemDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		backPressCloseHandler = new BackPressCloseHandler(this); // 뒤로 두번 종료

		itemDialog = (View) View.inflate(TabCategoryActivity.this,
				R.layout.item_click_dialog, null);
		dlg = new Dialog(this);
		dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dlg.getWindow().setBackgroundDrawable(
				new ColorDrawable(Color.TRANSPARENT));
		dlg.setContentView(itemDialog);

		setContentView(R.layout.tab_alllist);
		tabCategoryList();
	}

	private void tabCategoryList() {
		dataContorl = new ItemContorl(getApplicationContext());
		allCategory = new ArrayList<String>(dataContorl.getCategory());

		m_adapter = new CategoryAdapter(this, R.layout.listview_category,
				allCategory);

		categoryListView = (ListView) findViewById(R.id.list);
		categoryListView.setAdapter(m_adapter);

		categoryListView
				.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {

						Intent intent = new Intent(getApplicationContext(),
								SelectCategoryActivity.class);
						intent.putExtra("selectedCategory",
								allCategory.get(arg2));
						startActivity(intent);

						Toast.makeText(TabCategoryActivity.this,
								allCategory.get(arg2), Toast.LENGTH_SHORT)
								.show();
					}
				});
	}

	@Override
	public void onBackPressed() {
		backPressCloseHandler.onBackPressed();
	}

}