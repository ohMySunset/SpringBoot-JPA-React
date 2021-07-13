import React, {useState} from 'react';
import MenuList from "./MenuList";
import MenuCart from "./MenuCart";

const style={
    display : "flex",
    flexDirection : "row"
}

const Cafe = () => {

    const [menus, setMenus] = useState([])
    console.log(menus)

    const addItemToCart = (item) => {
        setMenus([...menus, item])
    }

    const operation = (type, menu) => {
            if(menu.cnt < 1){
                menus.splice(menu, 1)
                setMenus([...menus])
            } else {
                if (menus.indexOf(menu.name)==0) {
                    type === "plus" ? menu.cnt++ : menu.cnt--
                    const menuArr = [...menus]
                    setMenus([...menuArr])
                } else {
                    type === "plus" ? menu.cnt++ : menu.cnt--
                    const menuArr = [...menus]
                    setMenus([...menuArr, menu])
                }
            }
    }

    const deleteItem = (item) => {
        // item.name의 값이 일치하지 않는 데이터만 추려서 다시 배열로 만듬
        setMenus(menus.filter(menus=> menus.name != item.name))
    }

    const deleteAll = () => {
        menus.splice(0, menus.length)
        setMenus([])
    }

    return (
        <div >
            <h1>Bit Cafe</h1>
            <div style={style}>
                <MenuList addItemToCart={addItemToCart}></MenuList>
                <MenuCart menus={menus} oper={operation} deleteItem={deleteItem} deleteAll={deleteAll}></MenuCart>
            </div>

        </div>
    );
};

export default Cafe;