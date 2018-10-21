#!/usr/bin/env groovy
pipeline {
    agent any
    tools {
        maven 'MAVEN'
        jdk 'JDK'
    }
    environment {
        ProjectName='springboot-demo'
        Tag='0.0.1-SNAPSHOT'
    }
    stages {
        stage('Build') {
            steps {
              echo '开始执行jenkins构建过程'
              sh 'mvn clean package -DskipTest'
            }
        }
        stage('Run') {
            steps {
                echo '=========运行应用程序========='
                sh 'java --httpPort=8433 -jar target/${ProjectName)-${Tag}.jar'
            }
        }
    }
}