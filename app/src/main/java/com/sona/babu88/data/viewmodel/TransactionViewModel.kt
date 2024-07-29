package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import com.google.gson.reflect.TypeToken
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.TotalUserAccountLogsRequest
import com.sona.babu88.api.model.request.TransactionPLRequest
import com.sona.babu88.api.model.request.TransactionPLRequestFull
import com.sona.babu88.api.model.request.TransactionRecordRequest
import com.sona.babu88.api.model.request.UsersPromotionRequest
import com.sona.babu88.api.model.response.PlatFormListResponse
import com.sona.babu88.api.model.response.Provider
import com.sona.babu88.api.model.response.TotalUserAccountLogsResponse
import com.sona.babu88.api.model.response.TransactionPLResponse
import com.sona.babu88.api.model.response.TransactionsRecordsResponse
import com.sona.babu88.api.model.response.Type
import com.sona.babu88.api.model.response.UserSCPackResponse
import com.sona.babu88.api.model.response.UserSCPackResponseItem
import com.sona.babu88.api.model.response.UsersPromotionResponse
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.AppConstant.getTimeStamp
import com.sona.babu88.util.getCurrrentDate
import com.sona.babu88.util.getSeventhDayDate
import kotlinx.coroutines.launch
import org.json.JSONObject


enum class TransactionType(shortKey: String) {
    DEPOSIT("d"),
    WITHDRAWAL("w"),
    ADJUSTMENT("ad")
}

class TransactionViewModel : ViewModel() {

    private val _transactionPL = MutableLiveData<ApiResult<TransactionPLResponse?>>()
    val transactionPL: LiveData<ApiResult<TransactionPLResponse?>> get() = _transactionPL


    private val _transactionPLFull = MutableLiveData<ApiResult<Any?>>()
    val transactionPLFull: LiveData<ApiResult<Any?>> get() = _transactionPLFull

    private val _transactionRecord = MutableLiveData<ApiResult<TransactionsRecordsResponse?>>()
    val transactionRecord: LiveData<ApiResult<TransactionsRecordsResponse?>> get() = _transactionRecord

    private val _platFormList = MutableLiveData<ApiResult<PlatFormListResponse?>>()
    val platFormList: LiveData<ApiResult<PlatFormListResponse?>> get() = _platFormList

    private val _userSCPack = MutableLiveData<ApiResult<UserSCPackResponse?>>()
    val userSCPack: LiveData<ApiResult<UserSCPackResponse?>> get() = _userSCPack

    private val _userPromotions = MutableLiveData<ApiResult<UsersPromotionResponse?>>()
    val userPromotions: LiveData<ApiResult<UsersPromotionResponse?>> get() = _userPromotions

    private val _totalUserAccountLogs = MutableLiveData<ApiResult<TotalUserAccountLogsResponse?>>()
    val totalUserAccountLogs: LiveData<ApiResult<TotalUserAccountLogsResponse?>> get() = _totalUserAccountLogs

