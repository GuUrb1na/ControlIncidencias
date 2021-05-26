package mx.itg.controlincidencias.interfaces

interface BaseView<T> {
    fun setPresenter(presenter: T)
}