package kr.opid.wisay.contorl;

import java.util.ArrayList;

import kr.opid.wisay.Item;
import kr.opid.wisay.Lang;
import kr.opid.wisay.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<Item> {
	private ArrayList<Item> items;
	private Context ct = null;

	public ItemAdapter(Context context, int textViewResourceId,
			ArrayList<Item> items) {
		super(context, textViewResourceId, items);
		this.ct = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) ct
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.listview_item, null);
		}
		Item p = items.get(position);
		if (p != null) {
			TextView content = (TextView) v.findViewById(R.id.content);
			TextView person = (TextView) v.findViewById(R.id.person);
			TextView category = (TextView) v.findViewById(R.id.category);

			if (content != null) {
				content.setText(p.getContent());
			}
			if (person != null) {
				person.setText(p.getPerson());
			}
			if (category != null) {
				category.setText(p.getCategory());
			}
		}
		return v;
	}
}
