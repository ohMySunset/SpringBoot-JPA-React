import React from 'react';
import makeNum from "./numService";

/* 컴포넌트 호출 시 마다 함수를 생성하지 않도록 바깥으로 빼주었음. */
const getContent = (num) => {

    if(num % 2 === 0){
        return <h1>EVEN</h1>
    } else{
        return <h1>OOD</h1>
    }
}

const OddEven = ({num}) => {

    makeNum()

    return (
        <div>
            {getContent(num)}
        </div>
    );
};

export default OddEven;