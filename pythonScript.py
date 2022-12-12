import threading
import os
import subprocess
import re

lastCommit = ""

def pullStopAndDeploy():
  os.system('git pull')
  os.system('mvn clean compile install')
  os.system('java -jar target/ds-2020-0.0.1-SNAPSHOT.jar')
  os.system('cd frontend/ && npm install --force && npm start')

def app():
  os.system('git fetch')
  result = subprocess.run(['git', 'reflog', 'origin/master'], stdout=subprocess.PIPE)

  commitsOnRemote = result.stdout.splitlines()[0]
  regexResult = re.search("(.*) refs/remotes", commitsOnRemote.decode("utf-8"))
  lastCommitOnRemote = result.group(1)

  if(lastCommit == lastCommitOnRemote):
    lastCommit = lastCommitOnRemote
    pullStopAndDeploy()
  

def printit():
  threading.Timer(5.0, printit).start()
  app()

printit()



