#!/usr/bin/env groovy
def PID = ''
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
                echo '开始执行jenkins构建过程，跳过单元测试'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Run') {
            steps {
                script {
                    def filePath = '/home/${ProjectName}.pid'
                    if (fileExists(filePath)) {
                        PID = readFile(filePath)
                        echo 'kill -9 pid杀掉原有进程'
                        sh 'kill -9 ' + PID
                    }
                }
                echo '=========运行应用程序，并且为后台启动========='
                sh 'java -jar target/${ProjectName}-${Tag}.jar &'
                // 将进程id记录到文件中
                sh 'echo $! > /home/${ProjectName}.pid'
            }
        }
    }
}