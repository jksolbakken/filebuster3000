package jks

import jks.Filetype.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

@ExperimentalUnsignedTypes
class SignaturesTest {

   @Test
   fun `recognizes GIF 89a`() {
      val fileAsStream = this.javaClass.getResourceAsStream("/gif89a.gif")
      val expected = GIF89a
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes zip`() {
      val fileAsStream = this.javaClass.getResourceAsStream("/test.zip")
      val expected = ZIP
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes png`() {
      val fileAsStream = this.javaClass.getResourceAsStream("/kotlin.png")
      val expected = PNG
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `empty file should't cause crash`() {
      val fileAsStream = this.javaClass.getResourceAsStream("/empty_file")
      val expected = UNKNOWN
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

}
