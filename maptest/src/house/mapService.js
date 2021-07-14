const mapService = ()=>{

    let markers = []

    let fn;

    const setFn = (func)=>{
        fn = func
    }

    const addMarker =(marker)=>{
        markers.push(marker)
    }

    const removeMarker = (cart)=>{
        fn(markers[markers.indexOf(cart)]);
    }



    return {setFn, addMarker, removeMarker}
}
export default mapService()