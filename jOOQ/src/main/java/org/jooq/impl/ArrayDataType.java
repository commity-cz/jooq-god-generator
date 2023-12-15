/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Other licenses:
 * -----------------------------------------------------------------------------
 * Commercial licenses for this work are available. These replace the above
 * ASL 2.0 and offer limited warranties, support, maintenance, and commercial
 * database integrations.
 *
 * For more information, please visit: https://www.jooq.org/legal/licensing
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.jooq.impl;

import static org.jooq.impl.Tools.CONFIG;

import org.jooq.CharacterSet;
import org.jooq.Collation;
import org.jooq.Configuration;
import org.jooq.DataType;
import org.jooq.Field;
import org.jooq.Generator;
import org.jooq.Nullability;
import org.jooq.SQLDialect;
import org.jooq.impl.QOM.GenerationLocation;
import org.jooq.impl.QOM.GenerationOption;

/**
 * A wrapper for anonymous array data types
 *
 * @author Lukas Eder
 */
final class ArrayDataType<T> extends DefaultDataType<T[]> {

    final DataType<T> elementType;

    public ArrayDataType(DataType<T> elementType) {
        super(null, elementType.getArrayType(), elementType.getTypeName() + " array", elementType.getCastTypeName() + " array");

        this.elementType = elementType;
    }

    /**
     * [#3225] Performant constructor for creating derived types.
     */
    ArrayDataType(
        AbstractDataType<T[]> t,
        DataType<T> elementType,
        Integer precision,
        Integer scale,
        Integer length,
        Nullability nullability,
        boolean readonly,
        Generator<?, ?, T[]> generatedAlwaysAs,
        GenerationOption generationOption,
        GenerationLocation generationLocation,
        Collation collation,
        CharacterSet characterSet,
        boolean identity,
        Field<T[]> defaultValue
    ) {
        super(t, precision, scale, length, nullability, readonly, generatedAlwaysAs, generationOption, generationLocation, collation, characterSet, identity, defaultValue);

        this.elementType = elementType;
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    DefaultDataType<T[]> construct(
        Integer newPrecision,
        Integer newScale,
        Integer newLength,
        Nullability newNullability,
        boolean newReadonly,
        Generator<?, ?, T[]> newGeneratedAlwaysAs,
        GenerationOption newGenerationOption,
        GenerationLocation newGenerationLocation,
        Collation newCollation,
        CharacterSet newCharacterSet,
        boolean newIdentity,
        Field<T[]> newDefaultValue
    ) {
        return new ArrayDataType<>(
            this,
            (AbstractDataType<T>) elementType,
            newPrecision,
            newScale,
            newLength,
            newNullability,
            newReadonly,
            newGeneratedAlwaysAs,
            newGenerationOption,
            newGenerationLocation,
            newCollation,
            newCharacterSet,
            newIdentity,
            (Field) newDefaultValue
        );
    }

    @Override
    public final String getTypeName() {
        return getTypeName(CONFIG.get());
    }

    @Override
    public final String getTypeName(Configuration configuration) {
        String typeName = elementType.getTypeName(configuration);
        return getArrayType(configuration, typeName);
    }

    @Override
    public final String getCastTypeName() {
        return getCastTypeName(CONFIG.get());
    }

    @Override
    public final String getCastTypeName(Configuration configuration) {
        String castTypeName = elementType.getCastTypeName(configuration);
        return getArrayType(configuration, castTypeName);
    }

    @Override
    public final Class<?> getArrayComponentType() {
        return elementType.getType();
    }

    @Override
    public final DataType<?> getArrayComponentDataType() {
        return elementType;
    }

    @Override
    public final Class<?> getArrayBaseType() {
        return getArrayBaseDataType().getType();
    }

    @Override
    public final DataType<?> getArrayBaseDataType() {
        DataType<?> result = this;
        DataType<?> t;

        while ((t = result.getArrayComponentDataType()) != null)
            result = t;

        return result;
    }

    private static String getArrayType(Configuration configuration, String dataType) {
        switch (configuration.family()) {


            case POSTGRES:
            case YUGABYTEDB:
                return dataType + "[]";

            case H2:





                return dataType + " array";

            // Default implementation is needed for hash-codes and toString()
            default:
                return dataType + " array";
        }
    }
}
