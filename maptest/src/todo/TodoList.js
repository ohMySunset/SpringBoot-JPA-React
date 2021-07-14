import React, {useEffect, useState} from 'react';
import todoService from "./todoService";
import PageList from "./PageList";

const initState = {

    dtoList: [],
    pageList:[],
    totalPage: 0,
    page: 1,
    size: 0,
    start: 0,
    end: 0,
    prev: false,
    next: false
}

const TodoList = () => {

    const [todos, setTodos] = useState(initState)
    const [loading, setLoading] = useState(false)

    useEffect(()=>{
        setLoading(true)
        todoService.getList(todos.page).then(data=> {
            console.log(data)
            setTodos(data)
            setLoading(false)
        })
    },[todos.page])


    const changePage = (num) => {
        setTodos({...todos, page:num})
    }

    const list = todos.dtoList.map((todo, idx)=> <li key={idx}>{todo.title}</li>)
    console.log(list)

    return (
        <div>
            {loading? <h1>로딩중....🏃🏻‍️</h1> :
                <>
                <ul style={{listStyle:"none"}}>
                    {list}
                </ul>
                <PageList todos={todos} changePage={changePage}></PageList>
                </>
            }
        </div>
    );
};

export default TodoList;