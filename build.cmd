@echo off
if exist target (
    echo start delete target...
    del /F /S /Q target
) else (
    echo skip delete target
)
call mvn clean install -Dmaven.test.skip=true
if not exist target (
    echo create target dir...
    mkdir target
) else (
     echo skip create target
 )
echo start copy jar to target...
copy .\ndovel-author\target\ndovel-author-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-editor\target\ndovel-editor-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-reader\target\ndovel-reader-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-server\ndovel-server-center\target\ndovel-server-center-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-user\ndovel-user-server\target\ndovel-user-server-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-gateway\target\ndovel-gateway-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-log\logger-server\target\logger-server-2.0.0-SNAPSHOT.jar .\target\
copy .\ndovel-spider\target\ndovel-spider-2.0.0-SNAPSHOT.jar .\target