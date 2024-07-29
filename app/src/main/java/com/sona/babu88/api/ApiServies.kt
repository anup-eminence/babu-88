package com.sona.babu88.api

import com.sona.babu88.api.auth.CheckUserLogin
import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.ChangePasswordRequest
import com.sona.babu88.api.model.request.CreateDepositRequest
import com.sona.babu88.api.model.request.CurrencyRequest
import com.sona.babu88.api.model.request.GameListRequest
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.GetBankingChannelListRequest
import com.sona.babu88.api.model.request.GetBankingMethodsRequest
import com.sona.babu88.api.model.request.GetBanksListRequest
import com.sona.babu88.api.model.request.GetUserMatchDetailRequest
import com.sona.babu88.api.model.request.GetUserSideBarMatchesRequest
import com.sona.babu88.api.model.request.MatchResultListRequest
import com.sona.babu88.api.model.request.PinMatchRequest
import com.sona.babu88.api.model.request.PromoFilterRequest
import com.sona.babu88.api.model.request.PromotionListRequest
import com.sona.babu88.api.model.request.RegisterUserRequest
import com.sona.babu88.api.model.request.SpecialGameListRequest
import com.sona.babu88.api.model.request.SportsPLRequest
import com.sona.babu88.api.model.request.TotalUserAccountLogsRequest
import com.sona.babu88.api.model.request.TransactionPLRequest
import com.sona.babu88.api.model.request.TransactionPLRequestFull
import com.sona.babu88.api.model.request.TransactionRecordRequest
import com.sona.babu88.api.model.request.UpdateBirthDayRequest
import com.sona.babu88.api.model.request.UserBetListRequest
import com.sona.babu88.api.model.request.UserLoginActivityRequest
import com.sona.babu88.api.model.request.UsersPromotionRequest
import com.sona.babu88.api.model.request.VerifyEmailCodeRequest
import com.sona.babu88.api.model.request.VerifyEmailRequest
import com.sona.babu88.api.model.response.ActiveMultiMarketResponse
import com.sona.babu88.api.model.response.CurrencyResponse
import com.sona.babu88.api.model.response.EmailVerificationResponse
import com.sona.babu88.api.model.response.EmailVerifiedResponse
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.GetBankingChannelListResponse
import com.sona.babu88.api.model.response.GetBankingMethodsResponse
import com.sona.babu88.api.model.response.GetUserMatchDetailResponse
import com.sona.babu88.api.model.response.GetUserSideBarMatchesResponse
import com.sona.babu88.api.model.response.InPlayMatchCountResponse
import com.sona.babu88.api.model.response.MessageWebsiteResponse
import com.sona.babu88.api.model.response.PointListResponse
import com.sona.babu88.api.model.response.PromoFilterResponse
import com.sona.babu88.api.model.response.PromotionListResponse
import com.sona.babu88.api.model.response.RegisterUserResponse
import com.sona.babu88.api.model.response.RewardsBenefitsResponse
import com.sona.babu88.api.model.response.SpecialGameListResponse
import com.sona.babu88.api.model.response.TierCommDetailsResponse
import com.sona.babu88.api.model.response.TotalUserAccountLogsResponse
import com.sona.babu88.api.model.response.TransactionPLResponse
import com.sona.babu88.api.model.response.TransactionsRecordsResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.api.model.response.UserDetailsResponse
import com.sona.babu88.api.model.response.UserLoginActivityResponse
import com.sona.babu88.api.model.response.UserProfileResponse
import com.sona.babu88.api.model.response.UserVipDetailsResponse
import com.sona.babu88.api.model.response.UsersPromotionResponse
import com.sona.babu88.api.model.response.VipLevelsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiServies {

    @POST(ApiConstants.AUTHENTICATE_USER)
    suspend fun authenticateUser(@Body authenticateUserRequest: AuthenticateUserRequest) : Response<UserData>

    @POST(ApiConstants.GET_GAME_LIST)
    suspend fun getGameList(@Body gameListRequest: GameListRequest) : Response<GameListResponse>

    @POST(ApiConstants.VERIFY_USER)
    suspend fun verifyUser(@Body body: Any) : Response<Any>

    @POST(ApiConstants.GET_SPECIAL_GAME_LIST)
    suspend fun getSpecialGameList(@Body specialGameListRequest: SpecialGameListRequest) : Response<SpecialGameListResponse>

    @POST(ApiConstants.GET_LOGIN_TOKEN)
   // @Headers("Content-Type: application/json")
    suspend fun getLoginToken(@Body body: CheckUserLogin.ScreatKey) : Response<Any>

    @POST(ApiConstants.GET_PROMOTION_LIST)
    suspend fun getPromotionList(@Body promoRequest : PromotionListRequest) : Response<PromotionListResponse>

    @POST(ApiConstants.GET_PROMOTION_FILTER)
    suspend fun getPromoFilters(@Body promoFilterRequest: PromoFilterRequest) : Response<List<PromoFilterResponse>>

    @POST(ApiConstants.GET_SIGNUP_CURRENCY)
    suspend fun getSignupCurrency(@Body currencyRequest: CurrencyRequest) : Response<List<CurrencyResponse>>

    @POST(ApiConstants.VALIDATE_USER_SIGNUP)
    suspend fun validateUserSignup(@Body registerUserRequest: RegisterUserRequest) : Response<RegisterUserResponse>

    @POST(ApiConstants.GET_TRANSACTION_PL)
    suspend fun getTransactionPl(@Body transactionPLRequest: TransactionPLRequest) : Response<TransactionPLResponse>

    @POST(ApiConstants.GET_TRANSACTION_PL_FULL)
    suspend fun getTransactionPlFull(@Body transactionPLRequest: TransactionPLRequestFull) : Response<Any>

    @POST(ApiConstants.GET_TRANSACTION_RECORD)
    suspend fun getTransactionRecord(@Body transactionRecordRequest: TransactionRecordRequest) : Response<TransactionsRecordsResponse>

    @POST(ApiConstants.GET_USER_DETAILS)
    suspend fun getUserDetails(@Body generalRequest: GeneralRequest) : Response<UserDetailsResponse>

    @POST(ApiConstants.GET_EMAIL_VERIFICATION_CODE)
    suspend fun getEmailVerificationCode(@Body emailRequest: VerifyEmailRequest) : Response<EmailVerificationResponse>

    @POST(ApiConstants.VERIFICATION_EMAIL)
    suspend fun verifyEmail(@Body verifyEmailRequest: VerifyEmailCodeRequest) : Response<EmailVerifiedResponse>

    @POST(ApiConstants.UPDATE_BIRTHDAY)
    suspend fun updateBirthDay(@Body birthdayRequest: UpdateBirthDayRequest) : Response<GeneralResponse>

    @POST(ApiConstants.CHANGE_USER_PASSWORD)
    suspend fun changeUserPassword(@Body changePasswordRequest: ChangePasswordRequest): Response<GeneralResponse>

    @POST(ApiConstants.GET_BANKING_METHODS)
    suspend fun getBankingMethods(@Body getBankingMethodsRequest: GetBankingMethodsRequest): Response<GetBankingMethodsResponse>

    @POST(ApiConstants.GET_BANKING_CHANNEL_LIST)
    suspend fun getBankingChannelList(@Body getBankingChannelListRequest: GetBankingChannelListRequest): Response<GetBankingChannelListResponse>

    @POST(ApiConstants.GET_DEPOSIT_PROMOTIONS_LIST)
    suspend fun getDepositPromotionsList(@Body generalRequest: GeneralRequest): Response<Any>

    @POST(ApiConstants.CREATE_DEPOSIT_REQUEST)
    suspend fun createDepositRequest(@Body createDepositRequest: CreateDepositRequest): Response<GeneralResponse>

    @POST(ApiConstants.GET_WITHDRAW_METHODS)
    suspend fun getWithdrawMethods(@Body getBankingMethodsRequest: GetBankingMethodsRequest): Response<GetBankingMethodsResponse>

    @POST(ApiConstants.GET_USER_LOCKED_AMOUNT)
    suspend fun getUserLockedAmount(@Body generalRequest: GeneralRequest): Response<GeneralResponse>

    @POST(ApiConstants.GET_WITHDRAW_CHANNEL_LIST)
    suspend fun getWithdrawChannelList(@Body getBankingChannelListRequest: GetBankingChannelListRequest): Response<GeneralResponse>

    @POST(ApiConstants.GET_BANKS_LIST)
    suspend fun getBanksList(@Body getBanksListRequest: GetBanksListRequest): Response<Any>

    @POST(ApiConstants.GET_USER_SIDE_BAR_MATCHES)
    suspend fun getUserSideBarMatches(@Body getUserSideBarMatchesRequest: GetUserSideBarMatchesRequest): Response<GetUserSideBarMatchesResponse>

    @POST(ApiConstants.GET_IN_PLAY_MATCHES_COUNT)
    suspend fun getInPlayMatchesCount(@Body generalRequest: GeneralRequest): Response<InPlayMatchCountResponse>

    @POST(ApiConstants.GET_ACTIVE_MULTI_MARKET)
    suspend fun getActiveMultiMarket(@Body generalRequest: GeneralRequest): Response<ActiveMultiMarketResponse>

    @POST(ApiConstants.GET_IN_PLAY_MATCHES)
    suspend fun getInPlayMatches(@Body generalRequest: GeneralRequest): Response<GetUserSideBarMatchesResponse>

    @POST(ApiConstants.GET_TODAY_MATCHES)
    suspend fun getTodayMatches(@Body generalRequest: GeneralRequest): Response<GetUserSideBarMatchesResponse>

    @POST(ApiConstants.GET_TOMORROW_MATCHES)
    suspend fun getTomorrowMatches(@Body generalRequest: GeneralRequest): Response<GetUserSideBarMatchesResponse>

    @POST(ApiConstants.GET_USER_MATCH_DETAIL)
    suspend fun getUserMatchDetail(@Body getUserMatchDetailRequest: GetUserMatchDetailRequest): Response<GetUserMatchDetailResponse>

    @POST(ApiConstants.GET_MULTI_MATCH_USER)
    suspend fun getMultiMatchUser(@Body pinMatchRequest: PinMatchRequest): Response<GeneralResponse>

    @POST(ApiConstants.GET_USER_PROFILE)
    suspend fun getUserProfile(@Body generalRequest: GeneralRequest): Response<UserProfileResponse>

    @POST(ApiConstants.GET_USER_BETS_LIST)
    suspend fun getUserBetsList(@Body userBetListRequest: UserBetListRequest): Response<Any>

    @POST(ApiConstants.GET_SPORTS_PL)
    suspend fun getSportsPL(@Body sportsPLRequest: SportsPLRequest): Response<Any>

    @POST(ApiConstants.GET_MATCH_RESULT_LIST)
    suspend fun getMatchResultList(@Body matchResultListRequest: MatchResultListRequest): Response<Any>

    @POST(ApiConstants.GET_USER_LOGIN_ACTIVITY)
    suspend fun getUserLoginActivity(@Body userLoginActivityRequest: UserLoginActivityRequest): Response<UserLoginActivityResponse>

    @POST(ApiConstants.GET_MESSAGE_WEBSITE)
    suspend fun getMessageWebsite(@Body generalRequest: GeneralRequest): Response<MessageWebsiteResponse>

    @POST(ApiConstants.GET_PLAT_FORM_LIST)
    suspend fun getPlatFormList(@Body generalRequest: GeneralRequest): Response<Any>

    @POST(ApiConstants.GET_USER_SC_PACK)
    suspend fun getUserSCPack(@Body generalRequest: GeneralRequest): Response<Any>

    @POST(ApiConstants.GET_TIER_COMM_DETAILS)
    suspend fun getTierCommDetails(@Body generalRequest: GeneralRequest): Response<TierCommDetailsResponse>

    @POST(ApiConstants.GET_USERS_PROMOTIONS)
    suspend fun getUsersPromotions(@Body usersPromotionRequest: UsersPromotionRequest): Response<UsersPromotionResponse>

    @POST(ApiConstants.GET_TOTAL_USER_ACCOUNT_LOGS)
    suspend fun getTotalUserAccountLogs(@Body totalUserAccountLogsRequest: TotalUserAccountLogsRequest): Response<TotalUserAccountLogsResponse>

    @POST(ApiConstants.GET_USER_VIP_DETAILS)
    suspend fun getUserVipDetails(@Body generalRequest: GeneralRequest): Response<UserVipDetailsResponse>

    @POST(ApiConstants.GET_VIP_LEVELS)
    suspend fun getVipLevels(@Body generalRequest: GeneralRequest): Response<List<VipLevelsResponse>>

    @POST(ApiConstants.GET_REW_AND_BEN)
    suspend fun getRewAndBen(@Body generalRequest: GeneralRequest): Response<List<RewardsBenefitsResponse>>

    @POST(ApiConstants.GET_POINT_LIST)
    suspend fun getPointList(@Body generalRequest: GeneralRequest): Response<List<PointListResponse>>
}