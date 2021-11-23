package ru.samitin.LibaryDZ4.model

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import java.io.FileInputStream

import java.io.FileOutputStream
import java.lang.Exception
import java.lang.RuntimeException

const val ERROR_WRITE_READ_FILE:String="ERROR_WRITE_READ_FILE"
class RepositoryImpl() :Repository {

    override fun readBitmap(path: String) : Observable<Bitmap> {
       return Observable.create<Bitmap> {
            var bitmap:Bitmap?=null
            try {
                Thread.sleep(5000)
                it.onNext(BitmapFactory.decodeFile(path))
            }catch (e:Exception){
                it.onError(e)
            }
            it.onComplete()
        }

    }

    override fun writeBitmap(path: String, bitmap: Bitmap) :Completable{
       return Completable.create {
            try {
                val fileOS=FileOutputStream(path)
                bitmap.compress(Bitmap.CompressFormat.PNG,100,fileOS)
                fileOS.flush()
                fileOS.close()
                it.onComplete()
            }catch (e:Exception){
                it.onError(e)
                Log.e(ERROR_WRITE_READ_FILE,"Ошибка при записи " + e)
            }
        }

    }

}