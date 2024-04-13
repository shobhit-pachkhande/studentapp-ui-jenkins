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
               sh '''/opt/apache-maven-3.9.6/bin/sh '''mvn sonar:sonar \\
  -Dsonar.projectKey=studentapp-ui \\
  -Dsonar.host.url=http://65.2.166.32:9000 \\
  -Dsonar.login=7cc1bcd7fc69fccbc24feb09467147fab735d665'''
               
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
