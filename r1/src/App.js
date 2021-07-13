import './App.css';
import Counter1 from "./components/count1/Counter1";
import Cafe from "./components/practice01/Cafe";
import Counter2 from "./components/count1/Counter2";
import StoreBoard from "./components/startbuck/StoreBoard";


function App() {
  return (
     // 컴포넌트는 하나의 div 안에 위치
    <div className="App">
        <StoreBoard/>
    </div>
  );
}

export default App;
