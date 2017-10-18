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

import static commcare.capstone.comcare.R.id.agencyCB;
import static commcare.capstone.comcare.R.id.agencyET;
import static commcare.capstone.comcare.R.id.amobilityCB;
import static commcare.capstone.comcare.R.id.amobilityET;
import static commcare.capstone.comcare.R.id.familyCB;
import static commcare.capstone.comcare.R.id.familyET;
import static commcare.capstone.comcare.R.id.fundET;
import static commcare.capstone.comcare.R.id.hearingCB;
import static commcare.capstone.comcare.R.id.hearingET;
import static commcare.capstone.comcare.R.id.help3CB;
import static commcare.capstone.comcare.R.id.help3ET;
import static commcare.capstone.comcare.R.id.highmedCB;
import static commcare.capstone.comcare.R.id.highmedET;
import static commcare.capstone.comcare.R.id.lchildCB;
import static commcare.capstone.comcare.R.id.lchildET;
import static commcare.capstone.comcare.R.id.lelderlyCB;
import static commcare.capstone.comcare.R.id.lelderlyET;
import static commcare.capstone.comcare.R.id.othersCB;
import static commcare.capstone.comcare.R.id.othersET;
import static commcare.capstone.comcare.R.id.smobilityCB;
import static commcare.capstone.comcare.R.id.smobilityET;
import static commcare.capstone.comcare.R.id.ssoCB;
import static commcare.capstone.comcare.R.id.ssoET;
import static commcare.capstone.comcare.R.id.unemploymentCB;
import static commcare.capstone.comcare.R.id.unemploymentET;
import static commcare.capstone.comcare.R.id.unfitCB;
import static commcare.capstone.comcare.R.id.unfitET;
import static commcare.capstone.comcare.R.id.visualCB;
import static commcare.capstone.comcare.R.id.visualET;


public class DataCollectionFinancialFormActivity extends BaseActivity {
	Logger LOG = LoggerFactory.getLogger(DataCollectionFinancialFormActivity.class);

	HouseVisit hv;

	CheckBox unemploymentCB;
	EditText unemploymentET;
	CheckBox unfitCB;
	EditText unfitET;
	CheckBox familyCB;
	EditText familyET;
	CheckBox lelderlyCB;
	EditText lelderlyET;
	CheckBox lchildCB;
	EditText lchildET;
	CheckBox ssoCB;
	EditText ssoET;
	CheckBox agencyCB;
	EditText agencyET;
	CheckBox fundCB;
	EditText fundET;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollectionfinancialissues);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		unemploymentCB = (CheckBox) findViewById(R.id.unemploymentCB);
		unemploymentET = (EditText) findViewById(R.id.unemploymentET);
		unfitCB = (CheckBox) findViewById(R.id.unfitCB);
		unfitET = (EditText) findViewById(R.id.unfitET);
		familyCB = (CheckBox) findViewById(R.id.familyCB);
		familyET = (EditText) findViewById(R.id.familyET);
		lelderlyCB = (CheckBox) findViewById(R.id.lelderlyCB);
		lelderlyET = (EditText) findViewById(R.id.lelderlyET);
		lchildCB = (CheckBox) findViewById(R.id.lchildCB);
		lchildET = (EditText) findViewById(R.id.lchildET);
		ssoCB = (CheckBox) findViewById(R.id.ssoCB);
		ssoET = (EditText) findViewById(R.id.ssoET);
		agencyCB = (CheckBox) findViewById(R.id.agencyCB);
		agencyET = (EditText) findViewById(R.id.agencyET);
		fundCB = (CheckBox) findViewById(R.id.fundCB);
		fundET = (EditText) findViewById(R.id.fundET);
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionFinancialFormActivity.this, DataCollectionHealthFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionFinancialFormActivity.this, DataCollectionHealthFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResult();
				Intent intent = new Intent(DataCollectionFinancialFormActivity.this, DataCollectionOtherInfoFormActivity.class);
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
		hv.getDataCollectionForm().getIssues().setUnemployement(unemploymentCB.isChecked());
		hv.getDataCollectionForm().getIssues().setUnemployementMore(unemploymentET.getText().toString());
		hv.getDataCollectionForm().getIssues().setUnfit(unfitCB.isChecked());
		hv.getDataCollectionForm().getIssues().setUnfitMore(unfitET.getText().toString());
		hv.getDataCollectionForm().getIssues().setFamilyIllness(familyCB.isChecked());
		hv.getDataCollectionForm().getIssues().setFamilyIllnessMore(familyET.getText().toString());
		hv.getDataCollectionForm().getIssues().setLargeWithElder(lelderlyCB.isChecked());
		hv.getDataCollectionForm().getIssues().setLargeWithElderMore(lelderlyET.getText().toString());
		hv.getDataCollectionForm().getIssues().setLargeWithChild(lchildCB.isChecked());
		hv.getDataCollectionForm().getIssues().setLargeWithChildMore(lchildET.getText().toString());
		hv.getDataCollectionForm().getIssues().setSso(ssoCB.isChecked());
		hv.getDataCollectionForm().getIssues().setSsoMore(ssoET.getText().toString());
		hv.getDataCollectionForm().getIssues().setAgency(agencyCB.isChecked());
		hv.getDataCollectionForm().getIssues().setAgencyMore(agencyET.getText().toString());
		hv.getDataCollectionForm().getIssues().setSchoolFund(fundCB.isChecked());
		hv.getDataCollectionForm().getIssues().setSchoolFundMore(fundET.getText().toString());
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
			unemploymentCB.setChecked(hv.getDataCollectionForm().getIssues().isUnemployement());
			unemploymentET.setText(hv.getDataCollectionForm().getIssues().getUnemployementMore());
			unfitCB.setChecked(hv.getDataCollectionForm().getIssues().isUnfit());
			unfitET.setText(hv.getDataCollectionForm().getIssues().getUnfitMore());
			familyCB.setChecked(hv.getDataCollectionForm().getIssues().isFamilyIllness());
			familyET.setText(hv.getDataCollectionForm().getIssues().getFamilyIllnessMore());
			lelderlyCB.setChecked(hv.getDataCollectionForm().getIssues().isLargeWithElder());
			lelderlyET.setText(hv.getDataCollectionForm().getIssues().getLargeWithElderMore());
			lchildCB.setChecked(hv.getDataCollectionForm().getIssues().isLargeWithChild());
			lchildET.setText(hv.getDataCollectionForm().getIssues().getLargeWithChildMore());
			ssoCB.setChecked(hv.getDataCollectionForm().getIssues().isSso());
			ssoET.setText(hv.getDataCollectionForm().getIssues().getSsoMore());
			agencyCB.setChecked(hv.getDataCollectionForm().getIssues().isAgency());
			agencyET.setText(hv.getDataCollectionForm().getIssues().getAgencyMore());
			fundCB.setChecked(hv.getDataCollectionForm().getIssues().isSchoolFund());
			fundET.setText(hv.getDataCollectionForm().getIssues().getSchoolFundMore());

		}
	}




}
