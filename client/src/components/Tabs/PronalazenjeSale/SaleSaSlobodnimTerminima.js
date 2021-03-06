import React, { useEffect } from "react";

import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import Paper from "@material-ui/core/Paper";
import IconButton from "@material-ui/core/IconButton";
import Tooltip from "@material-ui/core/Tooltip";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Switch from "@material-ui/core/Switch";
import FilterListIcon from "@material-ui/icons/FilterList";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { Grid } from "@material-ui/core";
import TextField from "@material-ui/core/TextField";
import KalendarSala from "../BrzoZakazivanjePregleda/KalendarSala";
import PretragaSlobodnihLekara from "../../Tabs/BrzoZakazivanjePregleda/PretragaSlobodnihLekara";
import {
  searchSalaNaKlinici,
  getSlobodniTerminiSala,
  getListaSala,
  setSalaZaPregled
} from "../../../store/actions/sala";
import { setTerminP } from "../../../store/actions/lekar";

function desc(a, b, orderBy) {
  if (b[orderBy] < a[orderBy]) {
    return -1;
  }
  if (b[orderBy] > a[orderBy]) {
    return 1;
  }
  return 0;
}

function stableSort(array, cmp) {
  const stabilizedThis = array.map((el, index) => [el, index]);
  stabilizedThis.sort((a, b) => {
    const order = cmp(a[0], b[0]);
    if (order !== 0) return order;
    return a[1] - b[1];
  });
  return stabilizedThis.map(el => el[0]);
}

function getSorting(order, orderBy) {
  return order === "desc"
    ? (a, b) => desc(a, b, orderBy)
    : (a, b) => -desc(a, b, orderBy);
}

const useToolbarStyles = makeStyles(theme => ({
  title: {
    flex: "1 1 100%"
  }
}));

const EnhancedTableToolbar = () => {
  const classes = useToolbarStyles();

  return (
    <Toolbar>
      <Typography className={classes.title} variant="h6" id="tableTitle">
        Sale
      </Typography>

      <Tooltip title="Filter list">
        <IconButton aria-label="filter list">
          <FilterListIcon />
        </IconButton>
      </Tooltip>
    </Toolbar>
  );
};

const useStyles = makeStyles(theme => ({
  root: {
    width: "100%",
    marginTop: theme.spacing(3)
  },
  paper: {
    width: "100%",
    marginBottom: theme.spacing(2)
  },
  table: {
    minWidth: 750
  },
  tableWrapper: {
    overflowX: "auto"
  },
  visuallyHidden: {
    border: 0,
    clip: "rect(0 0 0 0)",
    height: 1,
    margin: -1,
    overflow: "hidden",
    padding: 0,
    position: "absolute",
    top: 20,
    width: 1
  },
  formControl: {
    minWidth: 120
  }
}));

