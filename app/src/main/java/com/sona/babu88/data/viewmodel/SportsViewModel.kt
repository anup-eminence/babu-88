package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.GetUserMatchDetailRequest
import com.sona.babu88.api.model.request.GetUserSideBarMatchesRequest
import com.sona.babu88.api.model.request.MatchResultListRequest
import com.sona.babu88.api.model.request.PinMatchRequest
import com.sona.babu88.api.model.request.SportsPLRequest
import com.sona.babu88.api.model.request.UserBetListRequest
import com.sona.babu88.api.model.request.UserLoginActivityRequest
import com.sona.babu88.api.model.response.ActiveMultiMarketResponse
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.GetUserSideBarMatchesResponse
import com.sona.babu88.api.model.response.InPlayMatchCountResponse
import com.sona.babu88.api.model.response.PreMatchMarket
import com.sona.babu88.api.model.response.ResultItem
import com.sona.babu88.api.model.response.SelectionIds
import com.sona.babu88.api.model.response.UserLoginActivityResponse
import com.sona.babu88.api.model.response.UserProfileResponse
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class SportsViewModel : ViewModel() {
    private val _userSideBarMatches = MutableLiveData<ApiResult<GetUserSideBarMatchesResponse?>>()
    val userSideBarMatches: LiveData<ApiResult<GetUserSideBarMatchesResponse?>> get() = _userSideBarMatches

    private val _inPlayMatchesCount = MutableLiveData<ApiResult<InPlayMatchCountResponse?>>()
    val inPlayMatchesCount: LiveData<ApiResult<InPlayMatchCountResponse?>> get() = _inPlayMatchesCount

    private val _activeMultiMarket = MutableLiveData<ApiResult<ActiveMultiMarketResponse?>>()
    val activeMultiMarket: LiveData<ApiResult<ActiveMultiMarketResponse?>> get() = _activeMultiMarket

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

    private val _userProfile = MutableLiveData<ApiResult<UserProfileResponse?>>()
    val userProfile: LiveData<ApiResult<UserProfileResponse?>> get() = _userProfile

    private val _userBetsList = MutableLiveData<ApiResult<Any?>>()
    val userBetsList: LiveData<ApiResult<Any?>> get() = _userBetsList

    private val _sportsPL = MutableLiveData<ApiResult<Any?>>()
    val sportsPL: LiveData<ApiResult<Any?>> get() = _sportsPL

    private val _matchResultList = MutableLiveData<ApiResult<Any?>>()
    val matchResultList: LiveData<ApiResult<Any?>> get() = _matchResultList

    private val _userLoginActivity = MutableLiveData<ApiResult<UserLoginActivityResponse?>>()
    val userLoginActivity: LiveData<ApiResult<UserLoginActivityResponse?>> get() = _userLoginActivity

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
                    if (codeResponse.isSuccessful) {
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
                        val gson = Gson()
                        val listType = object : TypeToken<List<SelectionIds>>() {}.type
                        val listType2 = object : TypeToken<List<PreMatchMarket>>() {}.type

                        val selectionIdsList = codeResponse.body()?.selctionIds?.let {
                            it.replace(it.substring(0,1),"[").replace("\"","")
                        }

                        println(">>>>>>>>prematch ${codeResponse.body()?.preMatchMarket}")


                        val selectionIdList: List<SelectionIds> = gson.fromJson(selectionIdsList, listType)
                        val preMatchMarketList: List<PreMatchMarket> = gson.fromJson(codeResponse.body()?.preMatchMarket, listType2)

                        println(">>>selectionIdsList $selectionIdsList")
                        _userMatchDetail.postValue(ApiResult.Success(codeResponse.body()?.copy(selectionIdsList = selectionIdList)?.copy(preMatchMarketList = preMatchMarketList)))
                    } else {
                        _userMatchDetail.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userMatchDetail.postValue(ApiResult.Error(e.message))
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
                        println(">>>>>>>error exception else ")
                        _multiMatchUser.postValue(
                            ApiResult.Error(
                                codeResponse.body()?.message ?: "Something Went Wrong!"
                            )
                        )
                    }
                }
            } catch (e: Exception) {
                println(">>>>>>>error exception")
                _multiMatchUser.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserProfile() {
        _userProfile.postValue(ApiResult.Loading())
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
                        RetrofitUtil.apiServies.getUserProfile(generalRequest)
                    if (codeResponse.isSuccessful) {
                        _userProfile.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userProfile.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userProfile.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserBetsList(
        sportId: String?,
        endDate: String?,
        pageNo: Int?,
        pageSize: Int?,
        startDate: String?,
        status: String?
    ) {
        _userBetsList.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val userBetListRequest = UserBetListRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    sportId = sportId,
                    endDate = endDate,
                    pageNo = pageNo,
                    pageSize = pageSize,
                    startDate = startDate,
                    status = status
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(userBetListRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getUserBetsList(userBetListRequest)
                    if (codeResponse.isSuccessful) {
                        _userBetsList.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userBetsList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userBetsList.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getSportsPL(
        start: String?,
        end: String?
    ) {
        _sportsPL.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val sportsPLRequest = SportsPLRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    start = start,
                    end = end
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(sportsPLRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getSportsPL(sportsPLRequest)
                    if (codeResponse.isSuccessful) {
                        _sportsPL.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _sportsPL.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _sportsPL.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getMatchResultList(
        sportId: String?,
        startDate: String?,
        endDate: String?
    ) {
        _matchResultList.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val matchResultListRequest = MatchResultListRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    sportId = sportId,
                    startDate = startDate,
                    endDate = endDate
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(matchResultListRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getMatchResultList(matchResultListRequest)
                    if (codeResponse.isSuccessful) {
                        _matchResultList.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _matchResultList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _matchResultList.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserLoginActivity(
        pageNo: Int?,
        pageSize: Int
    ) {
        _userLoginActivity.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val userLoginActivityRequest = UserLoginActivityRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    pageNo = pageNo,
                    pageSize = pageSize
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(userLoginActivityRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getUserLoginActivity(userLoginActivityRequest)
                    if (codeResponse.isSuccessful) {
                        _userLoginActivity.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userLoginActivity.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userLoginActivity.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}