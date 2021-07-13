import React, {useState} from 'react';

const Counter1 = () => {

    const [num, setNum] = useState(0);

    // 숫자를 카운트 하는 함수
    const changeNum = (value) => {
        setNum(num + value)
    }

    return (
        <div>
            <h1>Counter1</h1>
             <CountDisplay num={num}></CountDisplay>
             <CountButton changeNum={changeNum}></CountButton>
        </div>
    );
};

// 숫자를 표시해줄 컴포넌트
const CountDisplay = ({num}) => {
    return (
        <div>
        <h2>{num}</h2>
        </div>
    )
}

// 버튼을 표시해줄 컴포넌트
const CountButton = ({changeNum}) => {
    return (
        <div>
            <button onClick={()=>{changeNum(+1)}}>++</button>
            <button onClick={()=>{changeNum(-1)}}>--</button>
        </div>
    )
}

export default Counter1;