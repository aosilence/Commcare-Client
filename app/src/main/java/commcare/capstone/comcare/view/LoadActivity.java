package commcare.capstone.comcare.view;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;


public class LoadActivity extends Activity {
	Logger LOG = LoggerFactory.getLogger(LoadActivity.class);
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		load();
	}

	private void load()
	{
		setContentView(R.layout.activity_load);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

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
					LOG.error("LogadActivity LoadError",e);
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
					LOG.info("Loading Done.");
					Intent intent = new Intent(LoadActivity.this, MainActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					LoadActivity.this.startActivity(intent);
				}
				else
				{
					LOG.info("Loading Failed.");
					DataBiz.getInstance().logout();
					Intent intent = new Intent(LoadActivity.this, LoginActivity.class);
					intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
					LoadActivity.this.startActivity(intent);
				}

			}
		}.execute();
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
