import React, { useEffect } from "react";
import {
  MuiPickersUtilsProvider,
  KeyboardDatePicker
} from "@material-ui/pickers";
import DateFnsUtils from "@date-io/date-fns";
import { makeStyles } from "@material-ui/core/styles";
import Table from "@material-ui/core/Table";
import TableBody from "@material-ui/core/TableBody";
import TableCell from "@material-ui/core/TableCell";
import TableHead from "@material-ui/core/TableHead";
import TablePagination from "@material-ui/core/TablePagination";
import TableRow from "@material-ui/core/TableRow";
import TableSortLabel from "@material-ui/core/TableSortLabel";
import Paper from "@material-ui/core/Paper";
import Button from "@material-ui/core/Button";
import { connect } from "react-redux";
import { withRouter } from "react-router-dom";
import { getTipoviPrelgeda } from "../../store/actions/klinika";
import { getLekariKlinike } from "../../store/actions/lekar";
import { Grid } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";
import SlobodniTerminiDialog from "../Tabs/Lekar/SlobodniTerminiDialog";

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

const TabelaLekariKlinike = ({
  params,
  tipovi,
  getTipoviPrelgeda,
  getLekariKlinike,
  idKlinike,
  lekari
}) => {
  useEffect(() => {
    getTipoviPrelgeda();
    getLekariKlinike(idKlinike, params);
  }, []);
  const classes = useStyles();

  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("ime");

  const [page, setPage] = React.useState(0);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = event => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  const [selectedDate, setSelectedDate] = React.useState(params.datum || null);

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  const [state, setState] = React.useState({
    ocena: "",
    tip: params.tip,
    ime: "",
    prezime: ""
  });

  const handleSubmit = e => {
    e.preventDefault();
    if (state.tip !== "" || selectedDate != null) {
      getLekariKlinike(idKlinike, {
        ...state,
        datum: !selectedDate ? "" : selectedDate
      });
    }
  };

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const sort = lekari => {
    return lekari.sort((a, b) => {
      if (orderBy === "ime") {
        return order == "asc"
          ? a.korisnik.ime < b.korisnik.ime
            ? 1
            : -1
          : b.korisnik.ime < a.korisnik.ime
          ? 1
          : -1;
      } else if (orderBy === "ocena") {
        return order == "asc" ? a.ocena - b.ocena : b.ocena - a.ocena;
      } else {
        return order == "asc"
          ? a.korisnik.prezime < b.korisnik.prezime
            ? 1
            : -1
          : b.korisnik.prezime < a.korisnik.prezime
          ? 1
          : -1;
      }
    });
  };

  const handleRequestSort = (property, event) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  return (
    <div className={classes.root}>
      <form onSubmit={handleSubmit}>
        <Paper style={{ padding: 50, marginBottom: 50 }}>
          <Grid container spacing={3}>
            {params.datum === "" && (
              <Grid item sm={12} md={6} lg={3}>
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                  <KeyboardDatePicker
                    id="date-picker-dialog"
                    label="Datum pregleda"
                    format="MM/dd/yyyy"
                    value={selectedDate}
                    onChange={handleDateChange}
                    KeyboardButtonProps={{
                      "aria-label": "change date"
                    }}
                  />
                </MuiPickersUtilsProvider>
              </Grid>
            )}
            <Grid item sm={12} md={6} lg={2}>
              <FormControl
                className={classes.formControl}
                style={{ width: "80%" }}
              >
                <InputLabel id="demo-simple-select-label">Ocena</InputLabel>
                <Select
                  labelId="demo-simple-select-label"
                  id="demo-simple-select"
                  value={state.ocena}
                  name="ocena"
                  onChange={handleChange}
                >
                  <MenuItem value={1}>1+</MenuItem>
                  <MenuItem value={2}>2+</MenuItem>
                  <MenuItem value={3}>3+</MenuItem>
                  <MenuItem value={4}>4+</MenuItem>
                  <MenuItem value={5}>5</MenuItem>
                </Select>
              </FormControl>
            </Grid>
            {params.tip === "" && (
              <Grid item sm={12} md={6} lg={3}>
                <FormControl style={{ width: "80%" }}>
                  <InputLabel id="demo-simple-select-label">
                    Tip Pregleda
                  </InputLabel>
                  <Select
                    labelId="demo-simple-select-label"
                    id="demo-simple-select"
                    value={state.tip}
                    name="tip"
                    onChange={handleChange}
                  >
                    {tipovi.map(t => (
                      <MenuItem key={t} value={t}>
                        {t}
                      </MenuItem>
                    ))}
                  </Select>
                </FormControl>
              </Grid>
            )}
            <Grid item sm={12} md={6} lg={2}>
              <TextField
                style={{ width: "80%" }}
                value={state.ime}
                onChange={handleChange}
                name="ime"
                className={classes.textField}
                label="Ime"
              />
            </Grid>
            <Grid item sm={12} md={6} lg={2}>
              <TextField
                style={{ width: "80%" }}
                value={state.prezime}
                onChange={handleChange}
                name="prezime"
                className={classes.textField}
                label="Prezime"
              />
            </Grid>
            <Grid item md={2}>
              <Button variant="contained" color="primary" type="submit">
                Pretraži
              </Button>
            </Grid>
          </Grid>
        </Paper>
      </form>
      {lekari && (
        <Paper className={classes.paper}>
          <div className={classes.tableWrapper}>
            <Table
              className={classes.table}
              aria-labelledby="tableTitle"
              aria-label="enhanced table"
            >
              <TableHead>
                <TableRow>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "ime"}
                      direction={order}
                      onClick={() => handleRequestSort("ime")}
                    >
                      Ime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "prezime"}
                      direction={order}
                      onClick={() => handleRequestSort("prezime")}
                    >
                      Prezime
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "ocena"}
                      direction={order}
                      onClick={() => handleRequestSort("ocena")}
                    >
                      Ocena
                    </TableSortLabel>
                  </TableCell>

                  <TableCell align="right">Termini Pregleda</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {lekari &&
                  lekari.length > 0 &&
                  sort(lekari)
                    .filter(
                      l =>
                        l.korisnik.ime
                          .toUpperCase()
                          .includes(state.ime.toUpperCase()) &&
                        l.korisnik.prezime
                          .toUpperCase()
                          .includes(state.prezime.toUpperCase())
                    )
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row, index) => {
                      return (
                        <TableRow
                          hover
                          role="checkbox"
                          tabIndex={-1}
                          key={row.id}
                        >
                          <TableCell component="th" allign="left">
                            {row.korisnik.ime}
                          </TableCell>
                          <TableCell align="left">
                            {row.korisnik.prezime}
                          </TableCell>
                          <TableCell align="left">{row.ocena}</TableCell>

                          <TableCell align="right">
                            <SlobodniTerminiDialog
                              id={row.id}
                              datum={!selectedDate ? "" : selectedDate}
                              klinika={params.nazivKlinike}
                              tipPregleda={row.tipPregleda}
                              lekar={
                                row.korisnik.ime + " " + row.korisnik.prezime
                              }
                              adresa={params.adresa}
                            />
                          </TableCell>
                        </TableRow>
                      );
                    })}
                {rowsPerPage -
                  Math.min(rowsPerPage, lekari.length - page * rowsPerPage) >
                  0 && (
                  <TableRow
                    style={{
                      height:
                        53 *
                        (rowsPerPage -
                          Math.min(
                            rowsPerPage,
                            lekari.length - page * rowsPerPage
                          ))
                    }}
                  >
                    <TableCell colSpan={6} />
                  </TableRow>
                )}
              </TableBody>
            </Table>
          </div>
          <TablePagination
            rowsPerPageOptions={[5, 10, 25]}
            component="div"
            count={lekari.length}
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
        </Paper>
      )}
    </div>
  );
};

const mapStateToProps = state => ({
  tipovi: state.klinika.tipoviPregleda,
  lekari: state.lekar.lekarList
});

export default withRouter(
  connect(mapStateToProps, { getTipoviPrelgeda, getLekariKlinike })(
    TabelaLekariKlinike
  )
);
