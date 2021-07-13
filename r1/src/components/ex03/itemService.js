import React from 'react';


const ItemService = () => {

    const items = [
        {name : "연필", price : "500"},
        {name : "샤프", price : "2000"},
        {name : "노트", price : "1000"},
        {name : "지우개", price : "500"},
        {name : "샤프심", price : "800"},
        {name : "쫀드기", price : "600"}
    ]
    const getItems = () => {
        return items
    }

    return {getItems:getItems}
};

export default ItemService();
               // return {getItems:getItems}