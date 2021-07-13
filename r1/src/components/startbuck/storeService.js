
// 로직을 처리하는 코드

const storeService = () => {

    const stores = [

        {name : "스타벅스 주얼리시티점", lat:37.57137847049122, lng:126.99828740000382, cat:"일반"},
        {name : "스타벅스 서울극장점", lat:37.57001595609778, lng:126.99167392893233, cat:"일반"},
        {name : "스타벅스 종로3가점", lat:37.57081262602884, lng:126.99025398467536, cat:"일반"},
        {name : "스타벅스 더종로 R점", lat:37.571135816822846, lng:126.98370015790415, cat:"리저브"},
        {name: "스타벅스 광화문 R점", lat:37.57150997614141, lng:126.97644746487727, cat:"리저브"}
    ]

    // 데이터 읽기 전용
    const getStores = () => {
        return stores
    }

    // 카테고리 기준으로 데이터 검색
    const getByCat = (str) => {
        if(!str){
            // 값이 없으면 전체 반환
            return stores
        }
        return stores.filter(store=> store.cat === str)
    }

    return {getStores:getStores, getByCat:getByCat}
}

export default storeService()