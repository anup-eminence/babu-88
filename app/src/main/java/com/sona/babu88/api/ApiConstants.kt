package com.sona.babu88.api

object ApiConstants {
    private const val API_USER = "api-users"
    private const val API_AGENT = "api-agt"
    private const val API_BET = "api-bet"
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
    const val CHANGE_USER_PASSWORD = "$API_USER/changeUserPassword"
    const val GET_BANKING_METHODS = "$API_USER/getBankingMethods"
    const val GET_BANKING_CHANNEL_LIST = "$API_USER/getBankingChannelList"
    const val GET_DEPOSIT_PROMOTIONS_LIST = "$API_USER/getDepositPromotionsList"
    const val CREATE_DEPOSIT_REQUEST = "$API_USER/createDepositRequest"
    const val GET_WITHDRAW_METHODS = "$API_USER/getWithdrawMethods"
    const val GET_USER_LOCKED_AMOUNT = "$API_USER/getUserLockedAmount"
    const val GET_WITHDRAW_CHANNEL_LIST = "$API_USER/getWithdrawChannelList"
    const val GET_BANKS_LIST = "$API_USER/getBanksList"
    const val GET_USER_SIDE_BAR_MATCHES = "$API_USER/getUserSideBarMatches"
    const val GET_IN_PLAY_MATCHES_COUNT = "$API_USER/getInPlayMatchesCount"
    const val GET_ACTIVE_MULTI_MARKET = "$API_USER/getActiveMultiMarket"
    const val GET_IN_PLAY_MATCHES = "$API_USER/getInPlayMatches"
    const val GET_TODAY_MATCHES = "$API_USER/getTodayMatches"
    const val GET_TOMORROW_MATCHES = "$API_USER/getTomorrowMatches"
    const val GET_USER_MATCH_DETAIL = "$API_USER/getUserMatcheDetail"
    const val GET_MULTI_MATCH_USER = "$API_USER/mutltiMatchUser"
    const val GET_USER_PROFILE = "$API_USER/getUserProfile"
    const val GET_USER_BETS_LIST = "$API_BET/getUserBetsList"
    const val GET_SPORTS_PL = "$API_USER/getSportsPL"
    const val GET_MATCH_RESULT_LIST = "$API_USER/getMatchResultList"
    const val GET_USER_LOGIN_ACTIVITY = "$API_USER/getUserLoginActivity"
    const val GET_MESSAGE_WEBSITE = "$API_USER/getMessageWebsite"
    const val GET_PLAT_FORM_LIST = "$API_USER/getPlatFormList"
    const val GET_USER_SC_PACK = "$API_USER/getUserSCPack"
    const val GET_TIER_COMM_DETAILS = "$API_USER/getTierCommDetails"
    const val GET_USERS_PROMOTIONS = "$API_USER/getUsersPromotions"
    const val GET_TOTAL_USER_ACCOUNT_LOGS = "$API_USER/getTotalUserAccountLogs"
    const val GET_USER_VIP_DETAILS = "$API_USER/getUserVipDetails"
    const val GET_VIP_LEVELS = "$API_USER/getVipLevels"
    const val GET_REW_AND_BEN = "$API_USER/getRewAndBen"
    const val GET_POINT_LIST = "$API_USER/getPointList"
}