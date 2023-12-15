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
package org.jooq;

import static org.jooq.SQLDialect.*;
import static org.jooq.impl.DSL.*;

import java.util.*;

import org.jooq.impl.DSL;

import org.jetbrains.annotations.*;

/**
 * A step in the construction of the <code>CREATE TABLE</code> statement.
 * <p>
 * <h3>Referencing <code>XYZ*Step</code> types directly from client code</h3>
 * <p>
 * It is usually not recommended to reference any <code>XYZ*Step</code> types
 * directly from client code, or assign them to local variables. When writing
 * dynamic SQL, creating a statement's components dynamically, and passing them
 * to the DSL API statically is usually a better choice. See the manual's
 * section about dynamic SQL for details: <a href=
 * "https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql">https://www.jooq.org/doc/latest/manual/sql-building/dynamic-sql</a>.
 * <p>
 * Drawbacks of referencing the <code>XYZ*Step</code> types directly:
 * <ul>
 * <li>They're operating on mutable implementations (as of jOOQ 3.x)</li>
 * <li>They're less composable and not easy to get right when dynamic SQL gets
 * complex</li>
 * <li>They're less readable</li>
 * <li>They might have binary incompatible changes between minor releases</li>
 * </ul>
 */
@SuppressWarnings({ "unused" })
public interface CreateTableElementListStep extends CreateTableAsStep {

    /**
     * Add the <code>TABLE ELEMENTS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep tableElements(TableElement... tableElements);

    /**
     * Add the <code>TABLE ELEMENTS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep tableElements(Collection<? extends TableElement> tableElements);

    /**
     * Add the <code>COLUMNS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep columns(@Stringly.Name String... columns);

    /**
     * Add the <code>COLUMNS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep columns(Name... columns);

    /**
     * Add the <code>COLUMNS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep columns(Field<?>... columns);

    /**
     * Add the <code>COLUMNS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep columns(Collection<? extends Field<?>> columns);

    /**
     * Add the <code>COLUMN</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep column(Field<?> column);

    /**
     * Add the <code>COLUMN</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep column(@Stringly.Name String field, DataType<?> type);

    /**
     * Add the <code>COLUMN</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep column(Name field, DataType<?> type);

    /**
     * Add the <code>COLUMN</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep column(Field<?> field, DataType<?> type);

    /**
     * Add the <code>CONSTRAINTS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep constraints(Constraint... constraints);

    /**
     * Add the <code>CONSTRAINTS</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep constraints(Collection<? extends Constraint> constraints);

    /**
     * Add the <code>CONSTRAINT</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep constraint(Constraint constraint);

    /**
     * Add the <code>PRIMARY KEY</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep primaryKey(@Stringly.Name String... fields);

    /**
     * Add the <code>PRIMARY KEY</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep primaryKey(Name... fields);

    /**
     * Add the <code>PRIMARY KEY</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep primaryKey(Field<?>... fields);

    /**
     * Add the <code>PRIMARY KEY</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep primaryKey(Collection<? extends Field<?>> fields);

    /**
     * Add the <code>UNIQUE</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep unique(@Stringly.Name String... fields);

    /**
     * Add the <code>UNIQUE</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep unique(Name... fields);

    /**
     * Add the <code>UNIQUE</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep unique(Field<?>... fields);

    /**
     * Add the <code>UNIQUE</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support
    @NotNull @CheckReturnValue
    CreateTableElementListStep unique(Collection<? extends Field<?>> fields);

    /**
     * Add the <code>CHECK</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support({ CUBRID, DERBY, FIREBIRD, H2, HSQLDB, MARIADB, MYSQL, POSTGRES, SQLITE, YUGABYTEDB })
    @NotNull @CheckReturnValue
    CreateTableElementListStep check(Condition condition);

    /**
     * Add the <code>INDEXES</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES, YUGABYTEDB })
    @NotNull @CheckReturnValue
    CreateTableElementListStep indexes(Index... indexes);

    /**
     * Add the <code>INDEXES</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES, YUGABYTEDB })
    @NotNull @CheckReturnValue
    CreateTableElementListStep indexes(Collection<? extends Index> indexes);

    /**
     * Add the <code>INDEX</code> clause to the <code>CREATE TABLE</code> statement.
     */
    @Support({ FIREBIRD, MARIADB, MYSQL, POSTGRES, YUGABYTEDB })
    @NotNull @CheckReturnValue
    CreateTableElementListStep index(Index index);
}
