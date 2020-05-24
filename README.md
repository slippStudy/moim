[![Build Status](https://travis-ci.org/slippStudy/franchise.svg?branch=recruitmanage&kill_cache=1)](https://travis-ci.org/slippStudy/franchise)
[![Coverage Status](https://coveralls.io/repos/github/slippStudy/franchise/badge.svg?branch=recruitmanage&kill_cache=1)](https://coveralls.io/github/slippStudy/franchise?branch=recruitmanage&kill_cache=1)

실행방법 (프로젝트 루트 폴더에서 실행)
1. Docker 이미지 빌드
    > gradle jibDockerBuild 
2. Docker 이미지 한 번에 실행
    > docker-compose -f src/main/docker/app.yml up
3. Docker 이미지 한 번에 종료
    > docker-compose -f src/main/docker/app.yml down

