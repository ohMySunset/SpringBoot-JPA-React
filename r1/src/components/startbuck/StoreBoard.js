import React from 'react';
import StoreList from "./StoreList";
import StoreCart from "./StoreCart";
import StoreMap from "./StoreMap";

const StoreBoard = () => {

    const kakao = window.kakao
    console.log(kakao)

    return (
        <div>
            <h1>☕️️STARBUCKS☕️️</h1>
            <StoreMap></StoreMap>
            <StoreList></StoreList>
            <hr/>
            <StoreCart></StoreCart>
        </div>
    );
};

export default StoreBoard;