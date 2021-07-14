import axios from "axios";

const todoService = () => {

    const baseURL = "http://localhost:8080/todo"

    const getList = async (page) => {
        const result = await axios.get(baseURL +"/pages?page=" + page)
        const data = result.data
        return data
    }

    return {getList:getList}

}

export default todoService()