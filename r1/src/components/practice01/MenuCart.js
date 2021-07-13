import React from 'react';

const MenuCart = ({menus, oper, deleteItem, deleteAll}) => {

    return (
        <div style={{width:"30vw"}}>
            <h2>Cart List</h2>
            <ul>
                {menus.map((item, idx) =>
                    <li key={idx}>{item.name}    ₩{item.price}
                        <div>
                            <button onClick={()=>{oper("minus", item)}}>-</button>
                             수량 : {item.cnt}
                            <button onClick={()=>{oper("plus", item)}}>+</button>
                            <button onClick={()=>{deleteItem(item)}}>취소</button>
                        </div>
                   </li>)}
            </ul>
            <button onClick={()=> deleteAll()}>전체 삭제</button>
        </div>
    );
};

export default MenuCart;