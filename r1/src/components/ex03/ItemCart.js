import React from 'react';

const ItemCart = ({userItems}) => {
    return (
        <div style={{width:"30vw"}}>
            <h2>ItemCart</h2>
            <ul>
                {userItems.map((item, idx)=> <li key={idx}>{item.name} {item.price}</li>
                )}
            </ul>
        </div>
    );
};

export default ItemCart;