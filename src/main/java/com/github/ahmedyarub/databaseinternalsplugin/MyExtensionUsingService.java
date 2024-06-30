package com.github.ahmedyarub.databaseinternalsplugin;

import com.intellij.openapi.components.Service;
import com.intellij.openapi.extensions.ExtensionPointName;

@Service(Service.Level.PROJECT)
public final class MyExtensionUsingService {
//    public MyExtensionUsingService() {
//    }
//
private static final ExtensionPointName<MyBeanClass> EP_NAME =
        ExtensionPointName.create("com.intellij.database.introspector");

        public Boolean isIncremental(){
            return false;
        }

//    public void useRegisteredExtensions() {
////        for (MyBeanClass extension : EP_NAME.getExtensionList()) {
////            String clazz = extension.getImplementationClassName();
////            // ...
////        }
//    }

}
