import React from 'react';

const PageList = ({todos, changePage}) => {

    return (
        <div>
            {todos.prev && <button onClick={()=>changePage(todos.start-1)}>PREV</button>}
            {todos.pageList.map(p=> <span onClick={()=>changePage(p)}> [{p}] </span>)}
            {todos.next && <button onClick={()=>changePage(todos.end+1)}>NEXT</button>}
        </div>
    );
};

export default PageList;