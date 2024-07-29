package com.sona.babu88.data.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sona.babu88.api.ApiResult
import com.sona.babu88.api.RetrofitUtil
import com.sona.babu88.api.model.request.GameListRequest
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.PromoFilterRequest
import com.sona.babu88.api.model.request.PromotionListRequest
import com.sona.babu88.api.model.request.SpecialGameListRequest
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.api.model.response.MessageWebsiteResponse
import com.sona.babu88.api.model.response.PointListResponse
import com.sona.babu88.api.model.response.PromoFilterResponse
import com.sona.babu88.api.model.response.PromotionListResponse
import com.sona.babu88.api.model.response.RewardsBenefitsResponse
import com.sona.babu88.api.model.response.SpecialGameListResponse
import com.sona.babu88.api.model.response.TierCommDetailsResponse
import com.sona.babu88.api.model.response.VipLevelsResponse
import com.sona.babu88.util.AppConstant.SECRET_KEY
import com.sona.babu88.util.AppConstant.TIMESTAMP
import com.sona.babu88.util.AppConstant.getTimeStamp
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _gameList = MutableLiveData<ApiResult<GameListResponse?>>()
    val gameList: LiveData<ApiResult<GameListResponse?>> get() = _gameList

    private val _specialGameList = MutableLiveData<ApiResult<SpecialGameListResponse?>>()
    val specialGameList: LiveData<ApiResult<SpecialGameListResponse?>> get() = _specialGameList

    private val _promotionList = MutableLiveData<ApiResult<PromotionListResponse?>>()
    val promotionList: LiveData<ApiResult<PromotionListResponse?>> get() = _promotionList

    private val _promotionFilter = MutableLiveData<ApiResult<List<PromoFilterResponse>?>>()
    val promotionFilter: LiveData<ApiResult<List<PromoFilterResponse>?>> get() = _promotionFilter

    private val _messageWebsite = MutableLiveData<ApiResult<MessageWebsiteResponse?>>()
    val messageWebsite: LiveData<ApiResult<MessageWebsiteResponse?>> get() = _messageWebsite

    private val _tierCommDetails = MutableLiveData<ApiResult<TierCommDetailsResponse?>>()
    val tierCommDetails: LiveData<ApiResult<TierCommDetailsResponse?>> get() = _tierCommDetails

    private val _vipLevels = MutableLiveData<ApiResult<List<VipLevelsResponse>?>>()
    val vipLevels: LiveData<ApiResult<List<VipLevelsResponse>?>> get() = _vipLevels

    private val _rewardsBenefits = MutableLiveData<ApiResult<List<RewardsBenefitsResponse>?>>()
    val rewardsBenefits: LiveData<ApiResult<List<RewardsBenefitsResponse>?>> get() = _rewardsBenefits

    private val _pointList = MutableLiveData<ApiResult<List<PointListResponse>?>>()
    val pointList: LiveData<ApiResult<List<PointListResponse>?>> get() = _pointList

    fun getGameList(
        provider: String,
        category: String,
        page: Int,
    ) {
        viewModelScope.launch {
            try {
                _gameList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val game = GameListRequest(
                    provider = provider,
                    category = category,
                    page = page,
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY] ?: ""
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(game)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getGameList(game)
                    if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                        ApiResult.Success(response.body()).let { _gameList.postValue(it) }
                    } else {
                        _gameList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
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
                val game = SpecialGameListRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(game)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getSpecialGameList(game)
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


    fun getPromotionList(
        pageNo: Int
    ) {
        viewModelScope.launch {
            try {
                _promotionList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val promoRequest = PromotionListRequest(
                    timestamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString(),
                    pageNo = pageNo
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(promoRequest)
                if (verifyUser.isSuccessful) {
                    val promoResponse = RetrofitUtil.apiServies.getPromotionList(promoRequest)
                    if (promoResponse.isSuccessful) {
//                        println(">>>>>>>>>>>>>>>>>>> ${promoResponse.body()?.getData()}")
//                        println(">>>>>>>>>>>>>>>>>>> ${promoResponse.body()}")
                        _promotionList.postValue(ApiResult.Success(data = promoResponse.body()))
                    } else {
                        _promotionList.postValue(ApiResult.Error(message = "Something went wrong!"))
                    }
                }

            } catch (e: Exception) {
                _promotionList.postValue(ApiResult.Error(message = "Something went wrong!"))
            }
        }
    }

    fun getPromoFilters() {
        viewModelScope.launch {
            _promotionFilter.postValue(ApiResult.Loading())
            try {
                val request = getTimeStamp()
                val promoRequest = PromoFilterRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(promoRequest)

                if (verifyUser.isSuccessful) {
                    val promoFilter = RetrofitUtil.apiServies.getPromoFilters(promoRequest)
                    if (promoFilter.isSuccessful) {
                        _promotionFilter.postValue(ApiResult.Success(promoFilter.body()))
                    } else {
                        _promotionFilter.postValue(ApiResult.Error("Something went Wrong!"))
                    }
                } else {
                    _promotionFilter.postValue(ApiResult.Error("Something went Wrong!"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _promotionFilter.postValue(ApiResult.Error("Something went Wrong!"))
            }
        }
    }

    fun getMessageWebsite() {
        viewModelScope.launch {
            _messageWebsite.postValue(ApiResult.Loading())
            try {
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(generalRequest)

                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getMessageWebsite(generalRequest)
                    if (response.isSuccessful && response.code() == 200) {
                        _messageWebsite.postValue(ApiResult.Success(response.body()))
                    } else {
                        _messageWebsite.postValue(ApiResult.Error("Something went Wrong!"))
                    }
                } else {
                    _messageWebsite.postValue(ApiResult.Error("Something went Wrong!"))
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _messageWebsite.postValue(ApiResult.Error("Something went Wrong!"))
            }
        }
    }

    fun getTierCommDetails() {
        viewModelScope.launch {
            try {
                _tierCommDetails.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getTierCommDetails(generalRequest)
                    if (response.isSuccessful && response.code() == 200 && response.body() != null) {
                        _tierCommDetails.postValue(ApiResult.Success(response.body()))
                    } else {
                        _tierCommDetails.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _tierCommDetails.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getVipLevels() {
        viewModelScope.launch {
            try {
                _vipLevels.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getVipLevels(generalRequest)
                    if (response.isSuccessful) {
                        _vipLevels.postValue(ApiResult.Success(response.body()))
                    } else {
                        _vipLevels.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _vipLevels.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getRewAndBen() {
        viewModelScope.launch {
            try {
                _rewardsBenefits.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getRewAndBen(generalRequest)
                    if (response.isSuccessful) {
                        _rewardsBenefits.postValue(ApiResult.Success(response.body()))
                    } else {
                        _rewardsBenefits.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _rewardsBenefits.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }

    fun getPointList() {
        viewModelScope.launch {
            try {
                _pointList.postValue(ApiResult.Loading())
                val request = getTimeStamp()
                val generalRequest = GeneralRequest(
                    timeStamp = request[TIMESTAMP].toString(),
                    secretKey = request[SECRET_KEY].toString()
                )
                val verifyUser = RetrofitUtil.apiServies.verifyUser(generalRequest)
                if (verifyUser.isSuccessful) {
                    val response = RetrofitUtil.apiServies.getPointList(generalRequest)
                    if (response.isSuccessful) {
                        _pointList.postValue(ApiResult.Success(response.body()))
                    } else {
                        _pointList.postValue(ApiResult.Error("Something Went Wrong!"))
                    }
                }
            } catch (e: Exception) {
                _pointList.postValue(ApiResult.Error("Something Went Wrong!"))
            }
        }
    }
}