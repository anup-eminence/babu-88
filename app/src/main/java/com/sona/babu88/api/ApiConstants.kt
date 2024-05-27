package com.sona.babu88.api

object ApiConstants {
    private const val API_USER = "api-users"
    private const val API_AGENT = "api-agt"
    const val AUTHENTICATE_USER = "$API_USER/authenticateUser"
    const val GET_GAME_LIST = "$API_USER/getGamesList"
    const val VERIFY_USER = "api-sec/verifyUser"
    const val GET_SPECIAL_GAME_LIST = "$API_USER/getSpecialGameList"
    const val GET_LOGIN_TOKEN = "$API_USER/getLoginToken"
    const val GET_PROMOTION_LIST = "$API_USER/getPromotionsList"
    const val GET_PROMOTION_FILTER = "$API_USER/getPromoFilter"
    const val GET_SIGNUP_CURRENCY = "$API_AGENT/getSignUPCurrencyList"
    const val VALIDATE_USER_SIGNUP = "$API_AGENT/validateUserSignUp"
}