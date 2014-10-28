package kr.opid.wisay.contorl;

import java.util.ArrayList;

import kr.opid.wisay.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class CategoryAdapter extends ArrayAdapter<String> {
	private ArrayList<String> items;
	private Context ct = null;

	public CategoryAdapter(Context context, int textViewResourceId,
			ArrayList<String> items) {
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
			v = vi.inflate(R.layout.listview_category, null);
		}
		String p = items.get(position);
		if (p != null) {
			TextView name = (TextView) v.findViewById(R.id.category_name);
			TextView count = (TextView) v.findViewById(R.id.category_count);

			if (name != null) {
				name.setText(p);
			}
			if (count != null) {
				count.setText(" :)");
			}
		}
		return v;
	}
}
