package jks

import jks.Filetype.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.io.File

class SignaturesTest {

   @Test
   fun `recognizes GIF 89a`() {
      val fileAsStream = File(this.javaClass.getResource("/gif89a.gif").toURI()).inputStream()
      val expected = GIF89a
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes zip`() {
      val fileAsStream = File(this.javaClass.getResource("/test.zip").toURI()).inputStream()
      val expected = ZIP
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `recognizes png`() {
      val fileAsStream = File(this.javaClass.getResource("/kotlin.png").toURI()).inputStream()
      val expected = PNG
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

   @Test
   fun `empty file should't cause crash`() {
      val fileAsStream = File(this.javaClass.getResource("/empty_file").toURI()).inputStream()
      val expected = UNKNOWN
      val actual = determineType(fileAsStream)
      assertEquals(expected, actual)
   }

}
