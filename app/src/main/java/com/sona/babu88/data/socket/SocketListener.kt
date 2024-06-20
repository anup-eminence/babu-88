package com.sona.babu88.data.socket

interface SocketListener {
    fun onSocketErrorOccured(error : String)
    fun onSocketDisConnected()
    fun onSocketConnected()
    fun onSocketResponseReceived(data : Any)
}