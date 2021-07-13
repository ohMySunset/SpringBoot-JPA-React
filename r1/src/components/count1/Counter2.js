import React, {useState} from 'react';
import numService from "./numService";

const Counter2 = () => {
    //상태
    const [changed, setChanged] = useState(false)
    //함수
    const callChanged = () => {
        setChanged(!changed)
    }

    numService.setFn(callChanged)

    return (
        <div>
            <h1>Counter2</h1>
            <Count2Display></Count2Display>
            <Count2Buttons ></Count2Buttons>
        </div>
    );
};

const Count2Display = () => {

    return (
        <div>
            <h2>{numService.getCount()}</h2>
        </div>
    )
}

const Count2Buttons = () => {

    return(
        <div>
            <button onClick={() => {numService.changeValue(1) } }>++</button>
            <button onClick={() => {numService.resetCount() } }>reset</button>
            <button onClick={() => {numService.changeValue(-1)  } }>--</button>
        </div>
    )
}

export default Counter2;