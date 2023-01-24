import React, { useEffect, useState } from 'react'
import { PostAction } from '../models';
import { convertToBin, convertToDec, convertToHex } from '../utilities';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faPause, faStop, faRotateRight, faPlay, faCircle} from '@fortawesome/free-solid-svg-icons'

interface Props {
  programSource: number[];
  lineNumber: number;
  handlePostMethods: (action: PostAction, ix?: number | null) => void,
  breakPoints: Set<number>,
  autoPlayPaused: boolean,
  setAutoPlayPaused: React.Dispatch<React.SetStateAction<boolean>>
}

const ProgramCode = ({programSource, lineNumber, handlePostMethods, breakPoints, autoPlayPaused, setAutoPlayPaused}: Props) => {
  const [autoPlay, setAutoPlay] = useState<boolean>(false);
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
              <span className={'index' + (breakPoints.has(ix) ? ' breakpoint' : '')} onClick={() => handlePostMethods("ToggleBreakPoint", ix)}>
                <FontAwesomeIcon icon={faCircle}/>
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
      <div className='controls'>
        {autoPlay ? (
          <>
            {autoPlayPaused ? (
              <button onClick={
                (e) => {
                  e.preventDefault();
                  setAutoPlayPaused(false);
                  handlePostMethods("Resume");}}>
                <FontAwesomeIcon icon={faPlay} />
              </button>
            ) : (
              <button onClick={
                (e) => {
                  e.preventDefault();
                  setAutoPlayPaused(true);
                  handlePostMethods("Pause");}}>
                <FontAwesomeIcon icon={faPause} />
              </button>
            )}
            <button onClick={
              (e) => {
                e.preventDefault();
                setAutoPlay(false);
                handlePostMethods("Stop");}}>
              <FontAwesomeIcon icon={faStop} />
            </button>
            <button onClick={
              (e) => {
                e.preventDefault();
                handlePostMethods("Reset");}}>
              <FontAwesomeIcon icon={faRotateRight} />
            </button>
          </>
        ) : (
          <>
          <button onClick={
            (e) => {
              e.preventDefault();
              setAutoPlay(true);
              setAutoPlayPaused(false);
              handlePostMethods("Auto");}}>
            Auto
          </button>
          <button onClick={
            (e) => {
              e.preventDefault();
              handlePostMethods("Next");}}>
            Next
          </button>
          <button onClick={
              (e) => {
                e.preventDefault();
                handlePostMethods("Reset");}}>
              <FontAwesomeIcon icon={faRotateRight} />
          </button>
          </>
        )}
      </div>
    </div>
  )
}

export default ProgramCode