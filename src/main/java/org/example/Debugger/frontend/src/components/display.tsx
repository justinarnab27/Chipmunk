import { useEffect, useRef } from 'react'
import './styles.css'

interface Props {
    displayMatrix: number[][];
}

const Display = ({displayMatrix}: Props) => {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const width: number = 64;
    const height: number = 32;
    const pixelSize: number = 10;

    const updateDisplayMatrix = (displayMatrix: number[][]) => {
        for (let i = 0; i < displayMatrix.length; ++i) {
            for (let j = 0; j < displayMatrix[i].length; ++j) {
                const ctx = canvasRef.current?.getContext("2d");
                if (displayMatrix[i][j] === 0) {
                    ctx!.fillStyle = "black";
                } else {
                    ctx!.fillStyle = "white";
                }
                ctx!.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
            }
        }
    }

    useEffect(() => updateDisplayMatrix(displayMatrix), [displayMatrix]);
    
  return (
    <div>
        <canvas id="canvas" width ={width * pixelSize} height={height * pixelSize} ref={canvasRef}></canvas>
    </div>
  )
}

export default Display