package org.sirekanyan.warmongr

import android.content.ComponentName
import android.content.Intent
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.*
import org.junit.Test
import org.junit.runner.RunWith

private const val PACKAGE = "com.sirekanian.warmongr"
private const val ACTIVITY = "com.sirekanian.warmongr.MainActivity"

@RunWith(AndroidJUnit4::class)
class AndroidTest {

    private val device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    private val app = ApplicationProvider.getApplicationContext<App>()
    private val intent =
        Intent.makeMainActivity(ComponentName(PACKAGE, ACTIVITY))
            .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)

    @Test
    fun putin() {
        app.startActivity(intent)
        wait(By.desc("Search…")).click()
        wait(By.focused(true)).text = "путин"
        wait(By.text("Putin Vladimir Vladimirovich"))
    }

    private fun wait(selector: BySelector): UiObject2 {
        val condition = Until.hasObject(selector)
        check(device.wait(condition, 60000)) { "Timeout reached" }
        return device.findObject(selector)
    }

}