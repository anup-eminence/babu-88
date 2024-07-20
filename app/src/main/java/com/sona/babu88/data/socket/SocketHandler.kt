package com.sona.babu88.data.socket

import android.util.Log
import io.socket.client.IO
import io.socket.client.Socket
import org.json.JSONObject
import java.net.URISyntaxException

enum class SocketUrl(val url : String){
    Node7("http://node1.in"),  // "http://node7.in"
    CricketPremium("http://cricket.premiumsoccer.in")
}

class SocketHandler {
    lateinit var mSocket: Socket
    private lateinit var socketListener: SocketListener

    fun setSocket(socketUrl: SocketUrl) {
        try {
            val opts = IO.Options().apply {
                reconnection = true
                reconnectionAttempts = 5
                reconnectionDelay = 1000
                timeout = 20000
                transports = arrayOf("websocket")
                // Add other options as needed
            }
            mSocket = IO.socket(socketUrl.url, opts)
            mSocket.connect()
        } catch (e: URISyntaxException) {
            println(">>>error ${e.message}")
            e.printStackTrace()
        }
    }

    fun getSocket(): Socket {
        return mSocket
    }

    fun establishConnection(socketListener: SocketListener) {
        this.socketListener = socketListener
        closeConnection()
        Log.d("SocketHandler", "Attempting to connect to the socket")
        mSocket.connect()
    }

  private fun closeConnection() {
        mSocket.disconnect()
    }


    fun removeEventListener(eventName: String, data: Any) {
        try {
            mSocket.off("$eventName/$data")
            closeConnection()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun setSocketEvent(eventName: String, data: Any) {
        try {

            mSocket.on(Socket.EVENT_CONNECT) {
                Log.d("SocketData", "Socket connected")
                mSocket.emit(eventName, data)
                socketListener.onSocketConnected()
            }
            mSocket.on(Socket.EVENT_CONNECT_ERROR) { args ->
                Log.e("SocketData", "Connection error: ${args?.javaClass?.simpleName}")
                socketListener.onSocketErrorOccured(args?.javaClass?.simpleName.toString())
            }

            mSocket.on(Socket.EVENT_DISCONNECT) { args ->
                Log.e("SocketData", "Connection timeout: ${args?.javaClass?.simpleName}")
                socketListener.onSocketDisConnected()
            }

            mSocket?.on("$eventName/$data") { args ->
                if (args != null) {
                    try {
                        val arrayLength = java.lang.reflect.Array.getLength(args)
                        if (arrayLength > 0) {
                            val firstElement = java.lang.reflect.Array.get(args, 0)
                            Log.d(
                                "SocketData",
                                "Type of the first element: ${firstElement?.javaClass?.simpleName}"
                            )

                            if (firstElement is JSONObject) {
                                val jsonData = firstElement.toString()
                                Log.d("SocketData", "Received JSON: $jsonData")
                                socketListener.onSocketResponseReceived(eventName = eventName, data = jsonData)
                            }
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()

                    }
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}