    fun getTransactionPl(
        userId: String,
        start: String,
        end: String,
        plat: String,
        gType: String
    ) {
        viewModelScope.launch {
            try {
                _transactionPL.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val plRequest = TransactionPLRequest(
                    userId = userId,
                    start = start,
                    end = end,
                    plat = plat,
                    gType = gType,
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY].toString(),
                )
                val verifyApi = RetrofitUtil.apiServies.verifyUser(plRequest)
                if (verifyApi.isSuccessful) {
                    val req = RetrofitUtil.apiServies.getTransactionPl(plRequest)
                    if (req.isSuccessful) {
                        _transactionPL.postValue(ApiResult.Success(req.body()))
                    } else {
                        _transactionPL.postValue(ApiResult.Error("Something went Went wrong!"))
                    }
                }
            } catch (e: Exception) {
                _transactionPL.postValue(ApiResult.Error("Something went Went wrong!"))
            }
        }
    }

    fun getTransactionPlFull(
        sourceType: String
    ) {
        viewModelScope.launch {
            try {
                _transactionPLFull.postValue(ApiResult.Loading())
                val request = AppConstant.getTimeStamp()
                val plRequest = TransactionPLRequestFull(
                    userId = "ryzen2",
                    start = getSeventhDayDate(),
                    end = getCurrrentDate(),
                    sourceType = sourceType,
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY].toString(),
                )
                val verifyApi = RetrofitUtil.apiServies.verifyUser(plRequest)
                if (verifyApi.isSuccessful) {
                    val req = RetrofitUtil.apiServies.getTransactionPlFull(plRequest)
                    if (req.isSuccessful) {
                        _transactionPLFull.postValue(ApiResult.Success(req.body()))
                    } else {
                        _transactionPLFull.postValue(ApiResult.Error("Something went Went wrong!"))
                    }
                }
            } catch (e: Exception) {
                _transactionPLFull.postValue(ApiResult.Error("Something went Went wrong!"))
            }
        }
    }

    fun getTransactionRecord(
        userId: String,
        start: String,
        end: String,
        transactionType: String,
        status: String
    ) {
        viewModelScope.launch {
            try {
                _transactionRecord.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val transactionRecord = TransactionRecordRequest(
                    userId = userId,
                    start = start,
                    end = end,
                    type = transactionType,
                    status = status,
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY] ?: ""
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(transactionRecord)
                if (verifyApi.isSuccessful && verifyApi.code() == 200) {
                    val response = RetrofitUtil.apiServies.getTransactionRecord(transactionRecord)
                    if (response.isSuccessful && response.code() == 200) {
                        _transactionRecord.postValue(ApiResult.Success(response.body()))
                    } else {
                        _transactionRecord.postValue(ApiResult.Error("Something went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _transactionRecord.postValue(ApiResult.Error("Something went Wrong!"))
            }
        }
    }

    fun getPlatFormList() {
        viewModelScope.launch {
            try {
                _platFormList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY] ?: ""
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful && verifyApi.code() == 200) {
                    val response = RetrofitUtil.apiServies.getPlatFormList(generalRequest)
                    if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                        val code = response.body().toString().replace("\\", "").let { (it.replace(it.substring(0,6),"")) }
                        val platFormList = mutableListOf<Provider>()
                        try {
                            val data = JSONObject(code)
                            val providersJsonArray = data.getJSONArray("Providers")
                            for (i in 0 until providersJsonArray.length()) {
                                val providerJsonObject = providersJsonArray.getJSONObject(i)
                                val providerName = providerJsonObject.getString("provider_name")
                                val company = providerJsonObject.getString("company")
                                val providerId = providerJsonObject.getString("provider_id")
                                val typesJsonArray = providerJsonObject.getJSONArray("types")
                                val types = mutableListOf<Type>()
                                for (i in 0 until typesJsonArray.length()) {
                                    val typeJsonObject = typesJsonArray.getJSONObject(i)
                                    val typeId = typeJsonObject.getString("type_id")
                                    val typeName = typeJsonObject.getString("type_name")
                                    val type = Type(typeId, typeName)
                                    types.add(type)
                                }
                                platFormList.add(Provider(providerName, company, providerId, types))
                            }

                        } catch (e: JsonSyntaxException) {
                            println(">>>>>>>>>>>>>Exception  ${e.message}")
                        }
                        val platFormListResponse = PlatFormListResponse(platFormList)
                        _platFormList.postValue(ApiResult.Success(platFormListResponse))
                    } else {
                        println(">>>>>>>>>>>>>Else ${response.message()}")
                        _platFormList.postValue(ApiResult.Error("Something went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                println(">>>>>>>>>>>>>Exception ${e.message}")
                _transactionRecord.postValue(ApiResult.Error("Something went Wrong!"))
            }
        }
    }

    fun getUserSCPack() {
        viewModelScope.launch {
            try {
                _userSCPack.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY] ?: ""
                )
                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful && verifyApi.code() == 200) {
                    val response = RetrofitUtil.apiServies.getUserSCPack(generalRequest)
                    if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                        val userSCPackListType = object : TypeToken<List<UserSCPackResponseItem>>() {}.type
                        val userSCPackList: List<UserSCPackResponseItem> = Gson().fromJson(response.body().toString(), userSCPackListType)
                        val platFormListResponse = UserSCPackResponse(userSCPackList)
                        _userSCPack.postValue(ApiResult.Success(platFormListResponse))
                    } else {
                        _userSCPack.postValue(ApiResult.Error("Something went Went wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userSCPack.postValue(ApiResult.Error("Something went Went wrong!"))
            }
        }
    }

    fun getUsersPromotions(
        pageNo: Int,
        status: Int
    ) {
        viewModelScope.launch {
            try {
                _userPromotions.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val usersPromotionRequest = UsersPromotionRequest(
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY].toString(),
                    pageNo = pageNo,
                    status = status
                )
                val verifyApi = RetrofitUtil.apiServies.verifyUser(usersPromotionRequest)
                if (verifyApi.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getUsersPromotions(usersPromotionRequest)
                    if (response.isSuccessful) {
                        _userPromotions.postValue(ApiResult.Success(response.body()))
                    } else {
                        _userPromotions.postValue(ApiResult.Error("Something went Went wrong!"))
                    }
                }
            } catch (e: Exception) {
                _userPromotions.postValue(ApiResult.Error("Something went Went wrong!"))
            }
        }
    }

    fun getTotalUserAccountLogs(
        pageNo: Int,
        type: String
    ) {
        viewModelScope.launch {
            try {
                _totalUserAccountLogs.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val totalUserAccountLogsRequest = TotalUserAccountLogsRequest(
                    timeStamp = request[AppConstant.TIMESTAMP].toString(),
                    secretKey = request[AppConstant.SECRET_KEY].toString(),
                    pageNo = pageNo,
                    type = type
                )
                val verifyApi = RetrofitUtil.apiServies.verifyUser(totalUserAccountLogsRequest)
                if (verifyApi.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getTotalUserAccountLogs(totalUserAccountLogsRequest)
                    if (response.isSuccessful) {
                        _totalUserAccountLogs.postValue(ApiResult.Success(response.body()))
                    } else {
                        _totalUserAccountLogs.postValue(ApiResult.Error("Something went Went wrong!"))
                    }
                }
            } catch (e: Exception) {
                _totalUserAccountLogs.postValue(ApiResult.Error("Something went Went wrong!"))
            }
        }
    }
}