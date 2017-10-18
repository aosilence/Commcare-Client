package commcare.capstone.comcare.view;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import commcare.capstone.comcare.R;
import commcare.capstone.comcare.biz.DataBiz;
import commcare.capstone.comcare.model.GenogramObj;
import commcare.capstone.comcare.model.HouseVisit;
import commcare.capstone.comcare.model.datacollection.DataCollectionForm;

import static android.R.attr.x;
import static commcare.capstone.comcare.R.array.race_arrays;


public class DataCollectionFormActivity extends BaseActivity {

	Logger LOG = LoggerFactory.getLogger(DataCollectionFormActivity.class);

	EditText fullNameET;
	EditText nricET;
	RadioButton radioMale;
	RadioButton radioFemale;
	EditText dateET;
	EditText ageET;
	EditText ctET;
	EditText addressET;
	EditText nationalityET;
	Spinner msSpin;
	AutoCompleteTextView raceTV;
	AutoCompleteTextView flatTV;
	AutoCompleteTextView languageTV;
	AutoCompleteTextView religionTV;
	EditText occupationET;
	EditText salaryET;
	Spinner hiSpin;
	EditText a55ET;
	EditText a21ET;
	EditText b20ET;
	AutoCompleteTextView qualificationTV;
	AutoCompleteTextView vehTV;
	EditText illnessesET;
	Calendar myCalendar;
	DatePickerDialog.OnDateSetListener date;
	HouseVisit hv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_datacollection);
		setupPopupMenu();
		setupButtons();
		setupSelections();
	}

	private void setupSelections() {
		fullNameET = (EditText) findViewById(R.id.fullNameET);
		nricET = (EditText) findViewById(R.id.nricET);
		radioMale = (RadioButton) findViewById(R.id.radioMale);
		radioFemale = (RadioButton) findViewById(R.id.radioFemale);
		dateET = (EditText) findViewById(R.id.dateET);
		ageET = (EditText) findViewById(R.id.ageET);
		ctET = (EditText) findViewById(R.id.ctET);
		addressET = (EditText) findViewById(R.id.addressET);
		nationalityET = (EditText) findViewById(R.id.nationalityET);
		msSpin = (Spinner) findViewById(R.id.msSpin);
		raceTV = (AutoCompleteTextView) findViewById(R.id.raceTV);
		flatTV = (AutoCompleteTextView) findViewById(R.id.flatTV);
		languageTV = (AutoCompleteTextView) findViewById(R.id.languageTV);
		religionTV = (AutoCompleteTextView) findViewById(R.id.religionTV);
		occupationET = (EditText) findViewById(R.id.occupationET);
		salaryET = (EditText) findViewById(R.id.salaryET);
		hiSpin = (Spinner) findViewById(R.id.hiSpin);
		a55ET = (EditText) findViewById(R.id.a55ET);
		a21ET = (EditText) findViewById(R.id.a21ET);
		b20ET = (EditText) findViewById(R.id.b20ET);
		qualificationTV = (AutoCompleteTextView) findViewById(R.id.qualificationTV);
		vehTV = (AutoCompleteTextView) findViewById(R.id.vehTV);
		illnessesET = (EditText) findViewById(R.id.illnessesET);

		String[] race_arrays = getResources().getStringArray(R.array.race_arrays);
		ArrayAdapter<String> raceAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,race_arrays);
		raceTV.setAdapter(raceAdapter);
		raceTV.setThreshold(0);

		String[] tof_arrays = getResources().getStringArray(R.array.tof_arrays);
		ArrayAdapter<String> flatAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,tof_arrays);
		flatTV.setAdapter(flatAdapter);
		flatTV.setThreshold(0);

		String[] sl_arrays = getResources().getStringArray(R.array.sl_arrays);
		ArrayAdapter<String> languageAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,sl_arrays);
		languageTV.setAdapter(languageAdapter);
		languageTV.setThreshold(0);

		String[] r_arrays = getResources().getStringArray(R.array.r_arrays);
		ArrayAdapter<String> religionAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,r_arrays);
		religionTV.setAdapter(religionAdapter);
		religionTV.setThreshold(0);

		String[] edu_arrays = getResources().getStringArray(R.array.edu_arrays);
		ArrayAdapter<String> qualiAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,edu_arrays);
		qualificationTV.setAdapter(qualiAdapter);
		qualificationTV.setThreshold(0);

		String[] vo_arrays = getResources().getStringArray(R.array.vo_arrays);
		ArrayAdapter<String> vehAdapter = new ArrayAdapter<String>
				(this,android.R.layout.simple_list_item_1,vo_arrays);
		vehTV.setAdapter(vehAdapter);
		vehTV.setThreshold(0);

		myCalendar = Calendar.getInstance();
		myCalendar.set(1986,0,1);
		date = new DatePickerDialog.OnDateSetListener() {

			@Override
			public void onDateSet(DatePicker view, int year, int monthOfYear,
								  int dayOfMonth) {
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
				updateLabel();
			}
		};
		dateET.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				DatePickerDialog dpd = new DatePickerDialog(DataCollectionFormActivity.this, date, myCalendar
						.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
						myCalendar.get(Calendar.DAY_OF_MONTH));
				dpd.getDatePicker().setCalendarViewShown(false);
				dpd.show();
			}
		});
	}

	private void updateLabel() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);
		dateET.setText(sdf.format(myCalendar.getTime()));
	}

	private void setupButtons() {
		ImageView back = (ImageView) findViewById(R.id.back);
		back.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionFormActivity.this, HVDetailsActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});

		Button leftBtn = (Button) findViewById(R.id.leftBtn);
		leftBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(DataCollectionFormActivity.this, HVDetailsActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
		Button rightBtn = (Button) findViewById(R.id.rightBtn);
		rightBtn.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				saveResults();
				createGenogram();
				Intent intent = new Intent(DataCollectionFormActivity.this, DataCollectionGenoFormActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(intent);
			}
		});
	}

	private void createGenogram() {
		if (hv.getDataCollectionForm() != null
				&& hv.getDataCollectionForm().getGenogramObjs().size() == 0)
		{
			GenogramObj geno = new GenogramObj();
			geno.setRelation(GenogramObj.RELATIONSHIP_MAIN);
			geno.setFullName(hv.getResident().getFullName());
			geno.setAge(hv.getResident().getAge());
			geno.setOccupation(hv.getResident().getOccupation());
			geno.setSalary(hv.getResident().getSalary());
			geno.setIllness(hv.getResident().getIllnesses());
			geno.setParent(hv.getDataCollectionForm());
			DataBiz.getInstance().getDb().getGenogramObjRuntimeDAO().createOrUpdate(geno);
		}
		else
		{
			for (GenogramObj geno : hv.getDataCollectionForm().getGenogramObjs())
			{
				if (geno.getRelation().equals(GenogramObj.RELATIONSHIP_MAIN))
				{
					geno.setFullName(hv.getResident().getFullName());
					geno.setAge(hv.getResident().getAge());
					geno.setOccupation(hv.getResident().getOccupation());
					geno.setSalary(hv.getResident().getSalary());
					geno.setIllness(hv.getResident().getIllnesses());
					geno.setParent(hv.getDataCollectionForm());
					DataBiz.getInstance().getDb().getGenogramObjRuntimeDAO().createOrUpdate(geno);
				}
			}
		}

	}

	private void saveResults() {
		if(hv.getDataCollectionForm() == null)
		{
			hv.setDataCollectionForm(new DataCollectionForm());
		}
		hv.getResident().setFullName(fullNameET.getText().toString());
		hv.getResident().setNric(nricET.getText().toString());
		if (radioMale.isChecked())
		{
			hv.getResident().setSex("Male");
		}
		if (radioFemale.isChecked())
		{
			hv.getResident().setSex("Female");
		}
		hv.getResident().setDob(dateET.getText().toString());
		hv.getResident().setAge(ageET.getText().toString());
		hv.getResident().setCtcNumber(ctET.getText().toString());
		hv.getResident().setAddress(addressET.getText().toString());
		hv.getResident().setNationality(nationalityET.getText().toString());
		hv.getResident().setMaritalStatus(msSpin.getSelectedItem().toString());
		hv.getResident().setRace(raceTV.getText().toString());
		hv.getResident().setTypeOfFlat(flatTV.getText().toString());
		hv.getResident().setSpokenLanguage(languageTV.getText().toString());
		hv.getResident().setReligion(religionTV.getText().toString());
		hv.getResident().setOccupation(occupationET.getText().toString());
		hv.getResident().setSalary(salaryET.getText().toString());
		hv.getResident().setHouseholdIncome(hiSpin.getSelectedItem().toString());
//		String houseHoldMembers = "Senior Age 55 & Above: " + a55ET.getText().toString();
//		houseHoldMembers += "/n Adult Age 21 & Above: " + a21ET.getText().toString();
//		houseHoldMembers += "/n Young Children (Age 0 – 20): " + b20ET.getText().toString();
		String houseHoldMembers = a55ET.getText().toString() + "|" + a21ET.getText().toString() + "|" + b20ET.getText().toString();
		hv.getResident().setHouseholdMember(houseHoldMembers);
		hv.getResident().setHighestEducation(qualificationTV.getText().toString());
		hv.getResident().setVehOwner(vehTV.getText().toString());
		hv.getResident().setIllnesses(illnessesET.getText().toString());
		DataBiz.getInstance().getDb().getResidentRuntimeDAO().createOrUpdate(hv.getResident());
		DataBiz.getInstance().getDb().getDataCollectionFormRuntimeDAO().createOrUpdate(hv.getDataCollectionForm());
		DataBiz.getInstance().getDb().getHouseVisitRuntimeDAO().createOrUpdate(hv);
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		hv = DataBiz.getInstance().getSelectedHV();

		fullNameET.setText(hv.getResident().getFullName());
		nricET.setText(hv.getResident().getNric());
		if (hv.getResident().getSex().equals("Male"))
		{
			radioMale.setSelected(true);
		}
		else
		{
			radioFemale.setSelected(true);
		}
		dateET.setText(hv.getResident().getDob());
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMM/yyyy", Locale.US);
		try
		{
			Date dob = sdf.parse(hv.getResident().getDob());
			myCalendar.setTime(dob);

		}
		catch(Exception e)
		{
			LOG.error("Date Parse Error ", e);
		}

		ageET.setText(hv.getResident().getAge());
		ctET.setText(hv.getResident().getCtcNumber());
		addressET.setText(hv.getResident().getAddress());
		nationalityET.setText(hv.getResident().getNationality());
		String marital = hv.getResident().getMaritalStatus();
		for (int i = 0; i < msSpin.getAdapter().getCount(); i++)
		{
			if (msSpin.getAdapter().getItem(i).equals(marital))
			{
				msSpin.setSelection(i);
			}
		}
		raceTV.setText(hv.getResident().getRace());
		flatTV.setText(hv.getResident().getTypeOfFlat());
		languageTV.setText(hv.getResident().getSpokenLanguage());
		religionTV.setText(hv.getResident().getReligion());
		occupationET.setText(hv.getResident().getOccupation());
		salaryET.setText(hv.getResident().getSalary());
		String householdIncome = hv.getResident().getHouseholdIncome();
		for (int i = 0; i < hiSpin.getAdapter().getCount(); i++)
		{
			if (hiSpin.getAdapter().getItem(i).equals(householdIncome))
			{
				hiSpin.setSelection(i);
			}
		}
		String householdMembers = hv.getResident().getHouseholdMember();
		if (householdMembers != null)
		{
			String[] members = householdMembers.split("\\|");
			a55ET.setText(members[0]);
			a21ET.setText(members[1]);
			b20ET.setText(members[2]);
		}
		qualificationTV.setText(hv.getResident().getHighestEducation());
		vehTV.setText(hv.getResident().getVehOwner());
		illnessesET.setText(hv.getResident().getIllnesses());

	}




}
