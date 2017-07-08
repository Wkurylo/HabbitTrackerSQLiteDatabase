package com.example.wojtekkurylo.habbittrackersqlitedatabase.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.wojtekkurylo.habbittrackersqlitedatabase.data.HabbitTrackerContract.HabbitEntry;

/**
 * Created by wojtekkurylo on 07.07.2017.
 */

public class HabbitTrackerDbHelper extends SQLiteOpenHelper {

	// If you change the database schema, you must increment the database version.
	public static final int DATABASE_VERSION = 3;
	public static final String DATABASE_NAME = "habbit.db";


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
	public static final String SQL_CREATE_ENTRIES_VERSION_TWO =
			"CREATE TABLE " + HabbitEntry.TABLE_NAME + " (" +
					HabbitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					HabbitEntry.SLEEP_TIME_HR + " INTEGER NOT NULL," +
					HabbitEntry.WALKING_WITH_DOG_MIN + " INTEGER DEFAULT 0," +
					HabbitEntry.COMPUTER_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.JUST_RELAX_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.SPORT_NAME + " TEXT," +
					HabbitEntry.SPORT_TIME_MIN + " INTEGER DEFAULT 0)";

	// Constants used while onCreate and/or onUpgrade is called by constructor - VERSION 3
	public static final String SQL_CREATE_ENTRIES_VERSION_THREE =
			"CREATE TABLE " + HabbitEntry.TABLE_NAME + " (" +
					HabbitEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
					HabbitEntry.SLEEP_TIME_HR + " INTEGER NOT NULL," +
					HabbitEntry.WALKING_WITH_DOG_MIN + " INTEGER DEFAULT 0," +
					HabbitEntry.COMPUTER_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.JUST_RELAX_HR + " INTEGER DEFAULT 0," +
					HabbitEntry.SPORT_NAME + " TEXT," +
					HabbitEntry.SPORT_TIME_MIN + " INTEGER DEFAULT 0)";


	public static final String SQL_DELETE_ENTRIES =
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
		switch (oldVersion) {
			case 1:
				db.execSQL(SQL_DELETE_ENTRIES);
				db.execSQL(SQL_CREATE_ENTRIES_VERSION_TWO);
			case 2:
				db.execSQL(SQL_DELETE_ENTRIES);
				onCreate(db);
		}
	}
}
