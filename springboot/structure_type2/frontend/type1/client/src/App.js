import logo from './logo.svg';
import './App.css';
import { useEffect } from 'react'

function App() {
  useEffect( ()=>{
      fetch("http://127.0.0.1:8080/api/echo")
        .then((res) => res.json())  // 응답을 JSON 형식으로 변환
        .then((data) => {
          // 데이터를 처리하는 로직
          console.log("통신결과", data);
          alert(data.message);
        })
        .catch((error) => {
          // 오류 처리
          console.error("Error fetching data: ", error);
        });
    }, []);
  return (
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
