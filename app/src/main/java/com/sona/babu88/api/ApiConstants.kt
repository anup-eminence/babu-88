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
    const val GET_USER_DETAILS = "$API_USER/getUserDetails"
    const val GET_EMAIL_VERIFICATION_CODE = "$API_USER/getEmailVerfiyCode"
    const val VERIFICATION_EMAIL = "$API_USER/verifyEmailCode"
    const val UPDATE_BIRTHDAY = "$API_USER/updateUserBirthday"
    const val GET_TRANSACTION_PL = "$API_USER/getTransactionPL"
    const val GET_TRANSACTION_PL_FULL = "$API_USER/getTransactionPLFull"
    const val GET_TRANSACTION_RECORD= "$API_USER/getTransactionsRecords"
}