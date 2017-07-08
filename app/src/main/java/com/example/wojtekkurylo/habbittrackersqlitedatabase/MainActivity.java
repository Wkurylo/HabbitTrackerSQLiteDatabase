package com.example.wojtekkurylo.habbittrackersqlitedatabase;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.UserInput;
import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.HabbitTrackerDbHelper;
import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.HabbitTrackerContract.HabbitEntry;

public class MainActivity extends AppCompatActivity {

	/**
	 * Instance if HabbitTrackerDbHelper - subclass of SQLiteOpenHelper
	 */
	private HabbitTrackerDbHelper mInstanceDbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mInstanceDbHelper = new HabbitTrackerDbHelper(MainActivity.this);

		//Delete all rows & reset the Autoincrement
		deleteTable();
		// Put data from user input
		putDailyHabbitSummaryNoOne();
		// Update existing value
		updateSportName();
		// Print the Table and content to logcat
		displayResultInLogcat();
	}

	public void putDailyHabbitSummaryNoOne() {
		// Gets the data repository in write mode
		SQLiteDatabase db = mInstanceDbHelper.getWritableDatabase();

		// Receive data from user input && parse to correct Data Type
		int sleepTimeNoOne = Integer.parseInt(UserInput.sleepTimeNoOne);
		int walkingWithDogNoOne = Integer.parseInt(UserInput.walkingWithDogNoOne);
		int computerNoOne = Integer.parseInt(UserInput.computerNoOne);
		int justRelaxNoOne = Integer.parseInt(UserInput.relaxNoOne);
		int sportTimeNoOne = Integer.parseInt(UserInput.sportTimeNoOne);

		// Fill the table rows with following content
		ContentValues values = new ContentValues();
		values.put(HabbitEntry.SLEEP_TIME_HR, sleepTimeNoOne);
		values.put(HabbitEntry.WALKING_WITH_DOG_MIN, walkingWithDogNoOne);
		values.put(HabbitEntry.COMPUTER_HR, computerNoOne);
		values.put(HabbitEntry.JUST_RELAX_HR, justRelaxNoOne);
		// UserInput.SPORT_NAME_NO1 == String Object
		values.put(HabbitEntry.SPORT_NAME, UserInput.sportNameNoOne);
		values.put(HabbitEntry.SPORT_TIME_MIN, sportTimeNoOne);

		// Insert the new row, returning the primary key value of the new row
		long newRowId = db.insert(HabbitEntry.TABLE_NAME, null, values);

		if (newRowId == -1) {
			Log.v("Main Activity", "Error with insert method in putDailyHabbitSummaryNoOne, newRowId: " + newRowId);
		} else {
			Log.v("Main Activity", "Successfully filled row with No1 data, newRowId: " + newRowId);
		}

	}

	/**
	 * Method to update the value in Data Base
	 */
	public void updateSportName() {
		// Gets the data repository in write mode
		SQLiteDatabase db = mInstanceDbHelper.getWritableDatabase();

		// New value for one column
		ContentValues values = new ContentValues();
		// to which column add the value after coma
		values.put(HabbitEntry.SPORT_NAME, UserInput.sportNameNoTwo);

		// Which column to update
		String selection = HabbitEntry.SPORT_NAME + "=?";
		// Which entry from row to correct  // WHERE sport_name = cycling; //
		String[] selectionArg = new String[]{UserInput.sportNameNoOne};

		db.update(HabbitEntry.TABLE_NAME, values, selection, selectionArg);
	}

	/**
	 * Method to display the Table "DailyRoutine"
	 */
	public void displayResultInLogcat() {
		// Gets the data repository in read mode
		SQLiteDatabase db = mInstanceDbHelper.getReadableDatabase();

		// Equivalent of SELECT * FROM <tableName>
		String[] columns = {
				HabbitEntry.ID,
				HabbitEntry.SLEEP_TIME_HR,
				HabbitEntry.WALKING_WITH_DOG_MIN,
				HabbitEntry.COMPUTER_HR,
				HabbitEntry.JUST_RELAX_HR,
				HabbitEntry.SPORT_NAME,
				HabbitEntry.SPORT_TIME_MIN
		};

		// store in Cursor instance all rows from database table "DailyRoutine" with selected columns of interest
		Cursor cursor = db.query(
				HabbitEntry.TABLE_NAME,
				columns,
				null,
				null,
				null,
				null,
				null);

		final StringBuilder output = new StringBuilder();

		try {

			// Figure out the index of each column
			int idColumnIndex = cursor.getColumnIndex(HabbitEntry._ID);
			int sleepColumnIndex = cursor.getColumnIndex(HabbitEntry.SLEEP_TIME_HR);
			int walkingColumnIndex = cursor.getColumnIndex(HabbitEntry.WALKING_WITH_DOG_MIN);
			int computerColumnIndex = cursor.getColumnIndex(HabbitEntry.COMPUTER_HR);
			int relaxColumnIndex = cursor.getColumnIndex(HabbitEntry.JUST_RELAX_HR);
			int sportNameColumnIndex = cursor.getColumnIndex(HabbitEntry.SPORT_NAME);
			int sportTimeColumnIndex = cursor.getColumnIndex(HabbitEntry.SPORT_TIME_MIN);

			// Taking columns name
			String idColumnName = cursor.getColumnName(idColumnIndex);
			String sleepColumnName = cursor.getColumnName(sleepColumnIndex);
			String walkingColumnName = cursor.getColumnName(walkingColumnIndex);
			String computerColumnName = cursor.getColumnName(computerColumnIndex);
			String relaxColumnName = cursor.getColumnName(relaxColumnIndex);
			String sportNameColumnName = cursor.getColumnName(sportNameColumnIndex);
			String sportTimeColumnName = cursor.getColumnName(sportTimeColumnIndex);

			// Taking value of cursor position
			String cursorCurrentPosName = String.valueOf(cursor.getPosition());

			// Building StringBuilder with column Names
			output.append("\n" +
					idColumnName + "--" +
					sleepColumnName + "--" +
					walkingColumnName + "--" +
					computerColumnName + "--" +
					relaxColumnName + "--" +
					sportNameColumnName + "--" +
					sportTimeColumnName + "--" +
					"CursorCurrentRow int: " + cursorCurrentPosName + "\n"
			);

			while (cursor.moveToNext()) {

				// Taking the column index number
				int idCurrentRow = cursor.getInt(idColumnIndex);
				int sleepCurrentRow = cursor.getInt(sleepColumnIndex);
				int walkingCurrentRow = cursor.getInt(walkingColumnIndex);
				int computerCurrentRow = cursor.getInt(computerColumnIndex);
				int relaxCurrentRow = cursor.getInt(relaxColumnIndex);
				String sportNameCurrentRowString = cursor.getString(sportNameColumnIndex);
				int sportTimeCurrentRow = cursor.getInt(sportTimeColumnIndex);

				// Taking value from each Column x Row ID
				String idCurrentRowString = String.valueOf(idCurrentRow);
				String sleepCurrentRowString = String.valueOf(sleepCurrentRow);
				String walkingCurrentRowString = String.valueOf(walkingCurrentRow);
				String computerCurrentRowString = String.valueOf(computerCurrentRow);
				String relaxCurrentRowString = String.valueOf(relaxCurrentRow);
				String sportTimeCurrentRowString = String.valueOf(sportTimeCurrentRow);

				// Taking value of cursor position
				String cursorCurrentPos = String.valueOf(cursor.getPosition());

				// Building StringBuilder with column x row values
				output.append(idCurrentRowString + "--" +
						sleepCurrentRowString + "--" +
						walkingCurrentRowString + "--" +
						computerCurrentRowString + "--" +
						relaxCurrentRowString + "--" +
						sportNameCurrentRowString + "--" +
						sportTimeCurrentRowString + "--" +
						"CursorRow: " + cursorCurrentPos + "\n");
			}

		} finally {
			// Finally bloc execute always that is why It is good oportunity to prevent memory leakage and close cursor
			cursor.close();
		}
		// Display the table in logcat
		Log.v("Main Activity", "PRAGMA TABLE_INFO(DailyRoutine) " + output.toString());
	}

	public void deleteTable(){
		// Gets the data repository in read mode
		SQLiteDatabase db = mInstanceDbHelper.getReadableDatabase();

		//Convenience method for deleting rows in the database.
		// Delete all rows
		db.delete(HabbitEntry.TABLE_NAME,null,null);
		db.execSQL("UPDATE sqlite_sequence SET seq == 0;");
	}

}
