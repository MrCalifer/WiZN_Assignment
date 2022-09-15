package edu.califer.wiznassignment.persistance.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey var id : String,
    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "isFavourite") var isFavourite : Boolean = false,
    @ColumnInfo(name = "imageURL") var imageURL : String = ""
)