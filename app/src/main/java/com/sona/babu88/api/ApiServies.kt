package com.sona.babu88.api

import com.sona.babu88.api.model.request.AuthenticateUserRequest
import com.sona.babu88.api.model.request.GameListRequest
import com.sona.babu88.api.model.request.SpecialGameListRequest
import com.sona.babu88.api.model.response.GameListResponse
import com.sona.babu88.api.model.response.SpecialGameListResponse
import com.sona.babu88.api.model.response.UserData
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
}