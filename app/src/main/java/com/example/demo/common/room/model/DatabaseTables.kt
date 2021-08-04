package com.example.demo.common.room.model

import androidx.room.*

/**
 * This class contains declaration of all the table in detail which are used in the Local database
 */


@Entity(tableName = "user")
data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "name") var name: String = "",
    @ColumnInfo(name = "email") var email: String = "",
    @ColumnInfo(name = "password") var password: String? = "",
    @ColumnInfo(name = "place") var place: String? = ""
       /* if Any Sub Class there then use Type Converter in Room Db*/
    //@TypeConverters(DTCDriveIdentityDetail::class) @ColumnInfo(name = "driver_identity_detail") var driver_identity_detail: DriverIdentityDetail? = null,

)


@Entity(tableName = "chat_response")
data class ChatResponse(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id") val id: Int=0,
        @ColumnInfo (name = "chatId")val chatId: String ="",
        @ColumnInfo (name = "sender_id")val sender_id: Int?= 0,
        @ColumnInfo (name = "receiver_id")val receiver_id: Int?= 0,
        @ColumnInfo (name = "message")val message: String?= null
)


