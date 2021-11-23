package ru.samitin.LibaryDZ4.model

import android.graphics.Bitmap
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable

interface Repository {
    fun readBitmap(path:String): Observable<Bitmap>
    fun writeBitmap(path:String,bitmap:Bitmap): Completable
}