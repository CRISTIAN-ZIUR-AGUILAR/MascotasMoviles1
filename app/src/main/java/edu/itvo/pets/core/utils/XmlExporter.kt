package edu.itvo.pets.core.utils


import android.content.Context
import android.util.Xml
import edu.itvo.pets.data.local.entities.PetEntity
import java.io.File
import java.io.StringWriter

object XmlExporter {

    fun exportPetsToXml(pets: List<PetEntity>, context: Context): File? {
        return try {
            val writer = StringWriter()
            val xmlSerializer = Xml.newSerializer()
            xmlSerializer.setOutput(writer)

            // Inicia el documento XML
            xmlSerializer.startDocument("UTF-8", true)
            xmlSerializer.startTag(null, "pets")

            // Agrega cada mascota como un nodo XML
            for (pet in pets) {
                xmlSerializer.startTag(null, "pet")

                xmlSerializer.startTag(null, "id")
                xmlSerializer.text(pet.id.toString())
                xmlSerializer.endTag(null, "id")

                xmlSerializer.startTag(null, "name")
                xmlSerializer.text(pet.name)
                xmlSerializer.endTag(null, "name")

                xmlSerializer.startTag(null, "type")
                xmlSerializer.text(pet.type)
                xmlSerializer.endTag(null, "type")

                xmlSerializer.endTag(null, "pet")
            }

            // Finaliza el documento XML
            xmlSerializer.endTag(null, "pets")
            xmlSerializer.endDocument()

            // Guarda el archivo en el almacenamiento interno
            val file = File(context.filesDir, "pets_list.xml")
            file.writeText(writer.toString())
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}

