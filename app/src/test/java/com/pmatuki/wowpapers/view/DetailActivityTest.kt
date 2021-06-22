package com.pmatuki.wowpapers.view

import android.os.Looper
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.pmatuki.wowpapers.R
import com.pmatuki.wowpapers.view.detail.DetailActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.junit.Assert.assertEquals
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class DetailActivityTest {

    private lateinit var detailActivity: DetailActivity

    @Before
    fun setUp() {
        detailActivity = Robolectric.buildActivity(DetailActivity::class.java)
            .create()
            .resume()
            .get()
    }

    @Test
    fun `should have default margin`() {
        shadowOf(Looper.getMainLooper()).idle()
        val applyButton = detailActivity.findViewById<Button>(R.id.applyButton)
        val startMargin = (applyButton.layoutParams as ConstraintLayout.LayoutParams).marginStart
        assertEquals(12, startMargin)
    }
}