import React, { useEffect, useState } from 'react';
import './App.css';
import Display from './components/display';
import ProgramCode from './components/programCode';
import ProgramData from './components/programData';
import { PostAction, ProgramStateParsed, ProgramStateUnParsed } from './models';

const App = () => {
  const [programStateParsed, setProgramStateParsed] = useState<ProgramStateParsed>({
    programSource: [],
    lineNumber: 0,
    displayMatrix: [],
    allRegisterNames: [],
    allRegisters: [],
    programCounter: 0,
    breakPoints: new Set<number>()
  });
  const [prevAllRegisters, setPrevAllRegisters] = useState<number[]>([]);
  const [autoPlayPaused, setAutoPlayPaused] = useState<boolean>(false);
  const processProgramState = (programeState: ProgramStateUnParsed) => {
    // const arr = new Array() 
    setAutoPlayPaused(programeState.playPaused);
    const lines = programeState.displayMatrixAsString.split('\n');
    const arr: number[][] = [];
    for (let line of lines) {
      // console.log(line.split(' '));
      arr.push(line.split(' ').slice(0,-1).map(c => parseInt(c)));
      // arr.push()
    }

    // console.log("AAAAAAA " + programStateParsed.allRegisters);
    // console.log(prevAllRegisters.some((val, ix) => val !== programeState.allRegisters[ix]));
    
    const newState: ProgramStateParsed = {programSource: programeState.programSource,
          lineNumber: Math.floor((programeState.programCounter - 512)/2),
          displayMatrix: arr,
          allRegisterNames: programeState.allRegisterNames,
          allRegisters: programeState.allRegisters,
          programCounter: programeState.programCounter,
          breakPoints: new Set<number>(programeState.breakPoints)
        };
    setProgramStateParsed((prevState) => {
      // setPrevAllRegisters(prevState.allRegisters);
      if(programeState.allRegisters.some((val, ix) => val !== prevState.allRegisters[ix])) {
        // console.log("hiiiiii");
        setPrevAllRegisters(prevState.allRegisters);
      }
      return newState;
    });

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
  const handlePostMethods = (action: PostAction, ix: number | null = null) => {
        fetch("http://localhost:8080/", {
          method: 'POST',
          headers: new Headers(),
          body: action + (ix === null ? '' : ' ' + ix)
        })
    getProgramState();
  }
  useEffect(() => {
    // setTimeout(
    setInterval(
        () => getProgramState(),
        100
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
        <ProgramCode programSource={programStateParsed.programSource}
                    lineNumber={programStateParsed.lineNumber}
                    handlePostMethods={handlePostMethods}
                    breakPoints={programStateParsed.breakPoints}
                    autoPlayPaused={autoPlayPaused}
                    setAutoPlayPaused={setAutoPlayPaused}/>
        <Display displayMatrix={programStateParsed.displayMatrix}/>
        <ProgramData allRegisterNames={programStateParsed.allRegisterNames}
                    allRegisters={programStateParsed.allRegisters}
                    prevAllRegisters={prevAllRegisters}
                    programCounter={programStateParsed.programCounter}/>
      </div>
    </div>
  );
}

export default App;
