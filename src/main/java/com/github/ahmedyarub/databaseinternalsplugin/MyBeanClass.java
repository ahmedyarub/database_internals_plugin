package com.github.ahmedyarub.databaseinternalsplugin;

import com.intellij.serviceContainer.LazyExtensionInstance;
import org.jetbrains.annotations.Nullable;

public final class MyBeanClass extends LazyExtensionInstance<com.intellij.database.introspection.DBIntrospector.Factory> {
    MyBeanClass(){
        super();
    }

    @Override
    protected @Nullable String getImplementationClassName() {
        return "MyExtensionUsingService";
    }
}
