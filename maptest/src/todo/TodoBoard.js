import React, {useState} from 'react';
import TodoList from "./TodoList";

const TodoBoard = () => {

    return (
        <div>
            <h1>Todo리스트📝</h1>
            <TodoList></TodoList>
        </div>
    );
};

export default TodoBoard;