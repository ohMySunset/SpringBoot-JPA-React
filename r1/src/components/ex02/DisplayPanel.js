import React from 'react';

const style = {
    backgroundColor : "lightgrey",
    height : "10vh"
}

const DisplayPanel = ({num}) => {
    return (
        <div style={style}>
            <h1>DisplayPanel {num}</h1>
        </div>
    );
};

export default DisplayPanel;