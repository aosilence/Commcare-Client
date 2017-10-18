package commcare.capstone.comcare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.HouseVisit;

import static android.R.attr.data;
import static commcare.capstone.comcare.R.id.fullNameET;


public class HVDetailsActivity extends BaseActivity {
	Logger LOG = LoggerFactory.getLogger(HVDetailsActivity.class);

	HouseVisit selectedHV;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hvdetails);
		setupPopupMenu();
		setupButtons();
		setupDisplay();
	}

	private void setupDisplay() {
		HouseVisit hc = DataBiz.getInstance().getSelectedHV();
		TextView createdTV = (TextView) findViewById(R.id.createdTV);
		TextView resTV = (TextView) findViewById(R.id.resTV);
		TextView nricTV = (TextView) findViewById(R.id.nricTV);
		TextView ctTV = (TextView) findViewById(R.id.ctTV);
		TextView addressTV = (TextView) findViewById(R.id.addressTV);
		TextView assignTV = (TextView) findViewById(R.id.assignTV);
		TextView statusTV = (TextView) findViewById(R.id.statusTV);

		createdTV.setText(new SimpleDateFormat("dd/MMM/yyyy").format(hc.getDateCreated())+"");
		resTV.setText(hc.getResident().getFullName());
		nricTV.setText(hc.getResident().getNric());
		ctTV.setText(hc.getResident().getCtcNumber());
		addressTV.setText(hc.getResident().getAddress());
		assignTV.setText(hc.getCreatedBy().getFullName());
		statusTV.setText(hc.getStatus());
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HVDetailsActivity.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HVDetailsActivity.this, MainActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(HVDetailsActivity.this, DataCollectionFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		selectedHV = DataBiz.getInstance().getSelectedHV();
	}




}
