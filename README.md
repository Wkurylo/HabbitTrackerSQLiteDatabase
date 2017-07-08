# HabbitTrackerSQLiteDatabase
Android/Java Nanodegree UdaCity program by Google - Problem set 9

Thie project is SQLite - practice with Database task.
The project donot included UI.

The app is 

The result is printed in logcat, and methods are implemented in onCreate in MainActivity.

Project included:
1. UserInput Class - Let's assume that these are User Imputs in EditText field in UI;
2. Contract Class with Table schema (implements BaseColumns);
3. Subclass of SQLiteOpenHelper with 3 version of Data Base schema - just for practice reason && switch statement in onUpgrade. To update database in steeps by 1.
4. Put data method - ContentValuses && db.insert();
5. Update specific value in Column x Row data method - ContentValues && db.update();
6. Read data method - StringBuilder && db.query() && while loop for Coursor move throught row && try, finally block to close cursor;
7. Delete All rows && reset AUTOINCREMENT in sqlite_sequence table.

