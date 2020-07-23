package jks

import jks.Filetype.*
import java.io.InputStream

enum class Filetype {
   GIF89a,
   PNG,
   ZIP,
   UNKNOWN
}

@ExperimentalUnsignedTypes
fun determineType(fileContents: InputStream) = fileContents.use { stream ->
   val bytesToRead = FileSignatures.maxLength.coerceAtMost(stream.available())
   val buf = ByteArray(bytesToRead)
   stream.readNBytes(buf, 0, bytesToRead)
   val magicBytes = buf.map { it.toUByte() }.toUByteArray()
   FileSignatures.match(magicBytes)
}

@ExperimentalUnsignedTypes
private object FileSignatures {
   val maxLength: Int

   private val signatures = mapOf(
      ubyteArrayOf(0x47U, 0x49U, 0x46U, 0x38U, 0x39U, 0x61U) to GIF89a,
      ubyteArrayOf(0x89U, 0x50U, 0x4EU, 0x47U, 0x0DU, 0x0AU, 0x1AU, 0x0AU) to PNG,
      ubyteArrayOf(0x50U, 0x4BU, 0x03U, 0x04U) to ZIP
   )

   init {
      maxLength = signatures.keys.maxBy { it.size }?.size ?: 0
   }

   fun match(fileMagicBytes: UByteArray) =
      signatures.entries
         .filter { fileMagicBytes.size >= it.key.size }
         .map { Pair(it, fileMagicBytes.slice(it.key.indices).toUByteArray()) }
         .firstOrNull { (known, unknown) -> known.key.contentEquals(unknown) }?.first?.value ?: UNKNOWN
}




