import React, {useState} from 'react';

{/*랜더링 시 마다 함수를 매번 호출하기 때문에
 데이터의 원형이 되는 객체는 바깥에 위치하도록 만들기*/}
const initState = {
    title : '',
    content : ''
}

const TodoInput = () => {

    const [todo, setTodo] = useState({...initState})

    const change = (e) => {
        //console.log(e.target.name)
        //console.log(e.target.value)

        {/*
        todo.title  : 정적 코딩
        todo['title'] : 동적 코딩
        */}

        todo[e.target.name] = e.target.value
        setTodo({...todo}) // Deep Copy 필요
    }

    const resetTodo = () => {
        // todo에 입력을 시작하면 state값이 변한다.
        // 만약 그대로 initState를 가져다 쓰면
        // 딥카피 해주기전에 남은 데이터가 들어오기 때문에 공백이 반환되지 않음.
        // 한 두글자 씩 남게된다.
        setTodo(initState)
    }

    return (
        <div>
            <div>
                <input type={'text'} name={'title'} value={todo.title} onChange={change}/>
            </div>
            <div>
                <input type={'text'} name={'content'} value={todo.content} onChange={change}/>
            </div>
            <div>
                <button onClick={resetTodo}>RESET</button>
            </div>
        </div>
    );
};

export default TodoInput;