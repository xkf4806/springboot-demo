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
                echo '开始执行jenkins构建过程，跳过单元测试'
                sh 'mvn clean package -DskipTests'
            }
        }
        stage('Run') {
            steps {
                script {
                    def pid = sh returnStdout: true , script: "ps aux | grep java | grep ${ProjectName} | awk '{print" +
                            " \$2}'"
                    if (pid != null && pid != '') {
                        echo "杀死应用进程： ${pid}"
                        sh "kill -9 ${pid}"
                    }
                    echo '=========运行应用程序，并且为后台启动========='
                    withEnv(['JENKINS_NODE_COOKIE=background_job']) {
                        sh 'java -jar target/${ProjectName}-${Tag}.jar > /home/${ProjectName}.log &'
                        // 将进程id记录到文件中
                        sh 'echo $! > /home/${ProjectName}.pid'
                    }
                }
            }
        }
    }
}