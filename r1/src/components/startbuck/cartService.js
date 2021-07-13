
const cartService = () => {

    let arr = []

    let fn;

    const setFn = (fn1) => {
        fn = fn1
    }

    // 가게 추가
    const append  = (store) => {
        arr.push(store)
        fn()
    }

    // 가게 정보 가져오기
    const getAll = () => {
        return arr
    }

    // 가게 삭제
    const remove = (store) => {
        arr = arr.filter(s => s.name !== store.name)
        fn()
    }

    return {append, getAll, remove, setFn}

}
export default cartService()