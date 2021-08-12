package com.juanje1.feedmemvp.injection.component

import com.juanje1.feedmemvp.base.BaseView
import com.juanje1.feedmemvp.injection.module.ContextModule
import com.juanje1.feedmemvp.injection.module.NetworkModule
import com.juanje1.feedmemvp.ui.post.PostPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}