import React from 'react';
import './App.css';
import Display from './components/display';

function App() {
  return (
    <div className="App">
      <div>
        <h1>
          Welcome To Chipmunk Debugger!
        </h1>
      </div>
      <div className="display_container">
        <Display/>
      </div>
    </div>
  );
}

export default App;
