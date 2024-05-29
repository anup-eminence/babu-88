package com.sona.babu88.api

import com.sona.babu88.api.auth.CheckUserLogin
import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.CurrencyRequest
import com.sona.babu88.api.model.request.GameListRequest
import com.sona.babu88.api.model.request.GeneralRequest
import com.sona.babu88.api.model.request.PromoFilterRequest
import com.sona.babu88.api.model.request.PromotionListRequest
import com.sona.babu88.api.model.request.RegisterUserRequest
import com.sona.babu88.api.model.request.SpecialGameListRequest
import com.sona.babu88.api.model.request.TransactionPLRequest
import com.sona.babu88.api.model.request.TransactionPLRequestFull
import com.sona.babu88.api.model.request.TransactionRecordRequest
import com.sona.babu88.api.model.request.UpdateBirthDayRequest
import com.sona.babu88.api.model.request.VerifyEmailCodeRequest
import com.sona.babu88.api.model.request.VerifyEmailRequest
import com.sona.babu88.api.model.response.CurrencyResponse
import com.sona.babu88.api.model.response.EmailVerificationResponse
import com.sona.babu88.api.model.response.EmailVerifiedResponse
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.api.model.response.GeneralResponse
import com.sona.babu88.api.model.response.PromoFilterResponse
import com.sona.babu88.api.model.response.PromotionListResponse
import com.sona.babu88.api.model.response.RegisterUserResponse
import com.sona.babu88.api.model.response.SpecialGameListResponse
import com.sona.babu88.api.model.response.UserData
import com.sona.babu88.api.model.response.UserDetailsResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
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
    suspend fun getTransactionPl(@Body transactionPLRequest: TransactionPLRequest) : Response<Any>

    @POST(ApiConstants.GET_TRANSACTION_PL_FULL)
    suspend fun getTransactionPlFull(@Body transactionPLRequest: TransactionPLRequestFull) : Response<Any>

    @POST(ApiConstants.GET_TRANSACTION_RECORD)
    suspend fun getTransactionRecord(@Body transactionRecordRequest: TransactionRecordRequest) : Response<Any>

    @POST(ApiConstants.GET_USER_DETAILS)
    suspend fun getUserDetails(@Body generalRequest: GeneralRequest) : Response<UserDetailsResponse>

    @POST(ApiConstants.GET_EMAIL_VERIFICATION_CODE)
    suspend fun getEmailVerificationCode(@Body emailRequest: VerifyEmailRequest) : Response<EmailVerificationResponse>

    @POST(ApiConstants.VERIFICATION_EMAIL)
    suspend fun verifyEmail(@Body verifyEmailRequest: VerifyEmailCodeRequest) : Response<EmailVerifiedResponse>

    @POST(ApiConstants.UPDATE_BIRTHDAY)
    suspend fun updateBirthDay(@Body birthdayRequest: UpdateBirthDayRequest) : Response<GeneralResponse>
}

