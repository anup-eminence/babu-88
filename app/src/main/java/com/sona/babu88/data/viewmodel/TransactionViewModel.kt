package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.TransactionPLRequest
import com.sona.babu88.api.model.request.TransactionPLRequestFull
import com.sona.babu88.api.model.request.TransactionRecordRequest
import com.sona.babu88.util.AppConstant
import com.sona.babu88.util.AppConstant.getTimeStamp
import com.sona.babu88.util.getCurrrentDate
import com.sona.babu88.util.getSeventhDayDate
import kotlinx.coroutines.launch


enum class TransactionType(shortKey: String) {
    DEPOSIT("d"),
    WITHDRAWAL("w"),
    ADJUSTMENT("ad")
}

class TransactionViewModel : ViewModel() {

    private val _transactionPL = MutableLiveData<ApiResult<Any?>>()
    val transactionPL: LiveData<ApiResult<Any?>> get() = _transactionPL


    private val _transactionPLFull = MutableLiveData<ApiResult<Any?>>()
    val transactionPLFull: LiveData<ApiResult<Any?>> get() = _transactionPLFull

    private val _transactionRecord = MutableLiveData<ApiResult<Any?>>()
    val transactionRecord: LiveData<ApiResult<Any?>> get() = _transactionRecord

    fun getTransactionPl() {
        viewModelScope.launch {
            try {
                _transactionPL.postValue(ApiResult.Loading())
                val request = AppConstant.getTimeStamp()
                val plRequest = TransactionPLRequest(
                    userId = "ryzen2",
                    start = getSeventhDayDate(),
                    end = getCurrrentDate(),
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
        transactionType: String
    ) {
        viewModelScope.launch {
            try {
                _transactionRecord.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val transactionRecord = TransactionRecordRequest(
                    userId = "ryzen2",
                    start = getSeventhDayDate(),
                    end = getCurrrentDate(),
                    type = transactionType,
                    status = "app",
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

}