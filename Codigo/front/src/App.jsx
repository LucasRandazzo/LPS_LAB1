import { Outlet } from 'react-router-dom'
import './App.css'
import Footer from './common/components/App/Footer/Footer'
import Header from './common/components/App/Header/Header'
import Registration from './common/components/registration'

function App() {
  
  return (
    <div>
      <Header />
      <main>
        <Outlet />
        <Registration />
      </main>
      <Footer />
    </div>
  )
}

export default App
