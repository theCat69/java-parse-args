pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'Hello from my build'
        withMaven {
          sh "mvn -B -DskipTests clean package"
        }
      }
    }
  }
}
