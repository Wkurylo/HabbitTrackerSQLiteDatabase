package com.example.wojtekkurylo.habbittrackersqlitedatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.HabbitTrackerDbHelper;

public class MainActivity extends AppCompatActivity {

	private static HabbitTrackerDbHelper mInstanceDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mInstanceDbHelper = new HabbitTrackerDbHelper(MainActivity.this);

		//Delete all rows & reset the Autoincrement
		mInstanceDbHelper.deleteTable();
		// Put data from user input
		mInstanceDbHelper.putDailyHabbitSummaryNoOne();
		// Update existing value
		mInstanceDbHelper.updateSportName();
		// Load all columns and rows into Coursor // mInstanceDbHelper.readAllHabbits() //
		// Print the Table and content to logcat from Cursor
		mInstanceDbHelper.displayResultInLogcat(mInstanceDbHelper.readAllHabbits());
	}

}
