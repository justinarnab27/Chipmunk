import React, { useEffect, useState } from 'react';
import './App.css';
import Display from './components/display';
import ProgramCode from './components/programCode';
import ProgramData from './components/programData';
import { ProgramStateParsed, ProgramStateUnParsed } from './models';

const App = () => {
  const [programStateParsed, setProgramStateParsed] = useState<ProgramStateParsed>({programSource: [], lineNumber: 0, displayMatrix: []});
  const processProgramState = (programeState: ProgramStateUnParsed) => {
    // const arr = new Array() 

    const lines = programeState.displayMatrixAsString.split('\n');
    const arr: number[][] = [];
    for (let line of lines) {
      // console.log(line.split(' '));
      arr.push(line.split(' ').slice(0,-1).map(c => parseInt(c)));
      // arr.push()
    }

    // console.log(arr);
    const newState: ProgramStateParsed = {...programStateParsed, programSource: programeState.programSource,
          lineNumber: Math.floor((programeState.programCounter - 512)/2),
          displayMatrix: arr
        };
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
        <Display displayMatrix={programStateParsed.displayMatrix}/>
        <ProgramData/>
      </div>
    </div>
  );
}

export default App;
