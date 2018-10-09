mavenJob('directory-ldap-api-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-ldap-api', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-server-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-server', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install -Dtest="!ClientAddRequestTest,!OperationWithIndexTest,!*PerfIT,!ReferralIT,!TestUtils" -DfailIfNoTests=false')
    archivingDisabled(true)
}

job('directory-server-installers-docker-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-server', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    steps {
        maven {
            goals('-V -f pom.xml clean install -DskipTests')
            mavenInstallation('Maven')
        }
        maven {
            goals('-V -f installers/pom.xml -V clean install -Pinstallers -Pdocker')
            mavenInstallation('Maven')
        }
        shell ('''#!/bin/bash
# Test installers (deb, rpm, bin) in various docker containers.
set -e

docker ps

chmod +x installers/target/docker/run-tests.sh
installers/target/docker/run-tests.sh

docker ps
''')
    }
}


job('directory-studio-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-studio', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        xvfb('default') {
            screen('1024x768x24')
        }
        timeout {
            absolute(60)
        }
    }
    steps {
        maven {
            goals('-V -f pom-first.xml clean install')
            mavenInstallation('Maven')
        }
        maven {
            goals('-V -Denable-ui-tests -pl !product clean install')
            mavenInstallation('Maven')
        }
    }
    publishers {
        wsCleanup {
            includePattern('product/target/products/**')
            deleteDirectories(true)
        }
    }
}

mavenJob('directory-mavibot-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-mavibot', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-kerby-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-kerby', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-fortress-core-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-fortress-core', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-fortress-realm-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-fortress-realm', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-fortress-rest-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-fortress-enmasse', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

mavenJob('directory-fortress-web-generated') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-fortress-commander', 'master')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        timeout {
            absolute(60)
        }
    }
    rootPOM('pom.xml')
    goals('-V clean install')
    archivingDisabled(true)
}

job('clean-m2-repository-generated') {
    logRotator(-1, 3)
    wrappers {
        timeout {
            absolute(60)
        }
    }
    steps {
        shell ('''#!/bin/bash
set -e
#rm -rf ~/.m2/repository
rm -rf ~/.m2/repository/p2/osgi/bundle/org.apache.directory.*
''')
    }
}
