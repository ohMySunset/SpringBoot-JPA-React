import React from 'react';

const MenuService = () => {

    const menus = [
        {name : "아메리카노", price : 3000, cnt: 1},
        {name : "카페라떼", price : 3500, cnt: 1},
        {name : "카푸치노", price : 4000, cnt: 1},
        {name : "플랫화이트", price : 4500, cnt: 1},
        {name : "홍차", price : 3500, cnt: 1}
    ]

    const getMenus = () => {
        return menus
    }

    return {getMenus:getMenus}
};

export default MenuService();