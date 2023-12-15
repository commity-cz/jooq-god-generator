package org.jooq.codegen;

import org.jooq.meta.DataTypeDefinition;

public class JavaGodGenerator extends JavaGenerator {

    public JavaGodGenerator() {
        super(Language.JAVA);
    }

    @Override
    protected boolean effectivelyNotNull(DataTypeDefinition type) {
        return !type.isNullable();
    }
}
