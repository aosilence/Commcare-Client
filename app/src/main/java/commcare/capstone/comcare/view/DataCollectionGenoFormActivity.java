package commcare.capstone.comcare.view;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.datacollection.GenogramObj;


public class DataCollectionGenoFormActivity extends ListActivity
{
	Logger LOG = LoggerFactory.getLogger(DataCollectionGenoFormActivity.class);

	public PopupWindow popupMenu;

	MySimpleArrayAdapter adapter;
	ArrayList<GenogramObj> datas;
	Activity context;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_geno);
		context = this;
		setupPopupMenu();
		setupButtons();
		LOG.info("TasksActivity Created");
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionGenoFormActivity.this, DataCollectionFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionGenoFormActivity.this, DataCollectionFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
		Button centerBtn = (Button) findViewById(R.id.centerBtn);
		centerBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				GenoDrawDialog dialog = new GenoDrawDialog(DataCollectionGenoFormActivity.this, datas, true);
				dialog.show();
				Window window = dialog.getWindow();
				window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			}
		});


		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				GenoDrawDialog dialog = new GenoDrawDialog(DataCollectionGenoFormActivity.this, datas, false);
				dialog.show();
				Window window = dialog.getWindow();
				window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
			}
		});
	}
	protected void setupPopupMenu()
	{
		ImageView view = (ImageView) findViewById(R.id.menu);
		view.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showStatusPopup(DataCollectionGenoFormActivity.this);
			}
		});
	}

	public void showStatusPopup(final Activity context) {
		LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View layout = layoutInflater.inflate(R.layout.popup_menu, null);

		popupMenu = new PopupWindow(context);
		popupMenu.setContentView(layout);
		popupMenu.setWidth(LinearLayout.LayoutParams.WRAP_CONTENT);
		popupMenu.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
		popupMenu.setFocusable(true);


		Button logoutButton = (Button) layout.findViewById(R.id.logoutButton);
		logoutButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				DataBiz.getInstance().logout();
				Intent intent = new Intent(context, LoginActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				popupMenu.dismiss();
			}
		});

		Button homeButton = (Button) layout.findViewById(R.id.homeButton);
		homeButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(context, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
				popupMenu.dismiss();
			}
		});
		popupMenu.setBackgroundDrawable(new BitmapDrawable());

		popupMenu.showAtLocation(layout, Gravity.CENTER, 0, 0);
	}

	@Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		GenoDialog dialog = new GenoDialog(DataCollectionGenoFormActivity.this, datas.get(position));
		dialog.show();
	}
	public void updateUI()
	{
		datas = DataBiz.getInstance().getSelectedHV().getDataCollectionForm().getGenos();
		adapter = new MySimpleArrayAdapter(this, datas);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();
	}

	@Override
	protected void onResume() {
		super.onResume();

		datas = DataBiz.getInstance().getSelectedHV().getDataCollectionForm().getGenos();
		LOG.debug("datas size = "+datas.size());
		adapter = new MySimpleArrayAdapter(this, datas);
		setListAdapter(adapter);
		adapter.notifyDataSetChanged();

	}

	@Override
	public void onDestroy() {
		LOG.info("DESTROYED");
		super.onDestroy();

	}
	@Override
	public void onStop() {
		LOG.info("STOPPED");
		super.onStop();
	}

	@Override
	protected void onPause() {
		LOG.error("onPause");
		super.onPause();
	}

	private String shortenText(String displayName) {
		if (displayName.length() > 15)
		{
			return displayName.substring(0,15)+"...";
		}
		return displayName;
	}

	public class MySimpleArrayAdapter extends ArrayAdapter<GenogramObj> {
		private final Context context;
		private final ArrayList<GenogramObj> values;

		public MySimpleArrayAdapter(Context context, ArrayList<GenogramObj> datas) {
		super(context, R.layout.row, datas);
		this.context = context;
		this.values = datas;
		}

        @Override
        public void notifyDataSetChanged() {
            this.setNotifyOnChange(false);
            this.setNotifyOnChange(true);
            super.notifyDataSetChanged();
        }
		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(R.layout.geno_row, parent, false);
			TextView relatedTo = (TextView) rowView.findViewById(R.id.row_related_to);
			TextView relationship = (TextView) rowView.findViewById(R.id.row_relationship);
			TextView name = (TextView) rowView.findViewById(R.id.row_name);
			TextView age = (TextView) rowView.findViewById(R.id.row_age);
			TextView occupation = (TextView) rowView.findViewById(R.id.row_occupation);
			TextView salary = (TextView) rowView.findViewById(R.id.row_salary);
			TextView illness = (TextView) rowView.findViewById(R.id.row_illness);
			TextView sex = (TextView) rowView.findViewById(R.id.row_sex);
			GenogramObj data = values.get(position);

			if (data.getRelateTo() == null)
			{

			}
			else {
				relatedTo.setText(data.getRelateTo().getFullName());
			}
			relationship.setText(data.getRelation());
			name.setText(data.getFullName());
			age.setText(data.getAge());
			occupation.setText(data.getOccupation());
			salary.setText(data.getSalary());
			illness.setText(data.getIllness());
			sex.setText(data.getSex());
			return rowView;
		}


	}

	public void toast(String msg) {
		final String mesg = msg;
		runOnUiThread (new Thread(new Runnable() {
			public void run() {
				Toast.makeText(getApplicationContext(), mesg, Toast.LENGTH_SHORT).show();
			}
		}));
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			//preventing default implementation previous to android.os.Build.VERSION_CODES.ECLAIR
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
}
