// Autogenerated from Pigeon (v19.0.0), do not edit directly.
// See also: https://pub.dev/packages/pigeon
@file:Suppress("UNCHECKED_CAST", "ArrayInDataClass")

package com.zulip.flutter

import android.util.Log
import io.flutter.plugin.common.BasicMessageChannel
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.plugin.common.MessageCodec
import io.flutter.plugin.common.StandardMessageCodec
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

private fun wrapResult(result: Any?): List<Any?> {
  return listOf(result)
}

private fun wrapError(exception: Throwable): List<Any?> {
  return if (exception is FlutterError) {
    listOf(
      exception.code,
      exception.message,
      exception.details
    )
  } else {
    listOf(
      exception.javaClass.simpleName,
      exception.toString(),
      "Cause: " + exception.cause + ", Stacktrace: " + Log.getStackTraceString(exception)
    )
  }
}

/**
 * Error class for passing custom error details to Flutter via a thrown PlatformException.
 * @property code The error code.
 * @property message The error message.
 * @property details The error details. Must be a datatype supported by the api codec.
 */
class FlutterError (
  val code: String,
  override val message: String? = null,
  val details: Any? = null
) : Throwable()

/**
 * Corresponds to `android.app.PendingIntent`.
 *
 * See: https://developer.android.com/reference/android/app/PendingIntent
 *
 * Generated class from Pigeon that represents data sent in messages.
 */
data class PendingIntent (
  val requestCode: Long,
  /**
   * A value set on an extra on the Intent, and passed to
   * the on-notification-opened callback.
   */
  val intentPayload: String,
  /**
   * A combination of flags from [PendingIntent.flags], and others associated
   * with `Intent`; see Android docs for `PendingIntent.getActivity`.
   */
  val flags: Long

) {
  companion object {
    @Suppress("LocalVariableName")
    fun fromList(__pigeon_list: List<Any?>): PendingIntent {
      val requestCode = __pigeon_list[0].let { num -> if (num is Int) num.toLong() else num as Long }
      val intentPayload = __pigeon_list[1] as String
      val flags = __pigeon_list[2].let { num -> if (num is Int) num.toLong() else num as Long }
      return PendingIntent(requestCode, intentPayload, flags)
    }
  }
  fun toList(): List<Any?> {
    return listOf<Any?>(
      requestCode,
      intentPayload,
      flags,
    )
  }
}
private object AndroidNotificationHostApiCodec : StandardMessageCodec() {
  override fun readValueOfType(type: Byte, buffer: ByteBuffer): Any? {
    return when (type) {
      128.toByte() -> {
        return (readValue(buffer) as? List<Any?>)?.let {
          PendingIntent.fromList(it)
        }
      }
      else -> super.readValueOfType(type, buffer)
    }
  }
  override fun writeValue(stream: ByteArrayOutputStream, value: Any?)   {
    when (value) {
      is PendingIntent -> {
        stream.write(128)
        writeValue(stream, value.toList())
      }
      else -> super.writeValue(stream, value)
    }
  }
}

/** Generated interface from Pigeon that represents a handler of messages from Flutter. */
interface AndroidNotificationHostApi {
  /**
   * Corresponds to `android.app.NotificationManager.notify`,
   * combined with `androidx.core.app.NotificationCompat.Builder`.
   *
   * The arguments `tag` and `id` go to the `notify` call.
   * The rest go to method calls on the builder.
   *
   * The `color` should be in the form 0xAARRGGBB.
   * This is the form returned by [Color.value].
   *
   * The `smallIconResourceName` is passed to `android.content.res.Resources.getIdentifier`
   * to get a resource ID to pass to `Builder.setSmallIcon`.
   * Whatever name is passed there must appear in keep.xml too:
   * see https://github.com/zulip/zulip-flutter/issues/528 .
   *
   * See:
   *   https://developer.android.com/reference/kotlin/android/app/NotificationManager.html#notify
   *   https://developer.android.com/reference/androidx/core/app/NotificationCompat.Builder
   */
  fun notify(tag: String?, id: Long, channelId: String, color: Long?, contentIntent: PendingIntent?, contentText: String?, contentTitle: String?, extras: Map<String?, String?>?, smallIconResourceName: String?)

  companion object {
    /** The codec used by AndroidNotificationHostApi. */
    val codec: MessageCodec<Any?> by lazy {
      AndroidNotificationHostApiCodec
    }
    /** Sets up an instance of `AndroidNotificationHostApi` to handle messages through the `binaryMessenger`. */
    fun setUp(binaryMessenger: BinaryMessenger, api: AndroidNotificationHostApi?, messageChannelSuffix: String = "") {
      val separatedMessageChannelSuffix = if (messageChannelSuffix.isNotEmpty()) ".$messageChannelSuffix" else ""
      run {
        val channel = BasicMessageChannel<Any?>(binaryMessenger, "dev.flutter.pigeon.zulip.AndroidNotificationHostApi.notify$separatedMessageChannelSuffix", codec)
        if (api != null) {
          channel.setMessageHandler { message, reply ->
            val args = message as List<Any?>
            val tagArg = args[0] as String?
            val idArg = args[1].let { num -> if (num is Int) num.toLong() else num as Long }
            val channelIdArg = args[2] as String
            val colorArg = args[3].let { num -> if (num is Int) num.toLong() else num as Long? }
            val contentIntentArg = args[4] as PendingIntent?
            val contentTextArg = args[5] as String?
            val contentTitleArg = args[6] as String?
            val extrasArg = args[7] as Map<String?, String?>?
            val smallIconResourceNameArg = args[8] as String?
            val wrapped: List<Any?> = try {
              api.notify(tagArg, idArg, channelIdArg, colorArg, contentIntentArg, contentTextArg, contentTitleArg, extrasArg, smallIconResourceNameArg)
              listOf<Any?>(null)
            } catch (exception: Throwable) {
              wrapError(exception)
            }
            reply.reply(wrapped)
          }
        } else {
          channel.setMessageHandler(null)
        }
      }
    }
  }
}
