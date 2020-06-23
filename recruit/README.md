실행방법 (프로젝트 루트 폴더에서 실행)
1. Docker 이미지 빌드
```bash
> ./gradlew jibDockerBuild
```
     
2. Docker 이미지 한 번에 실행
```bash
> docker-compose -f src/main/docker/app.yml up -d
```
3. Docker 이미지 한 번에 종료
```bash
> docker-compose -f src/main/docker/app.yml down
```