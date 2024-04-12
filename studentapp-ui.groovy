pipeline {
    agent any

    stages {
        stage('Pull') {
            steps {
                git 'https://github.com/chetansomkuwar254/studentapp.ui.git'
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
                sh 'run mvn sonar:sonar mvn sonar:sonar   -Dsonar.projectKey=studentapp-ui   -Dsonar.host.url=http://13.234.204.176:9000   -Dsonar.login=5d71f02015694d38dad98d5a6d2641eff8adab2d''
                echo 'Here we are testing '
            }
        }
        stage('Quality Test') {
            steps {
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
}