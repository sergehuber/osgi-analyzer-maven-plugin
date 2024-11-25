/*
 * Copyright 2024 Serge Huber
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package dev.inoyu.maven.plugins.osgi.analyzer.mojos;

import org.apache.maven.it.Verifier;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

public class LocatePackageIT extends AbstractMojoTestCase {

    public void testLocatePackage() throws Exception {
        File testProjectDir = new File(getBasedir(), "target/it/projects/locate-package-test");

        Verifier verifier = new Verifier(testProjectDir.getAbsolutePath());
        verifier.setAutoclean(false);

        verifier.setSystemProperty("package", "org.osgi.framework");
        verifier.executeGoal("dev.inoyu:osgi-analyzer-maven-plugin:locate-package");

        verifier.verifyErrorFreeLog();
        verifier.verifyTextInLog("Package found in Dependency: org.osgi:org.osgi.core");

        verifier.resetStreams();
    }
}