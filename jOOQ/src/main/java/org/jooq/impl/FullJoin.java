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

import java.util.Collection;

import org.jooq.Condition;
import org.jooq.Field;
import org.jooq.JoinType;
import org.jooq.Record;
import org.jooq.Table;
import org.jooq.TableLike;

/**
 * @author Lukas Eder
 */
final class FullJoin
extends
    JoinTable<FullJoin>
implements
    QOM.FullJoin<Record>
{

    FullJoin(TableLike<?> lhs, TableLike<?> rhs) {
        super(lhs, rhs, JoinType.FULL_OUTER_JOIN);
    }

    FullJoin(TableLike<?> lhs, TableLike<?> rhs, Collection<? extends Field<?>> lhsPartitionBy) {
        super(lhs, rhs, JoinType.FULL_OUTER_JOIN, lhsPartitionBy);
    }

    // -------------------------------------------------------------------------
    // XXX: Query Object Model
    // -------------------------------------------------------------------------

    @Override
    FullJoin construct(
        Table<?> table1,
        Collection<? extends Field<?>> partitionBy1,
        Collection<? extends Field<?>> partitionBy2,
        Table<?> table2,
        Condition o,
        Collection<? extends Field<?>> u
    ) {
        return o != null
            ? new FullJoin(table1, table2, partitionBy1).partitionBy0(partitionBy2).on(o)
            : new FullJoin(table1, table2, partitionBy1).partitionBy0(partitionBy2).using(u);
    }
}
