package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.GetBankingChannelListRequest
import com.sona.babu88.api.model.request.GetBankingMethodsRequest
import com.sona.babu88.api.model.response.GetBankingChannelListResponse
import com.sona.babu88.api.model.response.GetBankingMethodsResponse
import com.sona.babu88.api.model.response.PromotionListResponse
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.launch

class DepositViewModel : ViewModel() {
    private val _bankingMethods = MutableLiveData<ApiResult<GetBankingMethodsResponse?>>()
    val bankingMethods: LiveData<ApiResult<GetBankingMethodsResponse?>> get() = _bankingMethods

    private val _bankingChannel = MutableLiveData<ApiResult<GetBankingChannelListResponse?>>()
    val bankingChannel: LiveData<ApiResult<GetBankingChannelListResponse?>> get() = _bankingChannel

    private val _depositPromotions = MutableLiveData<ApiResult<PromotionListResponse?>>()
    val depositPromotions: LiveData<ApiResult<PromotionListResponse?>> get() = _depositPromotions

    fun getBankingMethods(
        websiteId: String?
    ) {
        _bankingMethods.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val getBankingMethodsRequest = GetBankingMethodsRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    websiteId = websiteId
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(getBankingMethodsRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getBankingMethods(getBankingMethodsRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _bankingMethods.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        // _bankingMethods.postValue(ApiResult.Error(codeResponse.body()?.ok.toString()))
                    }
                }
            } catch (e: Exception) {
                _bankingMethods.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getBankingChannelList(
        websiteId: String?,
        method: String?
    ) {
        _bankingChannel.postValue(ApiResult.Loading())
        viewModelScope.launch {
            try {
                val request = AppConstant.getTimeStamp()
                val getBankingChannelListRequest = GetBankingChannelListRequest(
                    timeStamp = request[AppConstant.TIMESTAMP],
                    secretKey = request[AppConstant.SECRET_KEY],
                    websiteId = websiteId,
                    method = method
                )

                val verifyApi = RetrofitUtil.apiServies.verifyUser(getBankingChannelListRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getBankingChannelList(getBankingChannelListRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _bankingChannel.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
//                         _bankingChannel.postValue(ApiResult.Error(codeResponse.body()?))
                    }
                }
            } catch (e: Exception) {
                _bankingChannel.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getDepositPromotionsList() {
        viewModelScope.launch {
            _depositPromotions.postValue(ApiResult.Loading())
            val request = AppConstant.getTimeStamp()
            val generalRequest = GeneralRequest(
                timeStamp = request[AppConstant.TIMESTAMP],
                secretKey = request[AppConstant.SECRET_KEY]
            )
            try {
                val verifyApi = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyApi.isSuccessful) {
                    val codeResponse =
                        RetrofitUtil.apiServies.getDepositPromotionsList(generalRequest)
                    val code = codeResponse.body().toString().let {
                        it.replace(it.substring(0,6),"{\"data\":")
                    }
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _depositPromotions.postValue(ApiResult.Success(Gson().fromJson(code,PromotionListResponse::class.java)))
                    } else {
                        _depositPromotions.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _depositPromotions.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}