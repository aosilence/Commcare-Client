package commcare.capstone.comcare.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;
import commcare.capstone.comcare.model.datacollection.Issues;

import static commcare.capstone.comcare.R.id.amobilityCB;
import static commcare.capstone.comcare.R.id.amobilityET;
import static commcare.capstone.comcare.R.id.dateET;
import static commcare.capstone.comcare.R.id.financialCB;
import static commcare.capstone.comcare.R.id.hearingCB;
import static commcare.capstone.comcare.R.id.hearingET;
import static commcare.capstone.comcare.R.id.help3CB;
import static commcare.capstone.comcare.R.id.help3ET;
import static commcare.capstone.comcare.R.id.highmedCB;
import static commcare.capstone.comcare.R.id.highmedET;
import static commcare.capstone.comcare.R.id.othersCB;
import static commcare.capstone.comcare.R.id.othersET;
import static commcare.capstone.comcare.R.id.smobilityCB;
import static commcare.capstone.comcare.R.id.smobilityET;
import static commcare.capstone.comcare.R.id.visualCB;
import static commcare.capstone.comcare.R.id.visualET;


public class DataCollectionHealthFormActivity extends BaseActivity {

	Logger LOG = LoggerFactory.getLogger(DataCollectionHealthFormActivity.class);

	HouseVisit hv;
	CheckBox smobilityCB;
	EditText smobilityET;
	CheckBox amobilityCB;
	EditText amobilityET;
	CheckBox visualCB;
	EditText visualET;
	CheckBox hearingCB;
	EditText hearingET;
	CheckBox help3CB;
	EditText help3ET;
	CheckBox highmedCB;
	EditText highmedET;
	CheckBox othersCB;
	EditText othersET;



	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollectionhealthissues);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		smobilityCB = (CheckBox) findViewById(R.id.smobilityCB);
		smobilityET = (EditText) findViewById(R.id.smobilityET);
		amobilityCB = (CheckBox) findViewById(R.id.amobilityCB);
		amobilityET = (EditText) findViewById(R.id.amobilityET);
		visualCB = (CheckBox) findViewById(R.id.visualCB);
		visualET = (EditText) findViewById(R.id.visualET);
		hearingCB = (CheckBox) findViewById(R.id.hearingCB);
		hearingET = (EditText) findViewById(R.id.hearingET);
		help3CB = (CheckBox) findViewById(R.id.help3CB);
		help3ET = (EditText) findViewById(R.id.help3ET);
		highmedCB = (CheckBox) findViewById(R.id.highmedCB);
		highmedET = (EditText) findViewById(R.id.highmedET);
		othersCB = (CheckBox) findViewById(R.id.othersCB);
		othersET = (EditText) findViewById(R.id.othersET);
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionHealthFormActivity.this, DataCollectionIssuesFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionHealthFormActivity.this, DataCollectionIssuesFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResult();
				Intent intent = new Intent(DataCollectionHealthFormActivity.this, DataCollectionFinancialFormActivity.class);
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
		hv.getDataCollectionForm().getIssues().setSenior(smobilityCB.isChecked());
		hv.getDataCollectionForm().getIssues().setSeniorMore(smobilityET.getText().toString());
		hv.getDataCollectionForm().getIssues().setAdult(amobilityCB.isChecked());
		hv.getDataCollectionForm().getIssues().setAdultMore(amobilityET.getText().toString());
		hv.getDataCollectionForm().getIssues().setVisual(visualCB.isChecked());
		hv.getDataCollectionForm().getIssues().setVisualMore(visualET.getText().toString());
		hv.getDataCollectionForm().getIssues().setHearing(hearingCB.isChecked());
		hv.getDataCollectionForm().getIssues().setHearingMore(hearingET.getText().toString());
		hv.getDataCollectionForm().getIssues().setThree(help3CB.isChecked());
		hv.getDataCollectionForm().getIssues().setThreeMore(help3ET.getText().toString());
		hv.getDataCollectionForm().getIssues().setMedical(highmedCB.isChecked());
		hv.getDataCollectionForm().getIssues().setMedicalMore(highmedET.getText().toString());
		hv.getDataCollectionForm().getIssues().setOthers2(othersCB.isChecked());
		hv.getDataCollectionForm().getIssues().setOthers2More(othersET.getText().toString());
		DataBiz.getInstance().getDb().getResidentRuntimeDAO().createOrUpdate(hv.getResident());
		DataBiz.getInstance().getDb().getDataCollectionFormRuntimeDAO().createOrUpdate(hv.getDataCollectionForm());
		DataBiz.getInstance().getDb().getIssuesRuntimeDAO().createOrUpdate(hv.getDataCollectionForm().getIssues());
		DataBiz.getInstance().getDb().getHouseVisitRuntimeDAO().createOrUpdate(hv);

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hv = DataBiz.getInstance().getSelectedHV();
		if (hv.getDataCollectionForm() != null && hv.getDataCollectionForm().getIssues() != null)
		{
			smobilityCB.setChecked(hv.getDataCollectionForm().getIssues().isSenior());
			smobilityET.setText(hv.getDataCollectionForm().getIssues().getSeniorMore());
			amobilityCB.setChecked(hv.getDataCollectionForm().getIssues().isAdult());
			amobilityET.setText(hv.getDataCollectionForm().getIssues().getAdultMore());
			visualCB.setChecked(hv.getDataCollectionForm().getIssues().isVisual());
			visualET.setText(hv.getDataCollectionForm().getIssues().getVisualMore());
			hearingCB.setChecked(hv.getDataCollectionForm().getIssues().isHearing());
			hearingET.setText(hv.getDataCollectionForm().getIssues().getHearingMore());
			help3CB.setChecked(hv.getDataCollectionForm().getIssues().isThree());
			help3ET.setText(hv.getDataCollectionForm().getIssues().getThreeMore());
			highmedCB.setChecked(hv.getDataCollectionForm().getIssues().isMedical());
			highmedET.setText(hv.getDataCollectionForm().getIssues().getMedicalMore());
			othersCB.setChecked(hv.getDataCollectionForm().getIssues().isOthers2());
			othersET.setText(hv.getDataCollectionForm().getIssues().getOthers2More());
		}

	}




}
