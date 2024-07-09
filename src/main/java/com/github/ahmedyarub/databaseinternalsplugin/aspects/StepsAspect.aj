package com.github.ahmedyarub.databaseinternalsplugin.aspects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.intellij.database.dialects.postgres.introspector.PgIntrospector.MyDatabaseRetriever

public aspect StepsAspect {
  private static final Logger logger = LoggerFactory.getLogger(MyDatabaseRetriever.class);
  pointcut callMyDatabaseRetrieverSteps(MyDatabaseRetriever retriever):
             call(MutableList<IntroStep> MyDatabaseRetriever.steps()) && target(retriever);


    after(MyDatabaseRetriever retriever) : callMyDatabaseRetrieverSteps(retriever) {
        logger.info("************steps()*********");
    }
}