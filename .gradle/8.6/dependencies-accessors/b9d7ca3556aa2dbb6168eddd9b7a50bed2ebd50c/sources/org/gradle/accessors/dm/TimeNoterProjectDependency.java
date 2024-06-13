package org.gradle.accessors.dm;

import org.gradle.api.NonNullApi;
import org.gradle.api.artifacts.ProjectDependency;
import org.gradle.api.internal.artifacts.dependencies.ProjectDependencyInternal;
import org.gradle.api.internal.artifacts.DefaultProjectDependencyFactory;
import org.gradle.api.internal.artifacts.dsl.dependencies.ProjectFinder;
import org.gradle.api.internal.catalog.DelegatingProjectDependency;
import org.gradle.api.internal.catalog.TypeSafeProjectDependencyFactory;
import javax.inject.Inject;

@NonNullApi
public class TimeNoterProjectDependency extends DelegatingProjectDependency {

    @Inject
    public TimeNoterProjectDependency(TypeSafeProjectDependencyFactory factory, ProjectDependencyInternal delegate) {
        super(factory, delegate);
    }

    /**
     * Creates a project dependency on the project at path ":TimeNoterAndroid"
     */
    public TimeNoterAndroidProjectDependency getTimeNoterAndroid() { return new TimeNoterAndroidProjectDependency(getFactory(), create(":TimeNoterAndroid")); }

    /**
     * Creates a project dependency on the project at path ":TimeNoterApp"
     */
    public TimeNoterAppProjectDependency getTimeNoterApp() { return new TimeNoterAppProjectDependency(getFactory(), create(":TimeNoterApp")); }

}
