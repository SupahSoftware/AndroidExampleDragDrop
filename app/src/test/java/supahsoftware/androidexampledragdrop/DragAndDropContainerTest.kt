package supahsoftware.androidexampledragdrop

import android.content.ClipData
import android.content.ClipDescription
import android.view.View
import com.nhaarman.mockitokotlin2.*
import junit.framework.TestCase.assertEquals
import org.junit.Test

class DragAndDropContainerTest : BaseRobolectricTest() {

    @Test
    fun `set long click listener when layout is updated`() {
        val testObject = DragAndDropContainer(context, null)

        val expectedView = View(context)
        val viewSpy = spy(expectedView)
        val expectedTag = "view-tag"
        viewSpy.tag = expectedTag

        testObject.setContent(viewSpy)
        expectedView.performLongClick()

        val shadowCaptor = argumentCaptor<View.DragShadowBuilder>()
        val dataCaptor = argumentCaptor<ClipData>()
        verify(viewSpy).startDragAndDrop(dataCaptor.capture(), shadowCaptor.capture(), eq(viewSpy), eq(0))
        assertEquals(viewSpy, shadowCaptor.firstValue.view)

        val expectedItem = ClipData.Item(expectedTag)
        val expectedMimeTypes = arrayOf(ClipDescription.MIMETYPE_TEXT_PLAIN)
        assertEquals(expectedItem.text, dataCaptor.firstValue.getItemAt(0).text)
        assertEquals(expectedTag, dataCaptor.firstValue.description.label)
        assertEquals(expectedMimeTypes.first(), dataCaptor.firstValue.description.getMimeType(0))
    }
}