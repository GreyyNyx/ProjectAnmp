package com.nmp.habittracker.Util

import android.content.ContentValues
import android.content.Context
import android.os.Environment
import android.provider.MediaStore
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FileHelper (val context: Context) {
    val folderName = "habit_folder"
    val fileName = "habit_data.txt"

    // function untuk bikin file baru/load file jika sudah ada
    private fun getFileExternal(): File {
        val dir = File(context.getExternalFilesDir(null), folderName)
        if (!dir.exists()) {
            dir.mkdirs() // bikin folder jika folder belum ada
        }
        return File(dir, fileName)
    }

    // tulis string ke dalam file
    fun writeToFileExternal(data: String) {
        try {
            val file = getFileExternal()
            FileOutputStream(file, false).use { output ->
                output.write(data.toByteArray())
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    // Baca string dari file
    fun readFromFileExternal(): String {
        return try {
            val file = getFileExternal()
            file.bufferedReader().useLines { lines ->
                lines.joinToString("\n")
            }
        } catch (e: IOException) {
            e.printStackTrace().toString()
        }
    }

    // Hapus file
    fun deleteFileExternal(): Boolean {
        return getFileExternal().delete()
    }

    // Menghasilkan string path menuju file
    fun getFilePathExternal(): String {
        return getFileExternal().absolutePath
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }

    fun isExternalStorageReadable(): Boolean {
        val state = Environment.getExternalStorageState()
        return state == Environment.MEDIA_MOUNTED || state == Environment.MEDIA_MOUNTED_READ_ONLY
    }

    fun writeFileToDownload(
        context: Context,
        fileName: String,
        content: ByteArray,
        mimeType: String
    ): Boolean {

        val resolver = context.contentResolver

        val contentValues = ContentValues().apply {
            put(MediaStore.Downloads.DISPLAY_NAME, fileName)
            put(MediaStore.Downloads.MIME_TYPE, mimeType)
            put(MediaStore.Downloads.RELATIVE_PATH, Environment.DIRECTORY_DOWNLOADS)
        }

        return try {
            val uri = resolver.insert(
                MediaStore.Downloads.EXTERNAL_CONTENT_URI,
                contentValues
            )

            uri?.let {
                resolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(content)
                    outputStream.flush()
                }
                true
            } ?: false

        } catch (e: IOException) {
            e.printStackTrace()
            false
        }
    }
}