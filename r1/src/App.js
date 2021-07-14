import './App.css';
import MovieList from "./components/movies/MovieList";
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import TodoBoard from "./components/todo1/TodoBoard";

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

function App() {

    const classes = useStyles();

    return(
        <div className="App">
            <Grid className={classes.root}>
                <Grid container spacing={3}>
                    <Grid item xs={12}>
                        <h1 className={classes.paper}>현재 상영작</h1>
                    </Grid>
                        <MovieList></MovieList>
                </Grid>
            </Grid>
        </div>
    );
};

export default App;