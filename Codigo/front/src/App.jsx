import './App.css'
import { Outlet } from 'react-router-dom'
import Header from './common/components/App/Header/Header'
import Footer from './common/components/App/Footer/Footer'

function App() {
  return (
    <div>
      <Header />
      <main>
        <Outlet />
      </main>
      <Footer />
    </div>
  )
}

export default App
