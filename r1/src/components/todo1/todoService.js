// ajax통신을 전담하는 로직 객체로 쓰일 모듈

// 요청을 수행할 URL 경로
import axios from "axios";

const baseURL = "http://localhost:8080/todo"

const todoService = () => {

    const getTime = async () => {
        console.log("getTime.....")
        // await : 요청에 대한 응답이 올 때지 기다림
        // 무조건 async와 같이 쓰임
        const result = await axios.get(baseURL+"/now")
        return result
    }

    const getList = async (page) => {
        const result = await axios.get(baseURL+"/pages?page="+page)
        const data = await result.data
        return data
    }

    return {getTime:getTime, getList:getList}
}

export default todoService()