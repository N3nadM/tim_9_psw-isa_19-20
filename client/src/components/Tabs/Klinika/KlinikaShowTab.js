import React, { useEffect } from "react";
import { connect } from "react-redux";
import { getKlinika } from "../../../store/actions/klinika";
import Rating from "@material-ui/lab/Rating";
import { withStyles, makeStyles } from "@material-ui/core/styles";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";
import Divider from "@material-ui/core/Divider";
import Typography from "@material-ui/core/Typography";
import { Paper, Grid, Button } from "@material-ui/core";
import Skeleton from "@material-ui/lab/Skeleton";
import Box from "@material-ui/core/Box";
import FavoriteIcon from "@material-ui/icons/Favorite";
import { green } from "@material-ui/core/colors";
import CircularProgress from "@material-ui/core/CircularProgress";

const useStyles = makeStyles(theme => ({
  wrapper: {
    margin: theme.spacing(1),
    position: "relative"
  },
  buttonProgress: {
    color: green[500],
    position: "absolute",
    top: "50%",
    left: "50%",
    marginTop: -18,
    marginLeft: -18
  }
}));

const StyledRating = withStyles({
  iconFilled: {
    color: "#ff6d75"
  },
  iconHover: {
    color: "#ff3d47"
  }
})(Rating);

const PodaciKlinikaTabs = ({ getKlinika, klinika, id, state }) => {
  const classes = useStyles();
  const [submited, setSubmited] = React.useState(false);
  const [value, setValue] = React.useState(0);
  const [visible, setVisible] = React.useState(false);

  useEffect(() => {
    getKlinika(id);
  }, []);

  return (
    <>
      <Typography
        variant="h3"
        component="h2"
        style={{ marginBottom: 40, textAlign: "center" }}
      >
        Profil klinike
      </Typography>
      {!klinika && <Skeleton height={350} />}

      {klinika && (
        <Grid container justify="space-around">
          <Grid item md={5}>
            <Paper style={{ padding: 50, paddingBottom: 75, height: 250 }}>
              <List disablePadding>
                <ListItem>
                  <ListItemText primary="Naziv" />
                  <Typography variant="subtitle1">{klinika.naziv}</Typography>
                </ListItem>
                <ListItem>
                  <ListItemText primary="Opis" />
                  <Typography variant="subtitle1">{klinika.opis}</Typography>
                </ListItem>
                <Divider />
                <ListItem>
                  <ListItemText primary="Adresa" />
                  <Typography variant="subtitle1">{klinika.adresa}</Typography>
                </ListItem>
                <Divider />
              </List>
            </Paper>
          </Grid>
          <Grid item md={5}>
            <Paper
              style={{
                padding: 50,
                paddingBottom: 75,
                display: "flex",
                alignItems: "center",
                justifyContent: "center",
                flexDirection: "column",
                height: 250
              }}
              className={classes.wrapper}
            >
              <Box
                component="fieldset"
                borderColor="transparent"
                style={{ textAlign: "center" }}
              >
                <Typography component="legend" style={{ fontSize: 28 }}>
                  Oceni Kliniku:
                </Typography>
                <StyledRating
                  name="simple-controlled"
                  value={value}
                  onChange={(event, newValue) => {
                    setValue(newValue);
                    setVisible(true);
                  }}
                  icon={<FavoriteIcon fontSize="large" />}
                  disabled={submited}
                />
              </Box>
              {submited && (
                <CircularProgress
                  size={36}
                  className={classes.buttonProgress}
                />
              )}
              {visible && (
                <Button
                  disabled={submited}
                  color="secondary"
                  onClick={() => setSubmited(true)}
                >
                  Potvrdi
                </Button>
              )}
            </Paper>
          </Grid>
        </Grid>
      )}
    </>
  );
};

function mapStateToProps(state) {
  return {
    klinika: state.klinika.klinika
  };
}

export default connect(mapStateToProps, { getKlinika })(PodaciKlinikaTabs);
