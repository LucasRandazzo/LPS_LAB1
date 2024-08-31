import React from 'react'
import { Outlet } from 'react-router-dom'
import { DashboardLayout } from './common/layouts/DashboardLayout'

function App() {
  return (
    <div>
      <DashboardLayout>
        <Outlet />
      </DashboardLayout>
    </div>
  )
}

export default App