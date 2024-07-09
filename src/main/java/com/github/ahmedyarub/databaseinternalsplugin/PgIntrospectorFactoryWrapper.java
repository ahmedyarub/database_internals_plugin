package com.github.ahmedyarub.databaseinternalsplugin;

import com.intellij.database.Dbms;
import com.intellij.database.dialects.postgres.introspector.PgIntrospector;
import com.intellij.database.introspection.DBIntrospectionContext;
import com.intellij.database.introspection.DBIntrospector;
import com.intellij.database.model.ModelFactory;
import com.intellij.database.model.ObjectKind;
import com.intellij.database.model.basic.BasicElement;
import com.intellij.database.util.Version;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("UnstableApiUsage")
public final class PgIntrospectorFactoryWrapper implements DBIntrospector.Factory {
    private final PgIntrospector.Factory factory;

    PgIntrospectorFactoryWrapper() {
        this.factory = new PgIntrospector.Factory();
    }

    @Override
    public @NotNull DBIntrospector createIntrospector(@NotNull DBIntrospectionContext dbIntrospectionContext, @NotNull Dbms dbms, @NotNull ModelFactory modelFactory) {
        return new PgIntrospectorWrapper((PgIntrospector) this.factory.createIntrospector(dbIntrospectionContext, dbms, modelFactory), modelFactory);
    }

    @Override
    public boolean isSupported(@NotNull Version version) {
        return this.factory.isSupported(version);
    }

    @Override
    public int getVersion(@NotNull ObjectKind objectKind) {
        return this.factory.getVersion(objectKind);
    }

    @Override
    public boolean isOutdatedCheckSupported(@Nullable BasicElement basicElement) {
        return this.factory.isOutdatedCheckSupported(basicElement);
    }
}
