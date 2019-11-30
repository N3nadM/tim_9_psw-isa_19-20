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
import { getAllKlinike, searchKlinike } from "../../store/actions/klinika";
import { Grid } from "@material-ui/core";
import FormControl from "@material-ui/core/FormControl";
import InputLabel from "@material-ui/core/InputLabel";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import TextField from "@material-ui/core/TextField";

function createData(name, lokacija, ocena, carbs, protein) {
  return { name, lokacija, ocena, carbs, protein };
}

const rows = [
  createData("Cupcake", "a", 3.7, 67, 4.3),
  createData("Donut", "ab", 25.0, 51, 4.9),
  createData("Eclair", "asd", 16.0, 24, 6.0),
  createData("Frozen yoghurt", "fasd", 6.0, 24, 4.0),
  createData("Gingerbread", "ddd", 16.0, 49, 3.9),
  createData("Honeycomb", "masd", 3.2, 87, 6.5),
  createData("Ice cream sandwich", "237", 9.0, 37, 4.3),
  createData("Jelly Bean", "375", 0.0, 94, 0.0),
  createData("KitKat", "518", 26.0, 65, 7.0),
  createData("Lollipop", "392", 0.2, 98, 0.0),
  createData("Marshmallow", "318", 0, 81, 2.0),
  createData("Nougat", "360", 19.0, 9, 37.0),
  createData("Oreo", "437", 18.0, 63, 4.0)
];

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

const headCells = [
  {
    id: "naziv",
    numeric: false,
    disablePadding: true,
    label: "Naziv"
  },
  { id: "adresa", numeric: false, disablePadding: false, label: "Lokacija" },
  { id: "ocena", numeric: true, disablePadding: false, label: "Ocena" },
  { id: "saznaj", numeric: true, disablePadding: false, label: "Saznaj više" }
];

function EnhancedTableHead(props) {
  const { classes, order, orderBy, onRequestSort } = props;
  const createSortHandler = property => event => {
    onRequestSort(event, property);
  };

  return (
    <TableHead>
      <TableRow>
        <TableCell></TableCell>
        {headCells.map((headCell, i) => (
          <TableCell
            key={headCell.id}
            align={i !== headCells.length - 1 ? "left" : "right"}
            padding={headCell.disablePadding ? "none" : "default"}
            sortDirection={orderBy === headCell.id ? order : false}
          >
            {headCell.id !== "saznaj" ? (
              <TableSortLabel
                active={orderBy === headCell.id}
                direction={order}
                onClick={createSortHandler(headCell.id)}
              >
                {headCell.label}
                {orderBy === headCell.id ? (
                  <span className={classes.visuallyHidden}>
                    {order === "desc"
                      ? "sorted descending"
                      : "sorted ascending"}
                  </span>
                ) : null}
              </TableSortLabel>
            ) : (
              headCell.label
            )}
          </TableCell>
        ))}
      </TableRow>
    </TableHead>
  );
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

const ZakaziPregled = ({ klinike, getAllKlinike, searchKlinike, history }) => {
  useEffect(() => {
    getAllKlinike();
  }, []);

  const classes = useStyles();
  const [order, setOrder] = React.useState("asc");
  const [orderBy, setOrderBy] = React.useState("name");

  const [page, setPage] = React.useState(0);
  const [dense, setDense] = React.useState(false);
  const [rowsPerPage, setRowsPerPage] = React.useState(5);

  const handleRequestSort = (event, property) => {
    const isDesc = orderBy === property && order === "desc";
    setOrder(isDesc ? "asc" : "desc");
    setOrderBy(property);
  };

  const handleClick = event => {};

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

  const [selectedDate, setSelectedDate] = React.useState(new Date());

  const handleDateChange = date => {
    setSelectedDate(date);
  };

  const [state, setState] = React.useState({
    ocena: "",
    tip: "",
    lokacija: ""
  });
  // const [tip, setTip] = React.useState("");

  const handleSubmit = e => {
    e.preventDefault();
    searchKlinike(state);
  };

  const handleChange = e => {
    setState({ ...state, [e.target.name]: e.target.value });
  };

  const emptyRows =
    rowsPerPage - Math.min(rowsPerPage, rows.length - page * rowsPerPage);

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
                  <MenuItem value={10}>Pregled ociju</MenuItem>
                  <MenuItem value={20}>Fizikalna terapija</MenuItem>
                  <MenuItem value={30}>Rutinski pregled</MenuItem>
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
      <Paper className={classes.paper}>
        <EnhancedTableToolbar />
        <div className={classes.tableWrapper}>
          <Table
            className={classes.table}
            aria-labelledby="tableTitle"
            size={dense ? "small" : "medium"}
            aria-label="enhanced table"
          >
            <EnhancedTableHead
              classes={classes}
              order={order}
              orderBy={orderBy}
              onRequestSort={handleRequestSort}
              rowCount={rows.length}
            />
            <TableBody>
              {klinike &&
                stableSort(klinike, getSorting(order, orderBy))
                  .slice(page * rowsPerPage, page * rowsPerPage + rowsPerPage)
                  .map((row, index) => {
                    const labelId = `enhanced-table-checkbox-${index}`;

                    return (
                      <TableRow
                        hover
                        onClick={event => handleClick(event, row.name)}
                        role="checkbox"
                        tabIndex={-1}
                        key={row.naziv}
                      >
                        <TableCell></TableCell>
                        <TableCell
                          component="th"
                          id={labelId}
                          scope="row"
                          padding="none"
                        >
                          {row.naziv}
                        </TableCell>
                        <TableCell align="left">{row.adresa}</TableCell>
                        <TableCell align="left">{row.ocena}</TableCell>
                        <TableCell align="right">
                          <Button
                            variant="outlined"
                            color="primary"
                            onClick={() => {
                              history.push({
                                pathname: `/klinika/${row.id}`,
                                state
                              });
                            }}
                          >
                            Pogledaj
                          </Button>
                        </TableCell>
                      </TableRow>
                    );
                  })}
              {emptyRows > 0 && (
                <TableRow style={{ height: (dense ? 33 : 53) * emptyRows }}>
                  <TableCell colSpan={6} />
                </TableRow>
              )}
            </TableBody>
          </Table>
        </div>
        <TablePagination
          rowsPerPageOptions={[5, 10, 25]}
          component="div"
          count={rows.length}
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
      <FormControlLabel
        control={<Switch checked={dense} onChange={handleChangeDense} />}
        label="Smanji pading"
      />
    </div>
  );
};

const mapStateToProps = state => ({
  klinike: state.klinika.klinike
});

export default withRouter(
  connect(mapStateToProps, { getAllKlinike, searchKlinike })(ZakaziPregled)
);
