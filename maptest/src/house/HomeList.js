import React from 'react';
import homeService from "./homeService";
import cartService from "./cartService";
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles({
    root: {
        minWidth: 275,
    },
    bullet: {
        display: 'inline-block',
        margin: '0 2px',
        transform: 'scale(0.8)',
    },
    title: {
        fontSize: 14,
    },
    pos: {
        marginBottom: 12,
    },
});

const HomeList = () => {

    const classes = useStyles();
    const bull = <span className={classes.bullet}>•</span>;

    const list = homeService.getAll().map((home, idx) =>
        <li key={idx} onClick={()=>{cartService.append(home)}}>{home.name}</li>
    )

    const home = homeService.getAll().map((home, idx) =>
        <Card className={classes.root} key={idx}>
            <CardContent onClick={()=>{cartService.append(home)}}>
                <CardMedia
                    component="img"
                    alt="Contemplative Reptile"
                    width="10px"
                    image="/static/image/bear2.png"
                    title="Contemplative Reptile"
                />
                <Typography variant="h5" component="h2">
                    {home.name}
                </Typography>
                <Typography variant="body2" component="p">
                    위도 : {home.lat}
                    <br />
                    경도 : {home.lon}
                </Typography>
            </CardContent>
            <CardActions>
                <Button size="small">더보기</Button>
            </CardActions>
        </Card>
    )


    return (
        <div>
            <h1>HomeList</h1>
            <ul style={{listStyle:'none'}}>
                {home}
            </ul>
        </div>
    );
};

export default HomeList;