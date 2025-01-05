/* config-overrides.js */

// module.exports = function override(config, env) {
//     return config;
// }
const path = require('path');

module.exports = {
    webpack: function (config, env) {
        config.output = {
            ...config.output,
            // 아래에 기술한 내용은 이전 내용을 덮어쓴다
            // C:\Users\user\Documents\projects\deploy_type3\server\src\main\resources\static
            path: path.resolve(__dirname, '../../../backend/server/src/main/resources/static'), // 원하는 위치 지정
            filename: 'bundle.js'
        };
        return config;
    }
};