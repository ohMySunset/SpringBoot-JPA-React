import React, {useState} from 'react';

const InputEx = ({str}) => {

    {/*
    // useState 를 사용
    // input 의 onChange 라는 이벤트를 사용,
    // 이벤트에 등록하는 함수에서는 이벤트 객체 e 를 파라미터로 받아와서 사용 할 수 있는데
    // 이 객체의 e.target 은 이벤트가 발생한 DOM 인 input DOM 을 가르킨다.
    // 이 DOM 의 value 값, 즉 e.target.value 를 조회 -> 입력 값 확인
    */}

    const [title, setTitle] = useState(str)

    const changeTitle = (e) => {
        e.stopPropagation()
        setTitle(e.target.value)
    }

    const clearTitle = (e) => {
        setTitle("")
    }

    return (
        <div>
            {/* 자바스크립트는 기본적으로 이벤트 파라미터를 전송(파라미터의 종류에 상관없이 다 전송가능)*/}
           <input type={'text'} name={'title'} value={title} onChange={changeTitle}/>
            <button onClick={clearTitle}>CLEAR</button>
        </div>
    );
};

export default InputEx;