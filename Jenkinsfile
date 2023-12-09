pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        withMaven(mavenLocalRepo: '.repository') {
          sh "mvn -B -DskipTests clean package"
        }
      }
    }
    stage('Test') {
      steps {
        withMaven(mavenLocalRepo: '.repository') {
          sh "mvn test"
        }
      }
    }
  }
}
