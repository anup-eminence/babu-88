package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.GetUserMatchDetailRequest
import com.sona.babu88.api.model.request.GetUserSideBarMatchesRequest
import com.sona.babu88.api.model.request.PinMatchRequest
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.GetUserSideBarMatchesResponse
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SportsViewModel : ViewModel() {
    private val _userSideBarMatches = MutableLiveData<ApiResult<GetUserSideBarMatchesResponse?>>()
    val userSideBarMatches: LiveData<ApiResult<GetUserSideBarMatchesResponse?>> get() = _userSideBarMatches

    private val _inPlayMatchesCount = MutableLiveData<ApiResult<Any?>>()
    val inPlayMatchesCount: LiveData<ApiResult<Any?>> get() = _inPlayMatchesCount

    private val _activeMultiMarket = MutableLiveData<ApiResult<Any?>>()
    val activeMultiMarket: LiveData<ApiResult<Any?>> get() = _activeMultiMarket

    private val _inPlayMatches = MutableLiveData<ApiResult<GetUserSideBarMatchesResponse?>>()
    val inPlayMatches: LiveData<ApiResult<GetUserSideBarMatchesResponse?>> get() = _inPlayMatches

    private val _todayMatches = MutableLiveData<ApiResult<GetUserSideBarMatchesResponse?>>()
    val todayMatches: LiveData<ApiResult<GetUserSideBarMatchesResponse?>> get() = _todayMatches

    private val _tomorrowMatches = MutableLiveData<ApiResult<GetUserSideBarMatchesResponse?>>()
    val tomorrowMatches: LiveData<ApiResult<GetUserSideBarMatchesResponse?>> get() = _tomorrowMatches

    val cricketData = MutableLiveData<List<ResultItem?>?>()
    val soccerData = MutableLiveData<List<ResultItem?>?>()
    val tennisData = MutableLiveData<List<ResultItem?>?>()

    private val _userMatchDetail = MutableLiveData<ApiResult<GetUserMatchDetailResponse?>>()
    val userMatchDetail: LiveData<ApiResult<GetUserMatchDetailResponse?>> get() = _userMatchDetail

    private val _multiMatchUser = MutableLiveData<ApiResult<GeneralResponse?>>()
    val multiMatchUser: LiveData<ApiResult<GeneralResponse?>> get() = _multiMatchUser

    fun getUserSideBarMatches(
        sportId: String?
    ) {
        _userSideBarMatches.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val getUserSideBarMatchesReq = GetUserSideBarMatchesRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    sportId = sportId
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(getUserSideBarMatchesReq)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getUserSideBarMatches(getUserSideBarMatchesReq)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _userSideBarMatches.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userSideBarMatches.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userSideBarMatches.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getInPlayMatchesCount() {
        _inPlayMatchesCount.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getInPlayMatchesCount(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _inPlayMatchesCount.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _inPlayMatchesCount.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _inPlayMatchesCount.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getActiveMultiMarket() {
        _activeMultiMarket.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getActiveMultiMarket(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _activeMultiMarket.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _activeMultiMarket.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _activeMultiMarket.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getInPlayMatches() {
        _inPlayMatches.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getInPlayMatches(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        val response = codeResponse.body()?.results
                        val cricket = async {
                            response?.filter {
                                it?.sportId == 4
                            }
                        }.await()

                        val soccer = async {
                            response?.filter {
                                it?.sportId == 1
                            }
                        }.await()

                        val tennis = async {
                            response?.filter {
                                it?.sportId == 2
                            }
                        }.await()

                        cricketData.postValue(cricket)
                        soccerData.postValue(soccer)
                        tennisData.postValue(tennis)

                        _inPlayMatches.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _inPlayMatches.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _inPlayMatches.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getTodayMatches() {
        _todayMatches.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getTodayMatches(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        val response = codeResponse.body()?.results
                        val cricket = async {
                            response?.filter {
                                it?.sportId == 4
                            }
                        }.await()

                        val soccer = async {
                            response?.filter {
                                it?.sportId == 1
                            }
                        }.await()

                        val tennis = async {
                            response?.filter {
                                it?.sportId == 2
                            }
                        }.await()

                        cricketData.postValue(cricket)
                        soccerData.postValue(soccer)
                        tennisData.postValue(tennis)

                        _todayMatches.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _todayMatches.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _todayMatches.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getTomorrowMatches() {
        _tomorrowMatches.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY]
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getTomorrowMatches(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        val response = codeResponse.body()?.results
                        val cricket = async {
                            response?.filter {
                                it?.sportId == 4
                            }
                        }.await()

                        val soccer = async {
                            response?.filter {
                                it?.sportId == 1
                            }
                        }.await()

                        val tennis = async {
                            response?.filter {
                                it?.sportId == 2
                            }
                        }.await()

                        cricketData.postValue(cricket)
                        soccerData.postValue(soccer)
                        tennisData.postValue(tennis)

                        _tomorrowMatches.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _tomorrowMatches.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _tomorrowMatches.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserMatchDetail(
        eventId: String?
    ) {
        _userMatchDetail.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val getUserMatchDetailRequest = GetUserMatchDetailRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    eventId = eventId
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(getUserMatchDetailRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getUserMatchDetail(getUserMatchDetailRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _userMatchDetail.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userMatchDetail.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userMatchDetail.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getMultiMatchUser(
        matchId: String?
    ) {
        _multiMatchUser.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val pinMatchRequest = PinMatchRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    matchId = matchId
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(pinMatchRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getMultiMatchUser(pinMatchRequest)
                    if ((codeResponse.isSuccessful && codeResponse.code() == 200) || (codeResponse.isSuccessful && codeResponse.body()?.message.isNullOrEmpty()
                            .not() && codeResponse.body()?.message!!.contains(
                            "Added Successfully",
                            ignoreCase = true
                        ))
                    ) {
                        _multiMatchUser.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _multiMatchUser.postValue(
                            ApiResult.Error(
                                codeResponse.body()?.message ?: "Something Went Wrong!"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                _multiMatchUser.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}