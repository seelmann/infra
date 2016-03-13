mavenJob('directory-shared-generated') {
    logRotator(-1, 100)
    scm {
        github('apache/directory-shared', 'master')
    }
    triggers {
        scm('@daily')
    }
    rootPOM('pom.xml')
    goals('clean install')
}
