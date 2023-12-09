pipeline {
  agent any
  stages {
    stages {
      stage('Build') {
        steps {
          sh 'mvn -B -DskipTests clean package'
        }
      }
    }
  }
}
