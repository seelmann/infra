mavenJob('directory-shared-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-shared', 'trunk')
    }
    triggers {
        scm('@daily')
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
        scm('@daily')
    }
    rootPOM('pom.xml')
    goals('-V clean install')
}

