package com.example.manager.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true)
    val uid: Int? = null,
    val title: String? = null,
    val desc: String? = null,
):Serializable
