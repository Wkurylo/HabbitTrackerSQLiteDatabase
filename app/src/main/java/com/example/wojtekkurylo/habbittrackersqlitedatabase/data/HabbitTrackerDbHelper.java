package com.example.wojtekkurylo.habbittrackersqlitedatabase.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.HabbitTrackerContract.HabbitEntry;

/**
 * Created by wojtekkurylo on 07.07.2017.
 */

public class HabbitTrackerDbHelper extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database version.
	private static final int DATABASE_VERSION = 3;
	private static final String DATABASE_NAME = "habbit.db";


	// Constants used while onCreate and/or onUpgrade is called by constructor
	public static final String SQL_CREATE_ENTRIES =
			"CREATE TABLE " + HabbitEntry.TABLE_NAME + " (" +
					HabbitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					HabbitEntry.SLEEP_TIME_HR + " INTEGER NOT NULL," +
					HabbitEntry.WALKING_WITH_DOG_MIN + " INTEGER DEFAULT 0," +
					HabbitEntry.COMPUTER_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.JUST_RELAX_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.SPORT_NAME + " TEXT," +
					HabbitEntry.SPORT_TIME_MIN + " INTEGER DEFAULT 0)";

	// Constants used while onCreate and/or onUpgrade is called by constructor - VERSION 2
	private static final String SQL_CREATE_ENTRIES_VERSION_TWO =
			"CREATE TABLE " + HabbitEntry.TABLE_NAME + " (" +
					HabbitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					HabbitEntry.SLEEP_TIME_HR + " INTEGER NOT NULL," +
					HabbitEntry.WALKING_WITH_DOG_MIN + " INTEGER DEFAULT 0," +
					HabbitEntry.COMPUTER_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.JUST_RELAX_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.SPORT_NAME + " TEXT," +
					HabbitEntry.SPORT_TIME_MIN + " INTEGER DEFAULT 0)";

	// Constants used while onCreate and/or onUpgrade is called by constructor - VERSION 3
	private static final String SQL_CREATE_ENTRIES_VERSION_THREE =
			"CREATE TABLE " + HabbitEntry.TABLE_NAME + " (" +
					HabbitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					HabbitEntry.SLEEP_TIME_HR + " INTEGER NOT NULL," +
					HabbitEntry.WALKING_WITH_DOG_MIN + " INTEGER DEFAULT 0," +
					HabbitEntry.COMPUTER_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.JUST_RELAX_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.SPORT_NAME + " TEXT," +
					HabbitEntry.SPORT_TIME_MIN + " INTEGER DEFAULT 0)";


	private static final String SQL_DELETE_ENTRIES =
			"DROP TABLE IF EXISTS " + HabbitEntry.TABLE_NAME;


	/**
	 * Public Constructor
	 *
	 * @param context of the correct activity
	 */
	public HabbitTrackerDbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(SQL_CREATE_ENTRIES_VERSION_THREE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// This database is only a cache for online data, so its upgrade policy is
		// to simply discard the data and start over

		// ISSUE : User is loosing all data while onUpgrade calling - to be developed

		switch (oldVersion) {
			case 1:
				db.execSQL(SQL_DELETE_ENTRIES);
				db.execSQL(SQL_CREATE_ENTRIES_VERSION_TWO);
			case 2:
				db.execSQL(SQL_DELETE_ENTRIES);
				db.execSQL(SQL_CREATE_ENTRIES_VERSION_THREE);


		}
	}

	public void putDailyHabbitSummaryNoOne() {
		// Gets the data repository in write mode
		SQLiteDatabase db = getWritableDatabase();

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
		SQLiteDatabase db = getWritableDatabase();

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

	public Cursor readAllHabbits(){
		// Gets the data repository in read mode
		SQLiteDatabase db = getReadableDatabase();

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

		return cursor;
	}

	/**
	 * Method to display the Table "DailyRoutine"
	 */
	public void displayResultInLogcat(Cursor cursor) {


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
		// Gets the data repository in write mode
		SQLiteDatabase db = getWritableDatabase();

		//Convenience method for deleting rows in the database.
		// Delete all rows
		db.delete(HabbitEntry.TABLE_NAME,null,null);
		db.execSQL("UPDATE sqlite_sequence SET seq == 0;");
	}
}
