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

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Function;

import org.jooq.QueryPart;

/**
 * @author Lukas Eder
 */
sealed class QueryPartList<T extends QueryPart>
extends
    QueryPartListView<T>
permits
    CommonTableExpressionList,
    GroupFieldList,
    SelectFieldList,
    SortFieldList,
    TableList,
    TopLevelCte,
    WindowList,
    QualifiedSelectFieldList
{

    QueryPartList() {
        this((Collection<T>) null);
    }

    @SafeVarargs
    QueryPartList(T... wrappedList) {
        this(asList(wrappedList));
    }

    QueryPartList(Iterable<? extends T> wrappedList) {
        super(new ArrayList<>());

        addAll0(wrappedList);
    }

    @Override
    QueryPartList<T> qualify(boolean newQualify) {
        return (QueryPartList<T>) super.qualify(newQualify);
    }

    @Override
    QueryPartList<T> map(Function<? super T, ? extends T> newMapper) {
        return (QueryPartList<T>) super.map(newMapper);
    }

    @Override
    QueryPartList<T> map(ObjIntFunction<? super T, ? extends T> newMapper) {
        return (QueryPartList<T>) super.map(newMapper);
    }

    @Override
    QueryPartList<T> separator(String newSeparator) {
        return (QueryPartList<T>) super.separator(newSeparator);
    }

    @SuppressWarnings("rawtypes")
    private static final Lazy<QueryPartList> EMPTY_LIST = Lazy.of(() -> new QueryPartList<>());

    @SuppressWarnings("unchecked")
    static final <Q extends QueryPart> QueryPartList<Q> emptyList() {
        return EMPTY_LIST.get();
    }
}
