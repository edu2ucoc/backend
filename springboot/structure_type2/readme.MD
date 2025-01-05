# frontend/type1
    - npx create-react-app client
    - cd client
    - npm i --save-dev react-app-rewired
    - config-overrides.js 생성 
        ```
            const path = require('path');

            module.exports = {
                webpack: function (config, env) {
                    config.output = {
                        ...config.output,
                        // 아래 위치 커스텀 수정
                        path: path.resolve(__dirname, '../../../backend/server/src/main/resources/static'), // 원하는 위치 지정
                        filename: 'bundle.js'
                    };
                    return config;
                }
            };
        ```
    - package.json
        ```
            "scripts": {
                ...
                "rebuild": "react-app-rewired build",
                ...
            },
        ```
    - npm run rebuild
    - npm run build 
        - ~/backend/server/src/main/resources/static/*.* : 리소스들이 위치됨
        - ~/backend/server/src/main/resources/static/index.html -> ~/backend/server/src/main/resources/templates/index.html 이동(1회만)
    - 백엔드 서버 가동 하여 확인

# frontend/type2
    - npx create-vite@latest
        - Project name: client
        - Select a framework: › React 선택
        - Select a variant: › JavaScript 선택
    - cd client
    - npm install
    - vite.config.js 오픈
        - 다음 내용 추가
            ```
                ,
                build: {
                    outDir: '../../../backend/server/src/main/resources/static',  // 빌드 결과물이 생성될 위치, 상대경로로 서버 위치 저장
                },

            ```
        - 저장
    - npm run build 
        - ~/backend/server/src/main/resources/static/*.* : 리소스들이 위치됨
        - ~/backend/server/src/main/resources/static/index.html -> ~/backend/server/src/main/resources/templates/index.html 이동(매번 처리)
    - 백엔드 서버 가동 하여 확인
            