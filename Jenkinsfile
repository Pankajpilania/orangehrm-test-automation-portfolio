pipeline {
    agent any

    tools {
        maven 'Maven 3.8.x'
        jdk 'Java 17'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('Test: Smoke & Regression') {
            steps {
                dir('selenium-java') {
                    sh 'mvn clean test -Dgroups="smoke,regression" -Dbrowser=chrome'
                }
            }
        }
    }
    
    post {
        always {
            dir('selenium-java') {
                archiveArtifacts artifacts: 'target/test-output/ExtentReport.html', allowEmptyArchive: true
            }
        }
    }
}
