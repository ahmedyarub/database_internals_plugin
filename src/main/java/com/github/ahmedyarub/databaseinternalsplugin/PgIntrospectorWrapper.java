package com.github.ahmedyarub.databaseinternalsplugin;

import com.intellij.database.dataSource.LocalDataSource;
import com.intellij.database.dialects.base.introspector.BaseMultiDatabaseIntrospector;
import com.intellij.database.dialects.base.introspector.BaseNativeIntrospector;
import com.intellij.database.dialects.postgres.introspector.PgIntrospector;
import com.intellij.database.dialects.postgres.model.PgDatabase;
import com.intellij.database.dialects.postgres.model.PgModelHelper;
import com.intellij.database.dialects.postgres.model.PgRoot;
import com.intellij.database.dialects.postgres.model.PgSchema;
import com.intellij.database.dialects.postgresgreenplumbase.introspector.PgGPlumBaseIntroQueries;
import com.intellij.database.dialects.postgresgreenplumbase.introspector.PgGPlumBaseIntrospector;
import com.intellij.database.dialects.postgresgreenplumbase.model.PgGPlumBaseModelHelper;
import com.intellij.database.layoutedQueries.DBTransaction;
import com.intellij.database.model.basic.BasicModModel;
import com.intellij.database.util.TreePattern;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PgIntrospectorWrapper extends PgGPlumBaseIntrospector<PgRoot, PgDatabase, PgSchema> {
    private final PgIntrospector introspector;

    public PgIntrospectorWrapper(PgIntrospector introspector) {
        super();
        this.introspector = introspector;
    }

    @Override
    public @NotNull BasicModModel init(@Nullable BasicModModel basicModModel, @NotNull LocalDataSource localDataSource, @Nullable TreePattern treePattern) {
        return introspector.init(basicModModel, localDataSource, treePattern);
    }

    @Override
    protected @NotNull PgGPlumBaseModelHelper getHelper() {
        return PgModelHelper.INSTANCE;
    }

    @Override
    protected @NotNull PgGPlumBaseIntroQueries getQueries() {
        return PgIntrospector.QUERIES;
    }

    @Override
    public @NotNull String generateDbAge(@NotNull String s) {
        return introspector.generateDbAge(s);
    }

    @Override
    protected @NotNull BaseMultiDatabaseIntrospector<PgRoot, PgDatabase, PgSchema>.BaseDatabaseRetriever<? extends PgDatabase> createDatabaseRetriever(@NotNull DBTransaction dbTransaction, @NotNull PgDatabase pgDatabase) {
        return new PgGPlumBaseIntrospector<PgRoot, PgDatabase, PgSchema>.PgGPlumBaseDatabaseRetriever<>(dbTransaction, pgDatabase);
    }

    @Override
    protected @NotNull BaseNativeIntrospector<PgRoot, PgDatabase, PgSchema>.AbstractSchemaRetriever<? extends PgSchema> createSchemaRetriever(@NotNull DBTransaction dbTransaction, @NotNull PgSchema pgSchema) {
        return introspector.createSchemaRetriever(dbTransaction, pgSchema);
    }
}
