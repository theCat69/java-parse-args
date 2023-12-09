pipeline {
  agent any
  stages {
    withMaven {
      stage('Build') {
        steps {
          sh 'mvn -B -DskipTests clean package'
        }
      }
    }
  }
}
