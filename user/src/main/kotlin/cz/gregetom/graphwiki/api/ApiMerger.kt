package cz.gregetom.graphwiki.api

import org.springframework.core.io.ClassPathResource
import java.io.File
import java.io.PrintWriter

class ApiMerger {

    companion object {
        private const val DEFINITION_REF = "\$ref:"

        @JvmStatic
        fun main(args: Array<String>) {
            merge(File(args[0]), File(args[1]))
        }

        private fun merge(inputFile: File, outputFile: File) {
            outputFile.printWriter().use { writer ->
                inputFile.forEachLine { line ->
                    when {
                        line.contains(DEFINITION_REF) -> resolveDefinitionReference(line, writer)
                        else -> writer.println(line)
                    }
                }
            }
        }

        private fun resolveDefinitionReference(line: String, writer: PrintWriter) {
            val offset = line.substringBefore(DEFINITION_REF)
            val referencedFile = ClassPathResource(
                    line.substringAfter(DEFINITION_REF).trim().removeSurrounding("\"")
            ).file
            referencedFile.forEachLine { writer.println(offset + it) }
        }
    }
}
