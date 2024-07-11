import React from 'react'
import ReactDOM from 'react-dom/client'
import {App} from './App.jsx'
//import './index.css'
import { ProductApp } from './components/ProductApp.jsx'

ReactDOM.createRoot(document.getElementById('root')).render(
  <React.StrictMode>
    <ProductApp title={'lista de productos'} />
  </React.StrictMode>,
)
