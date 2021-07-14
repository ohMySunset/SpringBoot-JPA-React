import React, {useState} from 'react';
import cartService from "./cartService";
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1,
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    },
}));
const HomeCart = () => {

    const classes = useStyles();

    const [flag, setFlag] = useState(false)

    const list = cartService.getAll().map((cart, idx)=>
        <li key={idx} onClick={()=>{cartService.remove(cart)}}>{cart.name}</li>
    )

    const setFunction = () => setFlag(!flag);

    cartService.setFn(setFunction);

    return (
        <div>
            <h1>HomeCart</h1>
            <ul Paper className={classes.paper} style={{listStyle:'none'}}>
                {list}
            </ul>
        </div>
    );
};

export default HomeCart;