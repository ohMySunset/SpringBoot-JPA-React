import React from 'react';
import menuService from "./menuService";
import { makeStyles } from '@material-ui/core/styles';
import List from '@material-ui/core/List';
import ListItem from '@material-ui/core/ListItem';
import ListItemText from '@material-ui/core/ListItemText';
import ListItemAvatar from '@material-ui/core/ListItemAvatar';
import Avatar from '@material-ui/core/Avatar';
import Typography from '@material-ui/core/Typography';

const useStyles = makeStyles((theme) => ({
    root: {
        width:"100%",
        maxWidth: '36ch',
        backgroundColor: theme.palette.background.paper,

    },
    inline: {
        display: 'inline',
    },
}));

const MenuList = ({addItemToCart}) => {
    const classes = useStyles();

    const ItemDetail = ({item}) => {
        return (
            <div style={{width:"70vw"}}>
                <ListItemAvatar>
                    <Avatar alt="Remy Sharp"/>
                </ListItemAvatar>
                <ListItemText
                    primary={item.name}
                    secondary={
                        <React.Fragment>
                            {item.price}
                    <Typography
                        component="span"
                        variant="body2"
                        className={classes.inline}
                        color="textPrimary"
                    >
                    </Typography>
                        </React.Fragment>
                    }
                />
                <div><button onClick={()=>{addItem(item)}}>선택</button></div>
            </div>

        )
    };

    const menuList = menuService.getMenus().map((item, idx) => <ItemDetail key={idx} item={item}></ItemDetail>)

    const addItem = (item) => {
        addItemToCart(item)
    }

    return (
        <List className={classes.root}>
            <h2>Menu List</h2>
            <ListItem alignItems="flex-start">
                <ul>
                    {menuList}
                </ul>
            </ListItem>
         </List>
);
}

export default MenuList;