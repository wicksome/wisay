package kr.opid.wisay.model;

import java.io.IOException;
import java.util.ArrayList;

import kr.opid.wisay.Item;
import kr.opid.wisay.Lang;
import kr.opid.wisay.contorl.Setting;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class OpenItemDB {

	// ArrayList<Item> dataList = new ArrayList<Item>();
	private Cursor cursor;
	private SQLiteDatabase db;

	public OpenItemDB(Context context) {

		ItemDBHelper myDbHelper = new ItemDBHelper(context);

		try {
			myDbHelper.createDataBase();
			myDbHelper.openDataBase();
		} catch (IOException e) {
			e.printStackTrace();
			throw new Error("Unable to create database");
		} catch (SQLException sqle) {
			throw sqle;

		}

		cursor = null;
		db = myDbHelper.getWritableDatabase();

	}

	public ArrayList<Item> getDBAllItemList() {
		ArrayList<Item> tempList = new ArrayList<Item>();

		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:

			cursor = db.rawQuery(
					"SELECT category.eng category_eng, category.kor category_kor, "
							+ "content.content_eng, content.person_eng, "
							+ "content.content_kor, content.person_kor "
							+ "From content INNER JOIN category "
							+ "ON content.category_id = category.category_id "
							+ "order by category_kor asc;", null);
			break;
		case Lang.ENG:

			cursor = db.rawQuery(
					"SELECT category.eng category_eng, category.kor category_kor, "
							+ "content.content_eng, content.person_eng, "
							+ "content.content_kor, content.person_kor "
							+ "From content INNER JOIN category "
							+ "ON content.category_id = category.category_id "
							+ "order by category_eng asc;", null);
			break;
		}

		Item addItem;
		while (cursor.moveToNext()) {
			addItem = new Item(cursor.getString(cursor
					.getColumnIndex("category_eng")), cursor.getString(cursor
					.getColumnIndex("category_kor")), cursor.getString(cursor
					.getColumnIndex("content_eng")), cursor.getString(cursor
					.getColumnIndex("person_eng")), cursor.getString(cursor
					.getColumnIndex("content_kor")), cursor.getString(cursor
					.getColumnIndex("person_kor")));
			tempList.add(addItem);

		}

		return tempList;
	}

	public ArrayList<String> getDBCategoryList() {
		ArrayList<String> tempList = new ArrayList<String>();

		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:
			cursor = db.rawQuery("SELECT kor from category ORDER BY kor asc;",
					null);
			break;
		case Lang.ENG:
			cursor = db.rawQuery("SELECT eng from category ORDER BY eng asc;",
					null);
			break;
		}

		String addData;

		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:
			while (cursor.moveToNext()) {
				addData = new String(cursor.getString(cursor
						.getColumnIndex("kor")));
				tempList.add(addData);
			}
			break;
		case Lang.ENG:
			while (cursor.moveToNext()) {
				addData = new String(cursor.getString(cursor
						.getColumnIndex("eng")));
				tempList.add(addData);
			}
			break;
		}

		return tempList;
	}

	public ArrayList<Item> getDBSelectedCategory(String subject) {
		ArrayList<Item> tempList = new ArrayList<Item>();

		switch (Setting.getCurrentLanguage()) {
		case Lang.KOR:

			cursor = db.rawQuery(
					"SELECT category.eng category_eng, category.kor category_kor, "
							+ "content.content_eng, content.person_eng, "
							+ "content.content_kor, content.person_kor "
							+ "From content INNER JOIN category "
							+ "ON content.category_id = category.category_id "
							+ "WHERE category.kor = '" + subject + "' "
							+ "order by content_kor asc;", null);
			break;
		case Lang.ENG:

			cursor = db.rawQuery(
					"SELECT category.eng category_eng, category.kor category_kor, "
							+ "content.content_eng, content.person_eng, "
							+ "content.content_kor, content.person_kor "
							+ "From content INNER JOIN category "
							+ "ON content.category_id = category.category_id "
							+ "WHERE category.eng = '" + subject + "' "
							+ "order by content_eng asc;", null);
			break;
		}

		Item addItem;
		while (cursor.moveToNext()) {
			addItem = new Item(cursor.getString(cursor
					.getColumnIndex("category_eng")), cursor.getString(cursor
					.getColumnIndex("category_kor")), cursor.getString(cursor
					.getColumnIndex("content_eng")), cursor.getString(cursor
					.getColumnIndex("person_eng")), cursor.getString(cursor
					.getColumnIndex("content_kor")), cursor.getString(cursor
					.getColumnIndex("person_kor")));
			tempList.add(addItem);

		}

		return tempList;
	}
}
