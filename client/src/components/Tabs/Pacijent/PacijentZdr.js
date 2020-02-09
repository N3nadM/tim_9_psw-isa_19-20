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
import {
  getZdrKarton,
  getPacijent,
  editZdrKarton
} from "../../../store/actions/pacijent";
import EditZdrKartonTab from "./EditZdrKartonTab";
import { makeStyles } from "@material-ui/core/styles";
import TabelaBolest from "../../Tabele/TabelaBolestLek";
import TabelaRecept from "../../Tabele/TabelaRecept";
import Skeleton from "@material-ui/lab/Skeleton";
import Button from "@material-ui/core/Button";

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

const PacijentZdr = ({
  id,
  korisnik,
  pacijent,
  zdrKarton,
  editZdrKarton,
  getZdrKarton
}) => {
  useEffect(() => {
    getZdrKarton(pacijent.id);
    //eslint-disable-next-line
  }, []);

  const [isEdit, setIsEdit] = React.useState(false);

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

      {!zdrKarton && <Skeleton height={222} />}
      {isEdit && (
        <EditZdrKartonTab
          zdrKarton={zdrKarton}
          editZdrKarton={editZdrKarton}
          setIsEdit={setIsEdit}
        />
      )}
      {!isEdit && zdrKarton && (
        <>
          <List disablePadding>
            <ListItem>
              <ListItemText>
                <HeightIcon className={classes.icon} />
                Visina
              </ListItemText>
              <Typography variant="subtitle1">
                {!zdrKarton.visina && "Visina ce biti dodata na prvom pregledu"}
                {zdrKarton.visina && `${zdrKarton.visina}cm`}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText>
                <SettingsInputSvideoIcon className={classes.icon} />
                Težina
              </ListItemText>
              <Typography variant="subtitle1">
                {!zdrKarton.tezina && "Tezina ce biti dodata na prvom pregledu"}
                {zdrKarton.tezina && `${zdrKarton.tezina}kg`}
              </Typography>
            </ListItem>
            <Divider />
            <ListItem>
              <ListItemText>
                <ExposureIcon className={classes.icon} />
                Dioptrija
              </ListItemText>
              <Typography variant="subtitle1">
                {!zdrKarton.dioptrija &&
                  "Dioptrija ce biti dodata na prvom pregledu"}
                {zdrKarton.dioptrija && `${zdrKarton.dioptrija}`}
              </Typography>
            </ListItem>
            <ListItem>
              <ListItemText>
                <OpacityIcon className={classes.icon} />
                Krvna Grupa
              </ListItemText>
              <Typography variant="subtitle1">
                {!zdrKarton.krvnaGrupa &&
                  "Krvna grupa ce biti dodata na prvom pregledu"}
                {zdrKarton.krvnaGrupa && `${zdrKarton.krvnaGrupa}`}
              </Typography>
            </ListItem>

            <Divider />
            {korisnik.authority !== "ROLE_MED_SESTRA" && (
              <Button
                variant="contained"
                color="primary"
                style={{ float: "right", marginTop: 19 }}
                onClick={() => setIsEdit(true)}
              >
                Izmeni
              </Button>
            )}
          </List>
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
        </>
      )}
    </Paper>
  );
};

const mapStateToProps = state => ({
  korisnik: state.currentUser.user.role[0],
  zdrKarton: state.pacijent.zdrKarton,
  pacijent: state.pacijent.pacijent
});

export default connect(mapStateToProps, {
  getZdrKarton,
  getPacijent,
  editZdrKarton
})(PacijentZdr);
