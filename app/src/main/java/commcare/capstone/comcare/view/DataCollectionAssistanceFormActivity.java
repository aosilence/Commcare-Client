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
import commcare.capstone.comcare.model.datacollection.Assistances;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;
import commcare.capstone.comcare.model.datacollection.Issues;
import commcare.capstone.comcare.util.PDFGenerator;

import static commcare.capstone.comcare.R.id.othersCB;
import static commcare.capstone.comcare.R.id.smobilityCB;
import static commcare.capstone.comcare.R.id.smobilityET;
import static commcare.capstone.comcare.R.id.unemploymentCB;


public class DataCollectionAssistanceFormActivity extends BaseActivity {
	Logger LOG = LoggerFactory.getLogger(DataCollectionAssistanceFormActivity.class);

	HouseVisit hv;

	CheckBox mowCB;
	CheckBox tingkatCB;
	CheckBox homeFixCB;
	CheckBox seniorCB;
	CheckBox nwcdcCB;
	CheckBox utilitiesCB;
	CheckBox mobilityCB;
	CheckBox foreignCB;
	CheckBox longTermCB;
	CheckBox shortTermCB;
	EditText othersET;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollectionassistance);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		mowCB = (CheckBox) findViewById(R.id.mowCB);
		tingkatCB = (CheckBox) findViewById(R.id.tingkatCB);
		homeFixCB = (CheckBox) findViewById(R.id.homeFixCB);
		seniorCB = (CheckBox) findViewById(R.id.seniorCB);
		nwcdcCB = (CheckBox) findViewById(R.id.nwcdcCB);
		utilitiesCB = (CheckBox) findViewById(R.id.utilitiesCB);
		mobilityCB = (CheckBox) findViewById(R.id.mobilityCB);
		foreignCB = (CheckBox) findViewById(R.id.foreignCB);
		longTermCB = (CheckBox) findViewById(R.id.longTermCB);
		shortTermCB = (CheckBox) findViewById(R.id.shortTermCB);
		othersET = (EditText) findViewById(R.id.othersET);
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionAssistanceFormActivity.this, DataCollectionOtherInfoFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionAssistanceFormActivity.this, DataCollectionOtherInfoFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResult();
				Intent intent = new Intent(DataCollectionAssistanceFormActivity.this, MainActivity.class);
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
		if(hv.getDataCollectionForm().getAssistances() == null)
		{
			hv.getDataCollectionForm().setAssistances(new Assistances());
		}
		hv.getDataCollectionForm().getAssistances().setWheels(mowCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setTingkat(tingkatCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setHomeFix(homeFixCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setBefriend(seniorCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setFood(nwcdcCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setUtility(utilitiesCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setMobility(mobilityCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setGrant(foreignCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setLongTerm(longTermCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setShortTerm(shortTermCB.isChecked());
		hv.getDataCollectionForm().getAssistances().setOtherAssistance(othersET.getText().toString());

		DataBiz.getInstance().getDb().getResidentRuntimeDAO().createOrUpdate(hv.getResident());
		DataBiz.getInstance().getDb().getAssistancesRuntimeDAO().createOrUpdate(hv.getDataCollectionForm().getAssistances());
		DataBiz.getInstance().getDb().getIssuesRuntimeDAO().createOrUpdate(hv.getDataCollectionForm().getIssues());
		DataBiz.getInstance().getDb().getDataCollectionFormRuntimeDAO().createOrUpdate(hv.getDataCollectionForm());
		DataBiz.getInstance().getDb().getHouseVisitRuntimeDAO().createOrUpdate(hv);try
		{
			PDFGenerator.generateDataCollectionPDF(this, hv);
		}
		catch (Exception e)
		{
			LOG.error("PDF Generate Error ", e);
		}

	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hv = DataBiz.getInstance().getSelectedHV();
		if (hv.getDataCollectionForm() != null && hv.getDataCollectionForm().getAssistances() != null)
		{
			mowCB.setChecked(hv.getDataCollectionForm().getAssistances().isWheels());
			tingkatCB.setChecked(hv.getDataCollectionForm().getAssistances().isTingkat());
			homeFixCB.setChecked(hv.getDataCollectionForm().getAssistances().isHomeFix());
			seniorCB.setChecked(hv.getDataCollectionForm().getAssistances().isBefriend());
			nwcdcCB.setChecked(hv.getDataCollectionForm().getAssistances().isFood());
			utilitiesCB.setChecked(hv.getDataCollectionForm().getAssistances().isUtility());
			mobilityCB.setChecked(hv.getDataCollectionForm().getAssistances().isMobility());
			foreignCB.setChecked(hv.getDataCollectionForm().getAssistances().isGrant());
			longTermCB.setChecked(hv.getDataCollectionForm().getAssistances().isLongTerm());
			shortTermCB.setChecked(hv.getDataCollectionForm().getAssistances().isShortTerm());
			othersET.setText(hv.getDataCollectionForm().getAssistances().getOtherAssistance());

		}
	}




}
