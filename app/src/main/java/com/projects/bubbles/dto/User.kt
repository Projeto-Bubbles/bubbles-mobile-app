package com.projects.bubbles.dto

import android.provider.ContactsContract.CommonDataKinds.Email
import java.lang.reflect.Member


data class User(
    val id: Int? = null,
    val username: String? = null,
    val email: String? = null,
    val cpf: String? = null,
    val nickname: String? = null,
    val password: String? = null,
) {}