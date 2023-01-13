import React, { useEffect, useState } from 'react'
import { convertToBase } from '../utilities';

interface Props {
  allRegisterNames: string[];
  allRegisters: number[];
  prevAllRegisters: number[];
}

const ProgramData = ({allRegisterNames, allRegisters, prevAllRegisters}: Props) => {
  const [baseSelected, setBaseSelected] = useState<number>(16);
  // const [registerHasChanged, setRegisterHasChanged] = useState<boolean[]>([]);
  useEffect(() => {
    // console.log("HI!");
    // console.log(allRegisters, prevAllRegisters);
    // console.log("#########################");
  })
  
  return (
    <div className='data'>
      <ul className='registers'>
        <li className='header'>Registers</li>
        <span className="twocolumns">
          {allRegisters.map((item, ix) =>
          <li key={allRegisterNames[ix]} className={allRegisters[ix] !== prevAllRegisters[ix] ? "changed" : ""}>
            <span className='reg-name'>
              {allRegisterNames[ix]}
            </span>
            <span className='reg-val'>
              {convertToBase(item, baseSelected)} 
            </span>
          </li>
        )}
        </span>
      </ul>
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

export default ProgramData