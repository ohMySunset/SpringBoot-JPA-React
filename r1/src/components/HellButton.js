import React from 'react';

const HellButton = ({action}) => {

    console.log(action);

    return (
        <div>
            <button onClick={action}>Click</button>
        </div>
    );
};

export default HellButton;