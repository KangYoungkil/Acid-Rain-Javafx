package utils

import java.io.File
import java.io.InputStream
import java.net.URL
import java.util.stream.Stream

object ResourceHelper{


    val PATH = "../../resources/"

    fun convertURL(fileName:String): URL {
         val path = "$PATH$fileName"
        println(ResourceHelper.javaClass.getResource(path))
        return ResourceHelper.javaClass.getResource(path)
    }

    fun convertStream(fileName:String): InputStream {
        val path = "$PATH$fileName"
        return ResourceHelper.javaClass.getResourceAsStream(path)
    }

    fun convertFile(fileName:String):File{
        val path = ResourceHelper.javaClass.getResource("$PATH$fileName").toURI().path
        return File(path)
    }
}