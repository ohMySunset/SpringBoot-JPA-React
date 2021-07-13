import React, {useEffect, useState} from "react";
import cartService from "./cartService";

const StoreCart = () => {

    const [flag, setFlag] = useState(false);

    // const changeFlag = () => {
    //     setFlag(!flag)
    // }
    // setFn 상태를 변환시켜주는 함수
    //cartService.setFn(changeFlag)

    cartService.setFn(()=>{setFlag(!flag)})

    const storeItems = cartService.getAll()

    const list = storeItems.map((store, idx)=> <li key={idx}>{store.name}
    <button onClick={()=>{cartService.remove(store)}}>DELETE</button></li>)

    return (
        <div>
            <ul>
                {list}
            </ul>
        </div>
    );
};

export default StoreCart;