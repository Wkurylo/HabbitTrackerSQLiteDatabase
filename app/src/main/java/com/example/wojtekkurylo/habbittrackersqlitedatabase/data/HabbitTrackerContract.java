package com.example.wojtekkurylo.habbittrackersqlitedatabase.data;

import android.provider.BaseColumns;

/**
 * Created by wojtekkurylo on 07.07.2017.
 */

public class HabbitTrackerContract {

	/**
	 * Private constructor to prevent instantianting contract class
	 */
	private HabbitTrackerContract() {
		throw new AssertionError("No HabbitTrackerContract instance for You!");
	}

	/**
	 * Inner Class defines the DailyRoutine table contents in constants
	 */
	public static class HabbitEntry implements BaseColumns {

		public static final String TABLE_NAME = "DailyRoutine";
		/**
		 * Names of columns headers stored in constants
		 */
		public static final String ID = BaseColumns._ID;
		public static final String SLEEP_TIME_HR = "sleep_time_hr";
		public static final String WALKING_WITH_DOG_MIN = "walking_with_dog_min";
		public static final String COMPUTER_HR = "computer_hr";
		public static final String JUST_RELAX_HR = "just_relax_hr";
		public static final String SPORT_NAME = "sport_name";
		public static final String SPORT_TIME_MIN = "sport_time_min";
		public static final String ALTER_COLUMN_ONE = "alter_column_one";
	}
}
