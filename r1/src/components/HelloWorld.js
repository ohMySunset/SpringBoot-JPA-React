import React, {useState} from 'react';
import HellButton from "./HellButton";

const HelloWorld = () => {

    const [content, setContent] = useState("Hello World!!");

    const changeContent = ()=> {
        alert("Content");
        setContent("Hell World");
    }

    return (
        <div>
            <h1>{content}</h1>
            <HellButton action = {changeContent}></HellButton>
        </div>
    );
};

export default HelloWorld;