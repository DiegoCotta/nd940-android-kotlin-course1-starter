package com.udacity.shoestore.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.User

class LoginViewModel : ViewModel() {


    private val registredUserList = arrayListOf<User>()

    private val _eventLoginError = MutableLiveData<String?>()
    val eventLoginError: LiveData<String?>
        get() = _eventLoginError

    private val _eventLogin = MutableLiveData<Boolean>()
    val eventLogin: LiveData<Boolean>
        get() = _eventLogin

    private val _eventNewUser = MutableLiveData<Boolean>()
    val eventNewUser: LiveData<Boolean>
        get() = _eventNewUser

    init {
        _eventNewUser.value = false
        _eventLogin.value = false
        _eventLoginError.value = null
    }

    fun createUser(email: String, password: String) {
        val user = registredUserList.singleOrNull() { it.email == email && it.password == password }
        if (user == null) {
            registredUserList.add(User(email, password))
            _eventNewUser.value = true
        } else {
            _eventLoginError.value = "E-mail already registered"
        }
    }

    fun createUserDone() {
        _eventNewUser.value = false
    }

    fun login(email: String, password: String) {
        //to login you have create user before
        val user = registredUserList.singleOrNull() { it.email == email }
        when {
            user == null -> {
                _eventLoginError.value = "E-mail not registred"
            }
            user.password != password -> {
                _eventLoginError.value = "Wrong password"
            }
            else -> _eventLogin.value = true
        }
    }

    fun errorDone() {
        _eventLoginError.value = null
    }

    fun loginDone() {
        _eventLogin.value = false
    }
}