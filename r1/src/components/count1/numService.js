const numService =() => {

    let count = 0;
    let fn;

    const setFn = (fn1) =>{
        fn = fn1
    }

    const changeValue = (amount) => {
        count += amount
        fn()
        return count
    }

    const getCount = () => {
        return count
    }

    const resetCount = () => {
        count = 0
        fn()
        return count
    }

    return {changeValue,resetCount,getCount,setFn}
}

export default numService()