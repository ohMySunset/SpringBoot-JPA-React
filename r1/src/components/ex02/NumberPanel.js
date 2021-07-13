import React, {useState} from 'react';
import DisplayPanel from "./DisplayPanel";
import ControlPanel from "./ControlPanel";
import makeNum from "../ex01/numService";
import OddEven from "../ex01/OddEven";

const NumberPanel = () => {

    const [num, setNum] = useState({value : makeNum()})

    const change = () => {
        console.log("change.....")
        const newValue = makeNum()
        num.value = newValue
        setNum({value: newValue})

        //setNum(makeNum())
    }

    return (
        <div>
            <h1>Number Panel</h1>
            <DisplayPanel num = {num.value}></DisplayPanel>
            <OddEven num={num.value}></OddEven>
            <ControlPanel change = {change}></ControlPanel>
        </div>
    );
};

export default NumberPanel;