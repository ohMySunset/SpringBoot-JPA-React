import React from 'react';
import service from './itemService.js';



const ItemList = ({addItemToCart}) => {

    // 출력용 컴포넌트
    const ItemDetail = ({item}) => {
        return (
            <div>
                <h2>{item.name}</h2>
                <h2>{item.price}</h2>
                <div><button onClick={()=>{addCart(item)}}>CLICK</button></div>
            </div>
        )
    }

    console.log(service.getItems())

    const list = service.getItems().map((item, idx) => <ItemDetail key={idx} item={item}></ItemDetail>)

    const addCart = (item) => {
        console.log("add cart....", item)
        addItemToCart(item)
    }

    return (
        <div style={{width:"70vw"}}>
            <h2>Item List</h2>
            <ul>
                {list}
            </ul>
        </div>
    );
};

export default ItemList;