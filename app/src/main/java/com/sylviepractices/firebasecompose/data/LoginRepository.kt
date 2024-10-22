package com.sylviepractices.firebasecompose.data

import android.provider.ContactsContract.CommonDataKinds.Email
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuthService
) {

    fun doLogin(email: String, password: String): Boolean =
        firebaseAuth.doLogin(email = email, password = password)

}