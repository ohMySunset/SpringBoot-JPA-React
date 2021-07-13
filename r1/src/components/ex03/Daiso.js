import React, {useState} from 'react';
import ItemList from "./ItemList";
import ItemCart from "./ItemCart";

const style = {
    display: 'flex',
    flexDirection : 'row'
}
const Daiso = () => {

    const [userItems, setUserItems] = useState([])

    // 새로운 아이템을 추가하는 함수
    const addItemToCart = (item) => {

        setUserItems([...userItems, item]) // ...userItems 기존 배열을 펴서 새로운 item 추가
    }

    return (
        <div>
            <h1>Welcome to Daiso</h1>
            <div style={style}>
                <ItemList addItemToCart = {addItemToCart}></ItemList>
                <ItemCart userItems = {userItems}></ItemCart>
            </div>
        </div>
    );
};

export default Daiso;