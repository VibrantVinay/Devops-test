pipeline {
    agent any
    
    environment {
        DOCKER_HUB_CREDENTIALS = credentials('docker-hub-auth')
        IMAGE_NAME = 'yourdockerhubid/my-app'
        IMAGE_TAG = "v${env.BUILD_NUMBER}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }
        
        stage('BDD Testing (Cucumber & TestNG)') {
            steps {
                // Run the TestNG suite utilizing Maven
                sh 'mvn clean test'
            }
            post {
                always {
                    // Archive the Cucumber HTML report for visualization in Jenkins
                    archiveArtifacts artifacts: 'target/cucumber-reports.html', allowEmptyArchive: true
                }
            }
        }
        
        stage('Build & Push Docker Image') {
            steps {
                // Build the image using the Dockerfile
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} -t ${IMAGE_NAME}:latest ."
                
                // Authenticate and push to Docker Hub
                sh "echo \$DOCKER_HUB_CREDENTIALS_PSW | docker login -u \$DOCKER_HUB_CREDENTIALS_USR --password-stdin"
                sh "docker push ${IMAGE_NAME}:${IMAGE_TAG}"
                sh "docker push ${IMAGE_NAME}:latest"
            }
        }
        
        stage('Deploy to Kubernetes') {
            steps {
                // Update the deployment with the newly built image tag
                sh "kubectl set image deployment/my-app-deployment my-app=${IMAGE_NAME}:${IMAGE_TAG}"
                
                // Apply any other manifest changes
                sh "kubectl apply -f k8s/service.yaml"
                
                // Verify the rollout was successful
                sh "kubectl rollout status deployment/my-app-deployment"
            }
        }
    }
}
