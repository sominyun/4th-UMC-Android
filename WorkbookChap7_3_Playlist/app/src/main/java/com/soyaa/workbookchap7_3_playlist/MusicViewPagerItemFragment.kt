package com.soyaa.workbookchap7_3_playlist

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Palette.PaletteAsyncListener

class MusicViewPagerItemFragment(val title: String, val musicRes: Int, private val listener: OnContactListener) : Fragment() {

    interface OnContactListener {
        fun onContact(fragment: MusicViewPagerItemFragment)
        fun onChangeColor(dominantSwatch: Palette.Swatch?)
    }

    private lateinit var rootView: View
    private lateinit var albumArtIv: ImageView
    private lateinit var bitmap: Bitmap
    private lateinit var createBitmapThread: CreateBitmapThread
    private lateinit var generatedBitmapColorThread: GeneratedBitmapColorThread
    private val crateBitmapHandler = CrateBitmapHandler()
    private val generatedBitmapColorHandler = GeneratedBitmapColorHandler()
    private var dominantSwatch: Palette.Swatch?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.musiclist_item_viewpager, container, false)
        albumArtIv = rootView.findViewById(R.id.albumArt_iv)
        createBitmapThread = CreateBitmapThread()
        createBitmapThread.start()
        return rootView
    }

    override fun onResume() {
        super.onResume()
        listener.onContact(this)
    }

    private inner class CreateBitmapThread : Thread() {
        private val handler = CrateBitmapHandler()

        override fun run() {
            super.run()
            bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_newjeans)
            val bundle = Bundle()
            val message = handler.obtainMessage()
            try {
                sleep(1)
                message.data = bundle
                handler.sendMessage(message)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            }
        }
    }

    private inner class CrateBitmapHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            albumArtIv.setImageBitmap(bitmap)
            generatedBitmapColorThread = GeneratedBitmapColorThread()
            generatedBitmapColorThread.start()
        }
    }

    private inner class GeneratedBitmapColorThread : Thread() {
        private val handler = GeneratedBitmapColorHandler()

        override fun run() {
            super.run()
            Palette.from(bitmap).generate { palette ->
                dominantSwatch = palette?.dominantSwatch
                val message = handler.obtainMessage()
                handler.sendMessage(message)
            }
        }
    }

    private inner class GeneratedBitmapColorHandler : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            listener.onChangeColor(dominantSwatch)
        }
    }
}
