# MySQL-Primary-Replica toy project

### DB Master : 메인 프로세스 담당
### DB Slave : Master를 보조하는 Slave로 메인 프로세스 이외의 잡일 담당

## 역할 정리

### 1) DB 트래픽 분산일때
#### DB Master : 데이터의 변경( INSERT, UPDATE, DELETE )
#### DB Slave : 데이터 읽기 (SELECT)

### 2) 백업
#### DB Master : 데이터 원본 서버
#### DB Slave : 백업서버

