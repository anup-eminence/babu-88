package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.CurrencyRequest
import com.sona.babu88.api.model.request.RegisterUserRequest
import com.sona.babu88.api.model.response.CurrencyResponse
import com.sona.babu88.api.model.response.RegisterUserResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _authenticatUser  = MutableLiveData<ApiResult<UserData?>>()
    val authenticateUser : LiveData<ApiResult<UserData?>> get() = _authenticatUser

    private val _registerUser  = MutableLiveData<ApiResult<RegisterUserResponse?>>()
    val registerUser : LiveData<ApiResult<RegisterUserResponse?>> get() = _registerUser

    private val _registerUserCurrency  = MutableLiveData<ApiResult<List<CurrencyResponse>?>>()
    val registerUserCurrency : LiveData<ApiResult<List<CurrencyResponse>?>> get() = _registerUserCurrency


    fun authenticateUser(
        userId: String?,
        passWord: String?,
        host: String? = "localhost",
        ipad: String? = "180.151.28.84"
    ) {
        viewModelScope.launch {
            _authenticatUser.postValue(ApiResult.Loading())
            try {
                val response = RetrofitUtil.apiServies.authenticateUser(
                    AuthenticateUserRequest(
                        userId = userId,
                        passWord = passWord,
                        host = host,
                        ipad = ipad
                    )
                )
                if (response.isSuccessful && response.code() == 200 && response.body() != null){
                    ApiResult.Success(response.body()).let { _authenticatUser.postValue(it) }
                } else {
                    _authenticatUser.postValue(ApiResult.Error("Something Went Wrong!"))
                }

                println(">>>>>response ${response.isSuccessful}")
                println(">>>>>response ${response.body()}")
            }catch (e : Exception){
                _authenticatUser.postValue(ApiResult.Error("Something Went Wrong!"))
            }

        }

    }

    fun registerUser(
        registerUserRequest: RegisterUserRequest
    ){
        viewModelScope.launch {
            try {
              _registerUser.postValue(ApiResult.Loading())
                val verifyRequest = RetrofitUtil.apiServies.verifyUser(registerUserRequest)
                if (verifyRequest.isSuccessful){
                    val registerUser = RetrofitUtil.apiServies.validateUserSignup(registerUserRequest)
                    if (registerUser.isSuccessful){
                        _registerUser.postValue(ApiResult.Success(registerUser.body()))
                    } else {
                        _registerUser.postValue(ApiResult.Error("Something went wrong!"))
                    }
                }
            }catch (e : Exception) {
                _registerUser.postValue(ApiResult.Error("Something went wrong!"))
            }
        }
    }

    fun getRegisterCurrency(
        currencyRequest: CurrencyRequest
    ) {
        viewModelScope.launch {
            try {
                _registerUserCurrency.postValue(ApiResult.Loading())
                val verifyApi = RetrofitUtil.apiServies.verifyUser(currencyRequest)
                if (verifyApi.isSuccessful){
                    val currencyResponse = RetrofitUtil.apiServies.getSignupCurrency(currencyRequest)
                    if (currencyResponse.isSuccessful){
                        _registerUserCurrency.postValue(ApiResult.Success(currencyResponse.body()))
                    } else {
                        _registerUserCurrency.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            }catch (e : Exception) {
                _registerUserCurrency.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}