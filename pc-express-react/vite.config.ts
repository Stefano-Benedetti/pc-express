import { defineConfig } from 'vite'
import react from '@vitejs/plugin-react'

// https://vite.dev/config/
export default defineConfig({
  plugins: [react()],
  base: '/react/',
  build: {
    // specifica dove buildare il progetto e di svuotare la directory target
    outDir: '../src/main/resources/static/react',
    emptyOutDir: true,
  },
})
