package supahsoftware.androidexampledragdrop

import android.app.Application
import androidx.test.core.app.ApplicationProvider
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
abstract class BaseRobolectricTest {
    protected val context: Application by lazy {
        ApplicationProvider.getApplicationContext<Application>()
    }
}