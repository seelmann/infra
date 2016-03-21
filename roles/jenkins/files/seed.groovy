mavenJob('directory-shared-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-shared', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-server-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-server', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

job('directory-studio-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-studio', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    wrappers {
        xvfb('default') {
            screen('1024x768x24')
        }
    }
    steps {
        maven('-V -f pom-first.xml clean install')
        maven('-V -Denable-ui-tests -pl !product clean install')
    }
    publishers {
        wsCleanup {
            includePattern('product/target/products/**')
            deleteDirectories(true)
        }
    }
}

mavenJob('directory-mavibot-generated') {
    logRotator(-1, 100)
    scm {
        svn('https://svn.apache.org/repos/asf/directory/mavibot/trunk/')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-kerby-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-kerby', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-fortress-core-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-fortress-core', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-fortress-realm-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-fortress-realm', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-fortress-rest-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-fortress-enmasse', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

mavenJob('directory-fortress-web-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-fortress-commander', 'trunk')
    }
    triggers {
        cron('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

