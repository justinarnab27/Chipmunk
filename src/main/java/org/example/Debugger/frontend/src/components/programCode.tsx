import React, { useEffect, useState } from 'react'
import { convertToBin, convertToDec, convertToHex } from '../utilities';

interface Props {
  programSource: number[];
  lineNumber: number;
}

const ProgramCode = ({programSource, lineNumber}: Props) => {
  const [baseSelected, setBaseSelected] = useState<number>(16);
  const [currentInstruction, setCurrentInstruction] = useState<number>(0);
  const [instructionArray, setInstructionArray] = useState<string[]>([]);
  const changeInstructionsToBase = (programSource: number[], base: number) => {
    let convert;
    switch(base) {
      case 2:
        convert = convertToBin;
        break;
      case 10:
        convert = convertToDec;
        break;
      default:
        convert = convertToHex;
        break;
    } 
    const arr: string[] = [];
    for(let i = 0; i < programSource.length; i+=2) {
      arr[Math.floor(i/2)] = convert(programSource[i]) + " " + convert(programSource[i+1]);
    }
    setInstructionArray(arr);
  }
  useEffect(() => {
    changeInstructionsToBase(programSource, baseSelected);
  }, [programSource, baseSelected])
useEffect(() =>
    setCurrentInstruction(lineNumber)
    , [lineNumber]
  )

  return (
    <div className='code-container'>
      <div className='code'>
        <ul>
          <li className='header' >Instructions</li>
          {instructionArray.map(
            (item, ix) => 
            <li className={currentInstruction === ix ? 'selected-line' : ''} key={ix.toString() + item.toString()}>
              <span className='index'>
                {ix}
              </span>
              <span className='text'>
                {item.toString()}
              </span>
            </li>)}
        </ul>
      </div>
      <form className='buttons'>
            <input type="radio" className='dec'  checked={baseSelected === 2} onChange={()=>{setBaseSelected(2)}}/>
            <label>Binary</label>
            <input type="radio" className='bin' checked={baseSelected === 10} onChange={()=>{setBaseSelected(10)}}/>
            <label>Decimal</label> 
            <input type="radio" className='hex' checked={baseSelected === 16} onChange={()=>{setBaseSelected(16)}}/>
            <label>Hex</label>
      </form>
    </div>
  )
}

export default ProgramCode