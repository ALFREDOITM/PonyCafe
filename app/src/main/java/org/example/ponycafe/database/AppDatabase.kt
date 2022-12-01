package org.example.ponycafe.database

import androidx.room.Database
import androidx.room.RoomDatabase

const val DATABASE_VERSION = 1
const val TABLE_CART = "cart"
const val DATABASE_NAME = "appdatabase.sqlite"

@Database(entities = [CartEntity::class],
    version = DATABASE_VERSION
    )

abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}