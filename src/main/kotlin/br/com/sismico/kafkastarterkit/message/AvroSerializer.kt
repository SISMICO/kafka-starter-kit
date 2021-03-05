package br.com.sismico.kafkastarterkit.message

import org.apache.avro.generic.GenericDatumWriter
import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumWriter
import org.apache.avro.io.EncoderFactory
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.xml.bind.DatatypeConverter

class AvroSerializer<T : SpecificRecordBase?> : Serializer<T> {
    override fun close() {
        // No-op
    }

    override fun configure(arg0: Map<String?, *>?, arg1: Boolean) {
        // No-op
    }

    override fun serialize(topic: String, data: T?): ByteArray? {
        return try {
            var result: ByteArray? = null
            if (data != null) {
                LOGGER.debug("data='{}'", data)
                val byteArrayOutputStream = ByteArrayOutputStream()
                val binaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null)
                val datumWriter: DatumWriter<GenericRecord> = GenericDatumWriter(data.schema)
                datumWriter.write(data, binaryEncoder)
                binaryEncoder.flush()
                byteArrayOutputStream.close()
                result = byteArrayOutputStream.toByteArray()
                LOGGER.debug("serialized data='{}'", DatatypeConverter.printHexBinary(result))
            }
            result
        } catch (ex: IOException) {
            throw SerializationException(
                    "Can't serialize data='$data' for topic='$topic'", ex)
        }
    }

    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(AvroSerializer::class.java)
    }
}