import React, {useEffect, useState} from 'react';
import cartService from "./cartService";
import homeService from "./homeService";

const kakao = window.kakao

const HomeMap = () => {

    const [markers, setMarkers] = useState([])

    useEffect(()=>{
        const mapContainer = document.getElementById('map'), // 지도를 표시할 div
            mapOption = {
                center: new kakao.maps.LatLng(37.570390442621125, 126.98529262043198), // 지도의 중심좌표
                level: 3 // 지도의 확대 레벨
            };

        // 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
        const map = new kakao.maps.Map(mapContainer, mapOption);

        const panTo = (home) => {
            // 이동할 위도 경도 위치를 생성합니다
            const moveLatLon = new kakao.maps.LatLng(home.lat, home.lon);

            // 지도 중심을 부드럽게 이동시킵니다
            // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
            map.panTo(moveLatLon);

            // 마커를 생성합니다
            const marker = new kakao.maps.Marker({
                position: moveLatLon
            });

            // 마커가 지도 위에 표시되도록 설정합니다
            marker.setMap(map);

            // 생성된 마커를 배열에 추가합니다
            markers.push(marker);

            // 마커를 클릭했을 때 마커 위에 표시할 인포윈도우를 생성합니다
            var iwContent = '<div style="padding:5px;">'+home.name+'의 집 입니다.</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
                iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

            // 인포윈도우를 생성합니다
            var infowindow = new kakao.maps.InfoWindow({
                content : iwContent,
                removable : iwRemoveable
            });

            // 마커에 클릭이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'click', function() {
                // 마커 위에 인포윈도우를 표시합니다
                infowindow.open(map, marker);
            });

            // 마커에 마우스아웃 이벤트를 등록합니다
            kakao.maps.event.addListener(marker, 'mouseout', function() {
                // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
                infowindow.close();
            });

        }

        // 배열에 추가된 마커들을 지도에 표시하거나 삭제하는 함수입니다
        const setMarkers = (marker) => {
            const idx = markers.indexOf(marker.name)
                markers.splice(markers[idx], 1)
                console.log(markers)
                // markers
                //marker.setMap(map)
        }

          cartService.setMap(panTo)
          cartService.setRemove(setMarkers)
    },[]);


    return (
        <div>
            <div id={"map"} style={{height:"50vh",width:"100%"}}></div>
        </div>
    );
};

export default HomeMap;