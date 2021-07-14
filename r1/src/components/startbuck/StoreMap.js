import React, {useEffect} from 'react';
import storeService from "./storeService";

const style = {
    width : "100%",
    height : "30vh",
    backgroundColor : ""
}

const kakao = window.kakao

const StoreMap = () => {

    useEffect(()=>{
        console.log("StoreMap userEffect")
        const mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(37.570566870405585, 126.98524520083875), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };
        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);
        showMarkers(map)
    }, []) // 빈 배열은 컴포넌트가 로딩됐을 때 한번만 실행함.

    const showMarkers = (map) =>{
        const stores = storeService.getStores();

        // 마커를 표시할 위치와 title 객체 배열입니다
        const positions = stores.map(store=>
            ({title:store.name, latlng: new kakao.maps.LatLng(store.lat, store.lng)
            }))

        // 마커 이미지의 이미지 주소입니다
        var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

        for (var i = 0; i < positions.length; i ++) {

            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            // 마커를 생성합니다
            const marker = new kakao.maps.Marker({
                map: map, // 마커를 표시할 지도
                position: positions[i].latlng, // 마커를 표시할 위치
                title : positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
            });

            // 마커에 필요한 데이터 추가해서 붙여 쓸 수 있음.
            marker.origin = stores[i]

            // 마커에 클릭이벤트를 등록합니다
            // 클로저 형태로 설계되어 있기 때문에 for루프 돌고 마지막 데이터만 marker로 나오게됨
            // -> 가장 쉬운 해결방법은 참조하고 있는 함수를 const로 바꾸어 주는 것.
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                console.log(marker)
                console.log(marker.origin)
            });
        }

    }

    return (
        <div>
            <h1>StoreMap</h1>
           <div id={'map'} style={style}></div>
        </div>
    );
};

export default StoreMap;