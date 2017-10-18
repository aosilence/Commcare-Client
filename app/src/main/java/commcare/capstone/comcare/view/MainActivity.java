package commcare.capstone.comcare.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.biz.EnviromentBiz;
import commcare.capstone.comcare.model.HouseVisit;


public class MainActivity extends BaseListActivity
{
	Logger LOG = LoggerFactory.getLogger(MainActivity.class);
	private TextView mainHeaderTextView;
	ProgressBar mProgress;
	ImageView refresh;

	MySimpleArrayAdapter adapter;
	ArrayList<HouseVisit> datas;
	Activity context;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
		context = this;
		setupOnCreate();
		setupPopupMenu();
		LOG.info("TasksActivity Created");
	}

//	@Override
//	public void onSaveInstanceState(Bundle savedInstanceState) {
//		savedInstanceState.putSerializable("dataBiz", DataBiz.getInstance());
//		LOG.info("SAVE INSTANCE CALLED");
//		super.onSaveInstanceState(savedInstanceState);
//	}

	@Override
	 protected void onListItemClick(ListView l, View v, int position, long id) {
		Intent intent = new Intent(context, HVDetailsActivity.class);
		DataBiz.getInstance().setSelectedHV((HouseVisit) l.getItemAtPosition(position));
        startActivity(intent);
	}
	@Override
	protected void onResume() {
		super.onResume();
		DataBiz.getInstance().getDb(this);
		if(DataBiz.getInstance().getLoggedIn()!=null)
		{
			setTextInTextView(mainHeaderTextView, "Welcome " + shortenText(DataBiz.getInstance().getLoggedIn().getDisplayName()));
		}
		else
		{
			Intent intent = new Intent(context, LoginActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
			startActivity(intent);
		}
		datas = DataBiz.getInstance().getHVList();
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

	public class MySimpleArrayAdapter extends ArrayAdapter<HouseVisit> {
		private final Context context;
		private final ArrayList<HouseVisit> values;

		public MySimpleArrayAdapter(Context context, ArrayList<HouseVisit> datas) {
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
			View rowView = inflater.inflate(R.layout.task_row, parent, false);
			TextView created = (TextView) rowView.findViewById(R.id.row_created);
			TextView resident = (TextView) rowView.findViewById(R.id.row_resident);
			TextView contact = (TextView) rowView.findViewById(R.id.row_contact);
			TextView address = (TextView) rowView.findViewById(R.id.row_address);
			TextView status = (TextView) rowView.findViewById(R.id.row_status);
			HouseVisit data = values.get(position);

			created.setText(new SimpleDateFormat("dd/MMM/yyyy").format(data.getDateCreated())+"");

			resident.setText(data.getResident().getFullName()+"");
			contact.setText(data.getResident().getCtcNumber()+"");
			address.setText(data.getResident().getAddress());


			if (data.getStatus().equals(data.NEW))
			{
				status.setText("New");
				status.setBackgroundColor(Color.BLUE);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			else if (data.getStatus().equals(data.SUBMITTED))
			{
				status.setText("Submitted");
				status.setBackgroundColor(Color.GRAY);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			else if (data.getStatus().equals(data.REVERTED))
			{
				status.setText("Reverted");
				status.setBackgroundColor(Color.CYAN);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			else if (data.getStatus().equals(data.COMPLETED))
			{
				status.setText("Completion");
				status.setBackgroundColor(Color.GREEN);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			else if (data.getStatus().equals(data.CANCEL))
			{
				status.setText("Cancel");
				status.setBackgroundColor(Color.RED);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			else if (data.getStatus().equals(data.CANCELLED))
			{
				status.setText("Cancelled");
				status.setBackgroundColor(Color.RED);
				status.setTextColor(Color.parseColor("#ffffff"));
			}
			return rowView;
		}


	}

	private void setupOnCreate()
	{
		mainHeaderTextView = (TextView)findViewById(R.id.mainheader_text);
		mProgress = (ProgressBar) findViewById(R.id.progressBar1);

		refresh = (ImageView) findViewById(R.id.refresh);
		refresh.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				showLoadingDialog();
				refresh();
			}
		});

		TextView versionTextview = (TextView)findViewById(R.id.versionTextview);
		if (EnviromentBiz.getInstance().isTest())
		{

			versionTextview.setText("V."+EnviromentBiz.getInstance().getVersion()+ " (T)");
		}
		else if (EnviromentBiz.getInstance().isProduction())
		{
			versionTextview.setText("V."+EnviromentBiz.getInstance().getVersion()+ " (P)");
		}
		else
		{
			versionTextview.setText("V."+EnviromentBiz.getInstance().getVersion()+ " (S)");
		}

	}

	private void refresh() {
		new AsyncTask<Void, Void, String>(){
			@Override
			protected void onPreExecute() {
			};
			@Override
			protected String doInBackground(Void... params) {
				LOG.info("Loading...");
				try
				{
					DataBiz.getInstance().getHouseVisits();
				}
				catch(Exception e)
				{
					toast(e.getMessage());
					LOG.error("LoadActivity LoadError",e);
					e.printStackTrace();
					return "fail";
				}

				return "ok";
			}
			@Override
			protected void onPostExecute(String result)
			{
				if (result.equals("ok"))
				{
					datas = DataBiz.getInstance().getHVList();
					adapter = new MySimpleArrayAdapter(MainActivity.this, datas);
					setListAdapter(adapter);
					adapter.notifyDataSetChanged();
				}
				else
				{
				}
				dismissLoadingDialog();
			}
		}.execute();
	}

	public void showLoadingDialog() {
		mProgress.setVisibility(View.VISIBLE);
		refresh.setVisibility(View.GONE);
	}

	public void dismissLoadingDialog() {
		runOnUiThread (new Thread(new Runnable() {
			public void run() {
				mProgress.setVisibility(View.GONE);
				refresh.setVisibility(View.VISIBLE);
			}
		}));

	}

	public void toast(String msg) {
		final String mesg = msg;
		runOnUiThread (new Thread(new Runnable() {
			public void run() {
				Toast.makeText(getApplicationContext(), mesg, Toast.LENGTH_SHORT).show();
			}
		}));

	}
}
