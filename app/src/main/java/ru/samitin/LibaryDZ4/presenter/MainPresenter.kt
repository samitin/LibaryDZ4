package ru.samitin.LibaryDZ4.presenter

import android.graphics.Bitmap
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import ru.samitin.LibaryDZ4.model.Repository
import ru.samitin.LibaryDZ4.view.View

class MainPresenter (val view: View,val repository:Repository): Presenter {

    override fun startProcess(path: String) {
        view.showProcess()

        repository.readBitmap(path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            {bitmap ->
                Thread.sleep(5000)
                repository.writeBitmap(path.replace(".jpg",".png"),bitmap)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({

                    },{
                        view.showError()
                    })
            },
            {e ->
                view.showError()
            },
            {})
        view.cancelShowProcess()
    }

    override fun cancelPocess() {
        view.cancelShowProcess()
    }
}