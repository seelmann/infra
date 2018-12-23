job('directory-ldap-api') {
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
    steps {
        shell ('''#!/bin/bash
docker pull apachedirectory/maven-build:jdk-11
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/user/.m2 \
    -v $(pwd):/home/user/project \
    apachedirectory/maven-build:jdk-11
''')
        shell ('''#!/bin/bash
docker pull apachedirectory/maven-build:jdk-8
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/user/.m2 \
    -v $(pwd):/home/user/project \
    apachedirectory/maven-build:jdk-8
''')
    }
    publishers {
        wsCleanup()
    }
}

job('directory-server') {
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
        shell ('''#!/bin/bash
docker pull apachedirectory/maven-build:jdk-11
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/user/.m2 \
    -v $(pwd):/home/user/project \
    apachedirectory/maven-build:jdk-11
''')
        shell ('''#!/bin/bash
docker pull apachedirectory/maven-build:jdk-8
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/user/.m2 \
    -v $(pwd):/home/user/project \
    apachedirectory/maven-build:jdk-8
''')
    }
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
}


job('directory-studio') {
    logRotator(-1, 3)
    scm {
        github('apache/directory-studio', 'master')
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
        shell ('''#!/bin/bash
docker pull apachedirectory/studio-build:jdk-11
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/hnelson/.m2 \
    -v $(pwd):/home/hnelson/studio \
    apachedirectory/studio-build:jdk-11
''')
        shell ('''#!/bin/bash
docker pull apachedirectory/studio-build:jdk-8
docker run -i --rm \
    -u $(id -u):$(id -g) \
    -v ~/.m2:/home/hnelson/.m2 \
    -v $(pwd):/home/hnelson/studio \
    apachedirectory/studio-build:jdk-8
''')
    }
    publishers {
        wsCleanup()
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
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
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
    publishers {
        wsCleanup()
    }
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
