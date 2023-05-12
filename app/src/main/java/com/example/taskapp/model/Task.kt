package com.example.appleadd.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    val title: String? = null,
    val desc: String? = null,
) : java.io.Serializable