import React from 'react';

const ControlPanel = ({change}) => {
    return (
        <div>
            <h1>ControlPanel</h1>
            <div>
                <button onClick={()=> change()}>CLICK</button>
            </div>
        </div>
    );
};



export default ControlPanel;