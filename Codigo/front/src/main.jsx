import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import App from './App.jsx'

import { createBrowserRouter, Navigate, RouterProvider } from 'react-router-dom'
import HomePage from './common/components/Home/HomePage.jsx'
import OtherPage from './common/components/OtherPage.jsx'
import ErrorPage from './common/components/Error/ErrorPage.jsx'
import OtherDetails from './common/components/OtherDetails.jsx'

import { Provider } from 'react-redux'
import store from './common/redux/store.js'

const router = createBrowserRouter([
  {
    path: '/',
    element: <App />,
    errorElement: <ErrorPage />,
    children: [
      {
        path: '/',
        element: <HomePage />
      },
      {
        path: '/other',
        element: <OtherPage />
      },
      {
        path: 'otherDetails/:id',
        element: <OtherDetails />
      },
      {
        path: 'oldcontent',
        element: <Navigate to={'/other'} />
      }
    ]
  },

])

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <Provider store={store}>
      <RouterProvider router={router} />
    </Provider>
  </StrictMode>,
)
