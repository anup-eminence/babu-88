package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.GetBankingChannelListRequest
import com.sona.babu88.api.model.request.GetBankingMethodsRequest
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.GetBankingMethodsResponse
import com.sona.babu88.util.AppConstant
import kotlinx.coroutines.launch

class WithdrawViewModel : ViewModel() {
    private val _withdrawMethods = MutableLiveData<ApiResult<GetBankingMethodsResponse?>>()
    val withdrawMethods: LiveData<ApiResult<GetBankingMethodsResponse?>> get() = _withdrawMethods

    private val _userLockedAmount = MutableLiveData<ApiResult<GeneralResponse?>>()
    val userLockedAmount: LiveData<ApiResult<GeneralResponse?>> get() = _userLockedAmount

    private val _withdrawChannel = MutableLiveData<ApiResult<GeneralResponse?>>()
    val withdrawChannel: LiveData<ApiResult<GeneralResponse?>> get() = _withdrawChannel

    fun getWithdrawMethods(
        websiteId: String?
    ) {
        _withdrawMethods.postValue(ApiResult.Loading())
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
                        RetrofitUtil.apiServies.getWithdrawMethods(getBankingMethodsRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _withdrawMethods.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _withdrawMethods.postValue(ApiResult.Error(codeResponse.message()))
                    }
                }
            } catch (e: Exception) {
                _withdrawMethods.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getUserLockedAmount() {
        _userLockedAmount.postValue(ApiResult.Loading())
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
                        RetrofitUtil.apiServies.getUserLockedAmount(generalRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _userLockedAmount.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _userLockedAmount.postValue(ApiResult.Error(codeResponse.body()?.message))
                    }
                }
            } catch (e: Exception) {
                _userLockedAmount.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getWithdrawChannelList(
        websiteId: String?,
        method: String?
    ) {
        _withdrawChannel.postValue(ApiResult.Loading())
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
                        RetrofitUtil.apiServies.getWithdrawChannelList(getBankingChannelListRequest)
                    if (codeResponse.isSuccessful && codeResponse.code() == 200) {
                        _withdrawChannel.postValue(ApiResult.Success(codeResponse.body()))
                    } else {
                        _withdrawChannel.postValue(ApiResult.Error(codeResponse.body()?.message))
                    }
                }
            } catch (e: Exception) {
                _withdrawChannel.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}