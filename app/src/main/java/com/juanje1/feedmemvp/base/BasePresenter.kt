package com.juanje1.feedmemvp.base

import com.juanje1.feedmemvp.injection.component.DaggerPresenterInjector
import com.juanje1.feedmemvp.injection.component.PresenterInjector
import com.juanje1.feedmemvp.injection.module.ContextModule
import com.juanje1.feedmemvp.injection.module.NetworkModule
import com.juanje1.feedmemvp.ui.post.PostPresenter

/**
 * Base presenter any presenter of the application must extend. It provides initial injections and
 * required methods.
 * @param V the type of the View the presenter is based on
 * @property view the view the presenter is based on
 * @property injector The injector used to inject required dependencies
 * @constructor Injects the required dependencies
 */
abstract class BasePresenter<out V : BaseView>(protected val view: V) {

    /**
     * The injector used to inject required dependencies
     */
    private val injector: PresenterInjector = DaggerPresenterInjector
        .builder()
        .baseView(view)
        .contextModule(ContextModule)
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    /**
     * This method may be called when the presenter view is created
     */
    open fun onViewCreated(){}

    /**
     * This method may be called when the presenter view is destroyed
     */
    open fun onViewDestroyed(){}

    /**
     * Injects the required dependencies
     */
    private fun inject() {
        when (this) {
            is PostPresenter -> injector.inject(this)
        }
    }
}