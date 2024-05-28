package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.CurrencyRequest
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.RegisterUserRequest
import com.sona.babu88.api.model.request.UpdateBirthDayRequest
import com.sona.babu88.api.model.request.VerifyEmailCodeRequest
import com.sona.babu88.api.model.request.VerifyEmailRequest
import com.sona.babu88.api.model.response.CurrencyResponse
import com.sona.babu88.api.model.response.EmailVerificationResponse
import com.sona.babu88.api.model.response.EmailVerifiedResponse
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.RegisterUserResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.api.model.response.UserDetailsResponse
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _authenticatUser = MutableLiveData<ApiResult<UserData?>>()
    val authenticateUser: LiveData<ApiResult<UserData?>> get() = _authenticatUser

    private val _registerUser = MutableLiveData<ApiResult<RegisterUserResponse?>>()
    val registerUser: LiveData<ApiResult<RegisterUserResponse?>> get() = _registerUser

    private val _registerUserCurrency = MutableLiveData<ApiResult<List<CurrencyResponse>?>>()
    val registerUserCurrency: LiveData<ApiResult<List<CurrencyResponse>?>> get() = _registerUserCurrency

    private val _userDetails = MutableLiveData<ApiResult<UserDetailsResponse?>>()
    val userDetails: LiveData<ApiResult<UserDetailsResponse?>> get() = _userDetails

    private val _requestEmailCode = MutableLiveData<ApiResult<EmailVerificationResponse?>>()
    val requestEmailCode: LiveData<ApiResult<EmailVerificationResponse?>> get() = _requestEmailCode

    private val _verifyEmail = MutableLiveData<ApiResult<EmailVerifiedResponse?>>()
    val verifyEmail: LiveData<ApiResult<EmailVerifiedResponse?>> get() = _verifyEmail

    private val _updateBirthday = MutableLiveData<ApiResult<GeneralResponse?>>()
    val updateBirthday: LiveData<ApiResult<GeneralResponse?>> get() = _updateBirthday


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
                        userId = userId?.trim(),
                        passWord = passWord?.trim(),
                        host = host,
                        ipad = ipad
                    )
                )
                if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                    ApiResult.Success(response.body()).let { _authenticatUser.postValue(it) }
                } else {
                    _authenticatUser.postValue(ApiResult.Error("Something Went Wrong!"))
                }

                println(">>>>>response ${response.isSuccessful}")
                println(">>>>>response ${response.body()}")
            } catch (e: Exception) {
                _authenticatUser.postValue(ApiResult.Error("Something Went Wrong!"))
            }

        }

    }

    fun registerUser(
        registerUserRequest: RegisterUserRequest
    ) {
        viewModelScope.launch {
            try {
                _registerUser.postValue(ApiResult.Loading())
                val verifyRequest = RetrofitUtil.apiServies.verifyUser(registerUserRequest)
                if (verifyRequest.isSuccessful) {
                    val registerUser =
                        RetrofitUtil.apiServies.validateUserSignup(registerUserRequest)
                    if (registerUser.isSuccessful) {
                        _registerUser.postValue(ApiResult.Success(registerUser.body()))
                    } else {
                        _registerUser.postValue(ApiResult.Error("Something went wrong!"))
                    }
                }
            } catch (e: Exception) {
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
                if (verifyApi.isSuccessful) {
                    val currencyResponse =
                        RetrofitUtil.apiServies.getSignupCurrency(currencyRequest)
                    if (currencyResponse.isSuccessful) {
                        _registerUserCurrency.postValue(ApiResult.Success(currencyResponse.body()))
                    } else {
                        _registerUserCurrency.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _registerUserCurrency.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserDetails() {
        viewModelScope.launch {
            _userDetails.postValue(ApiResult.Loading())
            val request = AppConstant.getTimeStamp()
            val generalRequest = GeneralRequest(
                timeStamp = request[AppConstant.TIMESTAMP],
                secretKey =request[AppConstant.SECRET_KEY]
            )
            try {
                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val userResponse = RetrofitUtil.apiServies.getUserDetails(generalRequest)
                    if (userResponse.isSuccessful) {
                        _userDetails.postValue(ApiResult.Success(userResponse.body()))
                    } else {
                        _userDetails.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userDetails.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getEmailVerificationCode(
        requestCode: VerifyEmailRequest
    ) {
        viewModelScope.launch {
            _requestEmailCode.postValue(ApiResult.Loading())

            try {
                val verifyApi = RetrofitUtil.apiServies.verifyUser(requestCode)
                if (verifyApi.isSuccessful) {
                    val codeResponse = RetrofitUtil.apiServies.getEmailVerificationCode(requestCode)
                    if (codeResponse.isSuccessful) {
                        _requestEmailCode.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _requestEmailCode.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _requestEmailCode.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun verifyEmail(
        code: String
    ) {
        _verifyEmail.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val verifyCode = VerifyEmailCodeRequest(
                    code = code,
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(verifyCode)
                if (verifyApi.isSuccessful) {
                    val codeResponse = RetrofitUtil.apiServies.verifyEmail(verifyCode)
                    if (codeResponse.isSuccessful) {
                        _verifyEmail.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _verifyEmail.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _verifyEmail.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }


    }

    fun updateBirthDay(
        birthDay: String
    ) {
        _updateBirthday.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val updateBirthday = UpdateBirthDayRequest(
                    birthday = birthDay,
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(updateBirthday)
                if (verifyApi.isSuccessful) {
                    val codeResponse = RetrofitUtil.apiServies.updateBirthDay(updateBirthday)
                    if (codeResponse.isSuccessful) {
                        _updateBirthday.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _updateBirthday.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _updateBirthday.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }


    }
}