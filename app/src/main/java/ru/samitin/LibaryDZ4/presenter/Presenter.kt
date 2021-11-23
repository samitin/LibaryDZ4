package ru.samitin.LibaryDZ4.presenter

import ru.samitin.LibaryDZ4.view.View

interface Presenter {

    fun startProcess(path:String)
    fun cancelPocess()
}