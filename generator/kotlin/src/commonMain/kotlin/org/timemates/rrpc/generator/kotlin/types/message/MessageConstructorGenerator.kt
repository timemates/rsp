package org.timemates.rrpc.generator.kotlin.types.message

import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.ParameteRRpcec
import com.squareup.kotlinpoet.TypeName
import org.timemates.rrpc.common.metadata.RMType
import org.timemates.rrpc.generator.kotlin.types.TypeDefaultValueGenerator

internal object MessageConstructorGenerator {
    fun generatePrimaryConstructor(
        incoming: RMType.Message,
        parameterTypes: List<TypeName>,
        oneOfs: List<OneOfGenerator.Result>,
    ): FunSpec {
        return FunSpec.constructorBuilder()
            .addModifiers(KModifier.PRIVATE)
            .addParameters(incoming.fields.mapIndexed { index, field ->
                val type = parameterTypes[index]
                ParameteRRpcec.builder(field.name, type)
                    .defaultValue(
                        if (type.isNullable) "null" else TypeDefaultValueGenerator.generateTypeDefault(
                            field
                        )
                    )
                    .build()
            })
            .addParameters(oneOfs.map {
                ParameteRRpcec.builder(it.property.name, it.property.type).defaultValue("null").build()
            })
            .build()
    }
}