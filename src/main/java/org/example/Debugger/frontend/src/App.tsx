import React, { useEffect, useState } from 'react';
import './App.css';
import Display from './components/display';
import ProgramCode from './components/programCode';
import ProgramData from './components/programData';
import { ProgramStateParsed, ProgramStateUnParsed } from './models';

const App = () => {
  const [programStateParsed, setProgramStateParsed] = useState<ProgramStateParsed>({programSource: [], lineNumber: 0});
  const processProgramState = (programeState: ProgramStateUnParsed) => {
    const newState: ProgramStateParsed = {...programStateParsed, programSource: programeState.programSource, lineNumber: Math.floor((programeState.programCounter - 512)/2)};
    setProgramStateParsed(newState);

  }

  const getProgramState = () => {
    fetch("http://localhost:8080/")
            .then((json) => {
                json.json()
                    .then(t => {
                      // console.log(t);
                      processProgramState(t);
                    })});
  }
  useEffect(() => {
    // setTimeout(
    setInterval(
        () => getProgramState(),
        500
      );
  }, [])
  

  return (
    <div className="App">
      <div>
        <h1>
          Welcome To Chipmunk Debugger!
        </h1>
      </div>
      <div className="display_container">
        <ProgramCode programSource={programStateParsed.programSource} lineNumber={programStateParsed.lineNumber}/>
        <Display/>
        <ProgramData/>
      </div>
    </div>
  );
}

export default App;