const IzmenaSala = ({
  sale,
  getListaSala,
  lekar,
  klinika,
  searchSalaNaKlinici,
  getSlobodniTerminiSala,
  terminZaOperaciju,
  terminZaPregled,
  daLiTrebaDaSeTrazeTermini,
  trajanje,
  slobodniTermini,
  setSalaZaPregled,
  lekarZaPregled,
  promenjenLekar,
  pregled,
  setIzbor
}) => {
  useEffect(() => {
    getListaSala(klinika.id);

    terminZaOperaciju !== "" &&
      terminZaPregled === "" &&
      daLiTrebaDaSeTrazeTermini &&
      getSlobodniTerminiSala(
        lekar.korisnik.id,
        klinika.id,
        terminZaOperaciju,
        trajanje
      );

    terminZaPregled !== "" &&
      terminZaOperaciju === "" &&
      daLiTrebaDaSeTrazeTermini &&
      getSlobodniTerminiSala(
        lekar.korisnik.id,
        klinika.id,
        terminZaPregled,
        trajanje
      );
    //eslint-disable-next-line
  }, []);
  const [state, setState] = React.useState({
    zaIzmenu: "",
    broj: "",
    naziv: "",
    brojIspis: ""
  });

  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("korisnik.ime");

  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);
  const [zaLekare, setZaLekare] = React.useState({
    tip: "",
    datum: ""
  });

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };
  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const handleChangeDense = event => {
    setDense(event.target.checked);
  };

  const handleSubmit = e => {
    e.preventDefault();
    searchSalaNaKlinici(klinika.id, {
      ...state
    });
  };

  return (
    <div className={classes.root}>
      {slobodniTermini && (
        <>
          <form onSubmit={handleSubmit}>
            <Paper style={{ padding: 50, marginBottom: 50 }}>
              <Grid container spacing={3}>
                <Grid item md={3}>
                  <TextField
                    style={{ width: "80%" }}
                    margin="normal"
                    value={state.broj}
                    onChange={handleChange}
                    fullWidth
                    name="broj"
                    label="Broj sale"
                    type="text"
                    id="broj"
                  />
                </Grid>
                <Grid item md={3}>
                  <TextField
                    style={{ width: "80%" }}
                    margin="normal"
                    value={state.prezimePretraga}
                    onChange={handleChange}
                    fullWidth
                    name="naziv"
                    label="Naziv sale"
                    type="text"
                    id="naziv"
                  />
                </Grid>
                <Grid
                  item
                  md={3}
                  style={{
                    display: "flex",
                    alignItems: "center",
                    justifyContent: "center"
                  }}
                >
                  <Button variant="contained" color="primary" type="submit">
                    Pretraži
                  </Button>
                </Grid>
              </Grid>
            </Paper>
          </form>
          <Paper className={classes.paper}>
            <EnhancedTableToolbar />
            <div className={classes.tableWrapper}>
              <Table
                className={classes.table}
                aria-labelledby="tableTitle"
                size={dense ? "small" : "medium"}
                aria-label="enhanced table"
              >
                <TableHead>
                  <TableRow>
                    <TableCell align="left">
                      <TableSortLabel
                        active={orderBy === "sala.salaIdentifier"}
                        direction={order}
                        onClick={() => handleRequestSort("sala.salaIdentifier")}
                      >
                        Broj sale
                      </TableSortLabel>
                    </TableCell>
                    <TableCell align="left">
                      <TableSortLabel
                        active={orderBy === "sala.naziv"}
                        direction={order}
                        onClick={() => handleRequestSort("sala.naziv")}
                      >
                        Naziv sale
                      </TableSortLabel>
                    </TableCell>
                    {daLiTrebaDaSeTrazeTermini && slobodniTermini && (
                      <TableCell align="right">Prvi slobodan termin</TableCell>
                    )}
                    <TableCell align="right"></TableCell>
                    <TableCell align="right"></TableCell>
                  </TableRow>
                </TableHead>
                <TableBody>
                  {sale &&
                    stableSort(sale, getSorting(order, orderBy))
                      .slice(
                        page * rowsPerPage,
                        page * rowsPerPage + rowsPerPage
                      )
                      .map((row, index) => {
                        return (
                          <TableRow
                            hover
                            role="checkbox"
                            tabIndex={-1}
                            key={row.id}
                          >
                            <TableCell component="th" allign="left">
                              {row.salaIdentifier}
                            </TableCell>
                            <TableCell align="left">{row.naziv}</TableCell>
                            {daLiTrebaDaSeTrazeTermini && slobodniTermini && (
                              <TableCell align="right">
                                {slobodniTermini.get(String(row.id))}
                              </TableCell>
                            )}
                            <TableCell align="right">
                              <KalendarSala salaId={row.id} />
                            </TableCell>
                            <TableCell align="right">
                              <Button
                                variant="contained"
                                color="primary"
                                name={row.id}
                                label="Rezervisi"
                                className={classes.submit}
                                onClick={() => {
                                  setSalaZaPregled(row.id);

                                  setState({
                                    ...state,
                                    brojIspis: row.salaIdentifier
                                  });
                                }}
                              >
                                Rezervisi
                              </Button>
                            </TableCell>
                          </TableRow>
                        );
                      })}
                  {sale &&
                    rowsPerPage -
                      Math.min(rowsPerPage, sale.length - page * rowsPerPage) >
                      0 && (
                      <TableRow
                        style={{
                          height:
                            (dense ? 33 : 53) *
                            (rowsPerPage -
                              Math.min(
                                rowsPerPage,
                                sale.length - page * rowsPerPage
                              ))
                        }}
                      >
                        <TableCell colSpan={6} />
                      </TableRow>
                    )}
                </TableBody>
              </Table>
            </div>

            {sale && (
              <TablePagination
                rowsPerPageOptions={[5, 10, 25]}
                component="div"
                count={sale.length}
                rowsPerPage={rowsPerPage}
                page={page}
                backIconButtonProps={{
                  "aria-label": "previous page"
                }}
                nextIconButtonProps={{
                  "aria-label": "next page"
                }}
                onChangePage={handleChangePage}
                onChangeRowsPerPage={handleChangeRowsPerPage}
              />
            )}
            <TextField
              variant="outlined"
              margin="normal"
              required
              value={state.brojIspis}
              fullWidth
              id="iza"
              label="Izabrana sala"
              name="s"
              autoComplete="off"
            />
          </Paper>
          <FormControlLabel
            control={<Switch checked={dense} onChange={handleChangeDense} />}
            label="Smanji pading"
          />
          {console.log(
            slobodniTermini["_data"]["♠" + slobodniTermini["size"]][1]
          )}
          {slobodniTermini["_data"]["♠" + slobodniTermini["size"]][1] ===
            "nema" && (
            <>
              <Paper style={{ padding: 50, paddingBottom: 75 }}>
                <Typography
                  variant="h6"
                  component="h2"
                  style={{ marginBottom: 20, marginLeft: 13 }}
                >
                  Za slobodan termin sale koji je pronadjen potrebno je
                  promeniti lekara, jer je trenutni lekar zauzet za taj termin
                </Typography>

                <Typography
                  variant="h6"
                  component="h2"
                  style={{ marginBottom: 20, marginLeft: 13 }}
                >
                  Izbor lekara
                </Typography>
                <PretragaSlobodnihLekara
                  stariState={zaLekare}
                  idKlinike={klinika.id}
                  lekar={lekarZaPregled}
                  setIzbor={setIzbor}
                />
              </Paper>
            </>
          )}
        </>
      )}
    </div>
  );
};

const mapStateToProps = state => ({
  sale: state.sala.listaSala,
  slobodniTermini: state.sala.slobodniTerminiSala,
  pregled: state.pregled.pregled,
  promenjenLekar: state.lekar.promenjenLekar,
  lekarZaPregled: state.lekar.lekarZaPregled
});

export default withRouter(
  connect(mapStateToProps, {
    getListaSala,
    searchSalaNaKlinici,
    getSlobodniTerminiSala,
    setSalaZaPregled
  })(IzmenaSala)
);
