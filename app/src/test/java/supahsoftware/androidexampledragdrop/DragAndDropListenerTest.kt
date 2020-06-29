package supahsoftware.androidexampledragdrop

import android.view.DragEvent
import android.view.DragEvent.*
import android.view.View
import androidx.core.content.ContextCompat
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import org.junit.Test

class DragAndDropListenerTest : BaseRobolectricTest() {

    @Test
    fun `the view's outline should change depending on the action provided`() {
        val testObject = DragAndDropListener()
        val expectedLandingContainer = DragAndDropContainer(context, null)
        val expectedEvent: DragEvent = mock()
        whenever(expectedEvent.action).thenReturn(
            ACTION_DRAG_ENTERED,
            ACTION_DRAG_EXITED,
            ACTION_DRAG_ENTERED,
            ACTION_DROP
        )

        val actionDragEntered1 = testObject.onDrag(expectedLandingContainer, expectedEvent)
        assertEquals(true, actionDragEntered1)
        assertEquals(
            getDrawable(R.drawable.outline_gray_dashed).constantState,
            expectedLandingContainer.background.constantState
        )

        val actionDragExited = testObject.onDrag(expectedLandingContainer, expectedEvent)
        assertEquals(true, actionDragExited)
        assertEquals(
            getDrawable(R.drawable.outline_gray_solid).constantState,
            expectedLandingContainer.background.constantState
        )

        val actionDragEntered2 = testObject.onDrag(expectedLandingContainer, expectedEvent)
        assertEquals(true, actionDragEntered2)
        assertEquals(
            getDrawable(R.drawable.outline_gray_dashed).constantState,
            expectedLandingContainer.background.constantState
        )

        val expectedDraggingView = View(context)
        val expectedDraggingViewOrigination = DragAndDropContainer(context, null)
        expectedDraggingViewOrigination.addView(expectedDraggingView)
        whenever(expectedEvent.localState).thenReturn(expectedDraggingView)

        val actionDragDrop = testObject.onDrag(expectedLandingContainer, expectedEvent)
        assertEquals(true, actionDragDrop)
        assertEquals(
            getDrawable(R.drawable.outline_gray_solid).constantState,
            expectedLandingContainer.background.constantState
        )
    }

    @Test
    fun `content is removed from the original, and added to the intended location when the action is ACTION_DRAG_DROP`() {
        val testObject = DragAndDropListener()
        val expectedLandingContainer = DragAndDropContainer(context, null)
        val expectedEvent: DragEvent = mock()
        whenever(expectedEvent.action).thenReturn(ACTION_DROP)

        val expectedDraggingView = View(context)
        val expectedDraggingViewOrigination = DragAndDropContainer(context, null)
        expectedDraggingViewOrigination.addView(expectedDraggingView)
        whenever(expectedEvent.localState).thenReturn(expectedDraggingView)

        val actionDragDrop = testObject.onDrag(expectedLandingContainer, expectedEvent)
        assertEquals(true, actionDragDrop)
        assertNull(expectedDraggingViewOrigination.getChildAt(0))
        assertEquals(
            expectedDraggingView,
            expectedLandingContainer.getChildAt(0)
        )
    }

    private fun getDrawable(drawable: Int) = ContextCompat.getDrawable(context, drawable)!!
}

