import React, { useEffect, useRef } from 'react'
import './styles.css'

const Display = () => {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const width: number = 64;
    const height: number = 32;
    const pixelSize: number = 10;
    const displayMatrix = useRef<number[][]>([]);

    const updateDisplayMatrix = () => {
        for (let i = 0; i < displayMatrix.current.length; ++i) {
            for (let j = 0; j < displayMatrix.current[i].length; ++j) {
                const ctx = canvasRef.current?.getContext("2d");
                if (displayMatrix.current[i][j] === 0) {
                    ctx!.fillStyle = "black";
                } else {
                    ctx!.fillStyle = "white";
                }
                ctx!.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
            }
        }
    }
    
    const updateDisplay = (displayMatString: String) => {
        const displayMatList = displayMatString.split('\n');
        var arr: number[][] = new Array(height);
        for (let i = 0; i < arr.length; ++i) {
            arr[i] = new Array(width).fill(0);
        }
        for (let i = 0; i < arr.length; ++i) {
            const row: string[] = displayMatList[i].split(' ')
            for (let j = 0; j < width; ++j) {
                arr[i][j] = parseInt(row[j]);
            }
        }
        displayMatrix.current = arr;
        updateDisplayMatrix();
    }
    
    useEffect(() => {var arr: number[][] = new Array(height);
        for (let i = 0; i < arr.length; ++i) {
            arr[i] = new Array(width).fill(0);
        }
        displayMatrix.current = arr;
        updateDisplayMatrix();
    }, [])

    
  return (
    <div>
        <canvas id="canvas" width ={width * pixelSize} height={height * pixelSize} ref={canvasRef}></canvas>

    </div>
  )
}

export default Display