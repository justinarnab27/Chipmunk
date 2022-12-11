import React, { useEffect, useRef, useState } from 'react'
import './styles.css'

const Display = () => {
    const canvasRef = useRef<HTMLCanvasElement>(null);
    const width: number = 64;
    const height: number = 32;
    const pixelSize: number = 10;
    const [displayMatrix, setDisplayMatrix] = useState<number[][]>([]);
    useEffect(() => {
        // const ctx = canvasRef.current?.getContext("2d");
        // ctx!.fillStyle = "green";
        // ctx!.fillRect(10, 10, 150, 100);

        var arr: number[][] = new Array(height);
        for (let i = 0; i < arr.length; ++i) {
            arr[i] = new Array(width).fill(0);
        }
        arr[10].fill(1);
        arr[11].fill(1);
        arr[12].fill(1);
        setDisplayMatrix(arr);
        // console.log(arr);
    }, [])

    useEffect(() => {
        for (let i = 0; i < displayMatrix.length; ++i) {
            for (let j = 0; j < displayMatrix[i].length; ++j) {
                const ctx = canvasRef.current?.getContext("2d");
                // if ((i%2) === (j%2)) {
                //      ctx!.fillStyle = "green";
                // }
                //  else {
                //     ctx!.fillStyle = "black";
                //  }
                if (displayMatrix[i][j] === 0) {
                    ctx!.fillStyle = "white";
                } else {
                    ctx!.fillStyle = "black";
                }
                ctx!.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                console.log(j * pixelSize, i * pixelSize, pixelSize, pixelSize)
            }
        }
    }, [displayMatrix])
    // can
    // useEffect(() => {
      
    //   }
    // }, [canvas])
    // console.log(displayMatrix);
  return (
    <div>
        <canvas id="canvas" width ={width * pixelSize} height={height * pixelSize} ref={canvasRef}></canvas>

    </div>
  )
}

export default Display