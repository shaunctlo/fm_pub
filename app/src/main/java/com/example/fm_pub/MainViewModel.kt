package com.example.fm_pub

import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private var _isPlaying = false
    val isPlaying: Boolean
        get() = _isPlaying

    fun setPlay(isPlay: Boolean) {
        _isPlaying = isPlay
    }
}