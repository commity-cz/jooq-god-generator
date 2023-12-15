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
import static org.jooq.impl.Tools.removeGenerator;

import java.util.List;
import java.util.function.Predicate;

import org.jooq.Clause;
import org.jooq.Context;
import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.QueryPart;
import org.jooq.Record;
// ...
import org.jooq.Table;
import org.jooq.TableField;
// ...
import org.jooq.UniqueKey;

import org.jetbrains.annotations.NotNull;

/**
 * @author Lukas Eder
 */
final class TableAlias<R extends Record>
extends
    AbstractTable<R>
implements
    QOM.TableAlias<R>,
    SimpleCheckQueryPart
{

    final Alias<Table<R>>   alias;
    transient FieldsImpl<R> aliasedFields;

    TableAlias(Table<R> table, Name alias) {
        this(table, alias, null, c -> false);
    }

    TableAlias(Table<R> table, Name alias, Predicate<Context<?>> wrapInParentheses) {
        this(table, alias, null, wrapInParentheses);
    }

    TableAlias(Table<R> table, Name alias, Name[] fieldAliases) {
        this(table, alias, fieldAliases, c -> false);
    }

    TableAlias(Table<R> table, Name alias, Name[] fieldAliases, Predicate<Context<?>> wrapInParentheses) {
        super(table.getOptions(), alias, table.getSchema());

        this.alias = new Alias<>(table, this, alias, fieldAliases, wrapInParentheses);
    }

    @Override
    public final boolean isSimple(Context<?> ctx) {
        return !ctx.declareTables();
    }

    /**
     * Register fields for this table alias
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private final FieldsImpl<R> initFieldAliases() {

        // [#13418]
        List<Field<?>> result = Tools.map(this.alias.wrapped().fieldsRow().fields(), (f, i) -> new TableFieldImpl(
              alias.fieldAliases != null && alias.fieldAliases.length > i
            ? alias.fieldAliases[i]
            : f.getUnqualifiedName(), removeGenerator(CONFIG.get(), f.getDataType()), this, f.getCommentPart(), f.getBinding()
        ));

        return new FieldsImpl<>(result);
    }

    /**
     * Get the aliased table wrapped by this table.
     */
    Table<R> getAliasedTable() {
        if (alias != null)
            return alias.wrapped();

        return null;
    }

    @Override
    public final UniqueKey<R> getPrimaryKey() {
        return alias.wrapped().getPrimaryKey();
    }

    @Override
    public final List<UniqueKey<R>> getUniqueKeys() {
        return alias.wrapped().getUniqueKeys();
    }

    @Override
    public final List<ForeignKey<R, ?>> getReferences() {
        return alias.wrapped().getReferences();
    }

    @Override
    public final TableField<R, ?> getRecordVersion() {
        return alias.wrapped().getRecordVersion();
    }

    @Override
    public final TableField<R, ?> getRecordTimestamp() {
        return alias.wrapped().getRecordTimestamp();
    }

    @Override
    public final void accept(Context<?> ctx) {
        ctx.visit(alias);
    }

    @Override // Avoid AbstractTable implementation
    public final Clause[] clauses(Context<?> ctx) {
        return null;
    }

    @Override
    public final Table<R> as(Name as) {
        return alias.wrapped().as(as);
    }

    @Override
    public final Table<R> as(Name as, Name... fieldAliases) {
        return alias.wrapped().as(as, fieldAliases);
    }

    @Override
    public final boolean declaresTables() {
        return true;
    }

    @Override
    final FieldsImpl<R> fields0() {
        if (aliasedFields == null)
            aliasedFields = initFieldAliases();

        return aliasedFields;
    }

    @Override
    public Name getQualifiedName() {
        return getUnqualifiedName();
    }

    @Override
    public Class<? extends R> getRecordType() {
        return alias.wrapped().getRecordType();
    }

    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    public final Table<R> $table() {
        return alias.wrapped();
    }

    @Override
    public final Table<R> $aliased() {
        return $table();
    }

    @Override
    public final @NotNull Name $alias() {
        return getUnqualifiedName();
    }















    // ------------------------------------------------------------------------
    // XXX: Object API
    // ------------------------------------------------------------------------

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;

        if (that instanceof TableAlias<?> t)
            return getUnqualifiedName().equals(t.getUnqualifiedName());

        // [#14371] Unqualified TableImpls can be equal to TableAlias
        if (that instanceof TableImpl<?> t) {
            if (t.$alias() != null)
                return getUnqualifiedName().equals(t.$alias());

            // [#7172] [#10274] Cannot use getQualifiedName() yet here
            else if (t.getSchema() == null)
                return getUnqualifiedName().equals(t.getQualifiedName());
        }

        return super.equals(that);
    }
}
