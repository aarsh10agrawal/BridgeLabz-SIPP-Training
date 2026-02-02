/** @type {import('tailwindcss').Config} */
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        background: '#0F172A',
        surface: '#1E293B',
        primary: '#2DD4BF',
        text: '#E5E7EB',
        positive: '#22C55E',
        negative: '#F87171',
      },
      backgroundImage: {
        'app-bg': "url('https://wallpapercave.com/wp/wp9905571.jpg')",
        // 'app-bg': "url('https://img.freepik.com/premium-photo/stack-papers-desk-with-stack-papers-it_900958-3438.jpg?w=2000')",
      
      },
    },
  },
  plugins: [],
}

