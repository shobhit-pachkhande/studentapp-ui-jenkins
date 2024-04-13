pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                git 'https://github.com/shobhit-pachkhande/studentapp.ui.git'
                echo 'Yes, Application repository pull is done !'
            }
        }
        stage('Build') {
            steps {
                sh '/opt/apache-maven-3.9.6/bin/mvn clean package'
                echo 'Yes, Application Build is done !'
            }
        }
        stage('Test') {
            steps {
               sh '''/opt/apache-maven-3.9.6/bin/mvn sonar:sonar \\
  -Dsonar.projectKey=studentapp-ui \\
  -Dsonar.host.url=http://43.205.229.76:9000 \\
  -Dsonar.login=131713a85c7fe1f13b42fcacda50edaa2a1bb036'''
               
                echo 'Here we are testing '
            }
        }
        stage('Quality Test') {
            steps {
               waitForQualityGate abortPipeline: true, credentialsId: 'sonar-token'
                echo 'Here we are testing the quality'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy Done'
            }
        
        }
    
    }
  }
