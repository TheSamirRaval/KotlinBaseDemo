package com.example.demo.common.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.demo.common.constant.AppConstant
import com.example.demo.common.room.dao.ChatResponseDao
import com.example.demo.common.room.dao.UserDao
import com.example.demo.common.room.helpers.DTCCustomerDetails
import com.example.demo.common.room.model.ChatResponse
import com.example.demo.common.room.model.User


/**
 * This is database class
 */
@Database(
    entities = [User::class,ChatResponse::class],
    version = 1,
    exportSchema = false
)
//@TypeConverters(DTCCustomerDetails::class)
abstract class AppRoomDatabase : RoomDatabase() {

    companion object {

        @Volatile
        private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        /**
         * This Migration is for version 1 to 2
         */
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {

                database.execSQL(
                    """CREATE TABLE promo (
                    PromoID TEXT PRIMARY KEY NOT NULL,
                    MerchantID TEXT NOT NULL,
                    TerminalID TEXT NULL DEFAULT NULL,
                    PromoStatus TEXT NOT NULL,
                    StartDate TEXT NOT NULL DEFAULT '',
                    EndDate TEXT NOT NULL DEFAULT '',
                    PubDate TEXT NOT NULL DEFAULT '',
                    PubTo TEXT NOT NULL DEFAULT '',
                    PromoText TEXT NOT NULL DEFAULT '',
                    PromoIMG TEXT NULL DEFAULT NULL,
                    TotalUsers INTEGER NOT NULL DEFAULT 0,
                    LocalTimeStamp INTEGER NOT NULL DEFAULT 0,
                    CreatedAt TEXT NULL DEFAULT '',
                    ModifiedAt TEXT NULL DEFAULT ''
                )""".trimIndent()
                )

            }
        }

        /**
         * This Migration is for version 1 to 2
         * It includes addition of fields in Promo table and create new table promoTransaction
         */
        private val MIGRATION_2_3: Migration = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {

                //Alter Promotion table query
                database.execSQL(" ALTER TABLE promo ADD COLUMN PromoTitle TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN PromoTemp INTEGER NOT NULL DEFAULT 0;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN PromoBkgCol TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN IsBold TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN IsItalic TEXT DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN IsUnderline TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN Alignment TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontTitle TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontDesc TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontColorTitle TEXT DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontColorDesc TEXT  DEFAULT NULL;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontSizeTitle INTEGER NOT NULL DEFAULT 10;")
                database.execSQL(" ALTER TABLE promo ADD COLUMN FontSizeDesc INTEGER NOT NULL DEFAULT 10;")


                //Add promotion transaction table query
                database.execSQL(
                    """CREATE TABLE promosTransactions (
                    MctPromoTranID INTEGER PRIMARY KEY NOT NULL,
                    PromoID TEXT NOT NULL,
                    MerchantID TEXT NOT NULL,
                    TerminalID TEXT NULL DEFAULT NULL,
                    StartDate TEXT NOT NULL DEFAULT '',
                    EndDate TEXT NOT NULL DEFAULT '',
                    PubDate TEXT NOT NULL DEFAULT '',
                    PromoTitle TEXT NOT NULL DEFAULT '',
                    PromoText TEXT NOT NULL DEFAULT '',
                    PromoIMG TEXT NULL DEFAULT NULL,
                    UsedDate TEXT NOT NULL DEFAULT '',
                    DRCustomerID TEXT NULL DEFAULT NULL,
                    CustomerFeedback TEXT NULL DEFAULT NULL,
                    CustomerRating TEXT NULL DEFAULT NULL,
                    LocalTimeStamp INTEGER NOT NULL DEFAULT 0,
                    CreatedAt TEXT NULL DEFAULT '',
                    ModifiedAt TEXT NULL DEFAULT ''
                )""".trimIndent()
                )

            }
        }

        private val ALL_MIGRATION = arrayOf(MIGRATION_1_2, MIGRATION_2_3)

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppRoomDatabase::class.java, AppConstant.DefaultValues.DatabaseName
            )
                .addMigrations(*ALL_MIGRATION)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build()
    }

    abstract fun userDao(): UserDao
    abstract fun chatResponseDao(): ChatResponseDao


}