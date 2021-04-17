package com.example.happyplaces.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.happyplaces.models.HappyPlaceModel

class DatabaseHandler(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {

        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "HappyPlacesDatabase"
        private const val TABLE_HAPPY_PLACE = "HappyPlacesTable"

        //columns names
        private const val KEY_ID = "_id"
        private const val KEY_TITLE = "title"
        private const val KEY_IMAGE = "image"
        private const val KEY_DESCRIPTION = "description"
        private const val KEY_DATE = "date"
        private const val KEY_LOCATION = "location"
        private const val KEY_LATITUDE = "latitude"
        private const val KEY_LONGITUDE = "longitude"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // creating table
        val CREATE_HAPPY_PLACE_TABLE = ("CREATE TABLE " + TABLE_HAPPY_PLACE + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TITLE + " TEXT, "
                + KEY_IMAGE + " TEXT, "
                + KEY_DESCRIPTION + " TEXT, "
                + KEY_DATE + " TEXT, "
                + KEY_LOCATION + " TEXT, "
                + KEY_LATITUDE + " TEXT, "
                + KEY_LONGITUDE + " TEXT)")
        db?.execSQL(CREATE_HAPPY_PLACE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_HAPPY_PLACE")
        onCreate(db)
    }

    /**
     *  inserting data in table:
     */
    fun addHappyPlaces(happyPlaces: HappyPlaceModel): Long {

        val db = this.writableDatabase
        val contentValues = ContentValues()

        contentValues.put(KEY_TITLE, happyPlaces.title)
        contentValues.put(KEY_IMAGE, happyPlaces.image)
        contentValues.put(KEY_DESCRIPTION, happyPlaces.description)
        contentValues.put(KEY_DATE, happyPlaces.date)
        contentValues.put(KEY_LOCATION, happyPlaces.location)
        contentValues.put(KEY_LATITUDE, happyPlaces.latitude)
        contentValues.put(KEY_LONGITUDE, happyPlaces.longitude)

        val result = db.insert(TABLE_HAPPY_PLACE, null, contentValues)
        db.close()
        return result
    }
}