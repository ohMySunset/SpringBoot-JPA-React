import React, {useEffect, useState} from 'react';
import todoService from "./todoService";
import TodoList from "./TodoList";
import {AppBar} from "@material-ui/core";
import TodoAppBar from "./TodoAppBar";

const TodoBoard = () => {

    const [now, setNow] = useState('')
    const [flag, setFlag] = useState(false)

    console.log("TodoBoard....")

   //  // 그냥 요청할 시 응답이 도착하기 전에 출력을 해주기 때문에 promise가 반환됨
   // // console.log(todoService.getTime())
   //
   //  // async는 함수 안에서 응답을 받을 때 까지 대기가 가능함.
   //  // Hooks 중에서는 async 사용이 불가능한 경우가 있음 이 경우에는 then을 사용한다.
   //  const displayTime = async () => {
   //      const timeNow = await todoService.getTime()
   //      console.log(timeNow)
   //      setNow(timeNow.data)
   //  }
    // displayTime()


    // useEffect는 async 사용 불가
    useEffect(()=>{
        todoService.getTime().then((res)=>{
            setNow(res.data)
        })
    }, [flag]) // 빈 배열일 경우 컴포넌트가 로딩될 때 1번만 동작

    const getTime = () => {
        setFlag(!flag)
    }

    return (
        <div>
            <h1>Simple Todo {now}</h1>
            <button onClick={getTime}>CLICK</button>
            <TodoList></TodoList>
        </div>
    )
};

export default TodoBoard;