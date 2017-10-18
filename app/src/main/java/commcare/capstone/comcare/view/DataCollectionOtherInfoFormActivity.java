package commcare.capstone.comcare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;
import commcare.capstone.comcare.model.datacollection.Issues;



public class DataCollectionOtherInfoFormActivity extends BaseActivity {
	Logger LOG = LoggerFactory.getLogger(DataCollectionOtherInfoFormActivity.class);

	HouseVisit hv;

	EditText otherET;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollectionotherinformation);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		otherET = (EditText) findViewById(R.id.otherET);
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionOtherInfoFormActivity.this, DataCollectionFinancialFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionOtherInfoFormActivity.this, DataCollectionFinancialFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResult();
				Intent intent = new Intent(DataCollectionOtherInfoFormActivity.this, DataCollectionAssistanceFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
	}

	private void saveResult() {
		if(hv.getDataCollectionForm() == null)
		{
			hv.setDataCollectionForm(new DataCollectionForm());
		}
		if(hv.getDataCollectionForm().getIssues() == null)
		{
			hv.getDataCollectionForm().setIssues(new Issues());
		}
		hv.getDataCollectionForm().getIssues().setOtherInformation(otherET.getText().toString());
		DataBiz.getInstance().getDb().getResidentRuntimeDAO().createOrUpdate(hv.getResident());
		DataBiz.getInstance().getDb().getDataCollectionFormRuntimeDAO().createOrUpdate(hv.getDataCollectionForm());
		DataBiz.getInstance().getDb().getIssuesRuntimeDAO().createOrUpdate(hv.getDataCollectionForm().getIssues());
		DataBiz.getInstance().getDb().getHouseVisitRuntimeDAO().createOrUpdate(hv);
	}

	@Override
	protected void onResume() {
		super.onResume();
		hv = DataBiz.getInstance().getSelectedHV();
		if (hv.getDataCollectionForm() != null && hv.getDataCollectionForm().getIssues() != null) {
			otherET.setText(hv.getDataCollectionForm().getIssues().getOtherInformation());

		}
	}
}
