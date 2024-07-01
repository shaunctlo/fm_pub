package com.example.fm_pub

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import com.example.fm_pub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    companion object {
        val icrt_url: String = "http://live.leanstream.co/ICRTFM?args=tunein_aac"
        val asia_fm_url: String = "https://stream.rcs.revma.com/xpgtqc74hv8uv"
    }
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private var exoPlayer: ExoPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        exoPlayer = ExoPlayer.Builder(this).build()

        binding.btnIcrt.setOnClickListener {
            exoPlayer!!.stop()
            exoPlayer!!.clearMediaItems()
            val mediaItem = MediaItem.fromUri(icrt_url)
            exoPlayer!!.addMediaItem(mediaItem)
            exoPlayer!!.prepare()
            exoPlayer!!.play()
            viewModel.setPlay(true)
        }

        binding.btnAsiaFm.setOnClickListener {
            exoPlayer!!.stop()
            exoPlayer!!.clearMediaItems()
            val mediaItem = MediaItem.fromUri(asia_fm_url)
            exoPlayer!!.addMediaItem(mediaItem)
            exoPlayer!!.prepare()
            exoPlayer!!.play()
            viewModel.setPlay(true)
        }
    }

    override fun onPause() {
        super.onPause()
        if (viewModel.isPlaying) {
            exoPlayer!!.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isPlaying) {
            exoPlayer!!.play()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        if (viewModel.isPlaying) {
            exoPlayer!!.stop()
            viewModel.setPlay(false)
        }
        exoPlayer!!.release()
        exoPlayer = null
    }
}