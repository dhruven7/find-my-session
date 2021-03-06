package com.example.objects;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "tag_content.db";

	private static final int DATABASE_VERSION = 1;

	public static final String TABLE_CONTENT = "tag_content";
	public static final String TABLE_TAG = "content_tag";
	public static final String TABLE_CONTENT_TAG = "content_tags";

	public static final String COLUMN_ID = "_id";

	public static final String COLUMN_PAYLOAD = "payload";
	public static final String COLUMN_PLHEADER = "payload_header";
	public static final String COLUMN_PLTYPE = "payload_type";
	public static final String COLUMN_CREATED_AT = "created_at";

	public static final String COLUMN_NAME = "tag_name";

	public static final String COLUMN_CONTENT_ID = "content_id";
	public static final String COLUMN_TAG_ID = "tag_id";

	private static final String CREATE_TAG_CONTENT = "create table "
			+ TABLE_CONTENT + " (" + COLUMN_ID
			+ " integer primary key autoincrement, " + COLUMN_PAYLOAD
			+ " text not null, " + COLUMN_PLHEADER + " text not null, "
			+ COLUMN_PLTYPE + " text not null, "
			+ COLUMN_CREATED_AT + " DATETIME "+ ");";
	
	private static final String CREATE_CONTENT_TAG = "create table "
			+ TABLE_TAG + " (" + COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_NAME+ " text not null" + ");";
	
	private static final String CREATE_CONTENT_TAGS = "create table "
			+ TABLE_CONTENT_TAG + " (" + COLUMN_ID + " integer primary key autoincrement, " 
			+ COLUMN_CONTENT_ID+ " integer, " + COLUMN_TAG_ID + " integer "+ ");";
	

	public SQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TAG_CONTENT);
		database.execSQL(CREATE_CONTENT_TAG);
		database.execSQL(CREATE_CONTENT_TAGS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLiteHelper.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
						+ newVersion + ", which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTENT_TAG);
		
		onCreate(db);
	}

}