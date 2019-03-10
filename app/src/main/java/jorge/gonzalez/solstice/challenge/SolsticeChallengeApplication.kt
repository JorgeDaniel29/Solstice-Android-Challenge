package jorge.gonzalez.solstice.challenge

import android.app.Application
import jorge.gonzalez.solstice.challenge.module.appModules
import org.koin.android.ext.android.startKoin

class SolsticeChallengeApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, appModules)
    }
}