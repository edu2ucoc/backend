import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  build: {
    outDir: '../../../backend/server/src/main/resources/static',  // 빌드 결과물이 생성될 위치
  },

})
