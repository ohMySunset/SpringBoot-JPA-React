const homeService = () =>{

    let homes = [
        {name: 'wonjin', lat: 37.595321346965974, lon: 127.07610891432056},
        {name: 'hyenil', lat: 37.57496330775122, lon: 127.01700954255382},
        {name: 'jaeyeon', lat: 37.566021782140474, lon: 126.74729722159994},
        {name: 'sunkyung', lat: 37.634240622820926, lon: 127.04006611347305},
        {name: 'chanwoo', lat: 37.55911633259536, lon: 127.08316626950473},

    ];

    let fn;

    const setFn = (func)=>{
        fn = func
    }

    const getAll = ()=>homes

    const getOne = (name)=>{
        return homes.filter(home=>home.name === name)
    }







    return {setFn,getAll,getOne}
}

export default homeService()