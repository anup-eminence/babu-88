package com.sona.babu88.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.GameListRequest
import com.sona.babu88.api.model.request.SpecialGameListRequest
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.api.model.response.SpecialGameListResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.util.AppConstant.SECRET_KEY
import com.sona.babu88.util.AppConstant.TIMESTAMP
import com.sona.babu88.util.AppConstant.getTimeStamp
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _authenticatUser  = MutableLiveData<ApiResult<UserData?>>()
    val authenticateUser : LiveData<ApiResult<UserData?>> get() = _authenticatUser

    private val _gameList  = MutableLiveData<ApiResult<GameListResponse?>>()
    val gameList : LiveData<ApiResult<GameListResponse?>> get() = _gameList

    private val _specialGameList  = MutableLiveData<ApiResult<SpecialGameListResponse?>>()
    val specialGameList : LiveData<ApiResult<SpecialGameListResponse?>> get() = _specialGameList


    fun authenticateUser(
        userId: String?,
        passWord: String?,
        host: String?,
        ipad: String?
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


    fun getGameList(
        provider : String,
        category: String,
        page : Int,
    ) {
        viewModelScope.launch {
            try {
                _gameList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                println(">>>>>timestamp ${request[TIMESTAMP]}")
                println(">>>>>SECRET_KEY ${request[SECRET_KEY]}")
                val game = GameListRequest(
                    provider = provider,
                    category = category,
                    page = page,
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY] ?: ""
                )
               val verifyUser = RetrofitUtil.apiServies.verifyUser(game)
                if (verifyUser.isSuccessful){
                    val response = RetrofitUtil.apiServies.getGameList(game)
                    println(">>>>response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && response.body() != null){
                        ApiResult.Success(response.body()).let { _gameList.postValue(it) }
                    } else {
                        _gameList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            }catch (e: Exception) {
                println(">>>>>>e. ${e.printStackTrace()}")
                _gameList.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }


    fun getSpecialGameList() {
        viewModelScope.launch {
            try {
                _specialGameList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                println(">>>>>timestamp ${request[TIMESTAMP]}")
                println(">>>>>SECRET_KEY ${request[SECRET_KEY]}")
                val game = SpecialGameListRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(game)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getSpecialGameList(game)
                    println(">>>>response ${response.body()}")
                    if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                        ApiResult.Success(response.body()).let { _specialGameList.postValue(it) }
                    } else {
                        _specialGameList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _specialGameList.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}