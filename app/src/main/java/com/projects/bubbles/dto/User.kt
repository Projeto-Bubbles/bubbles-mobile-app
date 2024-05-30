package com.projects.bubbles.dto

import android.provider.ContactsContract.CommonDataKinds.Email
import java.lang.reflect.Member


data class User(
    val idUser: Int? = null,
    val username: String,
    val nickname: String,
    val email: String,
    val cpf: String? = null,
    val password: String? = null,
    val id: Int? = null,
) {}