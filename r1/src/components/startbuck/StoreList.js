import React, {useState} from 'react';
import storeService from "./storeService";
import cartService from "./cartService";

const StoreList = () => {

    // stores의 초기값 지정
    const [stores, setStores] = useState(storeService.getStores())
    // cart arr 값 불러오기
    //const cartService = cartService.getAll()

    const list = stores.map((store, idx)=>
        <li key={idx} onClick={()=>cartService.append(store)}>{store.name}</li>)
    // {} : 내가 지정한 값으로 직접 리턴, 없으면 그대로 리턴

    // 필터링된 목록을 다시 반환하기 때문에 반환된 값을 다시 상태로 저장해주어야함.
    const searchByCnt = (str) => {
        const filteredStores = storeService.getByCat(str)
        setStores(filteredStores)
    }

    return (
        <div>
            <div>
                <button onClick={()=>{searchByCnt()}}>ALL</button>
                <button onClick={()=>{searchByCnt('일반')}}>일반 매장</button>
                <button onClick={()=>{searchByCnt('리저브')}}>리저브 R</button>
            </div>
            <ul>
                {list}
            </ul>
        </div>
    );
};

export default StoreList;