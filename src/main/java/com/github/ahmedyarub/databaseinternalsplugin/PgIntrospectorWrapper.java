package com.github.ahmedyarub.databaseinternalsplugin;

import com.intellij.database.dataSource.DatabaseConnectionCore;
import com.intellij.database.dataSource.LocalDataSource;
import com.intellij.database.introspection.DBIntrospectionOptions;
import com.intellij.database.introspection.DBIntrospector;
import com.intellij.database.introspection.IntrospectionMetricKey;
import com.intellij.database.introspection.IntrospectionTask;
import com.intellij.database.model.ObjectKind;
import com.intellij.database.model.basic.BasicDatabase;
import com.intellij.database.model.basic.BasicElement;
import com.intellij.database.model.basic.BasicModModel;
import com.intellij.database.model.basic.BasicNamespace;
import com.intellij.database.util.TreePattern;
import com.intellij.database.util.common.TimeAggEntry;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.util.PairConsumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PgIntrospectorWrapper implements DBIntrospector {
    private final DBIntrospector introspector;

    public PgIntrospectorWrapper(DBIntrospector introspector) {
        this.introspector = introspector;
    }

    @Override
    public @NotNull BasicModModel init(@Nullable BasicModModel basicModModel, @NotNull LocalDataSource localDataSource, @Nullable TreePattern treePattern) {
        return introspector.init(basicModModel, localDataSource, treePattern);
    }

    @Override
    public @NotNull BasicModModel getModel() {
        return introspector.getModel();
    }

    @Override
    public @NotNull DBIntrospectionOptions getOptions() {
        return introspector.getOptions();
    }

    @Override
    public void setOptions(@NotNull DBIntrospectionOptions dbIntrospectionOptions) {
        introspector.setOptions(dbIntrospectionOptions);
    }

    @Override
    public @NotNull PairConsumer<String, Throwable> getErrorSink() {
        return introspector.getErrorSink();
    }

    @Override
    public void setErrorSink(@NotNull PairConsumer<String, Throwable> pairConsumer) {
        introspector.setErrorSink(pairConsumer);
    }

    @Override
    public @Nullable ProgressIndicator getProgressIndicator() {
        return introspector.getProgressIndicator();
    }

    @Override
    public void setProgressIndicator(@Nullable ProgressIndicator progressIndicator) {
        introspector.setProgressIndicator(progressIndicator);
    }

    @Override
    public void attachToDB(@NotNull DatabaseConnectionCore databaseConnectionCore) {
        introspector.attachToDB(databaseConnectionCore);
    }

    @Override
    public void detachFromDB() {
        introspector.detachFromDB();
    }

    @Override
    public void introspect(@NotNull Collection<? extends IntrospectionTask> collection) {
        introspector.introspect(collection);
    }

    @Override
    public void introspect(@NotNull IntrospectionTask introspectionTask) {
        introspector.introspect(introspectionTask);
    }

    @Override
    public void introspectAuto(@NotNull BasicNamespace[] basicNamespaces) {
        introspector.introspectAuto(basicNamespaces);
    }

    @Override
    public void introspectServerObjects() {
        introspector.introspectServerObjects();
    }

    @Override
    public void introspectNamespaces() {
        introspector.introspectNamespaces();
    }

    @Override
    public void introspectDatabaseSchemas(@NotNull BasicDatabase... basicDatabases) {
        introspector.introspectDatabaseSchemas(basicDatabases);
    }

    @Override
    public void introspectSessionState() {
        introspector.introspectSessionState();
    }

    @Override
    public @NotNull Map<BasicElement, Boolean> checkElementsUptodate(@NotNull Iterable<? extends BasicElement> iterable) {
        return introspector.checkElementsUptodate(iterable);
    }

    @Override
    public void introspectFragment(@NotNull BasicNamespace basicNamespace, boolean b, @NotNull ObjectKind objectKind, @NotNull String... strings) {
        introspector.introspectFragment(basicNamespace, b, objectKind, strings);
    }

    @Override
    public @NotNull Map<BasicElement, String[]> introspectNativeDefinitions(@NotNull Iterable<? extends BasicElement> iterable, boolean b) {
        return introspector.introspectNativeDefinitions(iterable, b);
    }

    @Override
    public @NotNull List<IntrospectionTask> listDeferredTasks() {
        return introspector.listDeferredTasks();
    }

    @Override
    public void processDeferredTasks() {
        introspector.processDeferredTasks();
    }

    @Override
    public void performFinalDiagnostics() {
        introspector.performFinalDiagnostics();
    }

    @Override
    public @Nullable String getCurrentDatabase() {
        return introspector.getCurrentDatabase();
    }

    @Override
    public @NotNull TreePattern getDefaultScope() {
        return introspector.getDefaultScope();
    }

    @Override
    public @NotNull List<TimeAggEntry<IntrospectionMetricKey>> getMetrics() {
        return introspector.getMetrics();
    }

    @Override
    public @Nullable String collectDiagnosticInfo(@NotNull Iterable<? extends BasicElement> iterable) {
        return introspector.collectDiagnosticInfo(iterable);
    }
}
