import React, { useEffect } from "react";
import { Paper } from "@material-ui/core";
import ListItem from "@material-ui/core/ListItem";
import Divider from "@material-ui/core/Divider";
import ListItemText from "@material-ui/core/ListItemText";
import Typography from "@material-ui/core/Typography";
import List from "@material-ui/core/List";
import HeightIcon from "@material-ui/icons/Height";
import SettingsInputSvideoIcon from "@material-ui/icons/SettingsInputSvideo";
import OpacityIcon from "@material-ui/icons/Opacity";
import ExposureIcon from "@material-ui/icons/Exposure";
import Grid from "@material-ui/core/Grid";
import { connect } from "react-redux";
import { getZdrKarton } from "../../store/actions/zdrKarton";
import { makeStyles } from "@material-ui/core/styles";
import TabelaBolest from "../Tabele/TabelaBolestLek";
import TabelaRecept from "../Tabele/TabelaRecept";
import Skeleton from "@material-ui/lab/Skeleton";

const useStyles = makeStyles(theme => ({
  icon: {
    position: "relative",
    top: 5,
    left: 0,
    marginRight: 5
  },
  tables: {
    marginTop: 85
  }
}));

const ZdrKarton = ({ pacijent: { pacijent }, zdrKarton, getZdrKarton }) => {
  useEffect(() => {
    getZdrKarton(pacijent.id);
  }, []);

  const classes = useStyles();

  return (
    <Paper style={{ padding: 50 }}>
      <Typography
        variant="h4"
        component="h2"
        style={{ marginBottom: 20, marginLeft: 13 }}
      >
        Zdravstveni Karton
      </Typography>
      {!zdrKarton ? (
        <Skeleton height={222} />
      ) : (
        <List disablePadding>
          <ListItem>
            <ListItemText>
              <HeightIcon className={classes.icon} />
              Visina
            </ListItemText>
            <Typography variant="subtitle1">
              {zdrKarton &&
                !zdrKarton.visina &&
                "Visina ce biti dodata na prvom pregledu"}
              {zdrKarton && zdrKarton.visina && `${zdrKarton.visina}cm`}
            </Typography>
          </ListItem>
          <ListItem>
            <ListItemText>
              <SettingsInputSvideoIcon className={classes.icon} />
              Težina
            </ListItemText>
            <Typography variant="subtitle1">
              {zdrKarton &&
                !zdrKarton.tezina &&
                "Tezina ce biti dodata na prvom pregledu"}
              {zdrKarton && zdrKarton.tezina && `${zdrKarton.tezina}kg`}
            </Typography>
          </ListItem>
          <Divider />
          <ListItem>
            <ListItemText>
              <ExposureIcon className={classes.icon} />
              Dioptrija
            </ListItemText>
            <Typography variant="subtitle1">
              {zdrKarton &&
                !zdrKarton.dioptrija &&
                "Dioptrija ce biti dodata na prvom pregledu"}
              {zdrKarton && zdrKarton.dioptrija && `${zdrKarton.dioptrija}`}
            </Typography>
          </ListItem>
          <ListItem>
            <ListItemText>
              <OpacityIcon className={classes.icon} />
              Krvna Grupa
            </ListItemText>
            <Typography variant="subtitle1">
              {zdrKarton &&
                !zdrKarton.krvnaGrupa &&
                "Krvna grupa ce biti dodata na prvom pregledu"}
              {zdrKarton && zdrKarton.krvnaGrupa && `${zdrKarton.krvnaGrupa}`}
            </Typography>
          </ListItem>

          <Divider />
        </List>
      )}
      <Grid container spacing={3} className={classes.tables}>
        <Grid item md={12} lg={6} xl={4}>
          <Typography variant="h5">Istorija bolesti</Typography>
          {zdrKarton && <TabelaBolest items={zdrKarton.istorijaBolesti} />}
          {!zdrKarton && <Skeleton height={350} />}
        </Grid>
        <Grid item md={12} lg={6} xl={4}>
          <Typography variant="h5">Alergije na lekove</Typography>
          {zdrKarton && <TabelaBolest items={zdrKarton.alergijaNaLek} />}
          {!zdrKarton && <Skeleton height={350} />}
        </Grid>
        <Grid item md={12} lg={6} xl={4}>
          <Typography variant="h5">Izdati recepti</Typography>
          {zdrKarton && <TabelaRecept items={zdrKarton.izdatiRecepti} />}
          {!zdrKarton && <Skeleton height={350} />}
        </Grid>
      </Grid>
    </Paper>
  );
};

const mapStateToProps = state => ({
  zdrKarton: state.zdrKarton.zdrKarton,
  pacijent: state.pacijent.pacijent
});

export default connect(mapStateToProps, { getZdrKarton })(ZdrKarton);
