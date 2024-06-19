@file:OptIn(ExperimentalSerializationApi::class)

package org.timemates.rsp.metadata

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber

/**
 * Represents metadata information.
 *
 * @property serviceName The name of the service.
 * @property procedureName The name of the procedure.
 * @property extra Additional key-value pairs of metadata.
 */
@Serializable
public data class ServerMetadata(
    @ProtoNumber(0)
    public val extra: Map<String, ByteArray>,
)