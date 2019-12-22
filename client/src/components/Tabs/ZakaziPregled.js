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
import {
  getAllKlinike,
  searchKlinike,
  getTipoviPrelgeda
} from "../../store/actions/klinika";
import { Grid } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";

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
        Klinike
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

const ZakaziPregled = ({
  klinike,
  tipovi,
  getAllKlinike,
  searchKlinike,
  getTipoviPrelgeda,
  history
}) => {
  useEffect(() => {
    getAllKlinike();
    getTipoviPrelgeda();
    //eslint-disable-next-line
  }, []);

  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("naziv");

  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

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

  const handleChangeDense = event => {
    setDense(event.target.checked);
  };

  const [selectedDate, setSelectedDate] = React.useState(null);

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  const [state, setState] = React.useState({
    ocena: "",
    tip: "",
    lokacija: ""
  });

  const handleSubmit = e => {
    e.preventDefault();
    searchKlinike({
      ...state,
      datum: !!selectedDate ? selectedDate : ""
    });
  };

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  return (
    <div className={classes.root}>
      <form onSubmit={handleSubmit}>
        <Paper style={{ padding: 50, marginBottom: 50 }}>
          <Grid container spacing={3}>
            <Grid item md={3}>
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
            <Grid item md={2}>
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
            <Grid item md={3}>
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
            <Grid item md={2}>
              <TextField
                style={{ width: "80%" }}
                id="standard-basic"
                value={state.lokacija}
                onChange={handleChange}
                name="lokacija"
                className={classes.textField}
                label="Lokacija"
              />
            </Grid>
            <Grid
              item
              md={2}
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
      {klinike && !!klinike.length && (
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
                      active={orderBy === "naziv"}
                      direction={order}
                      onClick={() => handleRequestSort("naziv")}
                    >
                      Naziv
                    </TableSortLabel>
                  </TableCell>
                  <TableCell align="left">
                    <TableSortLabel
                      active={orderBy === "adresa"}
                      direction={order}
                      onClick={() => handleRequestSort("adresa")}
                    >
                      Adresa
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
                  {!!state.tip.length && (
                    <TableCell align="right">Cena</TableCell>
                  )}
                  <TableCell align="right">Saznaj</TableCell>
                </TableRow>
              </TableHead>
              <TableBody>
                {klinike &&
                  stableSort(klinike, getSorting(order, orderBy))
                    .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                    .map((row, index) => {
                      return (
                        <TableRow
                          hover
                          role="checkbox"
                          tabIndex={-1}
                          key={row.naziv}
                        >
                          <TableCell component="th" allign="left">
                            {row.naziv}
                          </TableCell>
                          <TableCell align="left">{row.adresa}</TableCell>
                          <TableCell align="left">{row.ocena}</TableCell>
                          {!!state.tip.length && (
                            <TableCell align="right">
                              {row.tipPregleda.find(t => t.naziv === state.tip)
                                ? row.tipPregleda.find(
                                    t => t.naziv === state.tip
                                  ).cenaPregleda
                                : "Nema"}
                            </TableCell>
                          )}
                          <TableCell align="right">
                            <Button
                              variant="outlined"
                              color="primary"
                              onClick={() => {
                                history.push({
                                  pathname: `/klinika/${row.id}`,
                                  state: {
                                    ...state,
                                    nazivKlinike: row.naziv,
                                    adresa: row.adresa,
                                    datum: !!selectedDate ? selectedDate : ""
                                  }
                                });
                              }}
                            >
                              Pogledaj
                            </Button>
                          </TableCell>
                        </TableRow>
                      );
                    })}
                {rowsPerPage -
                  Math.min(rowsPerPage, klinike.length - page * rowsPerPage) >
                  0 && (
                  <TableRow
                    style={{
                      height:
                        (dense ? 33 : 53) *
                        (rowsPerPage -
                          Math.min(
                            rowsPerPage,
                            klinike.length - page * rowsPerPage
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
            count={klinike.length}
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
      <FormControlLabel
        control={<Switch checked={dense} onChange={handleChangeDense} />}
        label="Smanji pading"
      />
    </div>
  );
};

const mapStateToProps = state => ({
  klinike: state.klinika.klinike,
  tipovi: state.klinika.tipoviPregleda
});

export default withRouter(
  connect(mapStateToProps, { getAllKlinike, searchKlinike, getTipoviPrelgeda })(
    ZakaziPregled
  )
);
