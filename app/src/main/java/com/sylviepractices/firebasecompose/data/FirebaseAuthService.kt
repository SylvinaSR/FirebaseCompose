package com.sylviepractices.firebasecompose.data

import com.google.firebase.auth.FirebaseAuth
import javax.inject.Inject

class FirebaseAuthService @Inject constructor(
    private val auth: FirebaseAuth
) {

    private var successLogin: Boolean = false

    fun doLogin(email: String, password: String): Boolean {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            successLogin = task.isSuccessful
        }
        return successLogin
    }

}