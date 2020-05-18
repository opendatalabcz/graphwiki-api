def PROJECT_GIT_REPOSITORY = 'https://github.com/opendatalabcz/graphwiki-api.git'
def GIT_CREDENTIALS = 'github_GregerTomas'

node {
  git(
      url: PROJECT_GIT_REPOSITORY,
      credentialsId: GIT_CREDENTIALS
  )

  stage('MVN install') {
    sh 'mvn clean install'
  }
}
