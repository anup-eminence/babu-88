package com.sona.babu88.api.auth

import android.util.Base64
import com.google.gson.Gson
import com.google.gson.JsonObject
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

object AuthUtils {

    val encodeKey = "UFJGSFguW1Q0MjdPWF0xfQ=="

    val decodeKey = "WjdFPz5SQkF8fV1TUkdTMA=="

    fun encryptData(requestData: Any, base64DecodedString: String = encodeKey): JsonObject {
        // Convert the requestData to a JSON string
        val jsonString = Gson().toJson(requestData).replace("\\","")

        println(">>>>>>>json ${jsonString.substring(1, jsonString.length - 1)}")
        println(">>>>>>>requestData $requestData")
        // Decode the base64 string to get the key
        val decodedKey = Base64.decode(base64DecodedString, Base64.NO_WRAP)

        // Prepare the key
        val key: Key = SecretKeySpec(decodedKey, "AES")

        // Create the cipher instance and initialize it
        val cipher: Cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.ENCRYPT_MODE, key)

        // Encrypt the data
        val encryptedData: ByteArray = cipher.doFinal(jsonString.substring(1, jsonString.length - 1).toByteArray(Charsets.UTF_8))

        // Encode the encrypted data in Base64
        val base64EncryptedData: String = Base64.encodeToString(encryptedData, Base64.NO_WRAP)
        println(">>>>>>>>>.decryptRequest ${decryptData("69mbEuHEZ8v++M2Ki+eSWiYukS8c6ScaFmYIuTFm+De8pN1zVUJLGjXDWWU+UprdEeQ3HVhs05Wds6U/IoMlz6Wb7bC4+OiHjf2IWLl7nT4QqAxq9Tif7mVsI9Tl4ERxIHPHWSSNXdye3hqzeQnBMQ==", encodeKey)}")
        // Return the encrypted data in a JsonObject
        val result = JsonObject()
        result.addProperty("data", base64EncryptedData)
        return result
    }

   /* fun decryptData(encryptedData: String, base64DecodedKey: String= decodeKey): JsonObject {
        // Decode the base64 key
        val decodedKey: ByteArray = Base64.decode(base64DecodedKey, Base64.DEFAULT)

        // Prepare the AES key
        val key = SecretKeySpec(decodedKey, "AES")

        // Create and initialize the cipher
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key)

        // Decode the base64 encrypted data
        val decodedEncryptedData: ByteArray = Base64.decode(encryptedData, Base64.DEFAULT)

        // Decrypt the data
        val decryptedData: ByteArray = cipher.doFinal(decodedEncryptedData)

        // Convert decrypted data to a UTF-8 string
        val decryptedJsonString = String(decryptedData, Charsets.UTF_8)

        // Parse the JSON string to a JsonObject
        val gson = Gson()
        val data = gson.fromJson(decryptedJsonString, JsonObject::class.java)

        // Return the decrypted data
        val result = JsonObject()
        result.add("data", data)
        return result
    }*/

    fun decryptData(encryptedData: String, base64DecodedKey: String = decodeKey): String {
        // Decode the base64 key
        val decodedKey: ByteArray = Base64.decode(base64DecodedKey, Base64.DEFAULT)

        // Prepare the AES key
        val key = SecretKeySpec(decodedKey, "AES")

        // Create and initialize the cipher
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, key)

        // Decode the base64 encrypted data
        val decodedEncryptedData: ByteArray = Base64.decode(encryptedData, Base64.DEFAULT)

        // Decrypt the data
        val decryptedData: ByteArray = cipher.doFinal(decodedEncryptedData)

        // Convert decrypted data to a UTF-8 string
        return String(decryptedData, Charsets.UTF_8)
    }

}