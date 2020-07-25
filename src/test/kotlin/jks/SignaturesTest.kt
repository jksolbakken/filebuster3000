package jks

import jks.Filetype.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class SignaturesTest {

   @Test
   fun `recognizes GIF 89a`() {
      val fileAsStream = asStream("gif89a.gif")
      val expected = GIF89a
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes zip`() {
      val fileAsStream = asStream("test.zip")
      val expected = ZIP
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes png`() {
      val fileAsStream = asStream("kotlin.png")
      val expected = PNG
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes jpg jfif`() {
      val fileAsStream = asStream("sample1.jfif")
      val expected = JPG
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `empty file should't cause crash`() {
      val fileAsStream = asStream("empty_file")
      val expected = UNKNOWN
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   private fun asStream(name: String) = this.javaClass.classLoader.getResourceAsStream(name)

}
