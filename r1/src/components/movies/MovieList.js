import React, {useEffect, useState} from 'react';
import axios from "axios";
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardActionArea from '@material-ui/core/CardActionArea';
import CardActions from '@material-ui/core/CardActions';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Button from '@material-ui/core/Button';
import Typography from '@material-ui/core/Typography';
import { withStyles } from '@material-ui/core/styles';
import Dialog from '@material-ui/core/Dialog';
import MuiDialogTitle from '@material-ui/core/DialogTitle';
import MuiDialogContent from '@material-ui/core/DialogContent';
import MuiDialogActions from '@material-ui/core/DialogActions';
import IconButton from '@material-ui/core/IconButton';
import CloseIcon from '@material-ui/icons/Close';
import Grid from '@material-ui/core/Grid';

const useStyles = makeStyles((theme) => ({
    root: {
        maxWidth: 345,
        maxHeight: 400
    },
    paper: {
        padding: theme.spacing(2),
        textAlign: 'center',
        color: theme.palette.text.secondary,
    }
}));

const styles = (theme) => ({
    root: {
        margin: 0,
        padding: theme.spacing(2),
    },
    closeButton: {
        position: 'absolute',
        right: theme.spacing(1),
        top: theme.spacing(1),
        color: theme.palette.grey[500],
    },
});

const DialogTitle = withStyles(styles)((props) => {
    const { children, classes, onClose, ...other } = props;
    return (
        <MuiDialogTitle disableTypography className={classes.root} {...other}>
            <Typography variant="h6">{children}</Typography>
            {onClose ? (
                <IconButton aria-label="close" className={classes.closeButton} onClick={onClose}>
                    <CloseIcon />
                </IconButton>
            ) : null}
        </MuiDialogTitle>
    );
});

const DialogContent = withStyles((theme) => ({
    root: {
        padding: theme.spacing(1),
    },
}))(MuiDialogContent);

const DialogActions = withStyles((theme) => ({
    root: {
        margin: 0,
        padding: theme.spacing(1),
    },
}))(MuiDialogActions);



const initState = []

const MovieList = () => {

    const classes = useStyles();

    const [movies, setMovies] = useState(initState)

    useEffect(()=>{

        axios.get("/data/movies.json").then(res=>{
          setMovies(res.data.Movies.Items)
        })
    }, [])

    const list = movies.map(m => <Grid item xs={3}>
        <MediaCard className={classes.paper} key={m.RepresentationMovieCode} movie={m}>
        </MediaCard></Grid>)

    return (
         <>
            {list}
         </>
    )
}

export default MovieList

const MediaCard = ({movie}) => {

    const classes = useStyles();
    const [open, setOpen] = React.useState(false);

    const handleClickOpen = () => {
        setOpen(true);
    };
    const handleClose = () => {
        setOpen(false);
    };

    return (
        <Card className={classes.root}>
            <CardActionArea>
                <CardMedia style={{height:"25vh"}}
                    className={classes.media}
                    image={movie.PosterURL}
                    title="Contemplative Reptile"
                />
                <CardContent>
                    <Typography gutterBottom variant="h5" component="h2" style={{whiteSpace:"nowrap"}}>
                       {movie.MovieNameKR}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                        {movie.MovieGenreName} | {movie.ViewGradeNameUS}
                    </Typography>
                    <Typography variant="body2" color="textSecondary" component="p">
                        예매율 : {movie.ViewRate}% | ⭐️ : {movie.ViewEvaluation}
                    </Typography>
                </CardContent>
            </CardActionArea>
            <CardActions>
                <Button size="small" color="primary" onClick={handleClickOpen}>
                    더보기
                </Button>
                <Dialog onClose={handleClose} aria-labelledby="customized-dialog-title" open={open}>
                    <DialogTitle id="customized-dialog-title" onClose={handleClose}>
                        {movie.MovieNameKR}
                    </DialogTitle>
                    <DialogContent dividers>
                        <Typography gutterBottom>
                            <CardMedia style={{height:400, width:400}}
                                className={classes.media}
                                image={movie.PosterURL}
                                title="Contemplative Reptile"
                            />
                        </Typography>
                        <Typography gutterBottom>
                            관람객 평점 ⭐ <span style={{fontSize:"20px", fontWeight:"bold"}}>{movie.ViewEvaluation}  </span>
                            | 누적관객수 <span style={{fontSize:"20px", fontWeight:"bold"}}>{movie.KOFCustCnt}명</span>
                        </Typography>
                    </DialogContent>
                    <DialogActions>
                        <Button autoFocus onClick={handleClose} color="primary">
                            예매하기
                        </Button>
                    </DialogActions>
                </Dialog>
            </CardActions>
        </Card>
    );
}
