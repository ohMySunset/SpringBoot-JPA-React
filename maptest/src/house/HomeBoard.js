import React from 'react';
import HomeList from "./HomeList";
import HomeCart from "./HomeCart";
import HomeMap from "./HomeMap";
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

const HomeBoard = () => {

    const classes = useStyles();

    return (
        <div className={classes.root}>
        <Grid container spacing={3}>
            <Grid item xs={12}>
                <h1 className={classes.paper}>HomeBoard</h1>
            </Grid>
            <Grid item xs={12}>
                <HomeMap className={classes.paper}>xs=3</HomeMap>
            </Grid>
            <Grid item xs={4}>
                <HomeList className={classes.paper}>xs=6</HomeList>
            </Grid>
            <Grid item xs={6}>
                <HomeCart className={classes.paper}>xs=6</HomeCart>
            </Grid>
            <Grid item xs={12}>
                <Paper className={classes.paper}>-끝-</Paper>
            </Grid>
        </Grid>
    </div>
    );
};

export default HomeBoard;