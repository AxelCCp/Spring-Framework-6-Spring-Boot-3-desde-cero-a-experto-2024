import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'

export const App = () => {

  const [count, setCount] = useState(0)

  return (
    <>
     <h1>Vite + react</h1>
     <div className='card'>
      <button onClick={() => setCount((coun) => count + 1)}>
        El contador es coun {count}
      </button>
     </div>
    </>
  )

}


