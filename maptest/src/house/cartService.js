import mapService from "./mapService";

const cartService = () =>{

    let carts = [];
    let fn;
    let fnMap;
    let fnRemoveMarker;

    const setFn = (func)=>{
        fn = func
    }

    const setMap = (func) => {
        fnMap = func;
    };

    const setRemove = (func)=>{
        fnRemoveMarker = func
    }

    const append = (cart)=>{
        carts.push(cart)
        fnMap(cart);
        fn()
    }

    const getAll = ()=>carts

    const remove = (cart)=>{
        carts = carts.filter(ele=> ele.name !== cart.name)
        fnRemoveMarker(cart)
        fn()
    }

    return {setFn, append, getAll, remove, setMap, setRemove}
}
export default cartService()