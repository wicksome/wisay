package kr.opid.wisay.contorl;

import java.util.ArrayList;
import java.util.Random;

import kr.opid.wisay.Item;
import kr.opid.wisay.model.*;
import android.content.Context;

public class ItemContorl {
	private OpenItemDB of;

	private ArrayList<Item> data = null;
	private ArrayList<String> category = null;

	private int size = 0;

	public ItemContorl(Context context) {
		data = new ArrayList<Item>();

		of = new OpenItemDB(context);

		data = of.getDBAllItemList();
		category = of.getDBCategoryList();
		size = data.size();

	}

	public Item getRandomData() {
		Random ran = new Random();
		return data.get(ran.nextInt(size));
	}

	public ArrayList<Item> getAllData() {
		return data;
	}

	public Item getData(int index) {
		return data.get(index);
	}

	public ArrayList<String> getCategory() {
		return category;
	}

	public ArrayList<Item> getSelectedCategoryList(String subject) {
		return of.getDBSelectedCategory(subject);
	}
}
